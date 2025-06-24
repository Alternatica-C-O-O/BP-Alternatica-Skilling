package com.skilling.lms.users_service.repositories;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.shared.models.enums.UsuarioTipo;
import com.skilling.lms.users_service.domains.Usuario;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsuarioRepository extends ReactiveCrudRepository<Usuario, UUID> {
    
    Mono<Usuario> findByEmail(String email);
    Flux<Usuario> findByTipoUsuario(UsuarioTipo tipoUsuario);
    Flux<Usuario> findByEstadoActivo(Boolean estadoActivo);
    Flux<Usuario> findByFechaRegistroBetween(LocalDateTime start, LocalDateTime end);

    public interface UsuarioWithRolesProjection {
        UUID getId();
        String getNombre();
        String getApellido();
        String getEmail();
        @Column("clave_hash") String getClaveHash();
        @Column("fecha_registro") LocalDateTime getFechaRegistro();
        @Column("estado_activo") Boolean getEstadoActivo();
        @Column("tipo_usuario") UsuarioTipo getTipoUsuario();
        @Column("rol_id") UUID getRolId();
        @Column("nombre_rol") String getNombreRol();
        @Column("descripcion_rol") String getDescripcionRol();
    }

    @Query("SELECT u.id, u.nombre, u.apellido, u.email, u.clave_hash, u.fecha_registro, u.estado_activo, u.tipo_usuario, " +
           "r.id as rol_id, r.nombre_rol, r.descripcion as descripcion_rol " +
           "FROM usuarios u " +
           "LEFT JOIN usuario_role ur ON u.id = ur.usuarios_id " +
           "LEFT JOIN roles r ON ur.roles_id = r.id " +
           "WHERE u.id = :usuarioId")
    Flux<UsuarioWithRolesProjection> findUsuarioWithRolesById(UUID usuarioId);

    @Query("SELECT u.id, u.nombre, u.apellido, u.email, u.clave_hash, u.fecha_registro, u.estado_activo, u.tipo_usuario, " +
           "r.id as rol_id, r.nombre_rol, r.descripcion as descripcion_rol " +
           "FROM usuarios u " +
           "LEFT JOIN usuario_role ur ON u.id = ur.usuarios_id " +
           "LEFT JOIN roles r ON ur.roles_id = r.id")
    Flux<UsuarioWithRolesProjection> findAllUsuariosWithRoles();
}
