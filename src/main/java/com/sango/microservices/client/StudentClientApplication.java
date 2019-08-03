package com.sango.microservices.client;

import feign.RequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;


@SpringBootApplication
@EnableFeignClients(basePackages="com.sango.microservices.client")
@EnableDiscoveryClient
//@EnableCircuitBreaker
@EnableAutoConfiguration
public class StudentClientApplication {
	public static void main (String [] args) {
		SpringApplication.run(StudentClientApplication.class, args);
	}
}
