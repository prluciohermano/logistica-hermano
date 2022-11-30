package com.garagecontrolsystem.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_servico")
public class Servico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", length = 255)
	private String descricao;
	
	@Column(length = 50)
	private String garantia;
	
	@Column(length = 255)
	private String defeito;
	
	@Column(length = 255)
	private String observacoes;
	
	@Column
	private Date dataInicialServico;
	
	@Column
	private Date dataFinalServico;

	@Column(name = "preco_servico")
	private BigDecimal precoServico;
	
	@Column(name = "total")
	private BigDecimal total;
	
	@ManyToOne
	@JoinColumn(name = "veiculo_id")
	private Veiculo veiculo;
	
	@Column
	private String nomePessoa;
	
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	
	@OneToMany(mappedBy = "servico", cascade = CascadeType.ALL)
	private List<ItemServico> itens;
	
	public List<ItemServico> getItens() {
		if(this.itens == null) {
			this.itens = new ArrayList<>();
		}
		return itens;
	}
}
