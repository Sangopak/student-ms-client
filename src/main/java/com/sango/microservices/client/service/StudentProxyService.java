package com.sango.microservices.client.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sango.microservices.client.model.Student;

/*Removing the url as Ribbon would use a property - <RemoteServiceId.ribbon.listOfServers="http://localhost:8080","http://localhost:8081"> */
/*@FeignClient(
		name = "student-detail-service",
		url = "http://localhost:8080"
		)*/
@FeignClient(name="student-detail-service", fallback = StudentProxyServiceFallback.class)
@RibbonClient(name="student-detail-service")
public interface StudentProxyService {
	@GetMapping(path="/v1/api/students", produces="application/json")
	List<Student> getAllStudents();
	
	@GetMapping(path="/v1/api/students/{id}",produces="application/json")
	Optional<Student> getStudentById(@PathVariable(value="id") String id);

}
