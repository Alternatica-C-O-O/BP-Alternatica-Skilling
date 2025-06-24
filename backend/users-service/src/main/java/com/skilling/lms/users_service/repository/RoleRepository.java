package com.skilling.lms.users_service.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.users_service.domains.Role;

public interface RoleRepository extends ReactiveCrudRepository<Role, UUID> {

}
