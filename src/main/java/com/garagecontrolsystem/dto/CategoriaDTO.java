package com.garagecontrolsystem.dto;

import com.garagecontrolsystem.entity.CategoriaModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
	
	private Long id;
	private String nome;
	private String descricao;
	
	
	public CategoriaDTO(CategoriaModel obj) {

		this.id = obj.getId();
		this.nome = obj.getNome();
		this.descricao = obj.getDescricao();
	}

}
