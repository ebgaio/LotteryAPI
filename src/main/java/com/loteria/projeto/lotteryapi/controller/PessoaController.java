package com.loteria.projeto.lotteryapi.controller;

import com.loteria.projeto.lotteryapi.model.Numero;
import com.loteria.projeto.lotteryapi.model.Pessoa;
import com.loteria.projeto.lotteryapi.service.PessoaService;
import com.loteria.projeto.lotteryapi.service.exception.EmailJaExistenteException;
import com.loteria.projeto.lotteryapi.service.exception.NumeroExistenteException;
import com.loteria.projeto.lotteryapi.service.exception.PessoaInexistenteException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/mostraPessoa/{id}")
	public ResponseEntity<Pessoa> mostraPessoa(@PathVariable Long id) {
		
		Pessoa pessoa = pessoaService.mostraPessoa(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(pessoa);
	}
	
	@GetMapping("/consultandoApostas/{email}")
	public ResponseEntity<List<Numero>> consultandoApostas(@PathVariable String email) {
		
		List<Numero> numero = pessoaService.consultandoApostas(email);
		
		return ResponseEntity.ok(numero);
	}
	
	@PostMapping("/cadastraPessoa")
	public ResponseEntity<Pessoa> cadastraPessoa(@RequestBody Pessoa pessoa) {
		
		Pessoa pessoaSalva = pessoaService.cadastraPessoa(pessoa);
		
		return ResponseEntity.ok(pessoaSalva);
	}
	
	@PostMapping("/pessoaApostando/{email}")
    public ResponseEntity<Numero> pessoaApostando(@PathVariable String email){
		
        Numero numero = pessoaService.pessoaApostando(email);
        
        return ResponseEntity.status(HttpStatus.OK).body(numero);    
    }
	
    // Envia uma mensagem quando cadastrado email ja existente
    @ExceptionHandler({ EmailJaExistenteException.class })
    public ResponseEntity<Object> handlerEmailJaExistenteException(EmailJaExistenteException ex) {
		String mesageUser = messageSource.getMessage("pessoa.email-existente", null, LocaleContextHolder.getLocale());
		return ResponseEntity.badRequest().body(mesageUser);
    }
    
    // Envia uma mensagem quando ja exste o numero gerado dessa pessoa
    @ExceptionHandler({ NumeroExistenteException.class })
    public ResponseEntity<Object> handlerNumeroExistenteException(NumeroExistenteException ex) {
		String mesageUser = messageSource.getMessage("pessoa.numero-existente", null, LocaleContextHolder.getLocale());
		return ResponseEntity.badRequest().body(mesageUser);
    }
    
    // Nao existe pessoa cadastrada
    @ExceptionHandler({ PessoaInexistenteException.class })
    public ResponseEntity<Object> handlerPessoaInexistenteException(PessoaInexistenteException ex) {
		String mesageUser = messageSource.getMessage("pessoa.pessoa-inexistente", null, LocaleContextHolder.getLocale());
		return ResponseEntity.badRequest().body(mesageUser);
    }
}
