package com.garagecontrolsystem.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "TB_VEICULO")
public class Veiculo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", length = 100)
	//@NotEmpty(message = "O campo descrição do Veículo é obrigatorio")
	private String descricao;
	
	@Column(length = 20)
	//@NotEmpty(message = "O campo placa é obrigatorio")
	private String placaCar;
	
	@Column(length = 20)
	//@NotEmpty(message = "O campo ano modelo do Veículo é obrigatorio")
	private String anoModelo;
	
	@Column(length = 20)
	//@NotEmpty(message = "O campo cor do Veículo é obrigatorio")
	private String corVeiculo;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@Temporal(TemporalType.TIMESTAMP)
	private Date dataEntrada;

	@Column(name = "preco_entrada")
	//@NotNull(message = "O campo preço de entrada do Veículo é obrigatorio")
	private BigDecimal precoEntrada;
	
  //@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
//	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
	
	 
	@OneToMany(mappedBy = "veiculo")
	@JsonIgnore
	private Set<GarageBoxModel> garageBox = new HashSet<>();



	
}
