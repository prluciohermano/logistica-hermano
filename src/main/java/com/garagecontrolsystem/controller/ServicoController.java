package com.garagecontrolsystem.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.garagecontrolsystem.dto.ItemServicoDTO;
import com.garagecontrolsystem.dto.ProdutoDTO;
import com.garagecontrolsystem.dto.ServicoDTO;
import com.garagecontrolsystem.dto.VeiculoDTO;
import com.garagecontrolsystem.entity.ItemServico;
import com.garagecontrolsystem.entity.Pessoa;
import com.garagecontrolsystem.entity.Produto;
import com.garagecontrolsystem.entity.Servico;
import com.garagecontrolsystem.entity.Veiculo;
import com.garagecontrolsystem.repository.ProdutoRepository;
import com.garagecontrolsystem.service.ItemServicoService;
import com.garagecontrolsystem.service.ServicoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

	@Autowired
	private ServicoService servicoService;
	
	@Autowired
	private ItemServicoService itemServicoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@PostMapping //------------------------------------------------------ Salvar Serviço ---
	@ResponseBody
	public ResponseEntity<Servico> saveServico(@RequestBody @Valid Servico servico){
		
		servico.setDataInicialServico(LocalDateTime.now(ZoneId.of("UTC")));
		Servico pro = servicoService.save(servico);
		return new ResponseEntity<Servico>(pro, HttpStatus.OK);
	}
	
	@PostMapping("/{servicoId}/itens") //----------------------------------- Salvar Serviço ---
	public ResponseEntity<ItemServico> salvarItemService(@PathVariable Long servicoId,
										@RequestBody @Valid ItemServico itemServico){
		return servicoService.saveByItemServico(servicoId, itemServico);
	}
	
	@DeleteMapping("/{servicoId}/itens")
	public ResponseEntity<ItemServico> deleteItens(@PathVariable Long servicoId) {
		itemServicoService.deleteById(servicoId);
		return ResponseEntity.noContent().build();
	}

	
	@PutMapping("/{id}") /* ******************************************** Atualizar Serviço por ID */
	@ResponseBody
	public ResponseEntity<ServicoDTO> update(@RequestBody @Valid Servico obj, @PathVariable Long id ){
		Servico newObj = servicoService.update(id, obj);
		return ResponseEntity.ok().body(new ServicoDTO(newObj));
		
	}
	

	
	@DeleteMapping("{id}")
	public ResponseEntity<ServicoDTO> delete(@PathVariable Long id) {
		servicoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}") /* **************************************** Buscar Servico por ordem de descrição */
	public ResponseEntity<Servico> findById(@PathVariable Long id){
		Servico obj = servicoService.findById(id);
		return ResponseEntity.ok().body(obj);	
	}
	
	@GetMapping /* **************************************** Buscar Serviço por ordem de descrição */
	@ApiOperation("Busca todos os serviços")
	@ApiResponses({ @ApiResponse(code = 200, message = "Serviços encontrados com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public ResponseEntity<List<Servico>> findByOrderByDescricao(){
		List<Servico> list = servicoService.findByOrderByDescricao();
		return ResponseEntity.ok(list);
			
	}
	
	@RequestMapping("/nameBusca") //----------------------------------------- Buscar por nome ---
	@ResponseBody
	public ResponseEntity<List<ServicoDTO>> buscarPorNome(@RequestParam(name="nome") String nameBusca) {

		List<Servico> list = servicoService.findServicoByDescricao(nameBusca.trim().toUpperCase());
		List<ServicoDTO> listDTO = list.stream()
									  .map(obj -> new ServicoDTO(obj)).collect(Collectors.toList());
		return new ResponseEntity<List<ServicoDTO>>(listDTO, HttpStatus.OK);
	}
}





