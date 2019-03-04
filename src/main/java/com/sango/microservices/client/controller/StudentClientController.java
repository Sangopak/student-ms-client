package com.sango.microservices.client.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sango.microservices.client.config.StudentDetailProxyService;
import com.sango.microservices.client.model.StudentDetailResponse;

@RestController
@RequestMapping(path="/client")
public class StudentClientController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	StudentDetailProxyService studentDetailProxyService;

	/*Rest Template example where we need to define the response, url in the rest template */	
	@GetMapping(path="/resttemplate/students",produces="application/json")
	public List<StudentDetailResponse> getAllStudentsFromStudentDetail(){
		ResponseEntity<StudentDetailResponse [] > response = restTemplate.getForEntity("http://localhost:8080/v1/api/students", 
																						StudentDetailResponse[].class); 
		return Arrays.asList(response.getBody());
	}
	
	/*Feign Client approach is very clean where define a service proxy interface*/
	@GetMapping(path="/students",produces="application/json")
	public List<StudentDetailResponse> getAllStudentsFromStudentDetailFeign(){
		List<StudentDetailResponse> response = studentDetailProxyService.getAllStudents(); 
		return response;
	}
	
/*	@GetMapping(path="/students/{id}",produces="application/json")
	public Optional<StudentDetailResponse> getStudentByIdFromStudentDetailFeign(@PathVariable String id) {
		Optional<StudentDetailResponse> studentById = studentDetailProxyService.getStudentById(id);
		return studentById;
	}*/
	
	
}