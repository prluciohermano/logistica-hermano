package com.garagecontrolsystem.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "TB_PESSOA")
public class PessoaModel implements Serializable {
	public TipoPessoaModel getTipospessoa() {
		return tipospessoa;
	}

	public void setTipospessoa(TipoPessoaModel tipospessoa) {
		this.tipospessoa = tipospessoa;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID pessoaId;
	
	@NotBlank(message = "Campo Nome não pode ser vazio ou nulo!")
	@Column(name = "nome", length = 100)
	private String nome;
	
	@NotBlank(message = "Campo CPF não pode ser vazio ou nulo!")
	@Column(name = "cpf", length = 11)
	private String cpf;
	
	@NotBlank(message = "Campo Registro Geral não pode ser vazio ou nulo!")
	@Column(name = "rg", length = 100)
	private String rg;
	
	@NotBlank(message = "Campo Sexo não pode ser vazio ou nulo!")
	@Column(name = "sexo", length = 100)
	private String sexo;
	
	@NotBlank(message = "Campo CEP não pode ser vazio ou nulo!")
	@Column(name = "cep", length = 100)
	private String cep;
	
	@NotBlank(message = "Campo Rua não pode ser vazio ou nulo!")
	@Column(name = "rua", length = 100)
	private String rua;
	
	@NotBlank(message = "Campo Número não pode ser vazio ou nulo!")
	@Column(name = "numero", length = 100)
	private String numero;
	
	@NotBlank(message = "Campo Bairro não pode ser vazio ou nulo!")
	@Column(name = "bairro", length = 100)
	private String bairro;
	
	@Column(name = "comp", length = 100)
	private String comp;
	
	@NotBlank(message = "Campo Cidade não pode ser vazio ou nulo!")
	@Column(name = "cidade", length = 100)
	private String cidade;
	
	@NotBlank(message = "Campo Estado não pode ser vazio ou nulo!")
	@Column(name = "uf", length = 100)
	private String uf;
		
	@OneToMany(mappedBy = "pessoa", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<TelefoneModel> telefones;
	
	@ManyToOne(cascade = CascadeType.PERSIST) /* a variável tipospessoa está linkado com o atributo th:field="*{tipospessoa}" da página listapessoas*/
	private TipoPessoaModel tipospessoa;
	
	@Enumerated(EnumType.STRING)
	private Cargo cargo;
	
	
	public List<TelefoneModel> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<TelefoneModel> telefones) {
		this.telefones = telefones;
	}

	public void setTipoPessoaModel(TipoPessoaModel tipospessoa) {
		this.tipospessoa = tipospessoa;
	}
	

	public TipoPessoaModel getTipoPessoaModel() {
		return tipospessoa;
		
	}
	
}
