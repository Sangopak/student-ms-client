package com.sango.microservices.client.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sango.microservices.client.model.StudentDetailResponse;

@RestController
@RequestMapping(path="/client")
public class StudentClientController {
	
	@Autowired
	RestTemplate restTemplate;

	@GetMapping(path="/students",produces="application/json")
	public List<StudentDetailResponse> getAllStudentsFromStudentDetail(){
		ResponseEntity<StudentDetailResponse [] > response = restTemplate.getForEntity("http://localhost:8080/v1/api/students", StudentDetailResponse[].class); 
		return Arrays.asList(response.getBody());
	}
}
