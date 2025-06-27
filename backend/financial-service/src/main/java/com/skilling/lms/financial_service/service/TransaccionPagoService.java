package com.skilling.lms.financial_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.financial.request.TransaccionPagoRequestDTO;
import com.skilling.lms.shared.dtos.financial.response.TransaccionPagoResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransaccionPagoService {

    Mono<TransaccionPagoResponseDTO> createTransaccionPago(TransaccionPagoRequestDTO requestDTO);
    Mono<TransaccionPagoResponseDTO> getTransaccionPagoById(UUID id);
    Flux<TransaccionPagoResponseDTO> getAllTransaccionesPago();
    Mono<TransaccionPagoResponseDTO> updateTransaccionPago(UUID id, TransaccionPagoRequestDTO requestDTO);
    Mono<Void> deleteTransaccionPago(UUID id);
}
