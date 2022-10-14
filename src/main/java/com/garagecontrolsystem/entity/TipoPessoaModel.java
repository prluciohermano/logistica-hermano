package com.garagecontrolsystem.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_TIPOPESSOA")
public class TipoPessoaModel  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", length = 100)
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tipospessoa")
	private List<PessoaModel> pessoasModel = new ArrayList<PessoaModel>();

	
	public List<PessoaModel> getPessoasModel() {
		return pessoasModel;
	}
	
	public void setPessoasModel(List<PessoaModel> pessoasModel) {
		this.pessoasModel = pessoasModel;
	}

	
}
