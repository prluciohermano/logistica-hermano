package com.garagecontrolsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.garagecontrolsystem.entity.Pessoa;
import com.garagecontrolsystem.service.PessoaService;


@Controller
@RequestMapping("/api/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	
	@GetMapping /* ************************************************** Lista todas as pessoas */
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("pessoas");
		
		modelAndView.addObject("pessoas", pessoaService.findAll());
		modelAndView.addObject(new Pessoa());
		return modelAndView;
	}
	
	@PostMapping /* ************************************************** Adiciona uma pessoa */
	public String salvar(Pessoa pessoa) {
		pessoaService.save(pessoa);
		return "redirect:/pessoas";
	}
	
	@GetMapping(value = "buscarPorNome") /* *************************** Buscar pessoa por nome */	
	public ResponseEntity<List<Pessoa>> buscarPorNome(@RequestParam(name = "nome") String nome) { /* Recebe os dados para consultar */
		List<Pessoa> pessoa = pessoaService.buscarPorNome(nome.trim().toUpperCase());

		return new ResponseEntity<List<Pessoa>>(pessoa, HttpStatus.OK);

	}
}
