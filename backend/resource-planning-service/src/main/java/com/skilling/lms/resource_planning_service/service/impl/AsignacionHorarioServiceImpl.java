package com.skilling.lms.resource_planning_service.service.impl;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.skilling.lms.resource_planning_service.domains.AsignacionHorario;
import com.skilling.lms.resource_planning_service.repositories.AsignacionHorarioRepository;
import com.skilling.lms.resource_planning_service.service.AsignacionHorarioService;
import com.skilling.lms.shared.dtos.resource_planning.request.AsignacionHorarioRequestDTO;
import com.skilling.lms.shared.dtos.resource_planning.response.AsignacionHorarioResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class AsignacionHorarioServiceImpl implements AsignacionHorarioService {

    private final AsignacionHorarioRepository asignacionHorarioRepository;

    private static final String ASIGNACION_HORARIO_SERVICE_CB = "asignacionHorarioServiceCB";

    @Override
    @CircuitBreaker(name = ASIGNACION_HORARIO_SERVICE_CB, fallbackMethod = "fallbackCreate")
    public Mono<AsignacionHorarioResponseDTO> create(AsignacionHorarioRequestDTO dto) {
        AsignacionHorario entity = mapToEntity(dto);
        return asignacionHorarioRepository.save(entity).map(this::toAsignacionHorarioResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<AsignacionHorarioResponseDTO> fallbackCreate(AsignacionHorarioRequestDTO dto, Throwable t) {
        log.error("Fallback para create activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException(
                "El servicio de Asignaciones de Horario no está disponible o falló al crear la asignación."));
    }

    @Override
    @CircuitBreaker(name = ASIGNACION_HORARIO_SERVICE_CB, fallbackMethod = "fallbackGetById")
    public Mono<AsignacionHorarioResponseDTO> getById(UUID id) {
        return asignacionHorarioRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Asignación de horario no encontrada con ID: " + id)))
                .map(this::toAsignacionHorarioResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<AsignacionHorarioResponseDTO> fallbackGetById(UUID id, Throwable t) {
        log.error("Fallback para getById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException(
                "El servicio de Asignaciones de Horario no está disponible o falló al obtener la asignación."));
    }

    @Override
    @CircuitBreaker(name = ASIGNACION_HORARIO_SERVICE_CB, fallbackMethod = "fallbackGetAll")
    public Flux<AsignacionHorarioResponseDTO> getAll() {
        return asignacionHorarioRepository.findAll().map(this::toAsignacionHorarioResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<AsignacionHorarioResponseDTO> fallbackGetAll(Throwable t) {
        log.error("Fallback para getAll activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = ASIGNACION_HORARIO_SERVICE_CB, fallbackMethod = "fallbackUpdate")
    public Mono<AsignacionHorarioResponseDTO> update(UUID id, AsignacionHorarioRequestDTO dto) {
        return asignacionHorarioRepository.findById(id)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("Asignación de horario no encontrada para actualizar con ID: " + id)))
                .flatMap(existing -> {
                    BeanUtils.copyProperties(dto, existing, "id");
                    return asignacionHorarioRepository.save(existing);
                }).map(this::toAsignacionHorarioResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<AsignacionHorarioResponseDTO> fallbackUpdate(UUID id, AsignacionHorarioRequestDTO dto, Throwable t) {
        log.error("Fallback para update activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException(
                "El servicio de Asignaciones de Horario no está disponible o falló al actualizar la asignación."));
    }

    @Override
    @CircuitBreaker(name = ASIGNACION_HORARIO_SERVICE_CB, fallbackMethod = "fallbackDelete")
    public Mono<Void> delete(UUID id) {
        return asignacionHorarioRepository.findById(id)
                .switchIfEmpty(Mono
                        .error(new RuntimeException("Asignación de horario no encontrada para eliminar con ID: " + id)))
                .flatMap(asignacion -> asignacionHorarioRepository.delete(asignacion));
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDelete(UUID id, Throwable t) {
        log.error("Fallback para delete activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException(
                "El servicio de Asignaciones de Horario no está disponible o falló al eliminar la asignación."));
    }

    // --- Mappers ---
    private AsignacionHorario mapToEntity(AsignacionHorarioRequestDTO dto) {
        return AsignacionHorario.builder()
                .diaSemana(dto.diaSemana())
                .horaInicio(dto.horaInicio())
                .horaFin(dto.horaFin())
                .tipoAsignacion(dto.tipoAsignacion())
                .usuariosId(dto.usuariosId())
                .espacioFisicoId(dto.espacioFisicoId())
                .plataformaVirtualId(dto.plataformaVirtualId())
                .cursoOfertadoId(dto.cursoOfertadoId())
                .build();
    }

    private AsignacionHorarioResponseDTO toAsignacionHorarioResponseDTO(AsignacionHorario entity) {
        return new AsignacionHorarioResponseDTO(
                entity.getId(),
                entity.getEspacioFisicoId(),
                "AULA",
                entity.getPlataformaVirtualId(),
                "LMS",
                entity.getTipoAsignacion(),
                entity.getHoraInicio(),
                entity.getHoraFin(),
                entity.getDiaSemana());
    }
}
