package com.dueeuro.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;

import com.dueeuro.entities.Utente;
import com.dueeuro.smtp.dto.EmailDto;

public interface UtenteService {

	public Utente aggiungiUtente(Utente utente);
	public Utente modificaRuoli(Utente utente);
	public Utente modificaUtente(Utente utente);
	public boolean disattivaUtente(Utente utente);
	public boolean rimuoviUtente(Utente utente);
	public List<Utente> leggiUtenti();
	public Utente trovaUtenteDaUsername(HttpEntity<String> richiesta);
	public boolean recuperaCredenziali(Utente utenteDaRecuperare);
	public Utente modificaUtenteFull( Utente utente);
	public boolean inviaEmailARuoli(EmailDto emailDto);
	public boolean inviaEmailAUtente(EmailDto emailDto);
	public boolean contattaci(EmailDto emailDto);

}
