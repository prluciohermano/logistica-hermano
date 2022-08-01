package com.garagecontrolsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SuppressWarnings("deprecation")
//@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	final UserDetailsServiceImpl userDetailsService;
	
	public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
					.httpBasic()
					.and()
					.authorizeHttpRequests()
					.antMatchers(HttpMethod.GET, "/**").permitAll()
					.antMatchers(HttpMethod.POST, "/").hasRole("USER")
					.antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
					.anyRequest().authenticated();	
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());	
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
