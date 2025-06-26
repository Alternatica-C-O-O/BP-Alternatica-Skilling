package com.skilling.lms.resource_planning_service.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.skilling.lms.resource_planning_service.service.PlataformaVirtualService;
import com.skilling.lms.shared.dtos.resource_planning.request.PlataformaVirtualRequestDTO;
import com.skilling.lms.shared.dtos.resource_planning.response.PlataformaVirtualResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/plataformas-virtuales")
@RequiredArgsConstructor
public class PlataformaVirtualController {

  private final PlataformaVirtualService plataformaVirtualService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public Mono<PlataformaVirtualResponseDTO> createPlataformaVirtual(
      @Valid @RequestBody PlataformaVirtualRequestDTO requestDTO) {
    return plataformaVirtualService.createPlataformaVirtual(requestDTO);
  }

  @GetMapping
  public Flux<PlataformaVirtualResponseDTO> getAllPlataformasVirtuales() {
    return plataformaVirtualService.getAllPlataformasVirtuales();
  }

  @GetMapping("/{id}")
  public Mono<PlataformaVirtualResponseDTO> getPlataformaVirtualById(@PathVariable("id") UUID id) {
    return plataformaVirtualService.getPlataformaVirtualById(id)
        .switchIfEmpty(
            Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Plataforma virtual no encontrada")));
  }

  @PutMapping("/{id}")
  public Mono<PlataformaVirtualResponseDTO> updatePlataformaVirtual(@PathVariable("id") UUID id,
      @Valid @RequestBody PlataformaVirtualRequestDTO requestDTO) {
    return plataformaVirtualService.updatePlataformaVirtual(id, requestDTO)
        .switchIfEmpty(Mono.error(
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Plataforma virtual no encontrada para actualizar")));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deletePlataformaVirtual(@PathVariable("id") UUID id) {
    return plataformaVirtualService.deletePlataformaVirtual(id);
  }
}
