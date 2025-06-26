package com.skilling.lms.resource_planning_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.resource_planning.request.PlataformaVirtualRequestDTO;
import com.skilling.lms.shared.dtos.resource_planning.response.PlataformaVirtualResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlataformaVirtualService {
    Mono<PlataformaVirtualResponseDTO> createPlataformaVirtual(PlataformaVirtualRequestDTO requestDTO);

    Mono<PlataformaVirtualResponseDTO> getPlataformaVirtualById(UUID id);

    Flux<PlataformaVirtualResponseDTO> getAllPlataformasVirtuales();

    Mono<PlataformaVirtualResponseDTO> updatePlataformaVirtual(UUID id, PlataformaVirtualRequestDTO requestDTO);

    Mono<Void> deletePlataformaVirtual(UUID id);
}
