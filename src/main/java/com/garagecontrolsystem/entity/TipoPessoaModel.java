package com.garagecontrolsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "TB_TIPOPESSOA")
public class TipoPessoaModel  {
	
	@Id
	private Long id;
	
	private String nome;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return nome;
	}
	

}
