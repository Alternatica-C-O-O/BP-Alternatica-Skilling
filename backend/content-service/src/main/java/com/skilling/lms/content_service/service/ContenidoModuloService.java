package com.skilling.lms.content_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.content.request.ContenidoModuloRequestDTO;
import com.skilling.lms.shared.dtos.content.response.ContenidoModuloResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ContenidoModuloService {

    Mono<ContenidoModuloResponseDTO> createContenidoModulo(ContenidoModuloRequestDTO requestDTO);
    Mono<ContenidoModuloResponseDTO> getContenidoModuloById(UUID id);
    Flux<ContenidoModuloResponseDTO> getAllContenidosModulo();
    Mono<ContenidoModuloResponseDTO> updateContenidoModulo(UUID id, ContenidoModuloRequestDTO requestDTO); 
    Mono<Void> deleteContenidoModulo(UUID id);
}
