//package com.garagecontrolsystem.service;
//
//import javax.transaction.Transactional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.garagecontrolsystem.entity.Usuario;
//import com.garagecontrolsystem.repository.UsuarioRepository;
//import com.garagecontrolsystem.service.exceptions.ObjectNotFoundException;
//import com.garagecontrolsystem.service.impl.UsuarioServiceImpl;
//
//@Service
//public class UsuarioService {
//	
//	@Autowired
//	UsuarioServiceImpl usuarioService;
//    
//	@Autowired
//	PasswordEncoder passwordEncoder;
//	
//	@Autowired
//	UsuarioRepository usuarioRepository;
//	
//
//	
//	
//	private BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Transactional
//	public Usuario salvar(Usuario usuario) {
//		Usuario existsUser = usuarioRepository.findByLogin(usuario.getLogin());
//		
//		System.out.println(existsUser);
//		
//		if (existsUser != null) {
//			throw new ObjectNotFoundException("Usuário Já existe!");
//		}
//		
//		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
//		usuario.setSenha(senhaCriptografada);
//		usuario.setSenha(passwordEncoder().encode(usuario.getSenha()));
//		Usuario createdUser = usuarioRepository.save(usuario);
//		return usuario;
//		
//	}
	 

//	public Usuario logar(Usuario usuario) {
//		
//		Usuario existsUser = usuarioRepository.login(usuario.getLogin(), usuario.getSenha());
//		
//		System.out.println(existsUser + "Entrou aqui");
//		
//		return usuarioRepository.login(usuario.getLogin(), usuario.getSenha());
//	}
//	
	
	
	
//	
//	public User logar(User user) {
//		
//		
//		User existsUser = userRepository.findByUsername(user.getUsername());
//		User existsPass = userRepository.findByPassword(user.getPassword());
//		
//		
////		if (existsPass == null && existsUser == null) {
////			
////			throw new Error("Usuário ou senha não existem!");
////		}
//		
//		System.out.println(existsUser);
//		System.out.println(existsPass);
//		
//		//User createdUser = userRepository.save(user);
//		
//		return null;
//	
//	}
//
//	public Optional<User> buscarPorEmail(java.lang.String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
