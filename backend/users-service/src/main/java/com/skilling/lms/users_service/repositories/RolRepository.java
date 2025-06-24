package com.skilling.lms.users_service.repositories;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.users_service.domains.Rol;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RolRepository extends ReactiveCrudRepository<Rol, UUID> {

    Mono<Rol> findByNombreRol(String nombreRol);

    public interface RolWithPermisosProjection {
        UUID getId();
        @Column("nombre_rol") String getNombreRol();
        @Column("descripcion_rol") String getDescripcionRol();
        @Column("permiso_id") UUID getPermisoId();
        @Column("nombre_permiso") String getNombrePermiso();
        @Column("descripcion_permiso") String getDescripcionPermiso();
    }

    @Query("SELECT r.id, r.nombre_rol, r.descripcion as descripcion_rol, " +
           "p.id as permiso_id, p.nombre_permiso, p.descripcion as descripcion_permiso " +
           "FROM roles r " +
           "LEFT JOIN rol_permiso rp ON r.id = rp.roles_id " +
           "LEFT JOIN permisos p ON rp.permisos_id = p.id " +
           "WHERE r.id = :rolId")
    Flux<RolWithPermisosProjection> findRolWithPermisosById(UUID rolId);

    @Query("SELECT r.id, r.nombre_rol, r.descripcion as descripcion_rol, " +
           "p.id as permiso_id, p.nombre_permiso, p.descripcion as descripcion_permiso " +
           "FROM roles r " +
           "LEFT JOIN rol_permiso rp ON r.id = rp.roles_id " +
           "LEFT JOIN permisos p ON rp.permisos_id = p.id")
    Flux<RolWithPermisosProjection> findAllRolesWithPermisos();
}
