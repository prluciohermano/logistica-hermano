package com.garagecontrolsystem.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.garagecontrolsystem.entity.GarageBoxModel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GarageBoxDTO {
	
	@NotBlank
	private String numeroBox;

	@NotBlank
	@Size(max = 7)
	private String placaCar;
	
	@NotBlank
	private String marcaCar;
	
	@NotBlank
	private String modeloCar;
	
	@NotBlank
	private String corCar;
	
	@NotBlank
	private String nomeResp;
	
	@NotBlank
	private String defeitoCar;
	
	@NotBlank
	private String mecanicoCar;
	
	public GarageBoxDTO (GarageBoxModel obj) {
		this.numeroBox = obj.getNumeroBox();
		this.placaCar = obj.getPlacaCar();
		this.marcaCar = obj.getMarcaCar();
		this.modeloCar = obj.getModeloCar();
		this.corCar = obj.getCorCar();
		this.nomeResp = obj.getNomeResp();
		this.defeitoCar = obj.getDefeitoCar();
		this.mecanicoCar = obj.getMecanicoCar();
	}
}


