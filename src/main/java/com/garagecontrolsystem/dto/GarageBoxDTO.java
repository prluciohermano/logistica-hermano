package com.garagecontrolsystem.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
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
	
}


