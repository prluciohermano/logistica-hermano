package com.garagecontrolsystem.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.garagecontrolsystem.entity.PessoaModel;
import com.garagecontrolsystem.entity.ProdutoModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoDTO {
	private String descProduto;
	private String anoModelo;
	private String corProduto;
	private Date dataEntrada;
	private BigDecimal precoEntrada;
	
	public ProdutoDTO(ProdutoModel promod) {
		this.descProduto = promod.getDescProduto();
		this.anoModelo = promod.getAnoModelo();
		this.corProduto = promod.getCorProduto();
		this.dataEntrada = promod.getDataEntrada();
		this.precoEntrada = promod.getPrecoEntrada();
	}

}
