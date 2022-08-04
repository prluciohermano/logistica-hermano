package com.garagecontrolsystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.garagecontrolsystem.entity.PessoaModel;
import com.garagecontrolsystem.entity.TelefoneModel;
import com.garagecontrolsystem.entity.TipoPessoaModel;
import com.garagecontrolsystem.service.PessoaService;
import com.garagecontrolsystem.service.TelefoneService;
import com.garagecontrolsystem.service.TipoPessoaService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping
public class IndexController {
	
	@Autowired
	PessoaService pessoaService;
	
	@Autowired
	TelefoneService telefoneService;
	
	@Autowired
	private ReportUtil reportUtil;
	
	@Autowired
	private TipoPessoaService tipoPessoaService;
	
	final TipoPessoaModel tipoPessoaModel = new TipoPessoaModel();
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/listapessoas")
	public ModelAndView inicio() {
		ModelAndView modelAndView = new ModelAndView("restrito/listapessoas");
		modelAndView.addObject("pessoaobj", new PessoaModel());
		Iterable<PessoaModel> pessoasIt = pessoaService.findAll();
		modelAndView.addObject("pessoas", pessoasIt);
		modelAndView.addObject("tipos", tipoPessoaService.findAll()); /* "tipos" é o conjunto de tipos que
																		vai carregar no option */
		return modelAndView;
	}	

	
	@SuppressWarnings("unchecked")
	@PostMapping("**/salvarpessoa")
	public ModelAndView salvar(@Valid PessoaModel pessoa, BindingResult bindingResult) {
		
		pessoa.setTelefones((List<TelefoneModel>) telefoneService.findByFoneById(pessoa.getPessoaId()));
		
		if(bindingResult.hasErrors()) {
			ModelAndView modelandView = new ModelAndView("restrito/listapessoas");
			Iterable<PessoaModel> pessoasIt = pessoaService.findAll();
			modelandView.addObject("pessoas", pessoasIt); /* pessoas entre aspas vai para o front ${pessoas}*/
			modelandView.addObject("pessoaobj", pessoa);/* Envia o objeto vazio */
			
			List<String> msg = new ArrayList<>();
			for(ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage());
			}
			modelandView.addObject("msg", msg);
			modelandView.addObject("tipos", tipoPessoaService.findAll());
			return modelandView;
		}
		
		pessoaService.save(pessoa);
		
