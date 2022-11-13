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
import com.garagecontrolsystem.entity.Pessoa;
import com.garagecontrolsystem.entity.TipoPessoaModel;
import com.garagecontrolsystem.service.PessoaService;
import com.lowagie.text.DocumentException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/pessoas")
public class PessoaRestController {
	
	@Autowired
	private PessoaService pessoaService;
	
	
	@PostMapping //------------------------------------------------------ Salvar ---
	@ResponseBody
	public ResponseEntity<Pessoa> savePessoa(@RequestBody @Valid Pessoa pessoa){
		
		Pessoa pes = pessoaService.save(pessoa);
		
		return new ResponseEntity<Pessoa>(pes, HttpStatus.OK);
	}

	@GetMapping //---------------------------------------------=====----- Buscar Todos ---
	@ResponseBody
	public ResponseEntity<List<PessoaDTO>> findByOrderByNome(){	
		
		TipoPessoaModel tipo = new TipoPessoaModel();
		
		List<Pessoa> list = pessoaService.findByOrderByNome();
		List<PessoaDTO> listDTO = list.stream()
									  .map(obj -> new PessoaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	
	@GetMapping("/{id}") //------------------------------------------------ Buscar ---
	public ResponseEntity<Object> findByIdPessoa(@PathVariable Long id){
		Optional<Pessoa> pessoa = pessoaService.findById(id);
		if(!pessoa.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(pessoa.get());		
	}
	
	@DeleteMapping("/{id}") //---------------------------------------------- Deletar ---
	public ResponseEntity<Object> deletePessoa(@PathVariable Long id){
		Optional<Pessoa> obj = pessoaService.findById(id);
		if(!obj.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
		}
		pessoaService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Pessoa excluída com sucesso!");		
	}
	
	@PutMapping("/{id}") //-------------------------------------------------- Atualizar ---
	public ResponseEntity<Object> updatePessoa(@PathVariable Long id,
													@RequestBody @Valid PessoaDTO pessoaDTO){
		
		Optional<Pessoa> objOptional = pessoaService.findById(id);
		if(!objOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
		}
		
		var pessoa = new Pessoa();
		BeanUtils.copyProperties(pessoaDTO, pessoa);
		pessoa.setId(objOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoa));		
	}
	
	@RequestMapping("/nameBusca") //----------------------------------------- Buscar por nome ---
	@ResponseBody
	public ResponseEntity<List<PessoaDTO>> buscarPorNome(@RequestParam(name="nome") String nameBusca) {
								
		System.out.println(nameBusca);
		List<Pessoa> list = pessoaService.findPessoaByName(nameBusca.trim().toUpperCase());
		List<PessoaDTO> listDTO = list.stream()
									  .map(obj -> new PessoaDTO(obj)).collect(Collectors.toList());
		return new ResponseEntity<List<PessoaDTO>>(listDTO, HttpStatus.OK);
	}
	
	@GetMapping("/{id}/imprimirPDF") //------------------------------------------ Buscar Todos ---	
	public ResponseEntity<Pessoa> imprimirPDF(@PathVariable Long id) throws DocumentException{
		
		Optional<Pessoa> pessoa = pessoaService.findById(id);
		
//		Relatorio relatorioSimples = new RelatorioPDFSimples(pessoa.get());
//		relatorioSimples.gerarCabecalho();
//		relatorioSimples.imprimir();
		
		return ResponseEntity.ok().body(pessoa.get());
		
		
	}
		
}