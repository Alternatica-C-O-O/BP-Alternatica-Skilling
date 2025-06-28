package com.skilling.lms.curriculum_service.service.impl;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;

import com.skilling.lms.curriculum_service.domains.PerfilCurricular;
import com.skilling.lms.curriculum_service.repositories.CompetenciaRepository;
import com.skilling.lms.curriculum_service.repositories.ModeloEducativoRepository;
import com.skilling.lms.curriculum_service.repositories.PerfilCompetenciaRepository;
import com.skilling.lms.curriculum_service.repositories.PerfilCurricularRepository;
import com.skilling.lms.curriculum_service.service.PerfilCurricularService;
import com.skilling.lms.shared.dtos.curriculum.request.PerfilCurricularRequestDTO;
import com.skilling.lms.shared.dtos.curriculum.response.PerfilCurricularResponseDTO;
import com.skilling.lms.shared.models.PerfilCompetencia;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class PerfilCurricularServiceImpl implements PerfilCurricularService {

    private final PerfilCurricularRepository perfilCurricularRepository;
    private final PerfilCompetenciaRepository perfilCompetenciaRepository;
    private final ModeloEducativoRepository modeloEducativoRepository; 
    private final CompetenciaRepository competenciaRepository;
    private final R2dbcEntityTemplate r2dbcEntityTemplate; 

    private static final String PERFIL_CURRICULAR_SERVICE_CB = "perfilCurricularServiceCB";

    @Override
    @CircuitBreaker(name = PERFIL_CURRICULAR_SERVICE_CB, fallbackMethod = "fallbackCreatePerfilCurricular")
    public Mono<PerfilCurricularResponseDTO> createPerfilCurricular(PerfilCurricularRequestDTO requestDTO) {
        return perfilCurricularRepository.findByNombrePerfil(requestDTO.nombrePerfil())
                .flatMap(existingPerfil -> {
                    log.warn("Intento de crear perfil curricular duplicado: Nombre '{}'", requestDTO.nombrePerfil());
                    return Mono.error(new RuntimeException("Ya existe un perfil curricular con este nombre."));
                })
                .then(Mono.defer(() -> 
                    modeloEducativoRepository.findById(requestDTO.modeloEducativoId())
                        .switchIfEmpty(Mono.error(new RuntimeException("Modelo Educativo no encontrado con ID: " + requestDTO.modeloEducativoId())))
                        .then(validateCompetenciaIds(requestDTO.competenciaIds()))
                        .then(Mono.defer(() -> {
                            PerfilCurricular perfil = PerfilCurricular.builder()
                                    .nombrePerfil(requestDTO.nombrePerfil())
                                    .descripcion(requestDTO.descripcion())
                                    .modeloEducativoId(requestDTO.modeloEducativoId())
                                    .build();
                            return perfilCurricularRepository.save(perfil);
                        }))
                ))
                .cast(PerfilCurricular.class) 
                .flatMap(savedPerfil -> {
                    if (requestDTO.competenciaIds() != null && !requestDTO.competenciaIds().isEmpty()) {
                        return assignCompetenciasToPerfilCurricularInternal(savedPerfil.getId(), requestDTO.competenciaIds())
                                .then(getPerfilCurricularWithCompetenciasById(savedPerfil.getId())); 
                    }
                    return Mono.just(toPerfilCurricularResponseDTO(savedPerfil)); 
                });
    }

    @SuppressWarnings("unused")
    private Mono<PerfilCurricularResponseDTO> fallbackCreatePerfilCurricular(
            PerfilCurricularRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createPerfilCurricular activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Perfiles Curriculares no está disponible o falló al crear el perfil."));
    }

    @Override
    @CircuitBreaker(name = PERFIL_CURRICULAR_SERVICE_CB, fallbackMethod = "fallbackGetPerfilCurricularById")
    public Mono<PerfilCurricularResponseDTO> getPerfilCurricularById(UUID id) {
        return getPerfilCurricularWithCompetenciasById(id);
    }

    @SuppressWarnings("unused")
    private Mono<PerfilCurricularResponseDTO> fallbackGetPerfilCurricularById(UUID id, Throwable t) {
        log.error("Fallback para getPerfilCurricularById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Perfiles Curriculares no está disponible o falló al obtener el perfil."));
    }

    @Override
    @CircuitBreaker(name = PERFIL_CURRICULAR_SERVICE_CB, fallbackMethod = "fallbackGetAllPerfilesCurriculares")
    public Flux<PerfilCurricularResponseDTO> getAllPerfilesCurriculares() {
        return perfilCurricularRepository.findAll()
                .collectList() 
                .flatMapMany(perfiles -> Flux.fromIterable(perfiles)
                        .flatMap(perfil -> getPerfilCurricularWithCompetenciasById(perfil.getId())));
    }

    @SuppressWarnings("unused")
    private Flux<PerfilCurricularResponseDTO> fallbackGetAllPerfilesCurriculares(Throwable t) {
        log.error("Fallback para getAllPerfilesCurriculares activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = PERFIL_CURRICULAR_SERVICE_CB, fallbackMethod = "fallbackUpdatePerfilCurricular")
    public Mono<PerfilCurricularResponseDTO> updatePerfilCurricular(UUID id, PerfilCurricularRequestDTO requestDTO) {
        return perfilCurricularRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Perfil Curricular no encontrado para actualizar con ID: " + id)))
                .flatMap(existingPerfil -> {
                    Mono<PerfilCurricular> updateOperation;
                    if (!existingPerfil.getNombrePerfil().equals(requestDTO.nombrePerfil())) {
                        updateOperation = perfilCurricularRepository.findByNombrePerfil(requestDTO.nombrePerfil())
                                .flatMap(duplicatePerfil -> Mono.error(new RuntimeException("El nuevo nombre de perfil ya está en uso: " + requestDTO.nombrePerfil())))
                                .thenReturn(existingPerfil);
                    } else {
                        updateOperation = Mono.just(existingPerfil); 
                    }
                    
                    Mono<Void> validateModeloEducativo = modeloEducativoRepository.findById(requestDTO.modeloEducativoId())
                        .switchIfEmpty(Mono.error(new RuntimeException("Modelo Educativo no encontrado con ID: " + requestDTO.modeloEducativoId())))
                        .then();

                    Mono<Void> validateCompetencias = validateCompetenciaIds(requestDTO.competenciaIds());

                    return Mono.when(updateOperation, validateModeloEducativo, validateCompetencias)
                                .then(Mono.defer(() -> {
                                    existingPerfil.setNombrePerfil(requestDTO.nombrePerfil());
                                    existingPerfil.setDescripcion(requestDTO.descripcion());
                                    existingPerfil.setModeloEducativoId(requestDTO.modeloEducativoId());

                                    return perfilCurricularRepository.save(existingPerfil);
                                }))
                                .flatMap(updatedPerfil ->
                                    removeAllCompetenciasFromPerfilCurricularInternal(updatedPerfil.getId())
                                        .then(assignCompetenciasToPerfilCurricularInternal(updatedPerfil.getId(), requestDTO.competenciaIds()))
                                        .then(getPerfilCurricularWithCompetenciasById(updatedPerfil.getId()))
                                );
                });
    }

    @SuppressWarnings("unused")
    private Mono<PerfilCurricularResponseDTO> fallbackUpdatePerfilCurricular(
            UUID id, PerfilCurricularRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updatePerfilCurricular activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Perfiles Curriculares no está disponible o falló al actualizar el perfil."));
    }

    @Override
    @CircuitBreaker(name = PERFIL_CURRICULAR_SERVICE_CB, fallbackMethod = "fallbackDeletePerfilCurricular")
    public Mono<Void> deletePerfilCurricular(UUID id) {
        return r2dbcEntityTemplate.delete(PerfilCompetencia.class)
            .matching(Query.query(Criteria.where("perfilesCurricularesId").is(id)))
            .all()
            .then(perfilCurricularRepository.deleteById(id));
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeletePerfilCurricular(UUID id, Throwable t) {
        log.error("Fallback para deletePerfilCurricular activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Perfiles Curriculares no está disponible o falló al eliminar el perfil."));
    }

    @Override
    @CircuitBreaker(name = PERFIL_CURRICULAR_SERVICE_CB, fallbackMethod = "fallbackAssignCompetenciasToPerfilCurricular")
    public Mono<PerfilCurricularResponseDTO> assignCompetenciasToPerfilCurricular(UUID perfilCurricularId, Set<UUID> competenciaIds) {
        if (competenciaIds == null || competenciaIds.isEmpty()) {
            return Mono.error(new IllegalArgumentException("La lista de IDs de competencias no puede estar vacía."));
        }
        return assignCompetenciasToPerfilCurricularInternal(perfilCurricularId, competenciaIds)
            .then(getPerfilCurricularWithCompetenciasById(perfilCurricularId));
    }

    @SuppressWarnings("unused")
    private Mono<PerfilCurricularResponseDTO> fallbackAssignCompetenciasToPerfilCurricular(
            UUID perfilCurricularId, Set<UUID> competenciaIds, Throwable t) {
        log.error("Fallback para assignCompetenciasToPerfilCurricular activado para Perfil ID {}. Causa: {}", perfilCurricularId, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Perfiles Curriculares no está disponible o falló al asignar competencias."));
    }

    @Override
    @CircuitBreaker(name = PERFIL_CURRICULAR_SERVICE_CB, fallbackMethod = "fallbackRemoveCompetenciasFromPerfilCurricular")
    public Mono<PerfilCurricularResponseDTO> removeCompetenciasFromPerfilCurricular(UUID perfilCurricularId, Set<UUID> competenciaIds) {
        if (competenciaIds == null || competenciaIds.isEmpty()) {
            return Mono.error(new IllegalArgumentException("La lista de IDs de competencias no puede estar vacía."));
        }
        return Flux.fromIterable(competenciaIds)
            .flatMap(competenciaId ->
                r2dbcEntityTemplate.delete(PerfilCompetencia.class)
                    .matching(Query.query(Criteria.where("perfilesCurricularesId").is(perfilCurricularId)
                        .and("competenciasId").is(competenciaId))).all()
                    .then(Mono.empty())
            )
            .then(getPerfilCurricularWithCompetenciasById(perfilCurricularId));
    }

    @SuppressWarnings("unused")
    private Mono<PerfilCurricularResponseDTO> fallbackRemoveCompetenciasFromPerfilCurricular(
            UUID perfilCurricularId, Set<UUID> competenciaIds, Throwable t) {
        log.error("Fallback para removeCompetenciasFromPerfilCurricular activado para Perfil ID {}. Causa: {}", perfilCurricularId, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Perfiles Curriculares no está disponible o falló al remover competencias."));
    }

    // --- Métodos Auxiliares ---

    @SuppressWarnings("unused")
    private Mono<PerfilCurricular> updatePerfilCurricularFields(PerfilCurricular existingPerfil, PerfilCurricularRequestDTO requestDTO) {
        existingPerfil.setNombrePerfil(requestDTO.nombrePerfil());
        existingPerfil.setDescripcion(requestDTO.descripcion());
        existingPerfil.setModeloEducativoId(requestDTO.modeloEducativoId());
        return perfilCurricularRepository.save(existingPerfil);
    }

    private Mono<Void> assignCompetenciasToPerfilCurricularInternal(UUID perfilCurricularId, Set<UUID> competenciaIds) {
        if (competenciaIds == null || competenciaIds.isEmpty()) {
            return Mono.empty();
        }
        return perfilCurricularRepository.findById(perfilCurricularId)
                .switchIfEmpty(Mono.error(new RuntimeException("Perfil Curricular no encontrado con ID: " + perfilCurricularId))) 
                .flatMap(perfil ->
                    validateCompetenciaIds(competenciaIds) 
                        .then(Flux.fromIterable(competenciaIds)
                            .flatMap(competenciaId ->
                                perfilCompetenciaRepository.existsByPerfilesCurricularesIdAndCompetenciasId(perfilCurricularId, competenciaId)
                                    .flatMap(existingRelation -> {
                                        log.warn("Competencia ID {} ya está asignada al Perfil Curricular ID {}.", competenciaId, perfilCurricularId);
                                        return Mono.empty(); 
                                    })
                                    .switchIfEmpty(Mono.defer(() -> {
                                        log.info("Asignando Competencia ID {} al Perfil Curricular ID {}.", competenciaId, perfilCurricularId);
                                        return perfilCompetenciaRepository.save(new PerfilCompetencia(perfilCurricularId, competenciaId));
                                    }))
                            )
                            .then() 
                        )
                );
    }

    private Mono<Void> removeAllCompetenciasFromPerfilCurricularInternal(UUID perfilCurricularId) {
        return r2dbcEntityTemplate.delete(PerfilCompetencia.class)
            .matching(Query.query(Criteria.where("perfilesCurricularesId").is(perfilCurricularId)))
            .all()
            .then();
    }

    private Mono<Void> validateCompetenciaIds(Set<UUID> competenciaIds) {
        if (competenciaIds == null || competenciaIds.isEmpty()) {
            return Mono.empty(); 
        }
        return Flux.fromIterable(competenciaIds)
                .flatMap(id -> competenciaRepository.findById(id)
                        .switchIfEmpty(Mono.error(new RuntimeException("Competencia no encontrada con ID: " + id))))
                .then(); 
    }

    private Mono<PerfilCurricularResponseDTO> getPerfilCurricularWithCompetenciasById(UUID perfilCurricularId) {
        return perfilCurricularRepository.findById(perfilCurricularId)
            .switchIfEmpty(Mono.error(new RuntimeException("Perfil Curricular no encontrado con ID: " + perfilCurricularId)))
            .flatMap(perfil ->
                perfilCompetenciaRepository.findCompetenciaIdsByPerfilCurricularId(perfil.getId())
                    .collect(Collectors.toSet())
                    .map(competenciaIds -> new PerfilCurricularResponseDTO(
                        perfil.getId(),
                        perfil.getNombrePerfil(),
                        perfil.getDescripcion(),
                        perfil.getModeloEducativoId(),
                        competenciaIds 
                    ))
            );
    }

    private PerfilCurricularResponseDTO toPerfilCurricularResponseDTO(PerfilCurricular perfil) {
        return new PerfilCurricularResponseDTO(
            perfil.getId(),
            perfil.getNombrePerfil(),
            perfil.getDescripcion(),
            perfil.getModeloEducativoId(),
            Collections.emptySet() 
        );
    }
}
