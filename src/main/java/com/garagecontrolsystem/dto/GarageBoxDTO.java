package com.garagecontrolsystem.dto;

import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.garagecontrolsystem.entity.GarageBoxModel;
import com.garagecontrolsystem.entity.PessoaModel;
import com.garagecontrolsystem.entity.ProdutoModel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GarageBoxDTO {
	
	//@NotBlank
	private Long id;
	
	//@NotBlank
	private String numeroBox;
	
	//@NotBlank
	private PessoaModel pessoaModel;
	
	//@NotBlank
	private ProdutoModel produtoModel;
	
	//@NotBlank
	private String defeitoCar;
	
	//@NotBlank
//	private String corProduto;
	
	//@NotBlank
	private LocalDateTime entradaCar;
	
	//@NotBlank
	private String mecanicoCar;
	

//	private String descricao;
//
//	private LocalDateTime dataEntrada;
//
//	private String pessoaModel;
//
//	private String placaCar;
	
	
	public GarageBoxDTO (GarageBoxModel obj) {
		this.id = obj.getId();
		this.numeroBox = obj.getNumeroBox();
		this.defeitoCar = obj.getDefeitoCar();
		this.entradaCar = obj.getEntradaCar();
		this.mecanicoCar = obj.getMecanicoCar();
		this.produtoModel = obj.getProdutoModel();
		this.pessoaModel = obj.getPessoaModel();
		//this.corProduto = obj.getProdutoModel().getCorProduto();
		//this.descricao = obj.getProdutoModel().getDescricao();
		//this.dataEntrada = obj.getProdutoModel().getDataEntrada();
	}
}


