package com.dueeuro.entities;



import javax.persistence.*;



@Entity
@Table (name="nazioni")
public class Nazione {
	
	@Id
	@GeneratedValue
	@Column (name="id_nazione")
	private Long idNazione;
	
	@Column (name="nome_nazione")
	private String nomeNazione;
	
	@Column(name="nome_abitanti")
	private String nomeAbitanti;
	
	/*@JsonIgnore
	@OneToMany(mappedBy="nazione")
	private List<Moneta> monete;*/

	public Nazione() {
		super();
	}

	public Nazione(Long idNazione, String nomeNazione, String nomeAbitanti/*, List<Moneta> monete*/) {
		super();
		this.idNazione = idNazione;
		this.nomeNazione = nomeNazione;
		this.nomeAbitanti = nomeAbitanti;
		//this.monete = monete;
	}

	public Long getIdNazione() {
		return idNazione;
	}

	public void setIdNazione(Long idNazione) {
		this.idNazione = idNazione;
	}

	public String getNomeNazione() {
		return nomeNazione;
	}

	public void setNomeNazione(String nomeNazione) {
		this.nomeNazione = nomeNazione;
	}

	public String getNomeAbitanti() {
		return nomeAbitanti;
	}

	public void setNomeAbitanti(String nomeAbitanti) {
		this.nomeAbitanti = nomeAbitanti;
	}

	/*public List<Moneta> getMonete() {
		return monete;
	}

	public void setMonete(List<Moneta> monete) {
		this.monete = monete;
	}*/

	@Override
	public String toString() {
		return "Nazione [idNazione=" + idNazione + ", nomeNazione=" + nomeNazione + ", nomeAbitanti=" + nomeAbitanti
				+/* ", monete=" + monete +*/ "]";
	}
/*
	public String getInfo() {
		return this.idNazione+" - "+this.nomeNazione;
	}
*/

	public boolean equals(Object obj) {
		Nazione n=(Nazione) obj;
		return idNazione.equals(n.idNazione);
	}
	
	
}
