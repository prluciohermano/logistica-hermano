package com.garagecontrolsystem.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.garagecontrolsystem.enums.Cargo;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "TB_PESSOA")
public class Pessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
		
	@OneToMany(mappedBy = "pessoa")
	private List<TelefoneModel> telefones;
	
	
	@ManyToOne 
	@JoinColumn(name = "tipospessoa_id")
	private TipoPessoaModel tipospessoa;
		
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataNasci;
	
	
	@NotBlank(message = "Campo e-mail não pode ser vazio ou nulo!")
	@Email(message = "Campo e-mail não pode ser vazio ou nulo!")
	@Column(name = "email", length = 50)
	private String email;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pessoa")
	private List<Veiculo> produtos;
	
	@NotNull(message = "Campo Cargo não pode ser vazio ou nulo!")
	@Enumerated(EnumType.STRING)
	private Cargo cargo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pessoa")
    private List<Servico> servico;
}

