package com.dueeuro.entities;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="monete")
public class Moneta {
	
	@Id
	@Column(name="codice_moneta")
	private String codiceMoneta;
	
	private String descrizione;
	
	@Temporal(TemporalType.DATE)
	private Date emissione;
	
	private String tiratura;
	
	@ManyToOne
	@JoinColumn(name="id_nazione")
	private Nazione nazione;
	
	private String tipo;
	
	@Lob
    private byte[] foto;

	public Moneta() {
		super();
		
	}
	
	

	public Moneta(String codiceMoneta, String descrizione, Date emissione, String tiratura, String tipo, byte[] foto) {
		super();
		this.codiceMoneta = codiceMoneta;
		this.descrizione = descrizione;
		this.emissione = emissione;
		this.tiratura = tiratura;
		this.tipo = tipo;
		this.foto = foto;
		this.nazione=new Nazione();
	}



	public Moneta(String codiceMoneta, String descrizione, Date emissione, String tiratura, Nazione nazione,
			String tipo, byte[] foto) {
		super();
		this.codiceMoneta = codiceMoneta;
		this.descrizione = descrizione;
		this.emissione = emissione;
		this.tiratura = tiratura;
		this.nazione = nazione;
		this.tipo = tipo;
		this.foto = foto;
	}



	public String getCodiceMoneta() {
		return codiceMoneta;
	}



	public void setCodiceMoneta(String codiceMoneta) {
		this.codiceMoneta = codiceMoneta;
	}



	public String getDescrizione() {
		return descrizione;
	}



	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}



	public Date getEmissione() {
		return emissione;
	}



	public void setEmissione(Date emissione) {
		this.emissione = emissione;
	}



	public String getTiratura() {
		return tiratura;
	}



	public void setTiratura(String tiratura) {
		this.tiratura = tiratura;
	}



	public Nazione getNazione() {
		return nazione;
	}



	public void setNazione(Nazione nazione) {
		this.nazione = nazione;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public byte[] getFoto() {
		return foto;
	}



	public void setFoto(byte[] foto) {
		this.foto = foto;
	}



	@Override
	public String toString() {
		return "Moneta [codiceMoneta=" + codiceMoneta + ", descrizione=" + descrizione + ", emissione=" + emissione
				+ ", tiratura=" + tiratura + ", nazione=" + nazione + ", tipo=" + tipo + ", foto="
				+ Arrays.toString(foto) + "]";
	}


	

}
