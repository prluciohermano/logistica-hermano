package com.garagecontrolsystem.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
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
import com.garagecontrolsystem.dto.ProdutoDTO;
import com.garagecontrolsystem.entity.GarageBoxModel;
import com.garagecontrolsystem.entity.PessoaModel;
import com.garagecontrolsystem.entity.ProdutoModel;
import com.garagecontrolsystem.service.ProdutoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/produtos")
public class ProdutoRestController {

	@Autowired
	private ProdutoService produtoService;

	
	@GetMapping("/{id}") /* ******************************************** Buscar Produto por ID */
	public ResponseEntity<ProdutoModel> findById(@PathVariable Long id){
		ProdutoModel obj = produtoService.findById(id);
		return ResponseEntity.ok().body(obj);	
	}

	
	@PostMapping //------------------------------------------------------ Salvar Produto ---
	@ResponseBody
	public ResponseEntity<ProdutoModel> saveProduto(@RequestBody @Valid ProdutoModel produtoModel){
		
		produtoModel.setDataEntrada(LocalDateTime.now(ZoneId.of("UTC")));
		ProdutoModel pro = produtoService.save(produtoModel);
		
		return new ResponseEntity<ProdutoModel>(pro, HttpStatus.OK);
	}
	
	
	@PostMapping("/{boxId}/garage") //------------------------------------------------------ Salvar Produto ---
	@ResponseBody
	public ResponseEntity<GarageBoxModel> garageInById(@PathVariable Long boxId,
			@RequestBody @Valid GarageBoxModel garageBoxModel){
		
		garageBoxModel.setEntradaCar(LocalDateTime.now(ZoneId.of("UTC")));
		
		
//		garageBoxModel.getProdutoModel().getPessoaModel().getId();
//		System.out.println(garageBoxModel);
		
		//ResponseEntity<GarageBoxModel> pro = produtoService.saveByGarageInById(boxId, garageBoxModel);
		
		//return new ResponseEntity<GarageBoxModel>(pro, HttpStatus.OK);
		
		return produtoService.saveByGarageInById(boxId, garageBoxModel);
	}
	
	
	@GetMapping("/{produtoId}/garage") //------------------------------------------------------ Salvar Produto ---
	public List<GarageBoxModel> findAllByGarageId(@PathVariable Long produtoId){
				
		return produtoService.findAllByProdutoId(produtoId);
	}
	
	
	
	@GetMapping /* ***************************************************** Buscar Produto por categoria */
	public ResponseEntity<List<ProdutoDTO>> findAllByCategoria(
			@RequestParam(value = "categoria", defaultValue = "0") Long id_cat){
		List<ProdutoModel> list = produtoService.findAllByCategoria(id_cat);
		List<ProdutoDTO> listDTO = list.stream()
									   .map(promod -> new ProdutoDTO(promod)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);	
	}
	
	@GetMapping("/") /* **************************************** Buscar Produto por ordem de descrição */
	public ResponseEntity<List<ProdutoDTO>> findByOrderByDescricao(){
		List<ProdutoModel> list = produtoService.findByOrderByDescricao();
		List<ProdutoDTO> listDTO = list.stream()
									   .map(promod -> new ProdutoDTO(promod)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);	
	}
	
	@PutMapping("/{id}") /* ******************************************** Atualizar Produto por ID */
	public ResponseEntity<ProdutoDTO> update(@Valid @PathVariable Long id, @RequestBody ProdutoModel obj){
		ProdutoModel newObj = produtoService.update(id, obj);
		return ResponseEntity.ok().body(new ProdutoDTO(newObj));
		
	}
	
	@PatchMapping("/{id}") /* ******************************************** Atualizar Item Produto por ID */
	public ResponseEntity<ProdutoDTO> updatePatch(@PathVariable Long id, @RequestBody ProdutoModel obj){
		ProdutoModel newObj = produtoService.update(id, obj);
		return ResponseEntity.ok().body(new ProdutoDTO(newObj));
		
	}
	
	@DeleteMapping("/{id}") /* ******************************************** Deletar Produto por ID */
	public ResponseEntity<ProdutoDTO> delete(@PathVariable Long id){produtoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping("/nameBusca")
	@ResponseBody
	public ResponseEntity<List<ProdutoDTO>> buscarPorNome(@RequestParam(name="nome") String nameBusca) {
								
		System.out.println(nameBusca);
		List<ProdutoModel> list = produtoService.findProdutoByName(nameBusca.trim().toUpperCase());
		List<ProdutoDTO> listDTO = list.stream()
									  .map(obj -> new ProdutoDTO(obj)).collect(Collectors.toList());
		return new ResponseEntity<List<ProdutoDTO>>(listDTO, HttpStatus.OK);
	}
	
	@RequestMapping("/placaCarro") //----------------------------------------- Buscar por nome ---
	@ResponseBody
	public ResponseEntity<List<ProdutoDTO>> findByPlaca(@RequestParam(name="placaCar") String placaCarro) {

		List<ProdutoModel> result = produtoService.findByPlaca(placaCarro.trim().toUpperCase());
		List<ProdutoDTO> listDTO = result.stream()
				  					.map(obj -> new ProdutoDTO(obj)).collect(Collectors.toList());
		return new ResponseEntity<List<ProdutoDTO>>(listDTO, HttpStatus.OK);
		
		
	}

}
