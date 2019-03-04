package com.sango.microservices.client.config;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sango.microservices.client.model.StudentDetailResponse;

@FeignClient(
		name = "student-detail-service",
		url = "http://localhost:8080"
		)
public interface StudentDetailProxyService {
	@GetMapping(path="/v1/api/students", produces="application/json")
	public List<StudentDetailResponse> getAllStudents();
	
	@GetMapping(path="/v1/api/students/{id}",produces="application/json")
	public Optional<StudentDetailResponse> getStudentById(@PathVariable(value="id") String id);

}
