package com.loteria.projeto.lotteryapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loteria.projeto.lotteryapi.model.Numero;
import com.loteria.projeto.lotteryapi.model.Pessoa;
import com.loteria.projeto.lotteryapi.repository.NumeroRepository;
import com.loteria.projeto.lotteryapi.repository.PessoaRepository;
import com.loteria.projeto.lotteryapi.service.exception.EmailJaExistenteException;
import com.loteria.projeto.lotteryapi.service.exception.NumeroExistenteException;
import com.loteria.projeto.lotteryapi.service.exception.PessoaInexistenteException;
import com.loteria.projeto.lotteryapi.service.util.GeradorDeNumeros;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private NumeroRepository numeroRepository;
	
	@Autowired
	private GeradorDeNumeros geradorDeNumeros;
	
	public Pessoa cadastraPessoa(Pessoa pessoa) {
		
		String emailPessoa = pessoa.getEmail();
		boolean existeEmailPessoa = verificarExistenciaDeEmail(emailPessoa);
		
		if (existeEmailPessoa) {
			throw new EmailJaExistenteException("Email ja cadastrado.");
		}
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);

		return pessoaSalva;
	}

	public Pessoa mostraPessoa(Long id) {
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		
        if (pessoa.isEmpty()) {
			throw new PessoaInexistenteException();
		}
		
		return pessoa.get();
	}

	public List<Numero> consultandoApostas(String email) {
		
		Optional<Pessoa> pessoaSalva = verificarPessoaPorEmail(email);

		List<Numero> numeros = buscarApostas(pessoaSalva.get());
		
		return numeros;
	}
	
	public Numero pessoaApostando(String email) {

        Optional<Pessoa> pessoaSalva = verificarPessoaPorEmail(email);

        Pessoa pessoa = pessoaSalva.get();
        
        List<Numero> numerosSalvos = new ArrayList<Numero>();
        numerosSalvos = buscarApostas(pessoa);
        
        String numeroGerado = ""; 
        numeroGerado = geradorDeNumeros.gerarNumeros();
        
        Numero numeroTemp = new Numero();
        numeroTemp.setNumero(numeroGerado);
        
        boolean existeNumero = false;
        
        if (!numerosSalvos.isEmpty()) {
        	existeNumero = numerosSalvos.contains(numeroTemp);
		}
        
        if (existeNumero) {
			throw new NumeroExistenteException();
		}
        
        Integer tamanhoOrdem = pessoaSalva.get().getNumeros().size();
        Long ordem = tamanhoOrdem.longValue();
        ordem++;
        
        Numero numero = new Numero();
        numero.setNumero(numeroGerado);
        numero.setOrdem(ordem);
        numero.setPessoa(pessoa);
        numeroRepository.save(numero);
  
        numerosSalvos.add(numero);

        pessoa.setNumeros(numerosSalvos);
        pessoaRepository.save(pessoa);
        
        return numero;
    }

	private List<Numero> buscarApostas(Pessoa pessoa) {
		
		List<Numero> numeros = new  ArrayList<>();
		
		numeros	= pessoa.getNumeros();
		
		return numeros;
	}
	
	private Optional<Pessoa> verificarPessoaPorEmail(String email) {
		Optional<Pessoa> pessoaSalva = pessoaRepository.findByEmail(email);
		
		if (pessoaSalva.isEmpty()) {
			throw new PessoaInexistenteException();
		}
		return pessoaSalva;
	}
	
	private boolean verificarExistenciaDeEmail(String email) {
		
		Optional<Pessoa> pessoaSalva = pessoaRepository.findByEmail(email);
		
		if (pessoaSalva.isEmpty()) {
			return false;
		}
		return true;
	}

}
