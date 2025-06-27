package com.skilling.lms.financial_service.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.financial_service.domains.TransaccionPago;
import com.skilling.lms.financial_service.repositories.TransaccionPagoRepository;
import com.skilling.lms.financial_service.service.TransaccionPagoService;
import com.skilling.lms.shared.dtos.financial.request.TransaccionPagoRequestDTO;
import com.skilling.lms.shared.dtos.financial.response.TransaccionPagoResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransaccionPagoServiceImpl implements TransaccionPagoService {
    
    private final TransaccionPagoRepository transaccionPagoRepository;

    private static final String TRANSACCION_PAGO_SERVICE_CB = "transaccionPagoServiceCB";

    @Override
    @CircuitBreaker(name = TRANSACCION_PAGO_SERVICE_CB, fallbackMethod = "fallbackCreateTransaccionPago")
    public Mono<TransaccionPagoResponseDTO> createTransaccionPago(TransaccionPagoRequestDTO requestDTO) {

        TransaccionPago transaccionPago = TransaccionPago.builder()
                .montoPagado(requestDTO.montoPagado())
                .fechaTransaccion(LocalDateTime.now()) 
                .metodoPago(requestDTO.metodoPago())
                .estadoPago(requestDTO.estadoPago())
                .referenciaExterna(requestDTO.referenciaExterna())
                .usuariosId(requestDTO.usuariosId())
                .planPrecioId(requestDTO.planPrecioId())
                .cursoOfertadoId(requestDTO.cursoOfertadoId())
                .build();

        return transaccionPagoRepository.save(transaccionPago)
                .map(this::toTransaccionPagoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<TransaccionPagoResponseDTO> fallbackCreateTransaccionPago(
            TransaccionPagoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createTransaccionPago activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Transacciones de Pago no está disponible o falló al crear la transacción."));
    }

    @Override
    @CircuitBreaker(name = TRANSACCION_PAGO_SERVICE_CB, fallbackMethod = "fallbackGetTransaccionPagoById")
    public Mono<TransaccionPagoResponseDTO> getTransaccionPagoById(UUID id) {
        return transaccionPagoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Transacción de Pago no encontrada con ID: " + id)))
                .map(this::toTransaccionPagoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<TransaccionPagoResponseDTO> fallbackGetTransaccionPagoById(UUID id, Throwable t) {
        log.error("Fallback para getTransaccionPagoById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Transacciones de Pago no está disponible o falló al obtener la transacción."));
    }

    @Override
    @CircuitBreaker(name = TRANSACCION_PAGO_SERVICE_CB, fallbackMethod = "fallbackGetAllTransaccionesPago")
    public Flux<TransaccionPagoResponseDTO> getAllTransaccionesPago() {
        return transaccionPagoRepository.findAll()
                .map(this::toTransaccionPagoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<TransaccionPagoResponseDTO> fallbackGetAllTransaccionesPago(Throwable t) {
        log.error("Fallback para getAllTransaccionesPago activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = TRANSACCION_PAGO_SERVICE_CB, fallbackMethod = "fallbackUpdateTransaccionPago")
    public Mono<TransaccionPagoResponseDTO> updateTransaccionPago(UUID id,
            TransaccionPagoRequestDTO requestDTO) {

        return transaccionPagoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Transacción de Pago no encontrada para actualizar con ID: " + id)))
                .flatMap(existingTransaccion -> {
                    existingTransaccion.setMontoPagado(requestDTO.montoPagado());
                    existingTransaccion.setMetodoPago(requestDTO.metodoPago());
                    existingTransaccion.setEstadoPago(requestDTO.estadoPago());
                    existingTransaccion.setReferenciaExterna(requestDTO.referenciaExterna());
                    existingTransaccion.setUsuariosId(requestDTO.usuariosId());
                    existingTransaccion.setPlanPrecioId(requestDTO.planPrecioId());
                    existingTransaccion.setCursoOfertadoId(requestDTO.cursoOfertadoId());

                    return transaccionPagoRepository.save(existingTransaccion);
                })
                .map(this::toTransaccionPagoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<TransaccionPagoResponseDTO> fallbackUpdateTransaccionPago(
            UUID id, TransaccionPagoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateTransaccionPago activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Transacciones de Pago no está disponible o falló al actualizar la transacción."));
    }

    @Override
    @CircuitBreaker(name = TRANSACCION_PAGO_SERVICE_CB, fallbackMethod = "fallbackDeleteTransaccionPago")
    public Mono<Void> deleteTransaccionPago(UUID id) {
        return transaccionPagoRepository.deleteById(id)
                .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteTransaccionPago(UUID id, Throwable t) {
        log.error("Fallback para deleteTransaccionPago activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Transacciones de Pago no está disponible o falló al eliminar la transacción."));
    }

    // --- Método Auxiliar para Mapeo de Dominio a DTO de Respuesta ---
    private TransaccionPagoResponseDTO toTransaccionPagoResponseDTO(TransaccionPago transaccionPago) {
        return new TransaccionPagoResponseDTO(
            transaccionPago.getId(),
            transaccionPago.getMontoPagado(),
            transaccionPago.getFechaTransaccion(),
            transaccionPago.getMetodoPago(),
            transaccionPago.getEstadoPago(),
            transaccionPago.getReferenciaExterna(),
            transaccionPago.getUsuariosId(), 
            transaccionPago.getPlanPrecioId(),
            transaccionPago.getCursoOfertadoId()
        );
    }
}
