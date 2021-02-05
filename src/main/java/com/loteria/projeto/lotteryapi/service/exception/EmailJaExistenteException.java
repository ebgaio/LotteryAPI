package com.loteria.projeto.lotteryapi.service.exception;

public class EmailJaExistenteException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public EmailJaExistenteException() {
	}
	
	public EmailJaExistenteException(String message) {
		super(message);
	}
}
