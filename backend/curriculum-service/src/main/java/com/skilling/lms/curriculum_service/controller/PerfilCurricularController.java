package com.skilling.lms.curriculum_service.controller;

import java.util.Set;
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

import com.skilling.lms.curriculum_service.service.PerfilCurricularService;
import com.skilling.lms.shared.dtos.curriculum.request.PerfilCurricularRequestDTO;
import com.skilling.lms.shared.dtos.curriculum.response.PerfilCurricularResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/perfiles-curriculares")
public class PerfilCurricularController {

    private final PerfilCurricularService perfilCurricularService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<PerfilCurricularResponseDTO> createPerfilCurricular(@Valid @RequestBody PerfilCurricularRequestDTO requestDTO) {
        return perfilCurricularService.createPerfilCurricular(requestDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<PerfilCurricularResponseDTO> getAllPerfilesCurriculares() {
        return perfilCurricularService.getAllPerfilesCurriculares();
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<PerfilCurricularResponseDTO> getPerfilCurricularById(@PathVariable UUID id) {
        return perfilCurricularService.getPerfilCurricularById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil curricular no encontrado")));
    }
    
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<PerfilCurricularResponseDTO> updatePerfilCurricular(@PathVariable UUID id, @Valid @RequestBody PerfilCurricularRequestDTO requestDTO) {
        return perfilCurricularService.updatePerfilCurricular(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil curricular no encontrado para actualizar")));
    }
    
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletePerfilCurricular(@PathVariable UUID id) {
        return perfilCurricularService.deletePerfilCurricular(id);
    }
    
    // --- Endpoints para manejar la relación N:M con Competencias ---

    @PostMapping(value = "/{perfilCurricularId}/competencias", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<PerfilCurricularResponseDTO> assignCompetenciasToPerfilCurricular(@PathVariable UUID perfilCurricularId, @Valid @RequestBody Set<UUID> competenciaIds) {
        return perfilCurricularService.assignCompetenciasToPerfilCurricular(perfilCurricularId, competenciaIds)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil curricular no encontrado para asignar competencias")));
    }
    
    @DeleteMapping(value = "/{perfilCurricularId}/competencias", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<PerfilCurricularResponseDTO> removeCompetenciasFromPerfilCurricular(@PathVariable UUID perfilCurricularId, @Valid @RequestBody Set<UUID> competenciaIds) {
        return perfilCurricularService.removeCompetenciasFromPerfilCurricular(perfilCurricularId, competenciaIds)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil curricular no encontrado para remover competencias")));
    }
}
