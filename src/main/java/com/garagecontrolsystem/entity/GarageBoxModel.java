package com.garagecontrolsystem.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_GARAGE_BOX")
public class GarageBoxModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID Id;
	
	@Column(nullable = false, unique = true, length = 10)
	private String numeroBox;
	
	@Column(nullable = false, unique = true, length = 7)
	private String placaCar;
	
	@Column(nullable = false, length = 70)
	private String marcaCar;
	
	@Column(nullable = false, length = 70)
	private String modeloCar;
	
	@Column(nullable = false, length = 70)
	private String corCar;
	
	@Column(nullable = false)
	private LocalDateTime entradaCar;
	
	@Column(nullable = false, length = 130)
	private String nomeResp;
	
	@Column(nullable = false,length = 150)
	private String defeitoCar;
	
	@Column(nullable = false, length = 30)
	private String mecanicoCar;
}
