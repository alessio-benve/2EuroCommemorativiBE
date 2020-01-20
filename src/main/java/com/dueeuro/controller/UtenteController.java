package com.dueeuro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dueeuro.entities.Utente;
import com.dueeuro.service.UtenteService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1")
public class UtenteController {
	
	@Autowired UtenteService utenteService;
	
	
	@PostMapping("/utente/aggiungi")
	//AGGIUNGE SOLO L'UTENTE DI TIPO ROLE_USER
	public Utente aggiungiUtente(@Valid @RequestBody Utente utente) {
		return utenteService.aggiungiUtente(utente);		
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/utente/modificaRuoli")
	public Utente modificaRuoli(@Valid @RequestBody Utente utente) {
		return utenteService.modificaRuoli(utente);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_TESTER"})
	@PostMapping("/utente/modifica")
	public Utente modificaUtente(@Valid @RequestBody Utente utente) {
		return utenteService.modificaUtente(utente);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_TESTER"})
	@PostMapping("utente/disattiva")
	public boolean disattivaUtente(@Valid @RequestBody Utente utente) {
		return utenteService.disattivaUtente(utente);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("utente/cancella")
	//(SOLVED) NON FUNZIONANTE VIOLAZIONE SQL NELLA TABELLA UTENTI_RUOLI
	public boolean cancellaUtente(@Valid @RequestBody Utente utente) {
		System.out.println("Controller");
		return utenteService.rimuoviUtente(utente);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/utente/lista")
	public List<Utente> leggiUtenti(){
		return utenteService.leggiUtenti();
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_TESTER"})
	@PostMapping("/utente/recupera")
	public Utente leggiUtenteDaUsername(HttpEntity<String> richiesta) {
		return utenteService.trovaUtenteDaUsername(richiesta);
	}
	
}
