package com.garagecontrolsystem.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDTO {
	
	@NotNull(message = "{campo.codigo-pessoa.obrigatorio}")
	private Long pessoa;
	
	@NotNull(message = "{campo.total-pedido.obrigatorio}")
	private BigDecimal total;
	
	@NotEmpty(message = "{campo.items-pedido.obrigatorio}")
	private List<ItemPedidoDTO> items;

}
