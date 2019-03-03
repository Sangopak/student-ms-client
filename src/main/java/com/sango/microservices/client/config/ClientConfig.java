package com.sango.microservices.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile({"local","dev"})
public class ClientConfig {

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
