package com.garagecontrolsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
	private String login;
	private String email;
	private String senha;
	
	
	public UsuarioDTO(String login, String email, String senha) {
		this.login = login;
		this.email = email;
		this.senha = senha;
	}
	
	

}
