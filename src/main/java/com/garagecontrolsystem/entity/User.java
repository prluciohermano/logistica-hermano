//package com.garagecontrolsystem.entity;
//
//import java.util.List;
//import java.util.UUID;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@Entity
//@Table(name = "TB_USER")
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class User {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private UUID id;
//
//	private String name;
//	
//	private String username;
//	
//	private String password;
//	
//	private boolean admin;
//
//	
//	@ManyToMany
//	private List<Role> roles;
//
//
//	
//}
