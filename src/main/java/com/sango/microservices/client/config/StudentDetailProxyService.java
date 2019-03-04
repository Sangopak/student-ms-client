package com.sango.microservices.client.config;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sango.microservices.client.model.StudentDetailResponse;

/*Removing the url as Ribbon would use a property - <RemoteServiceId.ribbon.listOfServers="http://localhost:8080","http://localhost:8081"> */
/*@FeignClient(
		name = "student-detail-service",
		url = "http://localhost:8080"
		)*/
@FeignClient(name="student-detail-service")
@RibbonClient(name="student-detail-service")
public interface StudentDetailProxyService {
	@GetMapping(path="/v1/api/students", produces="application/json")
	public List<StudentDetailResponse> getAllStudents();
	
	@GetMapping(path="/v1/api/students/{id}",produces="application/json")
	public Optional<StudentDetailResponse> getStudentById(@PathVariable(value="id") String id);

}
