package com.garagecontrolsystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.garagecontrolsystem.dto.ProdutoDTO;
import com.garagecontrolsystem.entity.ProdutoModel;
import com.garagecontrolsystem.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoRestController {

	@Autowired
	private ProdutoService produtoService;

	@PostMapping /* ***************************************************** Salvar Produto */
	@ResponseStatus(HttpStatus.CREATED)
	
	public ProdutoModel save(@RequestBody @Valid ProdutoModel produto) {
		return produtoService.save(produto);
	}
	
	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> findAll(){
		List<ProdutoModel> list = produtoService.findAll();
		List<ProdutoDTO> listDTO = list.stream()
									   .map(promod -> new ProdutoDTO(promod)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);	
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModel> findById(@PathVariable Long id){
		ProdutoModel obj = produtoService.findById(id);
		return ResponseEntity.ok().body(obj);	
		
	}

}