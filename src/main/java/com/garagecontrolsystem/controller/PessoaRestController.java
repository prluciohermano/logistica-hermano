package com.garagecontrolsystem.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.garagecontrolsystem.dto.PessoaDTO;
import com.garagecontrolsystem.entity.PessoaModel;
import com.garagecontrolsystem.entity.TipoPessoaModel;
import com.garagecontrolsystem.service.PessoaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/pessoas")
public class PessoaRestController {
	
	@Autowired
	private PessoaService pessoaService;
	
	
	@PostMapping //------------------------------------------------------ Salvar ---
	@ResponseBody
	public ResponseEntity<PessoaModel> savePessoa(@RequestBody @Valid PessoaModel pessoaModel){
		
		PessoaModel pes = pessoaService.save(pessoaModel);
		
		return new ResponseEntity<PessoaModel>(pes, HttpStatus.OK);
	}

	@GetMapping //---------------------------------------------=====----- Buscar Todos ---
	@ResponseBody
	public ResponseEntity<List<PessoaDTO>> findByOrderByNome(){	
		
		TipoPessoaModel tipo = new TipoPessoaModel();
		
		List<PessoaModel> list = pessoaService.findByOrderByNome();
		List<PessoaDTO> listDTO = list.stream()
									  .map(obj -> new PessoaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	
	@GetMapping("/{id}") //------------------------------------------------ Buscar ---
	public ResponseEntity<Object> findByIdPessoa(@PathVariable Long id){
		Optional<PessoaModel> obj = pessoaService.findById(id);
		if(!obj.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(obj.get());		
	}
	
	@DeleteMapping("/{id}") //---------------------------------------------- Deletar ---
	public ResponseEntity<Object> deletePessoa(@PathVariable Long id){
		Optional<PessoaModel> obj = pessoaService.findById(id);
		if(!obj.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
		}
		pessoaService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Pessoa excluída com sucesso!");		
	}
	
	@PutMapping("/{id}") //-------------------------------------------------- Atualizar ---
	public ResponseEntity<Object> updatePessoa(@PathVariable Long id,
													@RequestBody @Valid PessoaDTO pessoaDTO){
		
		Optional<PessoaModel> objOptional = pessoaService.findById(id);
		if(!objOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
		}
		
		var pessoaModel = new PessoaModel();
		BeanUtils.copyProperties(pessoaDTO, pessoaModel);
		pessoaModel.setId(objOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoaModel));		
	}
	
	@RequestMapping("/nameBusca") //----------------------------------------- Buscar por nome ---
	@ResponseBody
	public ResponseEntity<List<PessoaDTO>> buscarPorNome(@RequestParam(name="nome") String nameBusca) {
								
		System.out.println(nameBusca);
		List<PessoaModel> list = pessoaService.findPessoaByName(nameBusca.trim().toUpperCase());
		List<PessoaDTO> listDTO = list.stream()
									  .map(obj -> new PessoaDTO(obj)).collect(Collectors.toList());
		return new ResponseEntity<List<PessoaDTO>>(listDTO, HttpStatus.OK);
	}
		
}





