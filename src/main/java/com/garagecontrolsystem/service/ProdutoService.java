package com.garagecontrolsystem.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.garagecontrolsystem.dto.CategoriaDTO;
import com.garagecontrolsystem.dto.ProdutoDTO;
import com.garagecontrolsystem.entity.CategoriaModel;
import com.garagecontrolsystem.entity.ProdutoModel;
import com.garagecontrolsystem.repository.CategoriaRepository;
import com.garagecontrolsystem.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CategoriaService categoriaService;

	
	@Transactional
	public ProdutoModel save(ProdutoModel produto) {
		return produtoRepository.save(produto);
	}
	
	public ProdutoModel findById(Long id){
		Optional<ProdutoModel> obj = produtoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id, ProdutoModel.class.getName()));
	}

	@Transactional
	public void deleteById(Long id) {
		produtoRepository.deleteById(id);
	}
	
	public List<ProdutoModel> findAll(){
		return produtoRepository.findAll();
	}
	
	public List<ProdutoModel> findAllByCategoria(Long id_cat){
		categoriaService.findById(id_cat);
		return produtoRepository.findAllByCategoria(id_cat);
	}
	
	public List<ProdutoModel> findProdutoByName(String descBusca){
		return produtoRepository.findProdutoByDesc(descBusca);
		
	}
	
	public ProdutoModel update(Long id, ProdutoDTO objDTO) {
		ProdutoModel obj = findById(id);
		obj.setDescProduto(objDTO.getDescProduto());
		obj.setAnoModelo(objDTO.getAnoModelo());
		obj.setCorProduto(objDTO.getCorProduto());
		obj.setDataEntrada(objDTO.getDataEntrada());
		obj.setPrecoEntrada(objDTO.getPrecoEntrada());
		return produtoRepository.save(obj);
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

}
