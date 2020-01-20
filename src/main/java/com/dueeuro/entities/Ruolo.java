package com.dueeuro.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ruoli")
public class Ruolo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5393739481546849036L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ruolo")
	
	private Long idRuolo;
	
	@Column(name = "nome_ruolo")
	private String nomeRuolo;

	public Long getIdRuolo() {
		return idRuolo;
	}

	public void setIdRuolo(Long idRuolo) {
		this.idRuolo = idRuolo;
	}

	public String getNomeRuolo() {
		return nomeRuolo;
	}

	public void setNomeRuolo(String nomeRuolo) {
		this.nomeRuolo = nomeRuolo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
