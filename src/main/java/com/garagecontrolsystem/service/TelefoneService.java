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
	
	public Optional<TelefoneModel> findById(UUID id) {
		return telefoneRepository.findById(id);
	}

	public TelefoneModel save(TelefoneModel telefone) {
		return telefoneRepository.save(telefone);
	}

	public Object findByFoneById(UUID pessoaid) {
		return telefoneRepository.getTelefones(pessoaid);
	}

	public TelefoneModel deleteById(UUID idtelefone) {
		telefoneRepository.deleteById(idtelefone);
		return null;
		
		
	}

}
