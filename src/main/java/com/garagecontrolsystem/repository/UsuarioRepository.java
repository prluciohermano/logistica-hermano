package com.garagecontrolsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.garagecontrolsystem.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario findByNomeOrEmail(String login, String email);
	
	Optional<Usuario> findByLogin(String login);
	
}