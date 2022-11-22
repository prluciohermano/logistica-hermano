package com.garagecontrolsystem.dto;

import java.io.Serializable;

import com.garagecontrolsystem.entity.Usuario;

import lombok.Data;

@Data
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nomeUsuario;
	

	public UsuarioDTO(Usuario usuario) {
		this.nomeUsuario = usuario.getNome();
		this.id = usuario.getId();
	}
    
}
