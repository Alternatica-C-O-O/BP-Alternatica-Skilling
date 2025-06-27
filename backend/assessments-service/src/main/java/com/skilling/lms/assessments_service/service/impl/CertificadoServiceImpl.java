package com.skilling.lms.assessments_service.service.impl;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.assessments_service.domains.Certificado;
import com.skilling.lms.assessments_service.repository.CertificadoRepository;
import com.skilling.lms.assessments_service.service.CertificadoService;
import com.skilling.lms.shared.dtos.assessments.request.CertificadoRequestDTO;
import com.skilling.lms.shared.dtos.assessments.response.CertificadoResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CertificadoServiceImpl implements CertificadoService {

    private final CertificadoRepository certificadoRepository;

    private static final String CERTIFICADO_SERVICE_CB = "certificadoServiceCB";

    @Override
    @CircuitBreaker(name = CERTIFICADO_SERVICE_CB, fallbackMethod = "fallbackCreateCertificado")
    public Mono<CertificadoResponseDTO> createCertificado(CertificadoRequestDTO requestDTO) {
        return certificadoRepository.findByInscripcionId(requestDTO.inscripcionId())
                .flatMap(existingCert -> {
                    log.warn("Intento de crear certificado duplicado para Inscripción ID: {}", requestDTO.inscripcionId());
                    return Mono.error(new RuntimeException("Ya existe un certificado para esta inscripción."));
                })
                .then(Mono.defer(() -> {
                    Certificado certificado = Certificado.builder()
                            .fechaEmision(LocalDate.now()) 
                            .urlCertificado(requestDTO.urlCertificado())
                            .codigoVerificacion(requestDTO.codigoVerificacion())
                            .inscripcionId(requestDTO.inscripcionId())
                            .build();

                    return certificadoRepository.save(certificado)
                            .map(this::toCertificadoResponseDTO);
                }));
    }

    @SuppressWarnings("unused")
    private Mono<CertificadoResponseDTO> fallbackCreateCertificado(
            CertificadoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createCertificado activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Certificados no está disponible o falló al crear el certificado."));
    }

    @Override
    @CircuitBreaker(name = CERTIFICADO_SERVICE_CB, fallbackMethod = "fallbackGetCertificadoById")
    public Mono<CertificadoResponseDTO> getCertificadoById(UUID id) {
        return certificadoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Certificado no encontrado con ID: " + id)))
                .map(this::toCertificadoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<CertificadoResponseDTO> fallbackGetCertificadoById(UUID id, Throwable t) {
        log.error("Fallback para getCertificadoById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Certificados no está disponible o falló al obtener el certificado."));
    }

    @Override
    @CircuitBreaker(name = CERTIFICADO_SERVICE_CB, fallbackMethod = "fallbackGetAllCertificados")
    public Flux<CertificadoResponseDTO> getAllCertificados() {
        return certificadoRepository.findAll()
                .map(this::toCertificadoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<CertificadoResponseDTO> fallbackGetAllCertificados(Throwable t) {
        log.error("Fallback para getAllCertificados activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = CERTIFICADO_SERVICE_CB, fallbackMethod = "fallbackUpdateCertificado")
    public Mono<CertificadoResponseDTO> updateCertificado(UUID id,
            CertificadoRequestDTO requestDTO) {

        return certificadoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Certificado no encontrado para actualizar con ID: " + id)))
                .flatMap(existingCertificado -> {
                    existingCertificado.setUrlCertificado(requestDTO.urlCertificado());
                    existingCertificado.setCodigoVerificacion(requestDTO.codigoVerificacion());
                    existingCertificado.setInscripcionId(requestDTO.inscripcionId());

                    return certificadoRepository.save(existingCertificado);
                })
                .map(this::toCertificadoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<CertificadoResponseDTO> fallbackUpdateCertificado(
            UUID id, CertificadoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateCertificado activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Certificados no está disponible o falló al actualizar el certificado."));
    }

    @Override
    @CircuitBreaker(name = CERTIFICADO_SERVICE_CB, fallbackMethod = "fallbackDeleteCertificado")
    public Mono<Void> deleteCertificado(UUID id) {
        return certificadoRepository.deleteById(id)
                .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteCertificado(UUID id, Throwable t) {
        log.error("Fallback para deleteCertificado activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Certificados no está disponible o falló al eliminar el certificado."));
    }

    // --- Método Auxiliar para Mapeo de Dominio a DTO de Respuesta ---
    private CertificadoResponseDTO toCertificadoResponseDTO(Certificado certificado) {
        return new CertificadoResponseDTO(
            certificado.getId(),
            certificado.getFechaEmision(),
            certificado.getUrlCertificado(),
            certificado.getCodigoVerificacion(),
            certificado.getInscripcionId()
        );
    }
}
