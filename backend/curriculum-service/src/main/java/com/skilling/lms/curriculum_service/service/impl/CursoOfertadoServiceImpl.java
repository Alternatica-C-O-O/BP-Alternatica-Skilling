package com.skilling.lms.curriculum_service.service.impl;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;

import com.skilling.lms.curriculum_service.domains.CursoOfertado;
import com.skilling.lms.curriculum_service.repositories.CursoOfertadoRepository;
import com.skilling.lms.curriculum_service.repositories.CursoPrerequisitoRepository;
import com.skilling.lms.curriculum_service.repositories.PlanEstudioRepository;
import com.skilling.lms.curriculum_service.service.CursoOfertadoService;
import com.skilling.lms.shared.dtos.curriculum.request.CursoOfertadoRequestDTO;
import com.skilling.lms.shared.dtos.curriculum.response.CursoOfertadoResponseDTO;
import com.skilling.lms.shared.models.CursoPrerequisito;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CursoOfertadoServiceImpl implements CursoOfertadoService {

    private final CursoOfertadoRepository cursoOfertadoRepository;
    private final CursoPrerequisitoRepository cursoPrerequisitoRepository;
    private final PlanEstudioRepository planEstudioRepository;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    private static final String CURSO_OFERTADO_SERVICE_CB = "cursoOfertadoServiceCB";

    @Override
    @CircuitBreaker(name = CURSO_OFERTADO_SERVICE_CB, fallbackMethod = "fallbackCreateCursoOfertado")
    public Mono<CursoOfertadoResponseDTO> createCursoOfertado(CursoOfertadoRequestDTO requestDTO) {
        return cursoOfertadoRepository.findByCodigoCurso(requestDTO.codigoCurso())
                .flatMap(existingCurso -> {
                    log.warn("Intento de crear curso ofertado duplicado por código: Código '{}'", requestDTO.codigoCurso());
                    return Mono.error(new RuntimeException("Ya existe un curso ofertado con este código."));
                })
                .then(cursoOfertadoRepository.findByNombreCursoAndPeriodoAcademicoId(requestDTO.nombreCurso(), requestDTO.periodoAcademicoId())
                    .flatMap(existingCurso -> {
                        log.warn("Intento de crear curso ofertado duplicado por nombre y periodo: Nombre '{}', Periodo ID '{}'",
                                requestDTO.nombreCurso(), requestDTO.periodoAcademicoId());
                        return Mono.error(new RuntimeException("Ya existe un curso ofertado con este nombre para el periodo académico especificado."));
                    })
                )
                .then(Mono.defer(() ->
                    planEstudioRepository.findById(requestDTO.planEstudioId())
                        .switchIfEmpty(Mono.error(new RuntimeException("Plan de Estudio no encontrado con ID: " + requestDTO.planEstudioId())))
                        .then(validatePrerequisitoIds(requestDTO.prerequisitoIds())) 
                        .then(Mono.defer(() -> {
                            CursoOfertado curso = CursoOfertado.builder()
                                    .nombreCurso(requestDTO.nombreCurso())
                                    .codigoCurso(requestDTO.codigoCurso())
                                    .creditos(requestDTO.creditos())
                                    .descripcion(requestDTO.descripcion())
                                    .duracionSemanas(requestDTO.duracionSemanas())
                                    .modalidad(requestDTO.modalidad())
                                    .periodoAcademicoId(requestDTO.periodoAcademicoId())
                                    .planEstudioId(requestDTO.planEstudioId())
                                    .build();
                            return cursoOfertadoRepository.save(curso);
                        }))
                ))
                .cast(CursoOfertado.class)
                .flatMap(savedCurso -> {
                    if (requestDTO.prerequisitoIds() != null && !requestDTO.prerequisitoIds().isEmpty()) {
                        return assignPrerequisitosToCursoInternal(savedCurso.getId(), requestDTO.prerequisitoIds())
                                .then(getCursoOfertadoWithPrerequisitosById(savedCurso.getId()));
                    }
                    return Mono.just(toCursoOfertadoResponseDTO(savedCurso));
                });
    }

    @SuppressWarnings("unused")
    private Mono<CursoOfertadoResponseDTO> fallbackCreateCursoOfertado(
            CursoOfertadoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createCursoOfertado activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Cursos Ofertados no está disponible o falló al crear el curso."));
    }

    @Override
    @CircuitBreaker(name = CURSO_OFERTADO_SERVICE_CB, fallbackMethod = "fallbackGetCursoOfertadoById")
    public Mono<CursoOfertadoResponseDTO> getCursoOfertadoById(UUID id) {
        return getCursoOfertadoWithPrerequisitosById(id);
    }

    @SuppressWarnings("unused")
    private Mono<CursoOfertadoResponseDTO> fallbackGetCursoOfertadoById(UUID id, Throwable t) {
        log.error("Fallback para getCursoOfertadoById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Cursos Ofertados no está disponible o falló al obtener el curso."));
    }

    @Override
    @CircuitBreaker(name = CURSO_OFERTADO_SERVICE_CB, fallbackMethod = "fallbackGetAllCursosOfertados")
    public Flux<CursoOfertadoResponseDTO> getAllCursosOfertados() {
        return cursoOfertadoRepository.findAll()
                .flatMap(curso -> getCursoOfertadoWithPrerequisitosById(curso.getId()));
    }

    @SuppressWarnings("unused")
    private Flux<CursoOfertadoResponseDTO> fallbackGetAllCursosOfertados(Throwable t) {
        log.error("Fallback para getAllCursosOfertados activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = CURSO_OFERTADO_SERVICE_CB, fallbackMethod = "fallbackUpdateCursoOfertado")
    public Mono<CursoOfertadoResponseDTO> updateCursoOfertado(UUID id, CursoOfertadoRequestDTO requestDTO) {
        return cursoOfertadoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Curso Ofertado no encontrado para actualizar con ID: " + id)))
                .flatMap(existingCurso -> {
                    Mono<CursoOfertado> checkCodigo = Mono.just(existingCurso);
                    if (!existingCurso.getCodigoCurso().equals(requestDTO.codigoCurso())) {
                        checkCodigo = cursoOfertadoRepository.findByCodigoCurso(requestDTO.codigoCurso())
                            .flatMap(duplicateCurso -> Mono.error(new RuntimeException("El nuevo código de curso ya está en uso: " + requestDTO.codigoCurso())))
                            .thenReturn(existingCurso); 
                    }

                    Mono<CursoOfertado> checkNombrePeriodo = Mono.just(existingCurso);
                    if (!existingCurso.getNombreCurso().equals(requestDTO.nombreCurso()) ||
                        !existingCurso.getPeriodoAcademicoId().equals(requestDTO.periodoAcademicoId())) {
                        checkNombrePeriodo = cursoOfertadoRepository.findByNombreCursoAndPeriodoAcademicoId(requestDTO.nombreCurso(), requestDTO.periodoAcademicoId())
                            .flatMap(duplicateCurso -> {
                                if (!duplicateCurso.getId().equals(id)) { 
                                    return Mono.error(new RuntimeException("Ya existe otro curso ofertado con este nombre para el periodo académico especificado."));
                                }
                                return Mono.empty(); 
                            })
                            .thenReturn(existingCurso);
                    }

                    Mono<Void> validatePlanEstudio = planEstudioRepository.findById(requestDTO.planEstudioId())
                        .switchIfEmpty(Mono.error(new RuntimeException("Plan de Estudio no encontrado con ID: " + requestDTO.planEstudioId())))
                        .then();

                    Mono<Void> validatePrerequisitos = validatePrerequisitoIds(requestDTO.prerequisitoIds());

                    return Mono.when(checkCodigo, checkNombrePeriodo, validatePlanEstudio, validatePrerequisitos)
                                .then(Mono.defer(() -> {
                                    existingCurso.setNombreCurso(requestDTO.nombreCurso());
                                    existingCurso.setCodigoCurso(requestDTO.codigoCurso());
                                    existingCurso.setCreditos(requestDTO.creditos());
                                    existingCurso.setDescripcion(requestDTO.descripcion());
                                    existingCurso.setDuracionSemanas(requestDTO.duracionSemanas());
                                    existingCurso.setModalidad(requestDTO.modalidad());
                                    existingCurso.setPeriodoAcademicoId(requestDTO.periodoAcademicoId());
                                    existingCurso.setPlanEstudioId(requestDTO.planEstudioId());

                                    return cursoOfertadoRepository.save(existingCurso);
                                }))
                                .flatMap(updatedCurso ->
                                    removeAllPrerequisitosFromCursoInternal(updatedCurso.getId())
                                        .then(assignPrerequisitosToCursoInternal(updatedCurso.getId(), requestDTO.prerequisitoIds()))
                                        .then(getCursoOfertadoWithPrerequisitosById(updatedCurso.getId()))
                                );
                });
    }

    @SuppressWarnings("unused")
    private Mono<CursoOfertadoResponseDTO> fallbackUpdateCursoOfertado(
            UUID id, CursoOfertadoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateCursoOfertado activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Cursos Ofertados no está disponible o falló al actualizar el curso."));
    }

    @Override
    @CircuitBreaker(name = CURSO_OFERTADO_SERVICE_CB, fallbackMethod = "fallbackDeleteCursoOfertado")
    public Mono<Void> deleteCursoOfertado(UUID id) {
        return cursoOfertadoRepository.findById(id)
            .switchIfEmpty(Mono.error(new RuntimeException("Curso Ofertado no encontrado para eliminar con ID: " + id)))
            .flatMap(curso ->
                r2dbcEntityTemplate.delete(CursoPrerequisito.class)
                    .matching(Query.query(Criteria.where("cursoId").is(id)))
                    .all()
                    .then(
                        r2dbcEntityTemplate.delete(CursoPrerequisito.class)
                            .matching(Query.query(Criteria.where("prerequisitoId").is(id)))
                            .all()
                    )
                    .then(cursoOfertadoRepository.deleteById(id))
            )
            .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteCursoOfertado(UUID id, Throwable t) {
        log.error("Fallback para deleteCursoOfertado activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Cursos Ofertados no está disponible o falló al eliminar el curso."));
    }

    @Override
    @CircuitBreaker(name = CURSO_OFERTADO_SERVICE_CB, fallbackMethod = "fallbackAddPrerequisitosToCurso")
    public Mono<CursoOfertadoResponseDTO> addPrerequisitosToCurso(UUID cursoId, Set<UUID> prerequisitoIds) {
        if (prerequisitoIds == null || prerequisitoIds.isEmpty()) {
            return Mono.error(new IllegalArgumentException("La lista de IDs de prerrequisitos no puede estar vacía."));
        }
        return assignPrerequisitosToCursoInternal(cursoId, prerequisitoIds)
                .then(getCursoOfertadoWithPrerequisitosById(cursoId));
    }

    @SuppressWarnings("unused")
    private Mono<CursoOfertadoResponseDTO> fallbackAddPrerequisitosToCurso(
            UUID cursoId, Set<UUID> prerequisitoIds, Throwable t) {
        log.error("Fallback para addPrerequisitosToCurso activado para Curso ID {}. Causa: {}", cursoId, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Cursos Ofertados no está disponible o falló al añadir prerrequisitos."));
    }

    @Override
    @CircuitBreaker(name = CURSO_OFERTADO_SERVICE_CB, fallbackMethod = "fallbackRemovePrerequisitosFromCurso")
    public Mono<CursoOfertadoResponseDTO> removePrerequisitosFromCurso(UUID cursoId, Set<UUID> prerequisitoIds) {
        if (prerequisitoIds == null || prerequisitoIds.isEmpty()) {
            return Mono.error(new IllegalArgumentException("La lista de IDs de prerrequisitos no puede estar vacía."));
        }
        return Flux.fromIterable(prerequisitoIds)
                .flatMap(prerequisitoId ->
                    r2dbcEntityTemplate.delete(CursoPrerequisito.class)
                        .matching(Query.query(Criteria.where("cursoId").is(cursoId)
                            .and("prerequisitoId").is(prerequisitoId))).all()
                        .then(Mono.empty())
                )
                .then(getCursoOfertadoWithPrerequisitosById(cursoId));
    }

    @SuppressWarnings("unused")
    private Mono<CursoOfertadoResponseDTO> fallbackRemovePrerequisitosFromCurso(
            UUID cursoId, Set<UUID> prerequisitoIds, Throwable t) {
        log.error("Fallback para removePrerequisitosFromCurso activado para Curso ID {}. Causa: {}", cursoId, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Cursos Ofertados no está disponible o falló al remover prerrequisitos."));
    }

    // --- Métodos Auxiliares ---

    private Mono<Void> validatePrerequisitoIds(Set<UUID> prerequisitoIds) {
        if (prerequisitoIds == null || prerequisitoIds.isEmpty()) {
            return Mono.empty(); 
        }
        return Flux.fromIterable(prerequisitoIds)
                .flatMap(id -> cursoOfertadoRepository.findById(id)
                        .switchIfEmpty(Mono.error(new RuntimeException("Prerrequisito de curso no encontrado con ID: " + id))))
                .then(); 
    }

    private Mono<Void> assignPrerequisitosToCursoInternal(UUID cursoId, Set<UUID> prerequisitoIds) {
        if (prerequisitoIds == null || prerequisitoIds.isEmpty()) {
            return Mono.empty();
        }
        return cursoOfertadoRepository.findById(cursoId)
                .switchIfEmpty(Mono.error(new RuntimeException("Curso Ofertado no encontrado con ID: " + cursoId)))
                .flatMap(curso ->
                    validatePrerequisitoIds(prerequisitoIds)
                        .then(Flux.fromIterable(prerequisitoIds)
                            .flatMap(prerequisitoId ->
                                cursoPrerequisitoRepository.existsByCursoIdAndPrerequisitoId(cursoId, prerequisitoId)
                                    .flatMap(exists -> {
                                        if (Boolean.TRUE.equals(exists)) {
                                            log.warn("Prerrequisito ID {} ya está asignado al Curso ID {}.", prerequisitoId, cursoId);
                                            return Mono.empty(); 
                                        } else {
                                            return cursoPrerequisitoRepository.save(new CursoPrerequisito(cursoId, prerequisitoId)).then();
                                        }
                                    })
                            )
                            .then()
                        )
                );
    }

    private Mono<Void> removeAllPrerequisitosFromCursoInternal(UUID cursoId) {
        return r2dbcEntityTemplate.delete(CursoPrerequisito.class)
            .matching(Query.query(Criteria.where("cursoId").is(cursoId)))
            .all()
            .then();
    }

    private Mono<CursoOfertadoResponseDTO> getCursoOfertadoWithPrerequisitosById(UUID cursoId) {
        return cursoOfertadoRepository.findById(cursoId)
            .switchIfEmpty(Mono.error(new RuntimeException("Curso Ofertado no encontrado con ID: " + cursoId)))
            .flatMap(curso ->
                cursoPrerequisitoRepository.findPrerequisitoIdsByCursoId(curso.getId())
                    .collect(Collectors.toSet())
                    .map(prerequisitoIds -> new CursoOfertadoResponseDTO(
                        curso.getId(),
                        curso.getNombreCurso(),
                        curso.getCodigoCurso(),
                        curso.getCreditos(),
                        curso.getDescripcion(),
                        curso.getDuracionSemanas(),
                        curso.getModalidad(),
                        curso.getPeriodoAcademicoId(),
                        curso.getPlanEstudioId(),
                        prerequisitoIds
                    ))
            );
    }

    private CursoOfertadoResponseDTO toCursoOfertadoResponseDTO(CursoOfertado curso) {
        return new CursoOfertadoResponseDTO(
            curso.getId(),
            curso.getNombreCurso(),
            curso.getCodigoCurso(),
            curso.getCreditos(),
            curso.getDescripcion(),
            curso.getDuracionSemanas(),
            curso.getModalidad(),
            curso.getPeriodoAcademicoId(),
            curso.getPlanEstudioId(),
            Collections.emptySet() 
        );
    }
}
