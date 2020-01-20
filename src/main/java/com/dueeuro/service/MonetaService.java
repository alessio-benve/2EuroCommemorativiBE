package com.dueeuro.service;


import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.dueeuro.entities.Moneta;

public interface MonetaService {

	public Object aggiungiMoneta(MultipartFile file, String moneta);

	public Moneta modificaMoneta(Moneta moneta);

	public boolean rimuoviMoneta(String idMoneta);

	public List<Moneta> leggiMonete();

	public List<Moneta> leggiMoneteDaAnno(String emissioneFine);

	public List<Moneta> leggiMoneteDaAnnoENazione(String emissioneFine, String nazione);

	public List<Moneta> leggiMoneteDaNazione(String nazione);

	public Map<String, String> trovaUltimoIdInserito();

	public List<Moneta> prova(String emissione, String nazione);

	public Moneta leggiMonetaDaId(String idMoneta);
	

	
	
	

}
