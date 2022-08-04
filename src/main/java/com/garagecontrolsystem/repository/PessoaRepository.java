package com.garagecontrolsystem.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.garagecontrolsystem.entity.PessoaModel;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, UUID>{

	@Query(value = "select p from PessoaModel p where upper(trim(p.nome)) like %?1%")
	List<PessoaModel> findPessoaByName(String nome);
	
	@Query(value = "select p from PessoaModel p where upper(trim(p.nome)) like %?1% and p.sexo = ?2")
	List<PessoaModel> findPessoaByNameSexo(String nome, String sexo);

	Optional<PessoaModel> findById(UUID id);

	List<PessoaModel> findAll();

	@Query(value = "select p from PessoaModel p where p.sexo =  ?1")
	List<PessoaModel> findPessoaBySexo(String sexo);

		
}
