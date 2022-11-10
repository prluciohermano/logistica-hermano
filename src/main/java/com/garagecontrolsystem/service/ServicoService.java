package com.garagecontrolsystem.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.garagecontrolsystem.entity.ItemServico;
import com.garagecontrolsystem.entity.Servico;
import com.garagecontrolsystem.repository.ItemServicoRepository;
import com.garagecontrolsystem.repository.ServicoRepository;

@Service
public class ServicoService {
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	@Autowired
	private ItemServicoRepository itemServicoRepository;
	
	

	@Transactional
	public Servico save(@Valid Servico servico) {	
		return servicoRepository.save(servico);
	}

	
//	public Optional<Servico> findById(Long id) {
//		return servicoRepository.findById(id);
//	}

//	@Transactional
//	public void delete(Servico id) {
//		servicoRepository.delete(id);
//		
//	}

	public List<Servico> findByOrderByDescricao() {
		return servicoRepository.findByOrderByDescricao();
		
	}

	public List<Servico> findServicoByDescricao(String nameBusca) {
		return servicoRepository.findServicoByDescricao(nameBusca);
		
	}


	@Transactional
	public Servico update( Long id, @Valid Servico servico) {
		Servico newObj = findById(id);
		updateData(newObj, servico);
		return servicoRepository.save(newObj);
	}


	public Servico findById(Long id) {
		Optional<Servico> obj = servicoRepository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Servico não encontrado! id: " + id, Servico.class.getName()));
	}


	private void updateData(Servico newObj, Servico servico) {
		newObj.setDescricao(servico.getDescricao());
		newObj.setGarantia(servico.getGarantia());
		newObj.setDefeito(servico.getDefeito());
		newObj.setObservacoes(servico.getObservacoes());
		newObj.setDataFinalServico(servico.getDataFinalServico());
		newObj.setPrecoServico(servico.getPrecoServico());
		newObj.setPessoa(servico.getPessoa());
		newObj.setItens(servico.getItens());
	}

	@Transactional
	public void deleteById(Long id) {
		servicoRepository.deleteById(id);
	}

	@Transactional
	public ResponseEntity<ItemServico> saveByItemServico(Long servicoId, ItemServico itemServico) {
		
		Optional<Servico> servico = servicoRepository.findById(servicoId);

		if (servico.isPresent()) {
			
			itemServico.setServico(servico.get());
			
			itemServicoRepository.save(itemServico);
			return ResponseEntity.status(HttpStatus.CREATED).body(itemServico);
		}
		
		return ResponseEntity.notFound().build();
	}

}



