package com.skilling.lms.enrollment_service.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.enrollment_service.domains.Mensaje;
import com.skilling.lms.enrollment_service.repositories.MensajeRepository;
import com.skilling.lms.enrollment_service.service.MensajeService;
import com.skilling.lms.shared.dtos.enrollment.request.MensajeRequestDTO;
import com.skilling.lms.shared.dtos.enrollment.response.MensajeResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class MensajeServiceImpl implements MensajeService {

    private final MensajeRepository mensajeRepository;

    private static final String MENSAJE_SERVICE_CB = "mensajeServiceCB";
    
    @Override
    @CircuitBreaker(name = MENSAJE_SERVICE_CB, fallbackMethod = "fallbackCreateMensaje")
    public Mono<MensajeResponseDTO> createMensaje(MensajeRequestDTO requestDTO) {
        Mensaje mensaje = Mensaje.builder()
                .asunto(requestDTO.asunto())
                .contenido(requestDTO.contenido())
                .fechaEnvio(LocalDateTime.now())
                .leido(false) 
                .usuariosId(requestDTO.usuariosId())
                .foroId(requestDTO.foroId())
                .build();

        return mensajeRepository.save(mensaje)
                .map(this::toMensajeResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<MensajeResponseDTO> fallbackCreateMensaje(
            MensajeRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createMensaje activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Mensajes no está disponible o falló al crear el mensaje."));
    }

    @Override
    @CircuitBreaker(name = MENSAJE_SERVICE_CB, fallbackMethod = "fallbackGetMensajeById")
    public Mono<MensajeResponseDTO> getMensajeById(UUID id) {
        return mensajeRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Mensaje no encontrado con ID: " + id)))
                .map(this::toMensajeResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<MensajeResponseDTO> fallbackGetMensajeById(UUID id, Throwable t) {
        log.error("Fallback para getMensajeById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Mensajes no está disponible o falló al obtener el mensaje."));
    }

    @Override
    @CircuitBreaker(name = MENSAJE_SERVICE_CB, fallbackMethod = "fallbackGetAllMensajes")
    public Flux<MensajeResponseDTO> getAllMensajes() {
        return mensajeRepository.findAll()
                .map(this::toMensajeResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<MensajeResponseDTO> fallbackGetAllMensajes(Throwable t) {
        log.error("Fallback para getAllMensajes activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = MENSAJE_SERVICE_CB, fallbackMethod = "fallbackUpdateMensaje")
    public Mono<MensajeResponseDTO> updateMensaje(UUID id,
            MensajeRequestDTO requestDTO) {

        return mensajeRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Mensaje no encontrado para actualizar con ID: " + id)))
                .flatMap(existingMensaje -> {
                    existingMensaje.setAsunto(requestDTO.asunto());
                    existingMensaje.setContenido(requestDTO.contenido());
                    existingMensaje.setUsuariosId(requestDTO.usuariosId());
                    existingMensaje.setForoId(requestDTO.foroId());

                    return mensajeRepository.save(existingMensaje);
                })
                .map(this::toMensajeResponseDTO);
    }

    @CircuitBreaker(name = MENSAJE_SERVICE_CB, fallbackMethod = "fallbackMarkMensajeAsRead")
    public Mono<MensajeResponseDTO> markMensajeAsRead(UUID id) {
        return mensajeRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Mensaje no encontrado para marcar como leído con ID: " + id)))
                .flatMap(existingMensaje -> {
                    existingMensaje.setLeido(true);
                    return mensajeRepository.save(existingMensaje);
                })
                .map(this::toMensajeResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<MensajeResponseDTO> fallbackMarkMensajeAsRead(UUID id, Throwable t) {
        log.error("Fallback para markMensajeAsRead activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Mensajes no está disponible o falló al marcar el mensaje como leído."));
    }

    @SuppressWarnings("unused")
    private Mono<MensajeResponseDTO> fallbackUpdateMensaje(
            UUID id, MensajeRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateMensaje activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Mensajes no está disponible o falló al actualizar el mensaje."));
    }

    @Override
    @CircuitBreaker(name = MENSAJE_SERVICE_CB, fallbackMethod = "fallbackDeleteMensaje")
    public Mono<Void> deleteMensaje(UUID id) {
        return mensajeRepository.deleteById(id)
                .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteMensaje(UUID id, Throwable t) {
        log.error("Fallback para deleteMensaje activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Mensajes no está disponible o falló al eliminar el mensaje."));
    }

    // --- Método Auxiliar para Mapeo de Dominio a DTO de Respuesta ---
    private MensajeResponseDTO toMensajeResponseDTO(Mensaje mensaje) {
        return new MensajeResponseDTO(
            mensaje.getId(),
            mensaje.getAsunto(),
            mensaje.getContenido(),
            mensaje.getFechaEnvio(),
            mensaje.getLeido(),
            mensaje.getUsuariosId(),
            mensaje.getForoId() 
        );
    }
}
