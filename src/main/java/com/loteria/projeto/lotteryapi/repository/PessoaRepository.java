package com.loteria.projeto.lotteryapi.repository;

import com.loteria.projeto.lotteryapi.model.Pessoa;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
        @Override
	public Optional<Pessoa> findById(Long id);
	
	public Optional<Pessoa> findByEmail(String email);

}
