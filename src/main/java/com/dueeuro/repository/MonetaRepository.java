package com.dueeuro.repository;

import java.util.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.dueeuro.entities.Moneta;
import com.dueeuro.entities.Nazione;



public interface MonetaRepository extends JpaRepository<Moneta, String>{
	public List<Moneta> findAllByEmissioneBetween (Date emissioneInizio, Date emissioneFine);
	public List<Moneta> findAllByEmissioneBetweenAndNazione(Date emissioneInizio, Date emissioneFine, Nazione n);
	public List<Moneta> findMonetaByNazione(Nazione n);		
	public Moneta findTopByOrderByCodiceMonetaDesc();
	public Moneta findMonetaByCodiceMoneta(String codiceMoneta);
}
