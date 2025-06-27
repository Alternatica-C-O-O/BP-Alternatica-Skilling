package com.skilling.lms.financial_service.controller;

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

import com.skilling.lms.financial_service.service.PlanPrecioService;
import com.skilling.lms.shared.dtos.financial.request.PlanPrecioRequestDTO;
import com.skilling.lms.shared.dtos.financial.response.PlanPrecioResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/planes-precio")
public class PlanPrecioController {

    private final PlanPrecioService planPrecioService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<PlanPrecioResponseDTO> createPlanPrecio(@Valid @RequestBody PlanPrecioRequestDTO requestDTO) {
        return planPrecioService.createPlanPrecio(requestDTO);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<PlanPrecioResponseDTO> getAllPlanesPrecio() {
        return planPrecioService.getAllPlanesPrecio();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<PlanPrecioResponseDTO> getPlanPrecioById(@PathVariable UUID id) {
        return planPrecioService.getPlanPrecioById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Plan precio no encontrado")));
    }
    
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<PlanPrecioResponseDTO> updatePlanPrecio(@PathVariable UUID id, @Valid @RequestBody PlanPrecioRequestDTO requestDTO) {
        return planPrecioService.updatePlanPrecio(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Plan precio no encontrado para actualizar")));
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletePlanPrecio(@PathVariable UUID id) {
        return planPrecioService.deletePlanPrecio(id);
    }

}
