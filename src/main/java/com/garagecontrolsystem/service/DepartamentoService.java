package com.garagecontrolsystem.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.garagecontrolsystem.entity.Departamento;
import com.garagecontrolsystem.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
	
	@Autowired
	private DepartamentoRepository departamentoRepository;

	/* Métodos de CRUD */
	
/* Método de buscar todos os Departamentos ******************************/	
	
	public List<Departamento> findAll(){
		return departamentoRepository.findAll();
	}

	
/* Método de salvar um Departamento ***********************************/	
	
	public Departamento save(@Valid Departamento departamento) {
		return departamentoRepository.saveAndFlush(departamento);
	}

/* Método de buscar um Departamentos por ID ***************************************/	
	
	public ResponseEntity<Departamento> findById(Long id) {
		Optional<Departamento> department = departamentoRepository.findById(id);
		if(department.isPresent()) {
			return ResponseEntity.ok(department.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	
/* Método de deletar um Departamento por ID ****************************************/	
	
	public ResponseEntity<Departamento> deleteById(Long id) {
		if(departamentoRepository.existsById(id)) {
			departamentoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
/* Método de update dos Departamentos ************************************************/

	public ResponseEntity<Departamento> updateById(Long id, Departamento nomeDepartamento) {
		
		if (departamentoRepository.existsById(id)) {
			nomeDepartamento.setId(id);
			return ResponseEntity.ok(departamentoRepository.save(nomeDepartamento));
		}
		return ResponseEntity.notFound().build();	
	}
	
	
/* Método de buscar por nome do Departamento *****************************************/	
	
	public List<Departamento> buscarPorDepartamento(String nomeDepartamento) {
		List<Departamento> department = departamentoRepository.buscarPorDepartamento(nomeDepartamento);
		return department;
	}
	
}
