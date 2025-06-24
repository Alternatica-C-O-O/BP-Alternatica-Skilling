package com.skilling.lms.resource_planning_service.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.resource_planning_service.domains.AsignacionHorario;
import com.skilling.lms.shared.models.enums.AsignacionTipo;

import reactor.core.publisher.Flux;

public interface AsignacionHorarioRepository extends ReactiveCrudRepository<AsignacionHorario, UUID> {

    Flux<AsignacionHorario> findByCursoOfertadoId(UUID cursoOfertadoId);
    Flux<AsignacionHorario> findByUsuariosId(UUID usuariosId); 
    Flux<AsignacionHorario> findByEspacioFisicoId(UUID espacioFisicoId);
    Flux<AsignacionHorario> findByPlataformaVirtualId(UUID plataformaVirtualId);
    Flux<AsignacionHorario> findByTipoAsignacion(AsignacionTipo tipoAsignacion); 
}
