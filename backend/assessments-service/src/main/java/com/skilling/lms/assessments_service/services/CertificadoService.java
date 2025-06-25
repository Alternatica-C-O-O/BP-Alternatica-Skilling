package com.skilling.lms.assessments_service.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.assessments_service.domains.Certificado;
import com.skilling.lms.assessments_service.repository.CertificadoRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CertificadoService {

    private final CertificadoRepository certificadoRepository;

    public Mono<Certificado> save(Certificado certificado) {
        return certificadoRepository.save(certificado);
    }

    public Flux<Certificado> findAll() {
        return certificadoRepository.findAll();
    }

    public Mono<Certificado> findById(UUID id) {
        return certificadoRepository.findById(id);
    }

    public Mono<Certificado> findByCodigoVerificacion(String codigo) {
        return certificadoRepository.findByCodigoVerificacion(codigo);
    }

    public Flux<Certificado> findByInscripcionId(UUID inscripcionId) {
        return certificadoRepository.findByInscripcionId(inscripcionId);
    }

    public Mono<Void> deleteById(UUID id) {
        return certificadoRepository.deleteById(id);
    }
}