package com.garagecontrolsystem.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.garagecontrolsystem.dto.ItemPedidoDTO;
import com.garagecontrolsystem.dto.PedidoDTO;
import com.garagecontrolsystem.entity.ItemPedido;
import com.garagecontrolsystem.entity.Pedido;
import com.garagecontrolsystem.entity.Pessoa;
import com.garagecontrolsystem.entity.Produto;
import com.garagecontrolsystem.enums.StatusPedido;
import com.garagecontrolsystem.exception.PedidoNaoEncontradoException;
import com.garagecontrolsystem.exception.RegraNegocioException;
import com.garagecontrolsystem.repository.ItemPedidoRepository;
import com.garagecontrolsystem.repository.PedidoRepository;
import com.garagecontrolsystem.repository.PessoaRepository;
import com.garagecontrolsystem.repository.ProdutoRepository;
import com.garagecontrolsystem.service.PedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
	
	private final PedidoRepository repository;
	private final PessoaRepository pessoaRepository;
	private final ProdutoRepository produtosRepository;
	private final ItemPedidoRepository itemsPedidoRepository;
	

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Long idPessoa = dto.getPessoa();
		Pessoa pessoa = pessoaRepository
				.findById(idPessoa)
				.orElseThrow( () -> new RegraNegocioException("Código de pessoa inválido.")); /* RegraNegócioException*/
		
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setPessoa(pessoa);
		pedido.setStatus(StatusPedido.REALIZADO);
		
		List<ItemPedido> itemsPedido =  converterItems(pedido, dto.getItems());
		repository.save(pedido);
		itemsPedidoRepository.saveAll(itemsPedido);
		pedido.setItens(itemsPedido);
		return pedido;
	}
	
	@Override
	public Optional<Pedido> obterPedidoCompleto( Long id ) {
		return repository.findByIdFetchItens(id);
	}

	@Override
	@Transactional
	public void atualizarStatus( Long id, StatusPedido statusPedido ) {
		repository
				.findById(id)
				.map( pedido -> {
					pedido.setStatus(statusPedido);
					return repository.save(pedido);
				}).orElseThrow( () -> new PedidoNaoEncontradoException()); /* Pedido não encontrado Exception*/
		
	}
	
	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
		if ( items.isEmpty() ) {
			throw new RegraNegocioException("Não é possível realizar um pedido sem ítens");
		}
		
		return items
				.stream()
				.map( dto -> {
					Long idProduto = dto.getProduto();
					Produto produto = produtosRepository
							.findById(idProduto)
							.orElseThrow(
									() -> new RegraNegocioException(
											"Código de produto inválido: " + idProduto
									));
					ItemPedido itemPedido = new ItemPedido();
					itemPedido.setQuantidade(dto.getQuantidade());
					itemPedido.setPedido(pedido);
					itemPedido.setProduto(produto);
					return itemPedido;
				}).collect(Collectors.toList());
	}

}
