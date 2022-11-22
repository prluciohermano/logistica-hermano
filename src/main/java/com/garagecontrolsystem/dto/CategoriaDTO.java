package com.garagecontrolsystem.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.garagecontrolsystem.entity.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
	
	private Long id;
	
	@NotEmpty(message = "Campo nome é requerido!")
	@Length(min = 3, max = 100, message = "O campo Nome dever ter pelo menos 3 caracteres. Máximo 100!")
	private String nome;
	
	@NotEmpty(message = "Campo Descrição é requerido!")
	@Length(min = 3, max = 150, message = "O campo Descrição dever ter pelo menos 3 caracteres. Máximo 150!")
	private String descricao;
	
	
	public CategoriaDTO(Categoria obj) {

		this.id = obj.getId();
		this.nome = obj.getNome();
		this.descricao = obj.getDescricao();
	}

}
