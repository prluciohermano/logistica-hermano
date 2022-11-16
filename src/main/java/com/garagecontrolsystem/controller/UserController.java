//package com.garagecontrolsystem.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.garagecontrolsystem.dto.UserRoleDTO;
//import com.garagecontrolsystem.entity.User;
//import com.garagecontrolsystem.service.RoleUserService;
//import com.garagecontrolsystem.service.UserService;
//
//@CrossOrigin("*")
////@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//@RestController
//@RequestMapping("/users")
//public class UserController {
//	
//	@Autowired
//	UserService userService;
//	
//	@Autowired
//	RoleUserService roleUserService;
//	
//	@PreAuthorize("hasRole('ADMIN')")
//	@PostMapping("/create")
//	@ResponseStatus(HttpStatus.CREATED)
//	public User create(@RequestBody User user) {
//		return userService.execute(user);
//	}
//	
//	@PreAuthorize("hasRole('ADMIN')")
//	@PostMapping("/role")
//	@ResponseStatus(HttpStatus.CREATED)
//	public User role(@RequestBody UserRoleDTO userRoleDTO) {
//		return roleUserService.execute(userRoleDTO);
//	}
//	
//	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//	@PostMapping("/login")
//	@ResponseStatus(HttpStatus.OK)
//	public User login(@RequestBody User user) {
//		return userService.logar(user);
//	}
//
//	@PreAuthorize("hasRole('ADMIN')")
//	@PostMapping("/salvar")
//	@ResponseStatus(HttpStatus.CREATED)
//	public User salvar(@RequestBody User user) {
//		return userService.execute(user);
//	}
//	
//}
