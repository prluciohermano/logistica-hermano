 package com.garagecontrolsystem.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import com.garagecontrolsystem.dto.GarageBoxDTO;
import com.garagecontrolsystem.dto.ProdutoDTO;
import com.garagecontrolsystem.entity.GarageBoxModel;
import com.garagecontrolsystem.entity.ProdutoModel;
import com.garagecontrolsystem.service.GarageBoxService;
import com.garagecontrolsystem.service.ProdutoService;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/garage-box")
public class GarageBoxController {

	final GarageBoxService garageBoxService;
	
	final ProdutoService produtoService;

	public GarageBoxController(GarageBoxService garageBoxService, ProdutoService produtoService) {
		this.garageBoxService = garageBoxService;
		this.produtoService = produtoService;
	}
	

	@PostMapping /******************************* Adicionar vagas */
	public ResponseEntity<Object> saveGarageBox(@RequestBody @Valid GarageBoxDTO garageBoxDTO, @Valid ProdutoDTO produtoDTO) {
					
		if(garageBoxService.existsByNumeroBox(garageBoxDTO.getNumeroBox())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Esse Box já está ocupado");}
		
		ProdutoModel produtoModel = new ProdutoModel();
		GarageBoxModel garageBoxModel = new GarageBoxModel();
		BeanUtils.copyProperties(garageBoxDTO, garageBoxModel);
		BeanUtils.copyProperties(produtoDTO, produtoModel);
		//garageBoxModel.setEntradaCar(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(garageBoxService.save(garageBoxModel));}
	
	
	@GetMapping /******************************* Buscar todas as vagas */
	public ResponseEntity<List<GarageBoxDTO>> findAllByNumeroBox(){
		List<GarageBoxModel> list = garageBoxService.findAllByNumeroBox();
		List<GarageBoxDTO> listDTO = list.stream()
									.map(obj -> new GarageBoxDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(listDTO);
	}
	
	
	@GetMapping("/{id}") /************************* Buscar vaga por ID */
	public ResponseEntity<Object> findByIdGarageBoxModel(@PathVariable(value = "id") Long id){
		Optional<GarageBoxModel> garageBoxModelOptional = garageBoxService.findById(id);
		
		if (!garageBoxModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse Box está vazio!!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(garageBoxModelOptional.get());
	}
	
	@DeleteMapping("/{id}") /************************* Deletar vaga por ID */
	public ResponseEntity<Object> deleteGarageBox(@PathVariable(value = "id") Long id){
		Optional<GarageBoxModel> garageBoxModelOptional = garageBoxService.findById(id);
		if (!garageBoxModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado pelo Id informado!");
		}
		garageBoxService.delete(garageBoxModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Registro deletado com sucesso!");
	}
	
	@PutMapping("/{id}") /************************* Editar dados de uma vaga */
	public ResponseEntity<Object> updateGarageBox(@PathVariable(value = "id") Long id,
													@RequestBody @Valid GarageBoxDTO garageBoxDTO){
		Optional<GarageBoxModel> garageBoxModelOptional = garageBoxService.findById(id);
		if (!garageBoxModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado pelo Id informado!");
		}
		GarageBoxModel garageBoxModel = new GarageBoxModel();
		BeanUtils.copyProperties(garageBoxDTO, garageBoxModel);
		garageBoxModel.setId(garageBoxModelOptional.get().getId());
//		garageBoxModel.setEntradaCar(garageBoxModelOptional.get().getEntradaCar());
		
		return ResponseEntity.status(HttpStatus.OK).body(garageBoxService.save(garageBoxModel));
	}
	
	@RequestMapping("/nameBusca") //----------------------------------------- Buscar por nome ---
	@ResponseBody
	public ResponseEntity<List<GarageBoxDTO>> buscarPorNumeroBox(@RequestParam(name="numeroBox") String nameBusca) {

		List<GarageBoxModel> list = garageBoxService.findGarageBoxByNumeroBox(nameBusca.trim().toUpperCase());
		List<GarageBoxDTO> listDTO = list.stream()
									  .map(obj -> new GarageBoxDTO(obj)).collect(Collectors.toList());
		return new ResponseEntity<List<GarageBoxDTO>>(listDTO, HttpStatus.OK);
	}
		
}
