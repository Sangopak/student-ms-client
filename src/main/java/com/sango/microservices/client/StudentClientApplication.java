package com.sango.microservices.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients(basePackages="com.sango.microservices.client")
@EnableDiscoveryClient
@EnableCircuitBreaker
public class StudentClientApplication {
	public static void main (String [] args) {
		SpringApplication.run(StudentClientApplication.class, args);
	}
}
