package com.garagecontrolsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfigSecurityV2 {
	
	@Bean
	public  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
					.httpBasic()
					.and()
					.authorizeHttpRequests()
					.antMatchers("/login").permitAll()
					.antMatchers("/index").permitAll()
					.antMatchers("/restrito/**").authenticated()
					.anyRequest().authenticated().and().formLogin().loginPage("/login")
					.defaultSuccessUrl("/restrito/arearestrita")
					.and().cors();
		
		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
