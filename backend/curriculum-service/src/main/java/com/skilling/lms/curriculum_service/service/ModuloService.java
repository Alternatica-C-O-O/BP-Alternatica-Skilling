package com.skilling.lms.curriculum_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.curriculum.request.ModuloRequestDTO;
import com.skilling.lms.shared.dtos.curriculum.response.ModuloResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ModuloService {

    Mono<ModuloResponseDTO> createModulo(ModuloRequestDTO requestDTO);
    Mono<ModuloResponseDTO> getModuloById(UUID id);
    Flux<ModuloResponseDTO> getAllModulos();
    Mono<ModuloResponseDTO> updateModulo(UUID id, ModuloRequestDTO requestDTO);
    Mono<Void> deleteModulo(UUID id);
}
