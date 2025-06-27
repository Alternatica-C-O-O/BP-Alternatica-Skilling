package com.skilling.lms.resource_planning_service.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.resource_planning_service.domains.AsignacionHorario;
import com.skilling.lms.resource_planning_service.repositories.AsignacionHorarioRepository;
import com.skilling.lms.resource_planning_service.service.AsignacionHorarioService;
import com.skilling.lms.shared.dtos.resources_planning.request.AsignacionHorarioRequestDTO;
import com.skilling.lms.shared.dtos.resources_planning.response.AsignacionHorarioResponseDTO;

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
    @CircuitBreaker(name = ASIGNACION_HORARIO_SERVICE_CB, fallbackMethod = "fallbackCreateAsignacionHorario")
    public Mono<AsignacionHorarioResponseDTO> createAsignacionHorario(AsignacionHorarioRequestDTO requestDTO) {
        AsignacionHorario asignacionHorario = AsignacionHorario.builder()
                .diaSemana(requestDTO.diaSemana())
                .horaInicio(requestDTO.horaInicio())
                .horaFin(requestDTO.horaFin())
                .tipoAsignacion(requestDTO.tipoAsignacion())
                .usuariosId(requestDTO.usuariosId())
                .espacioFisicoId(requestDTO.espacioFisicoId())
                .plataformaVirtualId(requestDTO.plataformaVirtualId())
                .cursoOfertadoId(requestDTO.cursoOfertadoId())
                .build();

        return asignacionHorarioRepository.save(asignacionHorario)
                .map(this::toAsignacionHorarioResponseDTO); 
    }

    @SuppressWarnings("unused")
    private Mono<AsignacionHorarioResponseDTO> fallbackCreateAsignacionHorario(AsignacionHorarioRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createAsignacionHorario activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Asignación Horario no está disponible o falló al crear la asignación."));
    }

    @Override
    @CircuitBreaker(name = ASIGNACION_HORARIO_SERVICE_CB, fallbackMethod = "fallbackGetAsignacionHorarioById")
    public Mono<AsignacionHorarioResponseDTO> getAsignacionHorarioById(UUID id) {
        return asignacionHorarioRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Asignación Horario no encontrada con ID: " + id)))
                .map(this::toAsignacionHorarioResponseDTO); 
    }

    @SuppressWarnings("unused")
    private Mono<AsignacionHorarioResponseDTO> fallbackGetAsignacionHorarioById(UUID id, Throwable t) {
        log.error("Fallback para getAsignacionHorarioById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Asignación Horario no está disponible o falló al obtener la asignación."));
    }

    @Override
    @CircuitBreaker(name = ASIGNACION_HORARIO_SERVICE_CB, fallbackMethod = "fallbackGetAllAsignacionesHorario")
    public Flux<AsignacionHorarioResponseDTO> getAllAsignacionesHorario() {
        return asignacionHorarioRepository.findAll()
                .map(this::toAsignacionHorarioResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<AsignacionHorarioResponseDTO> fallbackGetAllAsignacionesHorario(Throwable t) {
        log.error("Fallback para getAllAsignacionesHorario activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = ASIGNACION_HORARIO_SERVICE_CB, fallbackMethod = "fallbackUpdateAsignacionHorario")
    public Mono<AsignacionHorarioResponseDTO> updateAsignacionHorario(UUID id, AsignacionHorarioRequestDTO requestDTO) {
        return asignacionHorarioRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Asignación Horario no encontrada para actualizar con ID: " + id)))
                .flatMap(existingAsignacionHorario -> {
                    existingAsignacionHorario.setDiaSemana(requestDTO.diaSemana());
                    existingAsignacionHorario.setHoraInicio(requestDTO.horaInicio());
                    existingAsignacionHorario.setHoraFin(requestDTO.horaFin());
                    existingAsignacionHorario.setTipoAsignacion(requestDTO.tipoAsignacion());
                    existingAsignacionHorario.setUsuariosId(requestDTO.usuariosId());
                    existingAsignacionHorario.setEspacioFisicoId(requestDTO.espacioFisicoId());
                    existingAsignacionHorario.setPlataformaVirtualId(requestDTO.plataformaVirtualId());
                    existingAsignacionHorario.setCursoOfertadoId(requestDTO.cursoOfertadoId());

                    return asignacionHorarioRepository.save(existingAsignacionHorario);
                })
                .map(this::toAsignacionHorarioResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<AsignacionHorarioResponseDTO> fallbackUpdateAsignacionHorario(UUID id, AsignacionHorarioRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateAsignacionHorario activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Asignación Horario no está disponible o falló al actualizar la asignación."));
    }

    @Override
    @CircuitBreaker(name = ASIGNACION_HORARIO_SERVICE_CB, fallbackMethod = "fallbackDeleteAsignacionHorario")
    public Mono<Void> deleteAsignacionHorario(UUID id) {
        return asignacionHorarioRepository.deleteById(id)
                .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteAsignacionHorario(UUID id, Throwable t) {
        log.error("Fallback para deleteAsignacionHorario activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Asignación Horario no está disponible o falló al eliminar la asignación."));
    }

    // -- Método Auxiliar para Mapeo Simple
    private AsignacionHorarioResponseDTO toAsignacionHorarioResponseDTO(AsignacionHorario asignacion) {
        return new AsignacionHorarioResponseDTO(
            asignacion.getId(),
            asignacion.getDiaSemana(),
            asignacion.getHoraInicio(),
            asignacion.getHoraFin(),
            asignacion.getTipoAsignacion(),
            asignacion.getUsuariosId(),
            asignacion.getEspacioFisicoId(),
            asignacion.getPlataformaVirtualId(),
            asignacion.getCursoOfertadoId()
        );
    }

    // -- Métodos del servicio que usan los queries del repositorio
    
}
