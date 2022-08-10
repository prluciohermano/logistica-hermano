package com.garagecontrolsystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.garagecontrolsystem.entity.PessoaModel;
import com.garagecontrolsystem.entity.TelefoneModel;
import com.garagecontrolsystem.service.PessoaService;
import com.garagecontrolsystem.service.TelefoneService;
import com.garagecontrolsystem.service.TipoPessoaService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private TelefoneService telefoneService;
	
	@Autowired
	private ReportUtil reportUtil;
	
	@Autowired
	private TipoPessoaService tipoPessoaService;
		
	
	@RequestMapping(method = RequestMethod.GET, value = "/listapessoas")
	public ModelAndView inicio() {
		ModelAndView modelAndView = new ModelAndView("restrito/listapessoas");
		modelAndView.addObject("pessoaobj", new PessoaModel());
		modelAndView.addObject("pessoas", pessoaService.findAll());
		modelAndView.addObject("tipos", tipoPessoaService.findAll()); /* "tipos" é o conjunto de tipos que																vai carregar no option */
		return modelAndView;
	}	

	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST,
				    value = "**/salvarpessoa", consumes = {"multipart/form-data"})
	public ModelAndView salvar(@Valid PessoaModel pessoa,
								BindingResult bindingResult, final MultipartFile file) throws IOException {
		
		pessoa.setTelefones((List<TelefoneModel>) telefoneService.findByFoneById(pessoa.getPessoaId()));
		
		if(bindingResult.hasErrors()) {
			ModelAndView modelandView = new ModelAndView("restrito/listapessoas");
			modelandView.addObject("pessoas", pessoaService.findAll()); /* pessoas entre aspas vai para o front ${pessoas}*/
			modelandView.addObject("pessoaobj", pessoa);/* Envia o objeto vazio */
			
			List<String> msg = new ArrayList<>();
			for(ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage());
			}
			modelandView.addObject("msg", msg);
			modelandView.addObject("tipos", tipoPessoaService.findAll());
			return modelandView;
		}
		
		if (file.getSize() > 0) { /* Cadastrando um novo com documento */
			pessoa.setDocumento(file.getBytes());
			pessoa.setTipoFileDocumento(file.getContentType());
			pessoa.setNomeFileDocumento(file.getOriginalFilename());
		} else {
			if(pessoa.getPessoaId() != null ) { /* Se tiver editando com o documento */
				PessoaModel pessoaTemp = pessoaService.findById(pessoa.getPessoaId()).get();
				
				pessoa.setDocumento(pessoaTemp.getDocumento());
				pessoa.setTipoFileDocumento(pessoaTemp.getTipoFileDocumento());
				pessoa.setNomeFileDocumento(pessoaTemp.getNomeFileDocumento());
			}
		}
		
		pessoaService.save(pessoa);
		
		ModelAndView andView = new ModelAndView("restrito/listapessoas");
		andView.addObject("pessoas", pessoaService.findAll()); /* pessoas entre aspas vai para o front ${pessoas}*/
		andView.addObject("pessoaobj", new PessoaModel());/* Envia o objeto vazio */
		return andView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "restrito/listapessoas")
	public ModelAndView pessoas() {
		ModelAndView andView = new ModelAndView("restrito/listapessoas");
		andView.addObject("pessoas", pessoaService.findAll()); /* pessoas entre aspas vai para o front ${pessoas}*/
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
								@RequestParam("pesqsexo") String pesqsexo,
								@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
		
		Page<PessoaModel> pessoas;
		
		if(pesqsexo != null && !pesqsexo.isEmpty()) {
			pessoas = pessoaService.findPessoaBySexoPage(nameBusca, pesqsexo, pageable);
		} else {
			pessoas = pessoaService.findPessoaByNamePage(nameBusca, pageable);
		}
		
		ModelAndView andView = new ModelAndView("restrito/listapessoas");
		andView.addObject("pessoas", pessoas); /* pessoas entre aspas vai para o front ${pessoas}*/
		andView.addObject("pessoaobj", new PessoaModel());/* Envia o objeto vazio */
		andView.addObject("nameBusca", nameBusca);
		andView.addObject("pesqsexo", pesqsexo);
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
		
		byte[] pdf = reportUtil.gerarRelatorio(pessoas, "pessoa", request.getServletContext());
			response.setContentLength(pdf.length);
			response.setContentType("application/octet-stream");
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", "relatorio.pdf");
			response.setHeader(headerKey, headerValue);
			response.getOutputStream().write(pdf);
		
	}
	
	@GetMapping("/pessoaspag")
	public ModelAndView carregaPessoaPorPaginacao(@PageableDefault(size = 10) Pageable pageable,
													ModelAndView model) {	
		Page<PessoaModel> pagePessoa = pessoaService.findAll(pageable);
		model.addObject("pessoas", pagePessoa);
		model.addObject("pessoaobj", new PessoaModel());
		model.setViewName("restrito/listapessoas");

		return model;
	}
	
	
	@GetMapping("**/downloadDocs/{pessoaId}") /* MÓDULO - 3º - Spring Boot MVC + Thymeleaf + JPA + banco PostgreSQL */
	public void downloadDocs(@PathVariable("pessoaId") UUID pessoaId,
			HttpServletResponse response) throws IOException {
		
		Optional<PessoaModel> pessoa = pessoaService.findById(pessoaId);
		if(pessoa.get().getDocumento() != null) {
			response.setContentLength(pessoa.get().getDocumento().length);
			
			response.setContentType(pessoa.get().getTipoFileDocumento());
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", pessoa.get().getNomeFileDocumento());
			response.setHeader(headerKey, headerValue);
			response.getOutputStream().write(pessoa.get().getDocumento());
		}
	}
	
}
