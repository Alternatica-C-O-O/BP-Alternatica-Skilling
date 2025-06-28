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

import com.skilling.lms.curriculum_service.service.CursoOfertadoService;
import com.skilling.lms.shared.dtos.curriculum.request.CursoOfertadoRequestDTO;
import com.skilling.lms.shared.dtos.curriculum.response.CursoOfertadoResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cursos-ofertados")
public class CursoOfertadoController {

    private final CursoOfertadoService cursoOfertadoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<CursoOfertadoResponseDTO> createCursoOfertado(@Valid @RequestBody CursoOfertadoRequestDTO requestDTO) {
        return cursoOfertadoService.createCursoOfertado(requestDTO);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<CursoOfertadoResponseDTO> getAllCursosOfertados() {
        return cursoOfertadoService.getAllCursosOfertados();
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<CursoOfertadoResponseDTO> getCursoOfertadoById(@PathVariable UUID id) {
        return cursoOfertadoService.getCursoOfertadoById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso ofertado no encontrado")));
    } 
    
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<CursoOfertadoResponseDTO> updateCursoOfertado(@PathVariable UUID id, @Valid @RequestBody CursoOfertadoRequestDTO requestDTO) {
        return cursoOfertadoService.updateCursoOfertado(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso ofertado no encontrado para actualizar")));
    }
    
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCursoOfertado(@PathVariable UUID id) {
        return cursoOfertadoService.deleteCursoOfertado(id);
    }

    // --- Endpoints para manejar la relación N:M con Cursos Prerequisitos ---

    @PostMapping(value = "/{cursoId}/prerequisitos", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<CursoOfertadoResponseDTO> addPrerequisitosToCurso(@PathVariable UUID cursoId, @Valid @RequestBody Set<UUID> prerequisitoIds) {
        return cursoOfertadoService.addPrerequisitosToCurso(cursoId, prerequisitoIds)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso ofertado no encontrado para añadir prerrequisitos")));
    }

    @DeleteMapping(value = "/{cursoId}/prerequisitos", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<CursoOfertadoResponseDTO> removePrerequisitosFromCurso(@PathVariable UUID cursoId, @Valid @RequestBody Set<UUID> prerequisitoIds) {
        return cursoOfertadoService.removePrerequisitosFromCurso(cursoId, prerequisitoIds)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso ofertado no encontrado para remover prerrequisitos")));
    }
}
