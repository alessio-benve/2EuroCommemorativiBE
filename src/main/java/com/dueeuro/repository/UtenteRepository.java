package com.dueeuro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dueeuro.entities.Utente;

public interface UtenteRepository extends JpaRepository<Utente,Long> {
	
	public Utente findByUsername(String username);
	public Utente findUtenteByEmail(String email);
}
