////package com.garagecontrolsystem.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	
//    @Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	
//		auth
//			.inMemoryAuthentication()
//			.withUser("lucio").password("2201").roles("ADMIN", "USER")
//				.and()
//			.withUser("admin").password("2201").roles("ADMIN");
//	    }
//    
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		
//		http
//			.csrf().disable()
//			.httpBasic()
//			.and()
//			.authorizeHttpRequests()
//			.anyRequest().authenticated()
//			.and().formLogin().loginPage("/login").defaultSuccessUrl("/restrito/arearestrita")
//				.and()
//			.logout().logoutSuccessUrl("/login")
//			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//				.and()
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	
//	}
//	
//	@SuppressWarnings("deprecation")
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
//
//
//	@Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(
//        		"/templates/**",
//        		"/estilo/**",
//        		"/css/**", "/js/**", "/images/**",
//                "/v2/api-docs",
//                "/configuration/ui",
//                "/swagger-resources/**",
//                "/configuration/security",
//                "/swagger-ui.html",
//                "/webjars/**");
//    }
//
//}
