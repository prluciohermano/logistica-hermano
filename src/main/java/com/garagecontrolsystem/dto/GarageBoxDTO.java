package com.garagecontrolsystem.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.garagecontrolsystem.entity.GarageBoxModel;
import com.garagecontrolsystem.entity.Pessoa;
import com.garagecontrolsystem.entity.Veiculo;

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
	private Veiculo veiculo;
	
	@NotBlank
	private String defeitoCar;
	
	@NotBlank
	private Date entradaCar;
	
	@NotBlank
	private String mecanicoCar;
	
	
	public GarageBoxDTO (GarageBoxModel obj) {
		this.id = obj.getId();
		this.numeroBox = obj.getNumeroBox();
		this.defeitoCar = obj.getDefeitoCar();
		this.entradaCar = obj.getEntradaCar();
		this.mecanicoCar = obj.getMecanicoCar();
		this.veiculo = obj.getVeiculo();
	}
}


