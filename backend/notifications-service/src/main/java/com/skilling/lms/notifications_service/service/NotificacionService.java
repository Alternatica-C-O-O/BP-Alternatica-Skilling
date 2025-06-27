package com.skilling.lms.notifications_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.notifications.request.NotificacionesRequestDTO;
import com.skilling.lms.shared.dtos.notifications.response.NotificacionesResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NotificacionService {

    Mono<NotificacionesResponseDTO> createNotificacion(NotificacionesRequestDTO requestDTO);
    Mono<NotificacionesResponseDTO> getNotificacionById(UUID id);
    Flux<NotificacionesResponseDTO> getAllNotificaciones();
    Mono<NotificacionesResponseDTO> updateNotificacion(UUID id, NotificacionesRequestDTO requestDTO);
    Mono<Void> deleteNotificacion(UUID id);
}
