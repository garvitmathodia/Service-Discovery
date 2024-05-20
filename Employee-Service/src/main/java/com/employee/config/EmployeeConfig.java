package com.employee.config;

import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class EmployeeConfig {
	
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
	@LoadBalanced
	@Bean
	public RestTemplate geRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public WebClient getWebClient() {
		return WebClient.builder().build();
	}
}
