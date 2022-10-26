package com.garagecontrolsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.garagecontrolsystem.entity.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	@Query(value = "select p from Pessoa p where upper(trim(p.nome)) like %?1% order by nome")
	List<Pessoa> findPessoaByName(String nome);
	
	@Query(value = "select p from Pessoa p where upper(trim(p.nome)) like %?1% and p.sexo = ?2 order by nome")
	List<Pessoa> findPessoaByNameSexo(String nome, String sexo);

	
	Optional<Pessoa> findById(Long id);

	public List<Pessoa> findByOrderByNome();
	
	
	

	@Query(value = "select p from Pessoa p where p.sexo =  ?1")
	List<Pessoa> findPessoaBySexo(String sexo);

	default Page<Pessoa> findPessoaByNamePage(String nome, Pageable pageable){
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		
		/* Configurando a pesquisa para consultar com partes do nome com páginas */
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
			.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		Example<Pessoa> example = Example.of(pessoa, exampleMatcher);
		
		Page<Pessoa> pessoas = findAll(example, pageable);
		return pessoas;
	}
	
	default Page<Pessoa> findPessoaBySexoPage(String nome, String sexo, Pageable pageable){
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setSexo(sexo);
		
		/* Configurando a pesquisa para consultar com partes do nome com páginas */
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
			.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
			.withMatcher("sexo", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		Example<Pessoa> example = Example.of(pessoa, exampleMatcher);
		
		Page<Pessoa> pessoas = findAll(example, pageable);
		return pessoas;
	}

	
}
