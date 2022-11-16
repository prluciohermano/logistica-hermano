package com.garagecontrolsystem.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.garagecontrolsystem.dto.CredenciaisDTO;
import com.garagecontrolsystem.dto.TokenDTO;
import com.garagecontrolsystem.entity.Usuario;
import com.garagecontrolsystem.exception.SenhaInvalidaException;
import com.garagecontrolsystem.security.jwt.JwtService;
import com.garagecontrolsystem.service.impl.UsuarioServiceImpl;


import lombok.RequiredArgsConstructor;


@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	
    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
	
	
	@PostMapping("/criar")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar(@RequestBody @Valid Usuario usuario) {
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		return usuarioService.salvar(usuario);
	}
	
    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try{
            Usuario usuario = Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha()).build();
            @SuppressWarnings("unused")
			UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
	
//	
//	
//		
//	private UsuarioService usuarioService;	
//	
//	public UsuarioController(UsuarioService usuarioService) {
//		this.usuarioService = usuarioService;
//		
//	}
//	
//	@GetMapping
//	public ResponseEntity<List<Usuario>> listar(){
//		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listaTodos());
//	}
//	
//	@PostMapping
//	public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario) {
//		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuario));
//		
//	}
//	
//	@PutMapping
//	public ResponseEntity<Usuario> editarUsuario(@Valid @RequestBody Usuario usuario) {
//		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.editarUsuario(usuario));
//	}
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Usuario> deletarUsuario(@PathVariable Long id) {
//		usuarioService.deleteById(id);
//		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//	}
//	
//	@PostMapping("/login")
//	public ResponseEntity<Token> logar(@Valid @RequestBody UsuarioDTO usuario) {
//		Token token = usuarioService.gerarToken(usuario);
//		
//		if(token != null) {
//			return ResponseEntity.ok(token);
//		}
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//	}
//	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
//		Map<String, String> errors = new HashMap<>();
//		
//		ex.getBindingResult().getAllErrors().forEach((error) -> {
//			String fieldName = ((FieldError) error).getField();
//			String errorMessage = error.getDefaultMessage();
//			errors.put(fieldName, errorMessage);
//			
//		});
//		return errors;
//	}
}
