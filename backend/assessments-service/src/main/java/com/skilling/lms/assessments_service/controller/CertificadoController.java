package com.skilling.lms.assessments_service.controller;

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

import com.skilling.lms.assessments_service.service.CertificadoService;
import com.skilling.lms.shared.dtos.assessments.request.CertificadoRequestDTO;
import com.skilling.lms.shared.dtos.assessments.response.CertificadoResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/certificados")
public class CertificadoController {

    private final CertificadoService certificadoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<CertificadoResponseDTO> createCertificado(@Valid @RequestBody CertificadoRequestDTO requestDTO) {
        return certificadoService.createCertificado(requestDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<CertificadoResponseDTO> getAllCertificados() {
        return certificadoService.getAllCertificados();
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<CertificadoResponseDTO> getCertificadoById(@PathVariable UUID id) {
        return certificadoService.getCertificadoById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Cerficado no encontrado")));
    }
    
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<CertificadoResponseDTO> updateCertificado(@PathVariable UUID id, @Valid @RequestBody CertificadoRequestDTO requestDTO) {
        return certificadoService.updateCertificado(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificado no encontrado para actualizar")));
    }
    
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCertificado(@PathVariable UUID id) {
        return certificadoService.deleteCertificado(id);
    }
}
