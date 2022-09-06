package com.garagecontrolsystem.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.garagecontrolsystem.dto.CategoriaDTO;
import com.garagecontrolsystem.entity.CategoriaModel;
import com.garagecontrolsystem.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaRestController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaModel> findById(@PathVariable Long id){
		CategoriaModel obj = categoriaService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		List<CategoriaModel> list = categoriaService.findAll();
		List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<CategoriaModel> create(@RequestBody @Valid CategoriaModel obj){
		obj = categoriaService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @RequestBody CategoriaDTO objDTO){
		CategoriaModel newObj = categoriaService.update(id, objDTO);
		return ResponseEntity.ok().body(new CategoriaDTO(newObj));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
