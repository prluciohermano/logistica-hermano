package com.garagecontrolsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.garagecontrolsystem.entity.ItemServico;
import com.garagecontrolsystem.entity.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long>{

	@Query(value = "select p from Servico p where upper(trim(p.descricao)) like %?1% order by descricao")
	List<Servico> findServicoByDescricao(String nameBusca);
	
//	@Query(value = "select s from Servico s join s.produtos p")
	List<Servico> findByOrderByDescricao();

	ItemServico save(ItemServico iServico);

	//ItemServico save(ItemServico iServico);

	//void deleteById(Long id);

}
