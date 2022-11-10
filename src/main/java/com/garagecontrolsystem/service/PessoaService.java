package com.garagecontrolsystem.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.garagecontrolsystem.entity.Pessoa;
import com.garagecontrolsystem.repository.PessoaRepository;

@Service
@Transactional
public class PessoaService {
	
	final PessoaRepository pessoaRepository;
	
	public PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
	@Transactional
    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }
	
	public Page<Pessoa> findAll(Pageable pageable) {
		return pessoaRepository.findAll(pageable);
	}
	
	public Optional<Pessoa> findById(Long id) {
		return pessoaRepository.findById(id);
	}
	
	public Optional<Pessoa> findByIdPessoa(Long id) {
		return pessoaRepository.findById(id);
	}
	
	public Page<Pessoa> findAll() {
		return pessoaRepository.findAll(PageRequest.of(0, 10, Sort.by("nome")));
	}
	
	public List<Pessoa> findByOrderByNome() {
		return pessoaRepository.findByOrderByNome();
	}

	@Transactional
	public void deleteById(Long pessoaId) {
		pessoaRepository.deleteById(pessoaId);
	}

	public List<Pessoa> findPessoaByName(String nameBusca) {
		return pessoaRepository.findPessoaByName(nameBusca);
	}
	
	public List<Pessoa> findPessoaByNameSexo(String nameBusca, String sexo) {
		return pessoaRepository.findPessoaByNameSexo(nameBusca, sexo);
	}

	public List<Pessoa> findPessoaBySexo(String pesqsexo) {
		return pessoaRepository.findPessoaBySexo(pesqsexo);
	}

	public Page<Pessoa> findPessoaByNamePage(String nameBusca, Pageable pageable) {
		return pessoaRepository.findPessoaByNamePage(nameBusca, pageable);
	}

	public Page<Pessoa> findPessoaBySexoPage(String nameBusca, String pesqsexo, Pageable pageable) {
		return pessoaRepository.findPessoaBySexoPage(nameBusca, pesqsexo, pageable);
	}


}

