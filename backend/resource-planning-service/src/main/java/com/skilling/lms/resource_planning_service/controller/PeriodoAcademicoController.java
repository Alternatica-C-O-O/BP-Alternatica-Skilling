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

import com.skilling.lms.resource_planning_service.service.PeriodoAcademicoService;
import com.skilling.lms.shared.dtos.resource_planning.request.PeriodoAcademicoRequestDTO;
import com.skilling.lms.shared.dtos.resource_planning.response.PeriodoAcademicoResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/periodos-academicos")
@RequiredArgsConstructor
public class PeriodoAcademicoController {

  private final PeriodoAcademicoService periodoAcademicoService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public Mono<PeriodoAcademicoResponseDTO> createPeriodoAcademico(
      @Valid @RequestBody PeriodoAcademicoRequestDTO requestDTO) {
    return periodoAcademicoService.createPeriodoAcademico(requestDTO);
  }

  @GetMapping
  public Flux<PeriodoAcademicoResponseDTO> getAllPeriodosAcademicos() {
    return periodoAcademicoService.getAllPeriodosAcademicos();
  }

  @GetMapping("/{id}")
  public Mono<PeriodoAcademicoResponseDTO> getPeriodoAcademicoById(@PathVariable("id") UUID id) {
    return periodoAcademicoService.getPeriodoAcademicoById(id)
        .switchIfEmpty(
            Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Período académico no encontrado")));
  }

  @PutMapping("/{id}")
  public Mono<PeriodoAcademicoResponseDTO> updatePeriodoAcademico(@PathVariable("id") UUID id,
      @Valid @RequestBody PeriodoAcademicoRequestDTO requestDTO) {
    return periodoAcademicoService.updatePeriodoAcademico(id, requestDTO)
        .switchIfEmpty(Mono.error(
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Período académico no encontrado para actualizar")));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deletePeriodoAcademico(@PathVariable("id") UUID id) {
    return periodoAcademicoService.deletePeriodoAcademico(id);
  }
}
