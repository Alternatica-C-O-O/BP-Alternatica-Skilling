package com.skilling.lms.assessments_service.services.impl;

import com.skilling.lms.assessments_service.domains.Certificado;
import com.skilling.lms.assessments_service.repository.CertificadoRepository;
import com.skilling.lms.assessments_service.services.CertificadoService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CertificadoServiceImpl implements CertificadoService {

    private final CertificadoRepository certificadoRepository;
    private static final String CB_NAME = "certificadoServiceCB";

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackSave")
    public Mono<Certificado> save(Certificado certificado) {
        return certificadoRepository.save(certificado);
    }

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackFindAll")
    public Flux<Certificado> findAll() {
        return certificadoRepository.findAll();
    }

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackFindById")
    public Mono<Certificado> findById(UUID id) {
        return certificadoRepository.findById(id);
    }

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackFindByCodigo")
    public Mono<Certificado> findByCodigoVerificacion(String codigo) {
        return certificadoRepository.findByCodigoVerificacion(codigo);
    }

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackFindByInscripcion")
    public Flux<Certificado> findByInscripcionId(UUID inscripcionId) {
        return certificadoRepository.findByInscripcionId(inscripcionId);
    }

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackDelete")
    public Mono<Void> deleteById(UUID id) {
        return certificadoRepository.deleteById(id);
    }

    @SuppressWarnings("unused")
    private Mono<Certificado> fallbackSave(Certificado certificado, Throwable t) {
        log.error("Fallback save Certificado: {}", t.getMessage());
        return Mono.error(new RuntimeException("No se pudo guardar el certificado."));
    }

    @SuppressWarnings("unused")
    private Flux<Certificado> fallbackFindAll(Throwable t) {
        log.error("Fallback findAll Certificado: {}", t.getMessage());
        return Flux.empty();
    }

    @SuppressWarnings("unused")
    private Mono<Certificado> fallbackFindById(UUID id, Throwable t) {
        log.error("Fallback findById Certificado: {}", t.getMessage());
        return Mono.empty();
    }

    @SuppressWarnings("unused")
    private Mono<Certificado> fallbackFindByCodigo(String codigo, Throwable t) {
        log.error("Fallback findByCodigoVerificacion: {}", t.getMessage());
        return Mono.empty();
    }

    @SuppressWarnings("unused")
    private Flux<Certificado> fallbackFindByInscripcion(UUID inscripcionId, Throwable t) {
        log.error("Fallback findByInscripcionId: {}", t.getMessage());
        return Flux.empty();
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDelete(UUID id, Throwable t) {
        log.error("Fallback deleteById Certificado: {}", t.getMessage());
        return Mono.empty();
    }
}