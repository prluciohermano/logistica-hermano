package com.garagecontrolsystem.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.garagecontrolsystem.entity.CategoriaModel;
import com.garagecontrolsystem.entity.PessoaModel;
import com.garagecontrolsystem.entity.ProdutoModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoDTO {
	private Long id;
	private String descricao;
	private String anoModelo;
	private String placaCar;
	private String corProduto;
	private LocalDateTime dataEntrada;
	private BigDecimal precoEntrada;
	private CategoriaModel categoria;
	private PessoaModel pessoaModel;

	
	public ProdutoDTO(ProdutoModel promod) {
		this.id = promod.getId();
		this.descricao = promod.getDescricao();
		this.anoModelo = promod.getAnoModelo();
		this.placaCar = promod.getPlacaCar();
		this.corProduto = promod.getCorProduto();
		this.dataEntrada = promod.getDataEntrada();
		this.precoEntrada = promod.getPrecoEntrada();
		this.categoria = promod.getCategoria();
		this.pessoaModel = promod.getPessoaModel();

	}

}
