package com.garagecontrolsystem.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.garagecontrolsystem.entity.TelefoneModel;
import com.garagecontrolsystem.repository.TelefoneRepository;

@Service
public class TelefoneService {
	
	final TelefoneRepository telefoneRepository;

	public TelefoneService(TelefoneRepository telefoneRepository) {
		this.telefoneRepository = telefoneRepository;
	}
	
	public Optional<TelefoneModel> findById(Long id) {
		return telefoneRepository.findById(id);
	}

	public TelefoneModel save(TelefoneModel telefone) {
		return telefoneRepository.save(telefone);
	}

	public Object findByFoneById(Long id) {
		return telefoneRepository.getTelefones(id);
	}

	public TelefoneModel deleteById(Long idtelefone) {
		telefoneRepository.deleteById(idtelefone);
		return null;
		
		
	}

}
