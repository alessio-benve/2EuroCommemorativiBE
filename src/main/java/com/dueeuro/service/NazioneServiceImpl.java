package com.dueeuro.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dueeuro.entities.Moneta;
import com.dueeuro.entities.Nazione;
import com.dueeuro.repository.MonetaRepository;
import com.dueeuro.repository.NazioneRepository;

@Service
public class NazioneServiceImpl implements NazioneService {

	@Autowired NazioneRepository nazioneRepository;
	@Autowired MonetaRepository monetaRepository;
	
	@Override
	public Nazione aggiungiNazione(Nazione nazione) {
		Nazione n = nazioneRepository.findNazioneByNomeNazione(nazione.getNomeNazione());
		if(n!=null) return null;
		return nazioneRepository.save(nazione);		
	}

	@Override
	public Nazione modificaNazione(@Valid Nazione nazione) {
		Nazione n= nazioneRepository.findNazioneByNomeNazione(nazione.getNomeNazione());
		if (n==null) return null;
		nazione.setIdNazione(n.getIdNazione());
		return nazioneRepository.save(nazione);
	}

	@Override
	public boolean rimuoviNazione(String nomeNazione) {
		Nazione n= nazioneRepository.findNazioneByNomeNazione(nomeNazione);		
		List<Moneta> monete= monetaRepository.findMonetaByNazione(n);
		for (Moneta m:monete) {
			m.setNazione(null);
		}
		nazioneRepository.delete(n);
		if(nazioneRepository.findNazioneByNomeNazione(n.getNomeNazione())!=null)return false;
		return true;
		
	}

	@Override
	public List<Nazione> listaNazione() {
		return nazioneRepository.findAll();		
	}

	@Override
	public List<Nazione> trovaAltreNazioni(Nazione n) {
		List<Nazione> nazioni= nazioneRepository.findAll();	
		nazioni.remove(n);		
		return nazioni;
	}
	



}
