package com.garagecontrolsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.garagecontrolsystem.entity.PessoaModel;
import com.garagecontrolsystem.repository.PessoaRepository;

@Service
@Transactional
public class PessoaService {
	
	final PessoaRepository pessoaRepository;
	
	public PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
	@Transactional
    public PessoaModel save(PessoaModel pessoa) {
        return pessoaRepository.save(pessoa);
    }
	
	public Page<PessoaModel> findAll(Pageable pageable) {
		return pessoaRepository.findAll(pageable);
	}
	
	public Optional<PessoaModel> findById(UUID id) {
		return pessoaRepository.findById(id);
	}
	
	public Page<PessoaModel> findAll() {
		return pessoaRepository.findAll(PageRequest.of(0, 10, Sort.by("nome")));
	}

	@Transactional
	public void deleteById(UUID pessoaId) {
		pessoaRepository.deleteById(pessoaId);
	}

	public List<PessoaModel> findPessoaByName(String nameBusca) {
		return pessoaRepository.findPessoaByName(nameBusca);
	}
	
	public List<PessoaModel> findPessoaByNameSexo(String nameBusca, String sexo) {
		return pessoaRepository.findPessoaByNameSexo(nameBusca, sexo);
	}

	public List<PessoaModel> findPessoaBySexo(String pesqsexo) {
		return pessoaRepository.findPessoaBySexo(pesqsexo);
	}

	public Page<PessoaModel> findPessoaByNamePage(String nameBusca, Pageable pageable) {
		return pessoaRepository.findPessoaByNamePage(nameBusca, pageable);
	}

	public Page<PessoaModel> findPessoaBySexoPage(String nameBusca, String pesqsexo, Pageable pageable) {
		return pessoaRepository.findPessoaBySexoPage(nameBusca, pesqsexo, pageable);
	}

}

