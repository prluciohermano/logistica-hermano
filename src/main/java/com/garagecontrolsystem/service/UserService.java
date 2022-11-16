//package com.garagecontrolsystem.service;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.garagecontrolsystem.entity.User;
//import com.garagecontrolsystem.repository.UserRepository;
//
//@Service
//public class UserService {
//	
//	@Autowired
//	UserRepository userRepository;
//	private Object String;
//	
//	private BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	public User execute(User user) {
//		
//		User existsUser = userRepository.findByUsername(user.getUsername());
//		
//		if (existsUser != null) {
//			throw new Error("Usuário Já existe!");
//		}
//		
//		user.setPassword(passwordEncoder().encode(user.getPassword()));
//		
//		User createdUser = userRepository.save(user);
//		
//		return createdUser;
//	
//	}
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
