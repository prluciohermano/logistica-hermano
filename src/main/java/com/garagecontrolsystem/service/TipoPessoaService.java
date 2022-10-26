package com.garagecontrolsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garagecontrolsystem.entity.Pessoa;
import com.garagecontrolsystem.entity.TipoPessoaModel;
import com.garagecontrolsystem.repository.TipoPessoaRepository;

@Service
public class TipoPessoaService {
	
	@Autowired
	TipoPessoaRepository tipoPessoaRepository;

	
	@Transactional
    public TipoPessoaModel save(TipoPessoaModel nomeTipo) {
        return tipoPessoaRepository.save(nomeTipo);
    }
	
	public List<TipoPessoaModel> findAll() {
		return tipoPessoaRepository.findAll();
	}
	
	public Optional<TipoPessoaModel> findById(Long id) {
		return tipoPessoaRepository.findById(id);
	}
	
	@Transactional
	public void deleteById(Long tipoPessoaId) {
		tipoPessoaRepository.deleteById(tipoPessoaId);
	}

	public List<TipoPessoaModel> findByOrderByNome() {
		return tipoPessoaRepository.findByOrderByNome();
	}

	public List<TipoPessoaModel> findPessoaByName(String nameBusca) {
		return tipoPessoaRepository.findPessoaByName(nameBusca);
	}

}
