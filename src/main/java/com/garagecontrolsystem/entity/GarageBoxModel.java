package com.garagecontrolsystem.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_GARAGE_BOX")
public class GarageBoxModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@Column(nullable = false, unique = true, length = 10)
	private String numeroBox;
	
//	@Column(nullable = false, unique = true, length = 7)
//	private String placaCar;
	
//	@Column(length = 130)
//	private String nomeResp;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	//@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime entradaCar;
	
	@Column(nullable = false,length = 150)
	private String defeitoCar;
	
	@Column(nullable = false, length = 30)
	private String mecanicoCar;
	
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private ProdutoModel produtoModel;
	
	
	@ManyToOne
	private PessoaModel pessoaModel;

	
	public ProdutoModel getProdutoModel() {
		return produtoModel;
	}

	public void setProdutoModel(ProdutoModel produtoModel) {
		this.produtoModel = produtoModel;
	}

	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	
	
}
