package com.loteria.projeto.lotteryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loteria.projeto.lotteryapi.model.Numero;

public interface NumeroRepository extends JpaRepository<Numero, Long>{

}
