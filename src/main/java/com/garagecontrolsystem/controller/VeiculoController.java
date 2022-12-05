package com.garagecontrolsystem.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

import com.garagecontrolsystem.dto.GarageBoxDTO;
import com.garagecontrolsystem.dto.VeiculoDTO;
import com.garagecontrolsystem.entity.GarageBoxModel;
import com.garagecontrolsystem.entity.Veiculo;
import com.garagecontrolsystem.repository.GarageBoxRepository;
import com.garagecontrolsystem.service.VeiculoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoService veiculoService;

	@Autowired
	GarageBoxRepository garageBoxRepository;
	
	@GetMapping("/{id}") /* ******************************************** Buscar Veículo por ID */
	public ResponseEntity<Veiculo> findById(@PathVariable Long id){
		Veiculo obj = veiculoService.findById(id);
		return ResponseEntity.ok().body(obj);	
	}

	
	@PostMapping //------------------------------------------------------ Salvar Veículo ---
	@ResponseBody
	public ResponseEntity<Veiculo> saveVeiculo(@RequestBody @Valid Veiculo veiculo){
		
		veiculo.setDataEntrada(new Date());
				
		Veiculo pro = veiculoService.save(veiculo);
		
		return new ResponseEntity<Veiculo>(pro, HttpStatus.OK);
	}
	
	
	@PostMapping("/{boxId}/garage") //------------------------------------------------------ Salvar Veículo ---
	@ResponseBody
	public ResponseEntity<GarageBoxModel> garageInById(@PathVariable Long boxId,
			@RequestBody @Valid GarageBoxModel garageBoxModel, GarageBoxDTO garageBoxDTO){
		
		if(garageBoxRepository.existsByNumeroBox(garageBoxDTO.getNumeroBox())) {
			return null;
		}
		
		garageBoxModel.setEntradaCar(new Date());
		
		
		
		
//		garageBoxModel.getVeiculo().getPessoaModel().getId();
//		System.out.println(garageBoxModel);
		
		//ResponseEntity<GarageBoxModel> pro = VeículoService.saveByGarageInById(boxId, garageBoxModel);
		
		//return new ResponseEntity<GarageBoxModel>(pro, HttpStatus.OK);
		
		return veiculoService.saveByGarageInById(boxId, garageBoxModel);
	}
	
	
	@GetMapping("/{veiculoId}/garage") //------------------------------------------------------ Salvar Veículo ---
	public List<GarageBoxModel> findAllByGarageId(@PathVariable Long veiculoId){
				
		return veiculoService.findAllByVeiculoId(veiculoId);
	}
	
	
	
	@GetMapping /* ***************************************************** Buscar Veículo por categoria */
	public ResponseEntity<List<VeiculoDTO>> findAllByCategoria(
			@RequestParam(value = "categoria", defaultValue = "0") Long id_cat){
		List<Veiculo> list = veiculoService.findAllByCategoria(id_cat);
		List<VeiculoDTO> listDTO = list.stream()
									   .map(promod -> new VeiculoDTO(promod)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);	
	}
	
	@GetMapping("/") /* **************************************** Buscar Veículo por ordem de descrição */
	public ResponseEntity<List<VeiculoDTO>> findByOrderByDescricao(){
		List<Veiculo> list = veiculoService.findByOrderByDescricao();
		List<VeiculoDTO> listDTO = list.stream()
									   .map(promod -> new VeiculoDTO(promod)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);	
	}
	
	@PutMapping("/{id}") /* ******************************************** Atualizar Veículo por ID */
	public ResponseEntity<VeiculoDTO> update(@Valid @PathVariable Long id, @RequestBody Veiculo obj){
		Veiculo newObj = veiculoService.update(id, obj);
		return ResponseEntity.ok().body(new VeiculoDTO(newObj));
		
	}
	
	@PatchMapping("/{id}") /* ******************************************** Atualizar Item Veículo por ID */
	public ResponseEntity<VeiculoDTO> updatePatch(@PathVariable Long id, @RequestBody Veiculo obj){
		Veiculo newObj = veiculoService.update(id, obj);
		return ResponseEntity.ok().body(new VeiculoDTO(newObj));
		
	}
	
	@DeleteMapping("/{id}") /* ******************************************** Deletar Veículo por ID */
	public ResponseEntity<VeiculoDTO> delete(@PathVariable Long id){
		veiculoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping("/nameBusca")
	@ResponseBody
	public ResponseEntity<List<VeiculoDTO>> buscarPorNome(@RequestParam(name="nome") String nameBusca) {
								
		System.out.println(nameBusca);
		List<Veiculo> list = veiculoService.findVeiculoByName(nameBusca.trim().toUpperCase());
		List<VeiculoDTO> listDTO = list.stream()
									  .map(obj -> new VeiculoDTO(obj)).collect(Collectors.toList());
		return new ResponseEntity<List<VeiculoDTO>>(listDTO, HttpStatus.OK);
	}
	
	@RequestMapping("/placaCarro") //----------------------------------------- Buscar por nome ---
	@ResponseBody
	public ResponseEntity<List<VeiculoDTO>> findByPlaca(@RequestParam(name="placaCar") String placaCarro) {

		List<Veiculo> result = veiculoService.findByPlaca(placaCarro.trim().toUpperCase());
		List<VeiculoDTO> listDTO = result.stream()
				  					.map(obj -> new VeiculoDTO(obj)).collect(Collectors.toList());
		return new ResponseEntity<List<VeiculoDTO>>(listDTO, HttpStatus.OK);
		
		
	}

}
