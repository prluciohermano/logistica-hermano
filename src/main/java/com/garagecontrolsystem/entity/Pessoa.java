package com.garagecontrolsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "pessoa")
public class Pessoa {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome", length = 100)
	private String nome;
	
	@Column(name = "cpf", length = 11)
	private String cpf;
	
	@Column(name = "rg", length = 100)
	private String rg;
	
	@Column(name = "sexo", length = 100)
	private String sexo;
	
	@Column(name = "cep", length = 100)
	private String cep;
	
	@Column(name = "rua", length = 100)
	private String rua;
	
	@Column(name = "numero", length = 100)
	private String numero;
	
	@Column(name = "bairro", length = 100)
	private String bairro;
	
	@Column(name = "comp", length = 100)
	private String comp;
	
	@Column(name = "cidade", length = 100)
	private String cidade;
	
	@Column(name = "uf", length = 100)
	private String uf;
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY )
//	private Set<Pedido> pedidos;
	
}
