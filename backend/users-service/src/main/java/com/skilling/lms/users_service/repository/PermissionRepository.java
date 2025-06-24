package com.skilling.lms.users_service.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.users_service.domains.Permiso;

public interface PermissionRepository extends ReactiveCrudRepository<Permiso, UUID> {

}
