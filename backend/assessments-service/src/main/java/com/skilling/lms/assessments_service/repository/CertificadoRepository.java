package com.skilling.lms.assessments_service.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.assessments_service.domains.Certificado;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CertificadoRepository extends ReactiveCrudRepository<Certificado, UUID> {

    Mono<Certificado> findByCodigoVerificacion(String codigoVerificacion);
    Flux<Certificado> findByInscripcionId(UUID inscripcionId);
}
