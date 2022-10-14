package com.garagecontrolsystem.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_CATEGORIA")
public class CategoriaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Campo nome é requerido!")
	@Length(min = 3, max = 100, message = "O campo Nome dever ter pelo menos 3 caracteres. Máximo 100!")
	private String nome;
	
	@NotEmpty(message = "Campo Descrição é requerido!")
	@Length(min = 5, max = 150, message = "O campo Descrição dever ter pelo menos 5 caracteres. Máximo 150!")
	private String descricao;
	
	@JsonIgnore
	@OneToMany(mappedBy = "categoria")
	private List<ProdutoModel> produtos = new ArrayList<>();
	
	
	public List<ProdutoModel> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoModel> produtos) {
		this.produtos = produtos;
	}
}
