package com.garagecontrolsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.garagecontrolsystem.entity.ProdutoModel;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

	@Query(value = "select p from ProdutoModel p where upper(trim(p.descricao)) like %?1% order by descricao")
	List<ProdutoModel> findProdutoByDesc(String descBusca);

	@Query(value = "select obj from ProdutoModel obj where obj.categoria.id = :id_cat order by descricao")
	List<ProdutoModel> findAllByCategoria(@Param(value = "id_cat") Long id_cat);

	public List<ProdutoModel> findByOrderByDescricao();

	@Query(value = "select p from ProdutoModel p where upper(trim(p.placaCar)) like %?1%")
	List<ProdutoModel> findByPlaca(String placaCarro);

	
}
