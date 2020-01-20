package com.dueeuro.service;

import java.util.List;

import org.springframework.http.HttpEntity;

import com.dueeuro.entities.Utente;

public interface UtenteService {

	public Utente aggiungiUtente(Utente utente);
	public Utente modificaRuoli(Utente utente);
	public Utente modificaUtente(Utente utente);
	public boolean disattivaUtente(Utente utente);
	public boolean rimuoviUtente(Utente utente);
	public List<Utente> leggiUtenti();
	public Utente trovaUtenteDaUsername(HttpEntity<String> richiesta);

}
