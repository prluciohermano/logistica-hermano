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
	
	@NotBlank
	private Long id;
	
	@NotBlank
	private String numeroBox;
	
	@NotBlank
	private ProdutoModel produtoModel;
	
	@NotBlank
	private String defeitoCar;
	
	@NotBlank
	private LocalDateTime entradaCar;
	
	@NotBlank
	private String mecanicoCar;
	
	
	public GarageBoxDTO (GarageBoxModel obj) {
		this.id = obj.getId();
		this.numeroBox = obj.getNumeroBox();
		this.defeitoCar = obj.getDefeitoCar();
		this.entradaCar = obj.getEntradaCar();
		this.mecanicoCar = obj.getMecanicoCar();
		this.produtoModel = obj.getProdutoModel();
	}
}


