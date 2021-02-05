package com.loteria.projeto.lotteryapi.service.exception;

public class NumeroExistenteException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NumeroExistenteException() {
	}
	
	public NumeroExistenteException(String message) {
		super(message);
	}
}
