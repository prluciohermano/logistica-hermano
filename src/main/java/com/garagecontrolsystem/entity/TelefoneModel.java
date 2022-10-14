package com.garagecontrolsystem.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "TB_TELEFONE")
public class TelefoneModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@PrimaryKeyJoinColumn
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "numero", length = 20)
	private String numero;
	
	@Column(name = "tipo", length = 20)
	private String tipo;
	
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "pessoa_id")
	private PessoaModel pessoa;
}
