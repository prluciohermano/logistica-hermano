package com.garagecontrolsystem.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	//List<PessoaModel> findAll();

	@Query(value = "select p from PessoaModel p where p.sexo =  ?1")
	List<PessoaModel> findPessoaBySexo(String sexo);

	default Page<PessoaModel> findPessoaByNamePage(String nome, Pageable pageable){
		
		PessoaModel pessoa = new PessoaModel();
		pessoa.setNome(nome);
		
		/* Configurando a pesquisa para consultar com partes do nome com páginas */
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
			.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		Example<PessoaModel> example = Example.of(pessoa, exampleMatcher);
		
		Page<PessoaModel> pessoas = findAll(example, pageable);
		return pessoas;
	}
	
	default Page<PessoaModel> findPessoaBySexoPage(String nome, String sexo, Pageable pageable){
		
		PessoaModel pessoa = new PessoaModel();
		pessoa.setNome(nome);
		pessoa.setSexo(sexo);
		
		/* Configurando a pesquisa para consultar com partes do nome com páginas */
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
			.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
			.withMatcher("sexo", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		Example<PessoaModel> example = Example.of(pessoa, exampleMatcher);
		
		Page<PessoaModel> pessoas = findAll(example, pageable);
		return pessoas;
	}
}
