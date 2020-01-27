package com.dueeuro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dueeuro.entities.Nazione;
import com.dueeuro.service.NazioneService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1")
public class NazioneController {
	@Autowired
	NazioneService nazioneService;
	// AGGIUNGI MODIFICA RIMUOVI LEGGI
	
	@Secured({"ROLE_TESTER","ROLE_ADMIN"})
	@PostMapping("/nazione/aggiungi")
	public Nazione aggiungiNazione(@Valid @RequestBody Nazione nazione) {
		return nazioneService.aggiungiNazione(nazione);
	}
	
	@Secured({"ROLE_TESTER","ROLE_ADMIN"})
	@PostMapping("/nazione/modifica")
	public Nazione modificaNazione(@Valid @RequestBody Nazione nazione) {
		return nazioneService.modificaNazione(nazione);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/nazione/rimuovi/{nomeNazione}")
	public boolean rimuoviNazione(@PathVariable(name = "nomeNazione") String nomeNazione) {
		return nazioneService.rimuoviNazione(nomeNazione);
	}
	
	@Secured({"ROLE_TESTER","ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/nazione/lista")
	public List<Nazione> listaNazione() {
		return nazioneService.listaNazione();
	}
	/*@Secured({"ROLE_TESTER","ROLE_ADMIN"})
	@GetMapping("nazione/lista/{id}")
	public Nazione trovaNazioneDaId(@PathVariable Long id) {
		return nazioneService.getNazioneById(id);
	}*/
	
	@Secured({"ROLE_TESTER","ROLE_ADMIN"})
	@PostMapping("/nazione/altre")
	public List<Nazione> leggiAltreNazioni(@Valid @RequestBody Nazione n){
		return nazioneService.trovaAltreNazioni(n);
	}
}