		ModelAndView andView = new ModelAndView("restrito/listapessoas");
		Iterable<PessoaModel> pessoasIt = pessoaService.findAll();
		andView.addObject("pessoas", pessoasIt); /* pessoas entre aspas vai para o front ${pessoas}*/
		andView.addObject("pessoaobj", new PessoaModel());/* Envia o objeto vazio */
		return andView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "restrito/listapessoas")
	public ModelAndView pessoas() {
		ModelAndView andView = new ModelAndView("restrito/listapessoas");
		Iterable<PessoaModel> pessoasIt = pessoaService.findAll();
		andView.addObject("pessoas", pessoasIt); /* pessoas entre aspas vai para o front ${pessoas}*/
		andView.addObject("pessoaobj", new PessoaModel());
		andView.addObject("tipos", tipoPessoaService.findAll());
		return andView;
	}
	
	@GetMapping("**/editarpessoa/{idpessoa}")
	public ModelAndView editar(@PathVariable("idpessoa") UUID idpessoa) {
		
		Optional<PessoaModel> pessoa = pessoaService.findById(idpessoa);
		
		ModelAndView modelAndView = new ModelAndView("restrito/listapessoas");
		modelAndView.addObject("pessoaobj", pessoa.get()); /* pessoas entre aspas vai para o front ${pessoas}*/
		modelAndView.addObject("tipos", tipoPessoaService.findAll());
		return modelAndView;
	}
	
	@GetMapping("**/removerpessoa/{idpessoa}")
	public ModelAndView remover(@PathVariable("idpessoa") UUID pessoaId) {
		pessoaService.deleteById(pessoaId);
		
		ModelAndView modelAndView = new ModelAndView("restrito/listapessoas");
		modelAndView.addObject("pessoas", pessoaService.findAll()); /* pessoas entre aspas vai para o front ${pessoas}*/
		modelAndView.addObject("pessoaobj", new PessoaModel());
		return modelAndView;
	}
	
	@PostMapping("**/pesquisarpessoa")
	/* Essa URI "/pesquisar" pessoa vem do atributo "action" do "form" e o "name" é o parâmetro do @RequestParam.*/
	public ModelAndView pesquisar(@RequestParam("nameBusca") String nameBusca,
								@RequestParam("pesqsexo") String pesqsexo) {
		
		List<PessoaModel> pessoas = new ArrayList<PessoaModel>();
		
		if(pesqsexo != null && !pesqsexo.isEmpty()) {
			pessoas = pessoaService.findPessoaByNameSexo(nameBusca, pesqsexo);
		} else {
			pessoas = pessoaService.findPessoaByName(nameBusca);
		}
		
		ModelAndView andView = new ModelAndView("restrito/listapessoas");
		andView.addObject("pessoas", pessoas); /* pessoas entre aspas vai para o front ${pessoas}*/
		andView.addObject("pessoaobj", new PessoaModel());/* Envia o objeto vazio */
		return andView;
	}
	
	@GetMapping("**/telefones/{idpessoa}")
	public ModelAndView telefones(@PathVariable("idpessoa") UUID idpessoa) {
		Optional<PessoaModel> pessoa = pessoaService.findById(idpessoa);
		
		ModelAndView modelAndView = new ModelAndView("restrito/telefones");
		modelAndView.addObject("pessoaobj", pessoa.get()); /* pessoas entre aspas vai para o front ${pessoas}*/
		modelAndView.addObject("telefones", telefoneService.findByFoneById(idpessoa));
		return modelAndView;
	}
	
	@PostMapping("**/addfonePessoa/{pessoaid}")
	/* Essa URI "/addfonePessoa/{pessoaid}" pessoa vem do atributo "action" do "form" e o "name" é o parâmetro do @RequestParam.*/
	public ModelAndView addFonePessoa(@Valid TelefoneModel telefone,
										@PathVariable("pessoaid") UUID pessoaid) {
		PessoaModel pessoa = pessoaService.findById(pessoaid).get();
		
		if(telefone != null && telefone.getNumero().isEmpty()
				| telefone.getTipo().isEmpty()) {
			ModelAndView modelAndView = new ModelAndView("restrito/telefones");
			modelAndView.addObject("pessoaobj", pessoa);
			modelAndView.addObject("telefones", telefoneService.findByFoneById(pessoaid));
			
			List<String> msg = new ArrayList<String>();
			if (telefone.getNumero().isEmpty()) {
				msg.add("Número do telefone deve ser informado");
			}
			
			if (telefone.getTipo().isEmpty()) {
				msg.add("Tipo do telefone deve ser informado");
			}
			
			modelAndView.addObject("msg", msg);
			
			return modelAndView;
		}
		
		telefone.setPessoa(pessoa);
		telefoneService.save(telefone);
		
		
		ModelAndView andView = new ModelAndView("restrito/telefones");
		andView.addObject("pessoaobj", pessoa);/* Envia o objeto pessoa */
		andView.addObject("telefones", telefoneService.findByFoneById(pessoaid));
		return andView;
	}
	
	@GetMapping("**/removertelefone/{idtelefone}")
	public ModelAndView removerTelefone(@PathVariable("idtelefone") UUID idtelefone) {
		
		PessoaModel pessoa = telefoneService.findById(idtelefone).get().getPessoa();
		
		telefoneService.deleteById(idtelefone);
		
		ModelAndView modelAndView = new ModelAndView("restrito/telefones");
		modelAndView.addObject("pessoaobj", pessoa); /* pessoas entre aspas vai para o front ${pessoas}*/
		modelAndView.addObject("telefones", telefoneService.findByFoneById(pessoa.getPessoaId()));
		return modelAndView;
	}
	
	@GetMapping("**/pesquisarpessoa")
	public void imprimePDF(@RequestParam("nameBusca") String nameBusca,
						   @RequestParam("pesqsexo") String pesqsexo,
						   HttpServletRequest request, HttpServletResponse response) throws Exception{

		List<PessoaModel> pessoas = new ArrayList<PessoaModel>();
		
		if(pesqsexo != null && !pesqsexo.isEmpty() && nameBusca != null && !nameBusca.isEmpty()) {
			pessoas = pessoaService.findPessoaByNameSexo(nameBusca, pesqsexo);
			
		} else if(nameBusca != null && !nameBusca.isEmpty()) {
			pessoas = pessoaService.findPessoaByName(nameBusca);
			
		}else if(pesqsexo != null && !pesqsexo.isEmpty()) {
			pessoas = pessoaService.findPessoaBySexo(pesqsexo);
			
		} 
		else {
			Iterable<PessoaModel> iterator = pessoaService.findAll();
			for (PessoaModel pessoa : iterator) {
				pessoas.add(pessoa);
			}
		}
		
		byte[] pdf = reportUtil.gerarRelatório(pessoas, "pessoa", request.getServletContext());
			response.setContentLength(pdf.length);
			response.setContentType("application/octet-stream");
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", "relatorio.pdf");
			response.setHeader(headerKey, headerValue);
			response.getOutputStream().write(pdf);
		
	}
	
	
}
