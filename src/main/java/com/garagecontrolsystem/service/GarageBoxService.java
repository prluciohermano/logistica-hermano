package com.garagecontrolsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.garagecontrolsystem.entity.GarageBoxModel;
import com.garagecontrolsystem.repository.GarageBoxRepository;

@Service
public class GarageBoxService {

	
	final GarageBoxRepository garageBoxRepository;

	public GarageBoxService(GarageBoxRepository garageBoxRepository) {
		this.garageBoxRepository = garageBoxRepository;
	}

	@Transactional   /* Métodos construtivos e destrutivos precisa para Rolback */
	public GarageBoxModel save(GarageBoxModel garageBoxModel) {
		return garageBoxRepository.save(garageBoxModel);
	}

	public boolean existsByPlacaCar(String placaCar) {
		return garageBoxRepository.existsByPlacaCar(placaCar);
	}

	public boolean existsByNumeroBox(String numeroBox) {
		return garageBoxRepository.existsByNumeroBox(numeroBox);
	}

	public Page<GarageBoxModel> findAll(Pageable pageable) {
		return garageBoxRepository.findAll(pageable);
	}

	public Optional<GarageBoxModel> findById(UUID id) {
		return garageBoxRepository.findById(id);
	}

	@Transactional
	public void delete(GarageBoxModel garageBoxModel) {
		garageBoxRepository.delete(garageBoxModel);
		
	}

	public List<GarageBoxModel> findAll() {
		return garageBoxRepository.findAll();
	}
	
	
}
