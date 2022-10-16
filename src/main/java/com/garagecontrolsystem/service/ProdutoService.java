package com.garagecontrolsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.garagecontrolsystem.dto.ProdutoDTO;
import com.garagecontrolsystem.entity.CategoriaModel;
import com.garagecontrolsystem.entity.GarageBoxModel;
import com.garagecontrolsystem.entity.PessoaModel;
import com.garagecontrolsystem.entity.ProdutoModel;
import com.garagecontrolsystem.repository.GarageBoxRepository;
import com.garagecontrolsystem.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	GarageBoxRepository garageBoxRepository;

	@Transactional
	public ProdutoModel save(ProdutoModel produto) {
		return produtoRepository.save(produto);
	}

	@Transactional
	public ProdutoModel create(Long id_cat, ProdutoModel obj) {
		obj.setId(null);
		CategoriaModel cat = categoriaService.findById(id_cat);
		obj.setCategoria(cat);
		return produtoRepository.save(obj);
	}

	public ProdutoModel findById(Long id) {
		Optional<ProdutoModel> obj = produtoRepository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id, ProdutoModel.class.getName()));
	}

	@Transactional
	public void deleteById(Long id) {
		produtoRepository.deleteById(id);
	}

	public List<ProdutoModel> findByOrderByDescricao() {
		return produtoRepository.findByOrderByDescricao();
	}

	public List<ProdutoModel> findAllByCategoria(Long id_cat) {
		categoriaService.findById(id_cat);
		return produtoRepository.findAllByCategoria(id_cat);
	}

	public List<ProdutoModel> findProdutoByName(String descBusca) {
		return produtoRepository.findProdutoByDesc(descBusca);

	}

	public ProdutoModel update(Long id, ProdutoModel obj) {
		ProdutoModel newObj = findById(id);
		updateData(newObj, obj);
		return produtoRepository.save(newObj);
	}

	private void updateData(ProdutoModel newObj, ProdutoModel obj) {
		newObj.setDescricao(obj.getDescricao());
		newObj.setAnoModelo(obj.getAnoModelo());
		newObj.setCorProduto(obj.getCorProduto());
//		newObj.setDataEntrada(obj.getDataEntrada());
		newObj.setPrecoEntrada(obj.getPrecoEntrada());

	}

	public void delete(Long id) {
		findById(id);
		try {
			produtoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.garagecontrolsystem.service.exceptions.DataIntegrityViolationException(
					"Produto não pode ser deletado! Possui arquivos associados.");
		}
	}

	public ResponseEntity<GarageBoxModel> saveByGarageInById(Long boxId, GarageBoxModel garageBox) {

		Optional<ProdutoModel> produtoModel = produtoRepository.findById(boxId);
			if (produtoModel.isPresent()) {
				
				garageBox.setProdutoModel(produtoModel.get());
				Optional<ProdutoModel> pessoaModel = produtoRepository.findById(boxId);
				garageBox.setProdutoModel(pessoaModel.get());
				garageBoxRepository.save(garageBox);
				return ResponseEntity.status(HttpStatus.CREATED).body(garageBox);
			}
			
		return ResponseEntity.notFound().build();
	}
	

	public List<GarageBoxModel> findAllByProdutoId(Long produtoId) {
		Optional<ProdutoModel> produtoModel = produtoRepository.findById(produtoId);
		
		if (produtoModel.isPresent()) {
			return garageBoxRepository.findByProdutoModel(produtoModel.get());
		}
		return null;
	}

	public List<ProdutoModel> findByPlaca(String placaCarro) {
		
		return produtoRepository.findByPlaca(placaCarro);
	}

}
