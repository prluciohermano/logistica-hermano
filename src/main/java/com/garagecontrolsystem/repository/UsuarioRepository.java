package com.garagecontrolsystem.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.garagecontrolsystem.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{


	@Query("select u from Usuario u where u.login = ?1")
	Optional<Usuario> findByLogin(String login);
	
	@Query(value="select * from Usuario where login = :login and senha = :senha", nativeQuery = true)
	public Usuario login(String login, String senha);

	
	@Query("select u from Usuario u where u.login = ?1")
	Usuario findUserByLogin(String user);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value="update usuario set token = ?1 where username = ?2")
	void atualizaTokenUser(String token, String login);
}
