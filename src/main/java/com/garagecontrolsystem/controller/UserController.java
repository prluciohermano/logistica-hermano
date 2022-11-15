package com.garagecontrolsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garagecontrolsystem.dto.UserRoleDTO;
import com.garagecontrolsystem.entity.User;
import com.garagecontrolsystem.service.RoleUserService;
import com.garagecontrolsystem.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleUserService roleUserService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public User create(@RequestBody User user) {
		return userService.execute(user);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/role")
	public User role(@RequestBody UserRoleDTO userRoleDTO) {
		return roleUserService.execute(userRoleDTO);
	}

}
