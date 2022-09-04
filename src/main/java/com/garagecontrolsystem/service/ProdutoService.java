package com.garagecontrolsystem.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garagecontrolsystem.entity.ProdutoModel;
import com.garagecontrolsystem.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;

	
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
	
	public List<ProdutoModel> findProdutoByName(String descBusca){
		return produtoRepository.findProdutoByDesc(descBusca);
		
	}
}
