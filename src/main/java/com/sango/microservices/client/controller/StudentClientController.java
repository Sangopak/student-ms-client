package com.sango.microservices.client.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sango.microservices.client.model.Student;
import com.sango.microservices.client.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sango.microservices.client.service.StudentDetailProxyService;
import com.sango.microservices.client.model.StudentDetailResponse;

@Slf4j
@RestController
@RequestMapping(path="/client")
public class StudentClientController {
	//private static final Logger log = LoggerFactory.getLogger(StudentClientController.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private StudentDetailProxyService studentDetailProxyService;

	@Autowired
	private StudentRepository studentRepository;

	/*Rest Template example where we need to define the response, url in the rest template */	
	/*@GetMapping(path="/resttemplate/students",produces="application/json")
	public List<StudentDetailResponse> getAllStudentsFromStudentDetail(){
		ResponseEntity<StudentDetailResponse [] > response = restTemplate.getForEntity("http://localhost:8080/v1/api/students",
																						StudentDetailResponse[].class);
		return Arrays.asList(response.getBody());
	}*/
	
	/*Feign Client approach is very clean where define a service proxy interface*/
	@GetMapping(path="/students",produces="application/json")
	public List<StudentDetailResponse> getAllStudentsFromStudentDetailFeign(){
		List<Student> studentList = studentDetailProxyService.getAllStudents();
		List<StudentDetailResponse> response = new ArrayList<>();
		if (!studentList.isEmpty()) {
			log.info("From getAllStudentsFromStudentDetailFeign response count: {} , will store data locally to handle failure scenarios",response.size());
			try{
				studentList.forEach(student -> studentRepository.save(student));
				log.info("From getAllStudentsFromStudentDetailFeign  data persisted in local data store");

				for (Student student: studentList) {
					StudentDetailResponse studentDetailResponse = new StudentDetailResponse();
					studentDetailResponse.setStudent(student);
					studentDetailResponse.setFromLocalDataStore(false);
					response.add(studentDetailResponse);
				}
				
			}catch (Exception e){
				log.error("From getAllStudentsFromStudentDetailFeign got error while storing the data in local data store , exception message {} ",e.getMessage());
			}
		}
		return response;
	}
	
	@GetMapping(path="/students/{id}",produces="application/json")
	public StudentDetailResponse getStudentByIdFromStudentDetailFeign(@PathVariable String id) {
		StudentDetailResponse studentById = new StudentDetailResponse();
		Optional<Student> student = studentDetailProxyService.getStudentById(id);
		if(student.isPresent()){
			studentById.setStudent(student.get());
			studentById.setFromLocalDataStore(false);
			log.info("From getStudentByIdFromStudentDetailFeign studentById: {} ",studentById);
		}

		return studentById;
	}
	
	
}
