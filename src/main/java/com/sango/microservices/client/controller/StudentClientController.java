package com.sango.microservices.client.controller;

import com.sango.microservices.client.model.Student;
import com.sango.microservices.client.model.StudentResponse;
import com.sango.microservices.client.repository.StudentRepository;
import com.sango.microservices.client.service.OauthService;
import com.sango.microservices.client.service.StudentProxyService;
import com.sango.microservices.client.util.ObjectMapperUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Slf4j
@RestController
@RequestMapping(path="/client")
public class StudentClientController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private StudentProxyService studentDetailProxyService;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private OauthService oauthService;

/*	Rest Template example where we need to define the response, url in the rest template */
	@GetMapping(path="/resttemplate/students",produces="application/json")
	public List<StudentResponse> getAllStudentsFromStudentDetail(){
		String plainClientCredentials="barClientIdPassword:secret";
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.add("Authorization","Bearer "+oauthService.getOauthTokenFromAuthServer(plainClientCredentials));
		HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
		ResponseEntity<StudentResponse [] > response = restTemplate.exchange("http://localhost:8080/v1/api/students",
																						HttpMethod.GET,
																						httpEntity,
																						StudentResponse[].class);
		return Arrays.asList(response.getBody());
	}
	
	/*Feign Client approach is very clean where define a service proxy interface*/
	@GetMapping(path="/students",produces="application/json")
	public List<StudentResponse> getAllStudentsFromStudentDetailFeign(){
		List<Student> studentList = studentDetailProxyService.getAllStudents();
		List<StudentResponse> response = new ArrayList<>();
		if (!studentList.isEmpty()) {
			log.info("From getAllStudentsFromStudentDetailFeign response count: {} , will store data locally to handle failure scenarios",response.size());
			try{
				for (Student student: studentList) {
					StudentResponse studentDetailResponse = ObjectMapperUtility.transformStudentToStudentResponse(student);
					response.add(studentDetailResponse);
				}
				response.forEach(student -> studentRepository.save(student));
				log.info("From getAllStudentsFromStudentDetailFeign  data persisted in local data store");
				
			}catch (Exception e){
				log.error("From getAllStudentsFromStudentDetailFeign got error while storing the data in local data store , exception message {} ",e.getMessage());
			}
		}else {
			response = studentRepository.findAll();
			log.info("From getAllStudentsFromStudentDetailFeign getting data from Local data store as fallback triggerd");
		}
		return response;
	}
	
	@GetMapping(path="/students/{id}",produces="application/json")
	public StudentResponse getStudentByIdFromStudentDetailFeign(@PathVariable String id) {
		StudentResponse studentResponseById ;
		Optional<Student> studentById = studentDetailProxyService.getStudentById(id);
		if(studentById.isPresent()){
			studentResponseById = ObjectMapperUtility.transformStudentToStudentResponse(studentById.get());
			log.info("From getStudentByIdFromStudentDetailFeign studentById: {} ",studentById);
		}else {
			Optional<StudentResponse> studentResponseByIdLocalDataStore = studentRepository.findById(UUID.fromString(id));
			studentResponseById = studentResponseByIdLocalDataStore.isPresent()? studentResponseByIdLocalDataStore.get() : new StudentResponse();
			log.info("From getStudentByIdFromStudentDetailFeign getting data from Local data store as fallback triggerd");
		}

		return studentResponseById;
	}
	
	
}
