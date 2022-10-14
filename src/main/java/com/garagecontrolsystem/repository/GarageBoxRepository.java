package com.garagecontrolsystem.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.garagecontrolsystem.entity.GarageBoxModel;

import com.garagecontrolsystem.entity.ProdutoModel;

@Repository
public interface GarageBoxRepository extends JpaRepository<GarageBoxModel, Long>{

//	boolean existsByPlacaCar(String placaCar);
	
	boolean existsByNumeroBox(String numeroBox);
	
	@Query(value = "select p from GarageBoxModel p where upper(trim(p.numeroBox)) like %?1% ")
	List<GarageBoxModel> findGarageBoxByNumeroBox(String nameBusca);

	List<GarageBoxModel> findByProdutoModel(ProdutoModel produtoModel);

	@Query(value = "select p from ProdutoModel p where upper(trim(p.placaCar)) like %?1% ")
	List<ProdutoModel> findByPlaca(String placaCarro);
}
