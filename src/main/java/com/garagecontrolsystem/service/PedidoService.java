package com.garagecontrolsystem.service;

import java.util.Optional;

import com.garagecontrolsystem.dto.PedidoDTO;
import com.garagecontrolsystem.entity.Pedido;
import com.garagecontrolsystem.enums.StatusPedido;

public interface PedidoService {
	Pedido salvar( PedidoDTO dto );
	
	Optional<Pedido> obterPedidoCompleto(Long id);
	
	void atualizarStatus(Long id, StatusPedido statusPedido);

}
