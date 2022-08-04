package com.garagecontrolsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
					.antMatchers(HttpMethod.GET, "/listapessoas").hasAnyRole("ADMIN")
					.antMatchers(HttpMethod.GET, "/restrito/**").hasAnyRole("ADMIN", "USER")
					.antMatchers(HttpMethod.GET, "/restrito/arearestrita").hasAnyRole("ADMIN", "USER")
					.antMatchers("/restrito/**").authenticated()
					.anyRequest().authenticated().and().formLogin().loginPage("/login")
					.defaultSuccessUrl("/restrito/arearestrita")
					.and().logout().logoutSuccessUrl("/login")
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.and().cors();
		
		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	  public void configure(WebSecurity web) throws Exception {
	      web.ignoring().antMatchers(
	      		"/estilo/**",
	      		"/css/**", "/js/**", "/images/**", "/img/**",
	              "/v2/api-docs",
	              "/configuration/ui",
	              "/swagger-resources/**",
	              "/configuration/security",
	              "/swagger-ui.html",
	              "/webjars/**");
	  }
}
