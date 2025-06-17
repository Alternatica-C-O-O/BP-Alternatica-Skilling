package com.skilling.lms.api_gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
    
    @GetMapping("/users")
    public Mono<ResponseEntity<String>> usersServiceFallback() {
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Users Service is currently unavailable. Please try again later."));
    }

    @GetMapping("/curriculum")
    public Mono<ResponseEntity<String>> curriculumServiceFallback() {
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Curriculum Service is currently unavailable. Please try again later."));
    }

    @GetMapping("/resource-planning")
    public Mono<ResponseEntity<String>> resourcePlanningServiceFallback() {
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Resource Planning Service is currently unavailable. Please try again later."));
    }

    @GetMapping("/enrollment")
    public Mono<ResponseEntity<String>> enrollmentServiceFallback() {
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Enrollment Service is currently unavailable. Please try again later."));
    }

    @GetMapping("/content")
    public Mono<ResponseEntity<String>> contentServiceFallback() {
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Content Service is currently unavailable. Please try again later."));
    }

    @GetMapping("/assessments")
    public Mono<ResponseEntity<String>> assessmentsServiceFallback() {
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Assessments Service is currently unavailable. Please try again later."));
    }

    @GetMapping("/notifications")
    public Mono<ResponseEntity<String>> notificationsServiceFallback() {
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Notifications Service is currently unavailable. Please try again later."));
    }

    @GetMapping("/reporting")
    public Mono<ResponseEntity<String>> reportingServiceFallback() {
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Reporting Service is currently unavailable. Please try again later."));
    }
}
