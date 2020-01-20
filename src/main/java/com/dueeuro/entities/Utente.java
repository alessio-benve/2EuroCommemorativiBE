package com.dueeuro.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="utenti")
public class Utente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6909199698731710098L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_utente")
	private Long idUtente;
	
	@Column(unique = true)
	private String username;
	
	@Column(length = 60)
	private String password;
	
	@Column(length = 40)
	private String email;
	
	private String nome;
	
	private String cognome;
	
	private Boolean enable;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "utenti_ruoli"
	,joinColumns = @JoinColumn(name="utente_id"),inverseJoinColumns = @JoinColumn(name = "ruolo_id"))
	private List<Ruolo> ruoli;

	public String getEmail() {
		return email;
	}
	
	

	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCognome() {
		return cognome;
	}



	public void setCognome(String cognome) {
		this.cognome = cognome;
	}



	public void setEmail(String email) {
		this.email = email;
	}

	public Long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public List<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}



	@Override
	public String toString() {
		return "Utente [idUtente=" + idUtente + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", nome=" + nome + ", cognome=" + cognome + ", enable=" + enable + ", ruoli=" + ruoli + "]";
	}
	
	
	
	
}
