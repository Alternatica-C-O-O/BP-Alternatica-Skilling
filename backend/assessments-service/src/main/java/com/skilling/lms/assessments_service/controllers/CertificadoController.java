package com.skilling.lms.assessments_service.controllers;

import com.skilling.lms.assessments_service.domains.Certificado;
import com.skilling.lms.assessments_service.services.CertificadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/certificados")
public class CertificadoController {

    private final CertificadoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Certificado> create(@Valid @RequestBody Certificado certificado) {
        return service.save(certificado);
    }

    @GetMapping
    public Flux<Certificado> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Certificado> findById(@PathVariable UUID id) {
        return service.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping("/codigo/{codigo}")
    public Mono<Certificado> findByCodigo(@PathVariable String codigo) {
        return service.findByCodigoVerificacion(codigo)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping("/inscripcion/{inscripcionId}")
    public Flux<Certificado> findByInscripcion(@PathVariable UUID inscripcionId) {
        return service.findByInscripcionId(inscripcionId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable UUID id) {
        return service.deleteById(id);
    }
}