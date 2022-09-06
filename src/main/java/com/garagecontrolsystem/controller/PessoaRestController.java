package com.garagecontrolsystem.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garagecontrolsystem.dto.PessoaDTO;
import com.garagecontrolsystem.entity.PessoaModel;
import com.garagecontrolsystem.service.PessoaService;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaRestController {
	
	@Autowired
	private PessoaService pessoaService;
	
	
//	@PostMapping
//	public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid PessoaModel pessoa){
//		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoa));
//	}
//	
	
	@PostMapping //------------------------------------------------------ Salvar ---
	public ResponseEntity<Object> savePessoa(@RequestBody @Valid PessoaDTO pessoaDTO){
		var pessoaModel = new PessoaModel();
		BeanUtils.copyProperties(pessoaDTO, pessoaModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoaModel));
	}
	

	@GetMapping //---------------------------------------------=====----- Buscar Todos ---
	public ResponseEntity<List<PessoaDTO>> findAllPessoa(){	
		List<PessoaModel> list = pessoaService.findAllPessoa();
		List<PessoaDTO> listDTO = list.stream()
									  .map(obj -> new PessoaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(listDTO);
	}

	
	@GetMapping("/{id}") //------------------------------------------------ Buscar ---
	public ResponseEntity<Object> findByIdPessoa(@PathVariable UUID id){
		Optional<PessoaModel> obj = pessoaService.findById(id);
		if(!obj.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(obj.get());		
	}
	
	@DeleteMapping("/{id}") //---------------------------------------------- Deletar ---
	public ResponseEntity<Object> deletePessoa(@PathVariable UUID id){
		Optional<PessoaModel> obj = pessoaService.findById(id);
		if(!obj.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
		}
		pessoaService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Pessoa excluída com sucesso!");		
	}
	
	@PutMapping("/{id}") //-------------------------------------------------- Atualizar ---
	public ResponseEntity<Object> updateParkingSpot(@PathVariable UUID id,
													@RequestBody @Valid PessoaDTO pessoaDTO){
		
		Optional<PessoaModel> objOptional = pessoaService.findById(id);
		if(!objOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
		}
		
		var pessoaModel = new PessoaModel();
		BeanUtils.copyProperties(pessoaDTO, pessoaModel);
		pessoaModel.setPessoaId(objOptional.get().getPessoaId());
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoaModel));		
	}

}





