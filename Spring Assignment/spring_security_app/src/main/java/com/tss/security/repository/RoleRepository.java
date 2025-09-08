package com.tss.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.security.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByRolename(String Role);
}
