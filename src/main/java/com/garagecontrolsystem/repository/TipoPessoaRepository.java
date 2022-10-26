package com.garagecontrolsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.garagecontrolsystem.entity.Pessoa;
import com.garagecontrolsystem.entity.TipoPessoaModel;

@Repository
@Transactional
public interface TipoPessoaRepository extends JpaRepository<TipoPessoaModel, Long> {

	List<TipoPessoaModel> findByOrderByNome();

	@Query(value = "select p from TipoPessoaModel p where upper(trim(p.nome)) like %?1% order by nome")
	List<TipoPessoaModel> findPessoaByName(String nameBusca);

}
