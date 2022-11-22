package com.garagecontrolsystem.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_produto")
public class Produto {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 80)
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nomeProduto;
	
	@Column(length = 150)
	@NotEmpty(message = "{campo.descricao.obrigatorio}")
	private String descricao;
	
	@Column(name = "preco_unitario")
	@NotNull(message = "{campo.preco.obrigatorio}")
	private BigDecimal preco;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private List<ItemServico> itens;
	
	public List<ItemServico> getItens() {
		if(this.itens == null) {
			this.itens = new ArrayList<>();
		}
		return itens;
	}
}
