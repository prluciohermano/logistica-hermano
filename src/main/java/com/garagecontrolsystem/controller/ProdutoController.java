package com.garagecontrolsystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.garagecontrolsystem.dto.ProdutoDTO;
import com.garagecontrolsystem.entity.Produto;
import com.garagecontrolsystem.repository.ProdutoRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
//@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@PostMapping /* ***************************************************** Salvar Produto */
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Cria um novo produto")
	@ApiResponses({ @ApiResponse(code = 201, message = "Produto salvo com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public Produto save(@RequestBody @Valid Produto produto) {
		return produtoRepository.save(produto);
	}

	@PutMapping("{id}") /* ************************************************ Atualizar um Produto */
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Altera os dados de um produto")
	@ApiResponses({ @ApiResponse(code = 204, message = "Produto alterado com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public void update(@PathVariable Long id, @RequestBody @Valid Produto produto) {
		produtoRepository.findById(id).map(p -> {
			produto.setId(p.getId());
			produtoRepository.save(produto);
			return produto;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrado"));
	}

	@DeleteMapping("{id}") /* ********************************************* Deletar um Produto */
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Deleta um produto")
	@ApiResponses({ @ApiResponse(code = 204, message = "Produto deletado com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public void delete(@PathVariable Long id) {
		produtoRepository.findById(id).map(p -> {
			produtoRepository.delete(p);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
	}

	@GetMapping("/{id}") /* ************************************************ Buscar produto por ID */
	@ApiOperation("Obter detalhes de um produto pelo ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Produto encontrado"),
			@ApiResponse(code = 404, message = "Produto não encontrado para o ID informado") })
	public Produto getProdutoById(@PathVariable @ApiParam("id") Long id) {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
	}

	
	@GetMapping /* **************************************** Buscar Produto por ordem de descrição */
	@ApiOperation("Busca todos os produtos")
	@ApiResponses({ @ApiResponse(code = 200, message = "Produtos encontrados com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public ResponseEntity<List<Produto>> findByOrderByDescricao(){
		List<Produto> list = produtoRepository.findByOrderByDescricao();
		return ResponseEntity.ok(list);
			
	}
	
	@RequestMapping("/nameBusca") //--------------------------- Buscar Produto por nome ---
	@ResponseBody
	public ResponseEntity<List<ProdutoDTO>> buscarPorNome(@RequestParam(name="nome") String nameBusca) {
								
		List<Produto> list = produtoRepository.findProdutoByNome(nameBusca.trim().toUpperCase());
		List<ProdutoDTO> listDTO = list.stream()
									  .map(obj -> new ProdutoDTO(obj)).collect(Collectors.toList());
		return new ResponseEntity<List<ProdutoDTO>>(listDTO, HttpStatus.OK);
	}
}




