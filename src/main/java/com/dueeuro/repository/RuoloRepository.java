package com.dueeuro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dueeuro.entities.Ruolo;

public interface RuoloRepository extends JpaRepository<Ruolo, Long>{
	public Ruolo findRuoloByNomeRuolo(String nomeRuolo);

}
