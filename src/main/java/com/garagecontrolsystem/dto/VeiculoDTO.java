package com.garagecontrolsystem.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.garagecontrolsystem.entity.Categoria;
import com.garagecontrolsystem.entity.Pessoa;
import com.garagecontrolsystem.entity.Veiculo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VeiculoDTO {
	private Long id;
	private String descricao;
	private String anoModelo;
	private String placaCar;
	private String corVeiculo;
	private Date dataEntrada;
	private BigDecimal precoEntrada;
	private Categoria categoria;
	private Pessoa pessoa;
	

	
	public VeiculoDTO(Veiculo promod) {
		this.id = promod.getId();
		this.descricao = promod.getDescricao();
		this.anoModelo = promod.getAnoModelo();
		this.placaCar = promod.getPlacaCar();
		this.corVeiculo = promod.getCorVeiculo();
		this.dataEntrada = promod.getDataEntrada();
		this.precoEntrada = promod.getPrecoEntrada();
		this.categoria = promod.getCategoria();
		this.pessoa = promod.getPessoa();

	}

}
