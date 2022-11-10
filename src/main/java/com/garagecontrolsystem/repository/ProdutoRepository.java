package com.garagecontrolsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.garagecontrolsystem.entity.Pessoa;
import com.garagecontrolsystem.entity.Produto;
import com.garagecontrolsystem.entity.Veiculo;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {


	List<Produto> findByOrderByDescricao();

	@Query(value = "select p from Produto p where upper(trim(p.nomeProduto)) like %?1% order by nomeProduto")
	List<Produto> findProdutoByNome(String nameBusca);


}
