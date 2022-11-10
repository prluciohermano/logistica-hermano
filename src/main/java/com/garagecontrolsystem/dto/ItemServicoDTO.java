package com.garagecontrolsystem.dto;

import com.garagecontrolsystem.entity.ItemServico;
import com.garagecontrolsystem.entity.Produto;
import com.garagecontrolsystem.entity.Servico;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemServicoDTO {

	
	private Produto produto;
	private Servico servico;
	private Long quantidade;
	
	public ItemServicoDTO(ItemServico obj) {
		this.produto = obj.getProduto();
		this.servico = obj.getServico();
		this.quantidade = obj.getQuantidade();
	}
	
}
