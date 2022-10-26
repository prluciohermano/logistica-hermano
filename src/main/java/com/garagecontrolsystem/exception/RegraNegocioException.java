package com.garagecontrolsystem.exception;

@SuppressWarnings("serial")
public class RegraNegocioException extends RuntimeException {
	/* Vem lá do pedidoServiceImpl orElseThrow */
	public RegraNegocioException(String message) {
		super(message);
	}
}
