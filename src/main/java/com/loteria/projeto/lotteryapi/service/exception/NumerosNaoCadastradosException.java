package com.loteria.projeto.lotteryapi.service.exception;

public class NumerosNaoCadastradosException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NumerosNaoCadastradosException() {
	}
	
	public NumerosNaoCadastradosException(String message) {
		super(message);
	}
}
