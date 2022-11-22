package com.garagecontrolsystem.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.garagecontrolsystem.entity.Departamento;
import com.garagecontrolsystem.service.DepartamentoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/departamentos")
@Api("Api Departamentos")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService departamentoService;
			
	@GetMapping("/{id}")  /* ***************************************** Buscar pessoa por ID */
	@ApiOperation("Obter detalhes de um Departamento pelo ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Departamento encontrado"),
		@ApiResponse(code = 404, message = "Departamento não encontrado para o ID informado")
	})
	public ResponseEntity<Departamento> findById ( 
			@PathVariable("id") Long id) {
		return departamentoService.findById(id);
	}
		
	
	@PostMapping  /* *****************************************************  Salvar pessoa */
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Cria um novo Departamento")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Departamento salvo com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	public Departamento save( @RequestBody @Valid Departamento departamento ) {
		return departamentoService.save(departamento);
	}
	
	
	@DeleteMapping("/{id}")  /* ********************************************* Deletar Pessoa */
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Deleta um Departamento")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Departamento deletadao com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	public ResponseEntity<Departamento> deleteById( @PathVariable Long id) {
		return departamentoService.deleteById(id);
	}
	
	
	@PutMapping("/{id}")  /* ********************** Atualizar um Pessoa */
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Altera os dados de um Departamento")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Departamento alterado com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	public ResponseEntity<Departamento> update( @PathVariable Long id,
			@RequestBody @Valid Departamento pessoa){
			return departamentoService.updateById(id, pessoa);
	}
	
	 /* ******************************* Busca pessoa por nome */
	
	@GetMapping(value = "buscarPorDepartamento") /* mapeia a url */
	@ApiOperation("Busca um departamento")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Departamento encontrado com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	
	public ResponseEntity<List<Departamento>> buscarPorNome(@RequestParam(name = "nome") String nome) { /* Recebe os dados para consultar */

		List<Departamento> pessoa = departamentoService.buscarPorDepartamento(nome.trim().toUpperCase());

		return new ResponseEntity<List<Departamento>>(pessoa, HttpStatus.OK);

	}
	
	@GetMapping /* ************************************************ Busca todos as Pessoas */
	@ApiOperation("Busca todos os Departamentos")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Pessoas encontradas com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	    public List<Departamento> findAll(){
			return departamentoService.findAll();
		}

}
