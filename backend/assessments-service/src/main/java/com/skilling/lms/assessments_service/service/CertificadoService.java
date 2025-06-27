package com.skilling.lms.assessments_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.assessments.request.CertificadoRequestDTO;
import com.skilling.lms.shared.dtos.assessments.response.CertificadoResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CertificadoService {

    Mono<CertificadoResponseDTO> createCertificado(CertificadoRequestDTO requestDTO);
    Mono<CertificadoResponseDTO> getCertificadoById(UUID id);
    Flux<CertificadoResponseDTO> getAllCertificados();
    Mono<CertificadoResponseDTO> updateCertificado(UUID id, CertificadoRequestDTO requestDTO);
    Mono<Void> deleteCertificado(UUID id);
}
