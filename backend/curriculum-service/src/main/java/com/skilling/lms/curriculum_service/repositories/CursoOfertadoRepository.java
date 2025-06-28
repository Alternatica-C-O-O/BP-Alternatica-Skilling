package com.skilling.lms.curriculum_service.repositories;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.curriculum_service.domains.CursoOfertado;
import com.skilling.lms.shared.models.enums.ModalidadTipo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CursoOfertadoRepository extends ReactiveCrudRepository<CursoOfertado, UUID> {

    Mono<CursoOfertado> findByCodigoCurso(String codigoCurso);
    Flux<CursoOfertado> findByPeriodoAcademicoId(UUID periodoAcademicoId);
    Flux<CursoOfertado> findByPlanEstudioId(UUID planEstudioId);
    Flux<CursoOfertado> findByModalidad(ModalidadTipo modalidad);
    Mono<CursoOfertado> findByNombreCursoAndPeriodoAcademicoId(String nombreCurso, UUID periodoAcademicoId);

    public interface CursoOfertadoWithPrerequisitosProjection {
        UUID getId();
        @Column("nombre_curso") String getNombreCurso();
        @Column("codigo_curso") String getCodigoCurso();
        Integer getCreditos();
        String getDescripcion();
        @Column("duracion_semanas") Integer getDuracionSemanas();
        String getModalidad();
        @Column("periodo_academico_id") UUID getPeriodoAcademicoId();
        @Column("plan_estudio_id") UUID getPlanEstudioId();
        @Column("prerequisito_id") UUID getPrerequisitoId();
        @Column("prerequisito_nombre") String getPrerequisitoNombre();
        @Column("prerequisito_codigo") String getPrerequisitoCodigo();
    }
    
    @Query("SELECT co.id, co.nombre_curso, co.codigo_curso, co.creditos, co.descripcion, co.duracion_semanas, co.modalidad, co.periodo_academico_id, co.plan_estudio_id, " +
           "pco.prerequisito_id as prerequisito_id, pco_curso.nombre_curso as prerequisito_nombre, pco_curso.codigo_curso as prerequisito_codigo " +
           "FROM curso_ofertado co " +
           "LEFT JOIN curso_prerequisito pco ON co.id = pco.curso_id " +
           "LEFT JOIN curso_ofertado pco_curso ON pco.prerequisito_id = pco_curso.id " +
           "WHERE co.id = :cursoId")
    Flux<CursoOfertadoWithPrerequisitosProjection> findCursoOfertadoWithPrerequisitosById(UUID cursoId);
}
