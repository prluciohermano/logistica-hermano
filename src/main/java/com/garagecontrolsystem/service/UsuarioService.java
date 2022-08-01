//package com.garagecontrolsystem.service;
//
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.garagecontrolsystem.dto.UsuarioDTO;
//import com.garagecontrolsystem.entity.Usuario;
//import com.garagecontrolsystem.repository.UsuarioRepository;
//import com.garagecontrolsystem.security.Token;
//import com.garagecontrolsystem.security.TokenUtil;
//
//@Service
//public class UsuarioService {
//	
//	private UsuarioRepository usuarioRepository;
//	private PasswordEncoder passwordEncoder;
//	
//	public UsuarioService(UsuarioRepository usuarioRepository) {
//		this.usuarioRepository = usuarioRepository;
//		this.passwordEncoder = new BCryptPasswordEncoder();
//	}
//
//	
//	public List<Usuario> listaTodos() {
//		return usuarioRepository.findAll();
//	}
//
//	public Usuario salvarUsuario(Usuario usuario) {
//		String encoder = this.passwordEncoder.encode(usuario.getSenha());
//		usuario.setSenha(encoder);
//		return usuarioRepository.save(usuario);
//	}
//
//	public Usuario editarUsuario(Usuario usuario) {
//		String encoder = this.passwordEncoder.encode(usuario.getSenha());
//		usuario.setSenha(encoder);
//		return usuarioRepository.save(usuario);
//	}
//
//	public ResponseEntity<Usuario> deleteById(Long id) {
//		if(usuarioRepository.existsById(id)) {
//			usuarioRepository.deleteById(id);
//			return ResponseEntity.noContent().build();
//		}
//		return ResponseEntity.notFound().build();
//	}
//
//
//	@SuppressWarnings("deprecation")
//	public Boolean validarSenha(Usuario usuario) {
//		String senha = usuarioRepository.getById(usuario.getId()).getSenha();
//		Boolean valid = passwordEncoder.matches(usuario.getSenha(), senha);
//		return valid;
//	}
//
//
//	public Token gerarToken(@Valid UsuarioDTO usuario) {
//		Usuario user = usuarioRepository.findByNomeOrEmail(usuario.getLogin(), usuario.getEmail());
//		if(user != null) {
//			Boolean valid = passwordEncoder.matches(usuario.getSenha(), user.getSenha());
//			if(valid) {
//				return new Token(TokenUtil.createToken(user));
//			}
//		}
//		
//		return null;
//	}
//}
