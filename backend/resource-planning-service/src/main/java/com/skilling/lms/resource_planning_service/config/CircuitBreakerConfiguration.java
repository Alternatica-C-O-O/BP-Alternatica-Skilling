package com.skilling.lms.resource_planning_service.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

@Configuration
public class CircuitBreakerConfiguration {

  @Bean
  public CircuitBreakerRegistry circuitBreakerRegistry() {
    CircuitBreakerConfig config = CircuitBreakerConfig.custom()
        .failureRateThreshold(50)
        .waitDurationInOpenState(Duration.ofSeconds(30))
        .slidingWindowSize(10)
        .minimumNumberOfCalls(5)
        .build();

    CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);

    // Registrar circuit breakers específicos
    registry.circuitBreaker("espacioFisicoServiceCB");
    registry.circuitBreaker("periodoAcademicoServiceCB");
    registry.circuitBreaker("plataformaVirtualServiceCB");
    registry.circuitBreaker("asignacionHorarioServiceCB");

    return registry;
  }
}
