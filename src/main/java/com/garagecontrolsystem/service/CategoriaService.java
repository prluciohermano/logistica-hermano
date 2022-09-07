package com.garagecontrolsystem.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.garagecontrolsystem.dto.CategoriaDTO;
import com.garagecontrolsystem.entity.CategoriaModel;
import com.garagecontrolsystem.repository.CategoriaRepository;
import com.garagecontrolsystem.service.exceptions.ObjectNotFoundException;

@Service
@Transactional
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public CategoriaModel findById(Long id) {
		Optional<CategoriaModel> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Categoria não encontrada! Id: " + id + ", Tipo: " + CategoriaModel.class.getName()));
	}
	
	public List<CategoriaModel> findAll(){
		return categoriaRepository.findAll();
	}
	
	public CategoriaModel create(CategoriaModel obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}

	public CategoriaModel update(Long id, CategoriaDTO objDTO) {
		CategoriaModel obj = findById(id);
		obj.setNome(objDTO.getNome());
		obj.setDescricao(objDTO.getDescricao());
		return categoriaRepository.save(obj);
	}

	public void delete(Long id) {
		findById(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.garagecontrolsystem.service.exceptions.DataIntegrityViolationException(
					"Categoria não pode ser deletada! Possui arquivos associados.");
		}
		
	}
}
