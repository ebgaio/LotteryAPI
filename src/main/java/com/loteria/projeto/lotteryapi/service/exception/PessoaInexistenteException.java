package com.loteria.projeto.lotteryapi.service.exception;

public class PessoaInexistenteException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public PessoaInexistenteException() {
	}
	
	public PessoaInexistenteException(String message) {
		super(message);
	}

}
