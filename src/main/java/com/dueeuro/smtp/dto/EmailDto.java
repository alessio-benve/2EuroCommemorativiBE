package com.dueeuro.smtp.dto;

public class EmailDto {

	private String destinatario;
	private String oggetto;
	private String testo;

	public EmailDto() {
		super();
	}

	public EmailDto(String destinatario, String oggetto, String testo) {
		super();
		this.destinatario = destinatario;
		this.oggetto = oggetto;
		this.testo = testo;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	@Override
	public String toString() {
		return "EmailDto [destinatario=" + destinatario + ", oggetto=" + oggetto + ", testo=" + testo + "]";
	}

}
