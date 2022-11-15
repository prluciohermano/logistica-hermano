package com.garagecontrolsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.garagecontrolsystem.entity.User;
import com.garagecontrolsystem.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	private BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public User execute(User user) {
		
		User existsUser = userRepository.findByUsername(user.getUsername());
		
		if (existsUser != null) {
			throw new Error("Usuário Já existe!");
		}
		
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		
		User createdUser = userRepository.save(user);
		
		return createdUser;
	
	}
}
