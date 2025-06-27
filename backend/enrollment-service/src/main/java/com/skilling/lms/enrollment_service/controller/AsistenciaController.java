package com.skilling.lms.enrollment_service.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.skilling.lms.enrollment_service.service.AsistenciaService;
import com.skilling.lms.shared.dtos.enrollment.request.AsistenciaRequestDTO;
import com.skilling.lms.shared.dtos.enrollment.response.AsistenciaResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/asistencias")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes =  MediaType.APPLICATION_JSON)
    public Mono<AsistenciaResponseDTO> createAsistencia(@Valid @RequestBody AsistenciaRequestDTO requestDTO) {
        return asistenciaService.createAsistencia(requestDTO);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<AsistenciaResponseDTO> getAllAsistencias() {
        return asistenciaService.getAllAsistencias();
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<AsistenciaResponseDTO> getAsistenciaById(@PathVariable UUID id) {
        return asistenciaService.getAsistenciaById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Asistencia no encontrada")));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<AsistenciaResponseDTO> updateAsistencia(@PathVariable UUID id, @Valid @RequestBody AsistenciaRequestDTO requestDTO) {
        return asistenciaService.updateAsistencia(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Asistencia no encontrada para actualizar")));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAsistencia(@PathVariable UUID id) {
        return asistenciaService.deleteAsistencia(id);
    }
}
