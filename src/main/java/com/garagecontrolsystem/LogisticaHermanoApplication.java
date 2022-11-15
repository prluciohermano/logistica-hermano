package com.garagecontrolsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@SpringBootApplication
@Controller
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class LogisticaHermanoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogisticaHermanoApplication.class, args);

	}
	
	@RequestMapping ("/")
	public String index() {
		return "index.html";
	}

	@RequestMapping ("/login")
	public String login() {
		return "login.html";
	}
	
	@RequestMapping ("/listapessoas")
	public String listapessoas() {
		return "restrito/listapessoas.html";
	}
	
//	@RequestMapping ("/pessoas")
//	public String pessoas() {
//		return "restrito/pessoas.html";
//	}
	
	@RequestMapping ("/restrito/arearestrita")
	public String restrito() {
		return "restrito/arearestrita.html";
	}
	
	@RequestMapping ("/arearestrita")
	public String restritoo() {
		return "restrito/arearestrita.html";
	}
	
//	@RequestMapping ("/removerpessoa/**")
//	public String remover() {
//		return "restrito/listapessoas.html";
//	}
//	
	
}


//System.out.println(new BCryptPasswordEncoder().encode("senha"));
