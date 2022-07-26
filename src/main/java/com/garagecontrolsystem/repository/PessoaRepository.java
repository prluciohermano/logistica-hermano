package com.garagecontrolsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.garagecontrolsystem.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	@Query(value = "select p from Pessoa p where upper(trim(p.nome)) like %?1%")
	List<Pessoa> buscarPorNome(String nome);

}
