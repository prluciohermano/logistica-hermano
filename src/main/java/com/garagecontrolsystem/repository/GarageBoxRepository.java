package com.garagecontrolsystem.repository;

import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.garagecontrolsystem.entity.GarageBoxModel;

@Repository
public interface GarageBoxRepository extends JpaRepository<GarageBoxModel, UUID>{

	boolean existsByPlacaCar(String placaCar);
	
	boolean existsByNumeroBox(String numeroBox);

}
