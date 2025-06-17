package com.skilling.lms.resource_planning_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ResourcePlanningServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourcePlanningServiceApplication.class, args);
	}

}
