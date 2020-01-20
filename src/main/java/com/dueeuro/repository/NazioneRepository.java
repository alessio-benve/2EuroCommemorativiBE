package com.dueeuro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dueeuro.entities.Nazione;

public interface NazioneRepository extends JpaRepository<Nazione, Long> {
	public Nazione findNazioneByNomeNazione(String n);
}
