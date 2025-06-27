package com.skilling.lms.enrollment_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.enrollment.request.MensajeRequestDTO;
import com.skilling.lms.shared.dtos.enrollment.response.MensajeResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MensajeService {

    Mono<MensajeResponseDTO> createMensaje(MensajeRequestDTO requestDTO);
    Mono<MensajeResponseDTO> getMensajeById(UUID id);
    Flux<MensajeResponseDTO> getAllMensajes();
    Mono<MensajeResponseDTO> updateMensaje(UUID id, MensajeRequestDTO requestDTO);
    Mono<Void> deleteMensaje(UUID id);
}
