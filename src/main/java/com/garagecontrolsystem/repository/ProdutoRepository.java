package com.garagecontrolsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.garagecontrolsystem.entity.ProdutoModel;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

	@Query(value = "select p from ProdutoModel p where upper(trim(p.descProduto)) like %?1%")
	List<ProdutoModel> findProdutoByDesc(String descBusca);

	@Query(value = "select obj from ProdutoModel obj where obj.categoria.id = :id_cat order by descProduto")
	List<ProdutoModel> findAllByCategoria(@Param(value = "id_cat") Long id_cat);

}
