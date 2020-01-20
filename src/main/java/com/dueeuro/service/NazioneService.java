package com.dueeuro.service;



import java.util.List;

import javax.validation.Valid;

import com.dueeuro.entities.Nazione;

public interface NazioneService {

	Nazione aggiungiNazione(Nazione nazione);

	Nazione modificaNazione(@Valid Nazione nazione);

	boolean rimuoviNazione(String nomeNazione);

	List<Nazione> listaNazione();

	List<Nazione> trovaAltreNazioni(Nazione n);

	

}
