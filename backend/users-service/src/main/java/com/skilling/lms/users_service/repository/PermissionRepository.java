package com.skilling.lms.users_service.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.users_service.domains.Permission;

public interface PermissionRepository extends ReactiveCrudRepository<Permission, UUID> {

}
