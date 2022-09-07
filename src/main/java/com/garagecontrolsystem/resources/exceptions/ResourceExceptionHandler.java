package com.garagecontrolsystem.resources.exceptions;

import javax.servlet.ServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.garagecontrolsystem.service.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(
			com.garagecontrolsystem.service.exceptions.ObjectNotFoundException e, ServletRequest request){
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(
			com.garagecontrolsystem.service.exceptions.DataIntegrityViolationException ex, ServletRequest request){
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
