package com.garagecontrolsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	public void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
					.authorizeHttpRequests()
					.antMatchers(HttpMethod.POST, "/usuarios/login").permitAll()
					.antMatchers(HttpMethod.GET, "/").permitAll()
					.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
					.defaultSuccessUrl("/pessoas")
					.and().cors();
		
		http.addFilterBefore(new SecurityFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
	
	
	
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("lucio")
			.password(passwordEncoder().encode("2201")).authorities("USER");
	}


    @Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}



	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
        		"/templates/**",
        		"/estilo/**",
        		"/css/**", "/js/**", "/images/**",
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

}
