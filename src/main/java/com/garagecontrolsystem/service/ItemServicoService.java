package com.garagecontrolsystem.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garagecontrolsystem.repository.ItemServicoRepository;

@Service
public class ItemServicoService {
	
	@Autowired
	private ItemServicoRepository itemServicoRepository;
	
	
    @Transactional
	public void deleteById(Long servicoId) {
		itemServicoRepository.deleteById(servicoId);
		
	}

}
