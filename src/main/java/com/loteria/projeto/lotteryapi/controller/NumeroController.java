package com.loteria.projeto.lotteryapi.controller;

import com.loteria.projeto.lotteryapi.model.Numero;
import com.loteria.projeto.lotteryapi.service.NumeroService;
import com.loteria.projeto.lotteryapi.service.exception.NumerosNaoCadastradosException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/numeros")
public class NumeroController {

	@Autowired
	private NumeroService numeroService;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/mostrandoNumeros")
	public ResponseEntity<List<Numero>> mostrandoNumeros() {
		
		List<Numero> numeros = numeroService.mostrandoNumeros();
		
		if (numeros.isEmpty()) {
			throw new NumerosNaoCadastradosException();
		}
		
		return ResponseEntity.ok(numeros);
	}
	
    // Nao existe numeros cadastrados
    @ExceptionHandler({ NumerosNaoCadastradosException.class })
    public ResponseEntity<Object> handlerNumerosNaoCadastradosException(NumerosNaoCadastradosException ex) {
		String mesageUser = messageSource.getMessage("numero.numeros-nao-cadastrados", null, LocaleContextHolder.getLocale());
		return ResponseEntity.badRequest().body(mesageUser);
    }
}
