package com.garagecontrolsystem.controller;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.garagecontrolsystem.entity.PessoaModel;
import com.garagecontrolsystem.service.PessoaService;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api/pessoas")
public class PessoaRestController {
	
	final PessoaService pessoaService;
	
	public PessoaRestController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid PessoaModel pessoa){
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoa));
	}
	

	//@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping
	public ResponseEntity<Page<PessoaModel>> findAllPessoa(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable){	
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll(pageable));
	}

	
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/{id}")
	public ResponseEntity<Object> findByIdPessoa(@PathVariable(value = "id") UUID id){
		Optional<PessoaModel> pessoaOptional = pessoaService.findById(id);
		
		if(!pessoaOptional.isPresent()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
		}
		return ResponseEntity.status(HttpStatus.OK).body(pessoaOptional.get());
	}
	


}





