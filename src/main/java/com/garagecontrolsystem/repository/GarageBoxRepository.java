package com.garagecontrolsystem.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.garagecontrolsystem.entity.GarageBoxModel;

import com.garagecontrolsystem.entity.Veiculo;

@Repository
public interface GarageBoxRepository extends JpaRepository<GarageBoxModel, Long>{

	//boolean existsByPlacaCar(String placaCar);
	
	boolean existsByNumeroBox(String numeroBox);
	
	@Query(value = "select p from GarageBoxModel p where upper(trim(p.numeroBox)) like %?1% order by numeroBox")
	List<GarageBoxModel> findGarageBoxByNumeroBox(String nameBusca);

	
	List<GarageBoxModel> findByVeiculo(Veiculo produtoModel);

	@Query(value = "select p from Veiculo p where upper(trim(p.placaCar)) like %?1% ")
	List<Veiculo> findByPlaca(String placaCarro);

	
//	@Query(value = "SELECT * FROM GarageBoxModel ORDER BY numeroBox", countQuery = "SELECT count(*) FROM numeroBox", nativeQuery = true)
//	List<GarageBoxModel> findAllByNumeroBox();
}
