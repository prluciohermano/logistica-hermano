package com.garagecontrolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.garagecontrolsystem.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRole(String role);
}
