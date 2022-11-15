package com.garagecontrolsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.garagecontrolsystem.entity.User;
import com.garagecontrolsystem.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User existsUser = userRepository.findByUsernameFetchRoles(username);
		
		System.out.println(username);
		System.out.println(existsUser);
		
		if (existsUser == null) {
			throw new Error("Usuário ou senha não existem!");
		}
		return UserPrincipal.create(existsUser);
	}

}
