package com.dueeuro.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dueeuro.dto.RicercaDto;
import com.dueeuro.entities.Moneta;
import com.dueeuro.service.MonetaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1")
public class MonetaController {
	
	@Autowired
	MonetaService monetaService;
	
	@Secured({"ROLE_TESTER","ROLE_ADMIN"})
	@PostMapping("/moneta/aggiungi")
	public Object aggiungiMoneta(@RequestParam("image") MultipartFile file,
			@RequestParam("moneta") String moneta) throws IOException {
		System.out.println(moneta.toString());
		return monetaService.aggiungiMoneta (file,moneta);		
		
	}
	@Secured({"ROLE_TESTER","ROLE_ADMIN"})
	@PostMapping("/moneta/modifica")
	public Object modificaMoneta(@Valid @RequestBody Moneta moneta) {
		return monetaService.modificaMoneta(moneta);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/moneta/rimuovi/{id}")
	public boolean rimuoviMoneta(@PathVariable ("id") String idMoneta ) {
		return monetaService.rimuoviMoneta(idMoneta);
	}
	
	@Secured({"ROLE_USER","ROLE_TESTER","ROLE_ADMIN"})
	@GetMapping("/moneta/lista")
	public List<Moneta> leggiMonete(){
		return monetaService.leggiMonete();
	}
	
	@Secured({"ROLE_TESTER","ROLE_ADMIN"})
	@GetMapping("/moneta/{id}")
	public Moneta leggiMonetaDaId(@PathVariable ("id") String idMoneta ){
		return monetaService.leggiMonetaDaId(idMoneta);
	}
	
	
	@Secured({"ROLE_USER","ROLE_TESTER","ROLE_ADMIN"})
	@GetMapping("/moneta/lista/anno/{emissione}")
	public List<Moneta> leggiMoneteDaAnno(@PathVariable("emissione")String emissioneFine){
		return monetaService.leggiMoneteDaAnno(emissioneFine);
	}
	
	@Secured({"ROLE_USER","ROLE_TESTER","ROLE_ADMIN"})
	@GetMapping("/moneta/lista/nazione/{nazione}")
	public List<Moneta> leggiMoneteDaNazione(@PathVariable("nazione")String nazione){
		return monetaService.leggiMoneteDaNazione(nazione);
	}
	
	@Secured({"ROLE_USER","ROLE_TESTER","ROLE_ADMIN"})
	@GetMapping("/moneta/lista/anno/nazione/{emissione}/{nazione}")
	public List<Moneta> leggiMoneteDaAnnoENazione(@PathVariable("emissione")String emissioneFine,
			@PathVariable ("nazione")String nazione){
		return monetaService.leggiMoneteDaAnnoENazione(emissioneFine,nazione);
	}
	
	@Secured({"ROLE_TESTER","ROLE_ADMIN"})
	@GetMapping("/moneta/ultimoId")
	public Map<String, String> trovaUltimoIdInserito() {	    
		//System.out.println("ultimoId");
		return monetaService.trovaUltimoIdInserito();
	}
	
	@Secured({"ROLE_TESTER","ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/moneta/ricerca")
	public List<Moneta> prova(@ Valid @RequestBody RicercaDto ricerca ) {	    
		//System.out.println("ultimoId");
		return monetaService.prova(ricerca.emissione,ricerca.nazione);		
	}
	
}
