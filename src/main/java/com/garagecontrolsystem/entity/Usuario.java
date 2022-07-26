package com.garagecontrolsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@NotBlank(message = "O nome é obrigatório")
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@NotBlank(message = "O login é obrigatório")
	@Column(name = "login", length = 20, nullable = false)
	private String login;
	
	@Email(message = "Insira um e-mail válido")
	@NotBlank(message = "O e-mail é obrigatório")
	@Column(name = "email", length = 50, nullable = false)
	private String email;
	
	@NotBlank(message = "A senha é obrigatória")
	@Column(name = "senha", length = 200, columnDefinition = "TEXT", nullable = false)
	private String senha;

}
