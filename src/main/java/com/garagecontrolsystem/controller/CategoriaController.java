package com.garagecontrolsystem.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.garagecontrolsystem.dto.CategoriaDTO;
import com.garagecontrolsystem.entity.Categoria;
import com.garagecontrolsystem.service.CategoriaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Categoria> findById(@PathVariable Long id){
		Categoria obj = categoriaService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		
		List<Categoria> list = categoriaService.findAll();
		List<CategoriaDTO> listDTO = list.stream()
							.map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria obj){
		obj = categoriaService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDTO> update(@Valid @PathVariable Long id, @RequestBody CategoriaDTO objDTO){
		Categoria newObj = categoriaService.update(id, objDTO);
		return ResponseEntity.ok().body(new CategoriaDTO(newObj));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
