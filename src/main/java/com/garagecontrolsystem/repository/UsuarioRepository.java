package com.garagecontrolsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.garagecontrolsystem.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{


	@Query("select u from Usuario u where u.login = ?1")
	Optional<Usuario> findByLogin(String login);

}
