//package com.garagecontrolsystem.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//
//@AllArgsConstructor
//@Builder
//@Data
//@Entity
//@Table(name = "usuario")
//public class Usuario {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	@NotBlank(message = "O nome é obrigatório")
//	@Column(name = "nome", length = 100, nullable = false)
//	private String nome;
//	
//	@NotBlank(message = "O login é obrigatório")
//	@Column(name = "login", length = 20, nullable = false)
//	private String login;
//	
//	@Email(message = "Insira um e-mail válido")
//	@NotBlank(message = "O e-mail é obrigatório")
//	@Column(name = "email", length = 50, nullable = false)
//	private String email;
//	
//	@NotBlank(message = "A senha é obrigatória")
//	@Column(name = "senha", length = 200, columnDefinition = "TEXT", nullable = false)
//	private String senha;
//	
//	@Column(name = "admin")
//	private boolean admin;
//
//}
