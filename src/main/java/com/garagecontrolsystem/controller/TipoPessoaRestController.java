package com.garagecontrolsystem.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.garagecontrolsystem.entity.TipoPessoaModel;
import com.garagecontrolsystem.service.TipoPessoaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/tipopessoas")
public class TipoPessoaRestController {
	
	@Autowired
	private TipoPessoaService tipoPessoaService;
	
	
	@PostMapping //------------------------------------------------------ Salvar ---
	@ResponseBody
	public ResponseEntity<TipoPessoaModel> savePessoa(@RequestBody @Valid TipoPessoaModel tipoPessoaModel){
		
		TipoPessoaModel pes = tipoPessoaService.save(tipoPessoaModel);
		
		return new ResponseEntity<TipoPessoaModel>(pes, HttpStatus.OK);
	}

	@GetMapping //---------------------------------------------=====----- Buscar Todos ---
	@ResponseBody
	public ResponseEntity<List<TipoPessoaModel>> findByOrderByNome(){	
		
		
		List<TipoPessoaModel> list = tipoPessoaService.findByOrderByNome();

		return ResponseEntity.ok().body(list);
	}

	
	@GetMapping("/{id}") //------------------------------------------------ Buscar ---
	public ResponseEntity<Object> findByIdPessoa(@PathVariable Long id){
		Optional<TipoPessoaModel> obj = tipoPessoaService.findById(id);
		if(!obj.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(obj.get());		
	}
	
	@DeleteMapping("/{id}") //---------------------------------------------- Deletar ---
	public ResponseEntity<Object> deletePessoa(@PathVariable Long id){
		Optional<TipoPessoaModel> obj = tipoPessoaService.findById(id);
		if(!obj.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
		}
		tipoPessoaService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Pessoa excluída com sucesso!");		
	}
	
	@PutMapping("/{id}") //-------------------------------------------------- Atualizar ---
	public ResponseEntity<Object> updatePessoa(@PathVariable Long id,
													@RequestBody @Valid TipoPessoaModel tipoPessoaModel){
		
		Optional<TipoPessoaModel> objOptional = tipoPessoaService.findById(id);
		if(!objOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
		}
		
		var tipoPessoa = new TipoPessoaModel();
		BeanUtils.copyProperties(tipoPessoaModel, tipoPessoaModel);
		tipoPessoaModel.setId(objOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(tipoPessoaService.save(tipoPessoa));		
	}
	
	@RequestMapping("/nameBusca") //----------------------------------------- Buscar por nome ---
	@ResponseBody
	public ResponseEntity<List<TipoPessoaModel>> buscarPorNome(@RequestParam(name="nome") String nameBusca) {
								
		System.out.println(nameBusca);
		List<TipoPessoaModel> list = tipoPessoaService.findPessoaByName(nameBusca.trim().toUpperCase());
		
		return new ResponseEntity<List<TipoPessoaModel>>(list, HttpStatus.OK);
	}
		
}





