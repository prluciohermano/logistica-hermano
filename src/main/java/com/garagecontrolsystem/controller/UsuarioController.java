package com.garagecontrolsystem.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garagecontrolsystem.dto.UsuarioDTO;
import com.garagecontrolsystem.entity.Usuario;
import com.garagecontrolsystem.repository.UsuarioRepository;
import com.garagecontrolsystem.service.ImplementacaoUserDetailsService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	
	@Autowired 
	private final UsuarioRepository usuarioRepository;
	
	@Autowired
	private final ImplementacaoUserDetailsService usuarioService;
	
	private final PasswordEncoder passEncoder;
	

	/* Serviço RESTful */
	
	@GetMapping(value = "/{id}", produces = "application/json")
	@CacheEvict(value="cacheuser", allEntries = true)
	@CachePut("cacheuser")
	public ResponseEntity<UsuarioDTO> init(@PathVariable(value = "id")  Long id) {
		Optional<Usuario> usuario =  usuarioRepository.findById(id);
		return new ResponseEntity<UsuarioDTO>(new UsuarioDTO(usuario.get()), HttpStatus.OK);
	}
	
	/*Vamos supor que o carregamento de usuário seja um processo lento
	 * e queremos controlar ele com cache para agilizar o processo*/
	
	@GetMapping(value = "", produces = "application/json")
	@CacheEvict(value="cacheusuarios", allEntries = true)
	@CachePut("cacheusuarios")
	@ApiOperation("Obter nome dos usuarios")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pessoa encontrada"),
							@ApiResponse(code = 403, message = "Acesso não autorizado"),
							@ApiResponse(code = 404, message = "Pessoa não encontrada")})
	public ResponseEntity<List<UsuarioDTO>> usuarios () throws InterruptedException{
		List<Usuario> list = usuarioRepository.findAll();
		List<UsuarioDTO> listDTO = list.stream()
				.map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	
	@PostMapping(value = "", produces = "application/json") // -------------------------- Cadastrar
	public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid Usuario usuario) {
		String senhacriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senhacriptografada);
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
		
	}
	
	@PutMapping(value = "", produces = "application/json") // ----------------------------- Atualizar
	public ResponseEntity<UsuarioDTO> atualizar(@RequestBody Usuario usuario) {
		Usuario userTemporario = usuarioRepository.findUserByLogin(usuario.getLogin());
		
		if (!userTemporario.getSenha().equals(usuario.getSenha())) { /*Senhas diferentes*/
			String senhacriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
			usuario.setSenha(senhacriptografada);
		}
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		return new ResponseEntity<UsuarioDTO>(new UsuarioDTO(usuarioSalvo), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}") //---------------------------------------------- Deletar ---
	public ResponseEntity<Object> deleteUsuario(@PathVariable Long id){
		
		System.out.println(id);
		
		Optional<Usuario> obj = usuarioService.findById(id);
		if(!obj.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuário não encontrado!");
		}
		usuarioService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Usuário excluído com sucesso!");		
	}
}	
	
