package com.skilling.lms.users_service.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.shared.models.enums.UsuarioTipo;
import com.skilling.lms.users_service.domains.Usuario;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<Usuario, UUID> {
    Mono<Usuario> findByEmail(String email);
    Flux<Usuario> findByTipoUsuario(UsuarioTipo tipoUsuario);
}
