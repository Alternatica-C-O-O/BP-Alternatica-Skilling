package com.skilling.lms.reporting_service.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.skilling.lms.reporting_service.service.LogActividadService;
import com.skilling.lms.shared.dtos.reporting.request.LogActividadRequestDTO;
import com.skilling.lms.shared.dtos.reporting.response.LogActividadResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/logs-actividad")
public class LogActividadController {

    private final LogActividadService logActividadService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<LogActividadResponseDTO> createLogActividad(@Valid @RequestBody LogActividadRequestDTO requestDTO) {
        return logActividadService.createLogActividad(requestDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<LogActividadResponseDTO> getAllLogsActividad() {
        return logActividadService.getAllLogsActividad();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<LogActividadResponseDTO> getLogActividadById(@PathVariable UUID id) {
        return logActividadService.getLogActividadById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Log no encontrado")));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteLogActividad(@PathVariable UUID id) {
        return logActividadService.deleteLogActividad(id);
    }
}
