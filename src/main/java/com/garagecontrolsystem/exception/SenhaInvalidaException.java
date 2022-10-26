package com.garagecontrolsystem.exception;

	@SuppressWarnings("serial")
	public class SenhaInvalidaException extends RuntimeException {
	    public SenhaInvalidaException() {
	        super("Senha inválida");
	    }
}
