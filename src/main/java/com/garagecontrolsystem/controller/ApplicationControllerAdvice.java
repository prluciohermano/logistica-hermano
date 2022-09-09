package com.garagecontrolsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.garagecontrolsystem.service.exceptions.ObjectNotFoundException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleObjectNotFoundException(ObjectNotFoundException ex) {
		String mensagemErro = ex.getMessage();
		return new ApiErrors(mensagemErro);
		
	}

}
