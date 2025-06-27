package com.skilling.lms.assessments_service.services;

import com.skilling.lms.assessments_service.domains.Certificado;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CertificadoService {

    Mono<Certificado> save(Certificado certificado);
    Flux<Certificado> findAll();
    Mono<Certificado> findById(UUID id);
    Mono<Certificado> findByCodigoVerificacion(String codigo);
    Flux<Certificado> findByInscripcionId(UUID inscripcionId);
    Mono<Void> deleteById(UUID id);
}