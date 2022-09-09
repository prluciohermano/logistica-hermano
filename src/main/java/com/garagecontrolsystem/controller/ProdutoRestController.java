package com.garagecontrolsystem.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.garagecontrolsystem.dto.ProdutoDTO;
import com.garagecontrolsystem.entity.ProdutoModel;
import com.garagecontrolsystem.service.ProdutoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/produtos")
public class ProdutoRestController {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping("/{id}") /* ******************************************** Buscar Produto por ID */
	public ResponseEntity<ProdutoModel> findById(@PathVariable Long id){
		ProdutoModel obj = produtoService.findById(id);
		return ResponseEntity.ok().body(obj);	
	}

	@PostMapping /* ***************************************************** Salvar Produto */
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ProdutoModel> create(@Valid @RequestParam(value = "categoria", defaultValue = "0") Long id_cat,
							   @RequestBody ProdutoModel obj) {
		ProdutoModel newObj = produtoService.create(id_cat, obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/produtos/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping /* ***************************************************** Buscar Produto por categoria */
	public ResponseEntity<List<ProdutoDTO>> findAllByCategoria(
			@RequestParam(value = "categoria", defaultValue = "0") Long id_cat){
		List<ProdutoModel> list = produtoService.findAllByCategoria(id_cat);
		List<ProdutoDTO> listDTO = list.stream()
									   .map(promod -> new ProdutoDTO(promod)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);	
	}
	
	@GetMapping("/") /* ***************************************************** Buscar Produto por categoria */
	public ResponseEntity<List<ProdutoDTO>> findAll(){
		List<ProdutoModel> list = produtoService.findAll();
		List<ProdutoDTO> listDTO = list.stream()
									   .map(promod -> new ProdutoDTO(promod)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);	
	}
	
	@PutMapping("/{id}") /* ******************************************** Atualizar Produto por ID */
	public ResponseEntity<ProdutoDTO> update(@Valid @PathVariable Long id, @RequestBody ProdutoModel obj){
		ProdutoModel newObj = produtoService.update(id, obj);
		return ResponseEntity.ok().body(new ProdutoDTO(newObj));
		
	}
	
	@PatchMapping("/{id}") /* ******************************************** Atualizar Item Produto por ID */
	public ResponseEntity<ProdutoDTO> updatePatch(@PathVariable Long id, @RequestBody ProdutoModel obj){
		ProdutoModel newObj = produtoService.update(id, obj);
		return ResponseEntity.ok().body(new ProdutoDTO(newObj));
		
	}
	
	@DeleteMapping("/{id}") /* ******************************************** Deletar Produto por ID */
	public ResponseEntity<Void> delete(@PathVariable Long id){produtoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
