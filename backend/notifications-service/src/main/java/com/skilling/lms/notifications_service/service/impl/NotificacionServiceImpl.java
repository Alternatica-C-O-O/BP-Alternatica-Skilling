package com.skilling.lms.notifications_service.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.notifications_service.domains.Notificacion;
import com.skilling.lms.notifications_service.repositories.NotificacionRepository;
import com.skilling.lms.notifications_service.service.NotificacionService;
import com.skilling.lms.shared.dtos.notifications.request.NotificacionesRequestDTO;
import com.skilling.lms.shared.dtos.notifications.response.NotificacionesResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificacionServiceImpl implements NotificacionService {
    
    private final NotificacionRepository notificacionRepository;

    private static final String NOTIFICACIONES_SERVICE_CB = "notificacionesServiceCB";

    @Override
    @CircuitBreaker(name = NOTIFICACIONES_SERVICE_CB, fallbackMethod = "fallbackCreateNotificacion")
    public Mono<NotificacionesResponseDTO> createNotificacion(NotificacionesRequestDTO requestDTO) {

        Notificacion notificacion = Notificacion.builder()
                .tipoNotificacion(requestDTO.tipoNotificacion())
                .titulo(requestDTO.titulo())
                .contenido(requestDTO.contenido())
                .fechaEnvio(LocalDateTime.now())
                .fechaLectura(null)
                .estaLeida(false) 
                .urlAccion(requestDTO.urlAccion())
                .eventoOrigen(requestDTO.eventoOrigen())
                .referenciaId(requestDTO.referenciaId())
                .usuariosId(requestDTO.usuariosId())
                .plantillaNotificacionesId(requestDTO.plantillaNotificacionesId())
                .build();

        return notificacionRepository.save(notificacion)
                .map(this::toNotificacionesResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<NotificacionesResponseDTO> fallbackCreateNotificacion(
            NotificacionesRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createNotificacion activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Notificaciones no está disponible o falló al crear la notificación."));
    }

    @Override
    @CircuitBreaker(name = NOTIFICACIONES_SERVICE_CB, fallbackMethod = "fallbackGetNotificacionById")
    public Mono<NotificacionesResponseDTO> getNotificacionById(UUID id) {
        return notificacionRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Notificación no encontrada con ID: " + id)))
                .map(this::toNotificacionesResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<NotificacionesResponseDTO> fallbackGetNotificacionById(UUID id, Throwable t) {
        log.error("Fallback para getNotificacionById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Notificaciones no está disponible o falló al obtener la notificación."));
    }

    @Override
    @CircuitBreaker(name = NOTIFICACIONES_SERVICE_CB, fallbackMethod = "fallbackGetAllNotificaciones")
    public Flux<NotificacionesResponseDTO> getAllNotificaciones() {
        return notificacionRepository.findAll()
                .map(this::toNotificacionesResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<NotificacionesResponseDTO> fallbackGetAllNotificaciones(Throwable t) {
        log.error("Fallback para getAllNotificaciones activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = NOTIFICACIONES_SERVICE_CB, fallbackMethod = "fallbackUpdateNotificacion")
    public Mono<NotificacionesResponseDTO> updateNotificacion(UUID id,
            NotificacionesRequestDTO requestDTO) {

        return notificacionRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Notificación no encontrada para actualizar con ID: " + id)))
                .flatMap(existingNotificacion -> {
                    existingNotificacion.setTipoNotificacion(requestDTO.tipoNotificacion());
                    existingNotificacion.setTitulo(requestDTO.titulo());
                    existingNotificacion.setContenido(requestDTO.contenido());
                    existingNotificacion.setUrlAccion(requestDTO.urlAccion());
                    existingNotificacion.setEventoOrigen(requestDTO.eventoOrigen());
                    existingNotificacion.setReferenciaId(requestDTO.referenciaId());
                    existingNotificacion.setUsuariosId(requestDTO.usuariosId());
                    existingNotificacion.setPlantillaNotificacionesId(requestDTO.plantillaNotificacionesId());
                    return notificacionRepository.save(existingNotificacion);
                })
                .map(this::toNotificacionesResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<NotificacionesResponseDTO> fallbackUpdateNotificacion(
            UUID id, NotificacionesRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateNotificacion activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Notificaciones no está disponible o falló al actualizar la notificación."));
    }

    @Override
    @CircuitBreaker(name = NOTIFICACIONES_SERVICE_CB, fallbackMethod = "fallbackDeleteNotificacion")
    public Mono<Void> deleteNotificacion(UUID id) {
        return notificacionRepository.deleteById(id)
                .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteNotificacion(UUID id, Throwable t) {
        log.error("Fallback para deleteNotificacion activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Notificaciones no está disponible o falló al eliminar la notificación."));
    }

    // --- Método Auxiliar para Mapeo de Dominio a DTO de Respuesta ---
    private NotificacionesResponseDTO toNotificacionesResponseDTO(Notificacion notificacion) {
        return new NotificacionesResponseDTO(
            notificacion.getId(),
            notificacion.getTipoNotificacion(),
            notificacion.getTitulo(),
            notificacion.getContenido(),
            notificacion.getFechaEnvio(),
            notificacion.getFechaLectura(),
            notificacion.getEstaLeida(),
            notificacion.getUrlAccion(),
            notificacion.getEventoOrigen(),
            notificacion.getReferenciaId(),
            notificacion.getUsuariosId(), 
            notificacion.getPlantillaNotificacionesId() 
        );
    }
    
}
