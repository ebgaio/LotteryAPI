package com.loteria.projeto.lotteryapi.service;

import com.loteria.projeto.lotteryapi.model.Numero;
import com.loteria.projeto.lotteryapi.repository.NumeroRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumeroService {
	
	@Autowired
	private NumeroRepository numeroRepository;

	public List<Numero> mostrandoNumeros() {

		List<Numero> numerosSalvos = numeroRepository.findAll();
		
		return numerosSalvos;
	}

}
