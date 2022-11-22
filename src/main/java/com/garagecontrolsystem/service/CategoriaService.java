package com.garagecontrolsystem.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.garagecontrolsystem.dto.CategoriaDTO;
import com.garagecontrolsystem.entity.Categoria;
import com.garagecontrolsystem.repository.CategoriaRepository;
import com.garagecontrolsystem.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;


	public Categoria findById(Long id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Categoria não encontrada! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	@Transactional
	public Categoria create(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}

	@Transactional
	public Categoria update(Long id, CategoriaDTO objDTO) {
		Categoria obj = findById(id);
		obj.setNome(objDTO.getNome());
		obj.setDescricao(objDTO.getDescricao());
		return categoriaRepository.save(obj);
	}

	@Transactional
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
