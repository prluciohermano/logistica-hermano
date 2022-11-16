//package com.garagecontrolsystem.service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.garagecontrolsystem.dto.UserRoleDTO;
//import com.garagecontrolsystem.entity.Role;
//import com.garagecontrolsystem.entity.User;
//import com.garagecontrolsystem.repository.UserRepository;
//
//@Service
//public class RoleUserService {
//
//	@Autowired
//	UserRepository userRepository;
//	
//	public User execute(UserRoleDTO userRoleDTO) {
//		
//		Optional<User> userExists = userRepository.findById(userRoleDTO.getIdUser());
//		List<Role> roles = new ArrayList<>();
//		
//		if (userExists.isEmpty()) {
//			throw new Error("Usuário não existe!");
//		}
//		
//		roles = userRoleDTO.getIdsRoles().stream().map(role -> {
//			return new Role(role);
//		}).collect(Collectors.toList());
//		
//		User user = userExists.get();
//		user.setRoles(roles);
//		userRepository.save(user);
//		
//		
//		return user;
//			
//	}
//}
