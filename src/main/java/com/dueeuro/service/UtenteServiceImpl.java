package com.dueeuro.service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dueeuro.entities.Ruolo;
import com.dueeuro.entities.Utente;
import com.dueeuro.repository.RuoloRepository;
import com.dueeuro.repository.UtenteRepository;
import com.dueeuro.smtp.dto.EmailDto;
import com.dueeuro.smtp.service.EmailSender;

@Service
public class UtenteServiceImpl implements UserDetailsService, UtenteService {

	Logger log = LoggerFactory.getLogger(UtenteServiceImpl.class);

	@Autowired
	private UtenteRepository utenteRepository;
	@Autowired
	private RuoloRepository ruoloRepository;
	@Autowired
	@Qualifier("passwordEncoder")
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private EmailSender emailSender;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Utente utente = utenteRepository.findByUsername(username);

		if (utente == null) {
			log.error("UTENTE NON TROVATO NEL DB");
			throw new UsernameNotFoundException("UTENTE NON TROVATO");
		}

		List<GrantedAuthority> authorities = utente.getRuoli().stream()
				.map(ruoli -> new SimpleGrantedAuthority(ruoli.getNomeRuolo())).collect(Collectors.toList());

		return new User(utente.getUsername(), utente.getPassword(), utente.getEnable(), true, true, true, authorities);
	}

	@Override
	public Utente aggiungiUtente(Utente utente) {
		Utente usernameGiaInserito = utenteRepository.findByUsername(utente.getUsername());
		if (usernameGiaInserito != null) {
			Utente u = new Utente();
			u.setUsername("Username già inserito, prova con uno differente");
			return u;
		}
		Utente emailGiaInserita = utenteRepository.findUtenteByEmail(utente.getEmail());
		if (emailGiaInserita != null) {
			Utente u = new Utente();
			u.setUsername("Email già inserita, prova con uno differente");
			return u;
		}
		Ruolo r = ruoloRepository.findRuoloByNomeRuolo("ROLE_USER");
		utente.setRuoli(Arrays.asList(r));
		utente.setPassword(passwordEncoder.encode(utente.getPassword()));
		utente.setEnable(true);
		System.out.println(utente.toString());
		Utente salvato = utenteRepository.save(utente);
		if (salvato.getNome().equals(utente.getNome())) {
			// Salvataggio effettuato con successo;
			// Opzionale inviare email

			return salvato;
		}
		return null;
	}

	@Override
	public Utente modificaRuoli(Utente utente) {
		Utente presente = utenteRepository.findByUsername(utente.getUsername());
		if (presente != null) {
			presente.setRuoli(utente.getRuoli());
			return utenteRepository.save(presente);
			// inviare mail (Opzionale)
		}
		return null;
	}

	@Override
	// RUOLI E USERNAME NON MODIFICABILI
	public Utente modificaUtente(Utente utente) {
		Utente utenteDaModificare = utenteRepository.findByUsername(utente.getUsername());
		if (utenteDaModificare != null) {
			utenteDaModificare.setCognome(utente.getCognome());			
			utenteDaModificare.setNome(utente.getNome());
			utenteDaModificare.setPassword(passwordEncoder.encode(utente.getPassword()));
			inviaEmailModificaDati(utenteDaModificare);
			utenteDaModificare.setEmail(utente.getEmail());
			return utenteRepository.save(utenteDaModificare);
		}
		return null;

	}

	@Override
	public boolean disattivaUtente(Utente utente) {
		Utente utenteDaDisattivare = utenteRepository.findByUsername(utente.getUsername());
		if (utenteDaDisattivare != null) {
			utenteDaDisattivare.setEnable(!utente.getEnable());
			utenteRepository.save(utenteDaDisattivare);
			return true;
		}
		return false;
	}

	@Override
	// (SOLVED)NON FUNZIONANTE VIOLAZIONE SQL NELLA TABELLA UTENTI_RUOLI
	public boolean rimuoviUtente(Utente utente) {
		Utente u = utenteRepository.findByUsername(utente.getUsername());
		u.getRuoli().removeAll(u.getRuoli());
		utenteRepository.delete(u);
		u = utenteRepository.findByUsername(utente.getUsername());

		if (u == null)
			return true;
		return false;
	}

	@Override
	public List<Utente> leggiUtenti() {
		return utenteRepository.findAll();

	}

	@Override
	public Utente trovaUtenteDaUsername(HttpEntity<String> richiesta) {
		String username = richiesta.getBody();
		return utenteRepository.findByUsername(username);

	}

	@Override
	public boolean recuperaCredenziali(Utente utenteDaRecuperare) {
		Utente utenteRecuperatoDaUsername=null;
		Utente utenteRecuperatoDaEmail=null;
		if(utenteDaRecuperare.getUsername()!=null ) {
			utenteRecuperatoDaUsername=utenteRepository.findByUsername(utenteDaRecuperare.getUsername());
		}
		if(utenteDaRecuperare.getEmail()!=null ) {
			utenteRecuperatoDaEmail=utenteRepository.findUtenteByEmail(utenteDaRecuperare.getEmail());
		}
		if(utenteDaRecuperare.getUsername()!=null && utenteDaRecuperare.getEmail()!=null) {
		if(utenteRecuperatoDaEmail!=null && utenteRecuperatoDaUsername!=null) {
			if(utenteRecuperatoDaEmail.getUsername().equals(utenteRecuperatoDaUsername.getUsername())) {
				//TODO utente trovato da username e email combacianti, settare nuova password e inviarla via mail
				String result = randomPassword();
				utenteRecuperatoDaEmail.setPassword(passwordEncoder.encode(result));
				System.out.println(utenteRecuperatoDaEmail.toString()+" Password chiara: "+result);
				utenteRepository.save(utenteRecuperatoDaEmail);
				inviaEmailCredenziali(result,utenteRecuperatoDaEmail);
				return true;
			}
			else return false; //Utente trovato da Username e Email ma non combaciano.
		}
		return false;
		}
		
		if(utenteRecuperatoDaUsername!=null) {
			String result = randomPassword();
			utenteRecuperatoDaUsername.setPassword(passwordEncoder.encode(result));
			System.out.println(utenteRecuperatoDaUsername.toString()+" Password chiara: "+result);
			utenteRepository.save(utenteRecuperatoDaUsername);
			inviaEmailCredenziali(result,utenteRecuperatoDaUsername);
			return true;
		}
		if(utenteRecuperatoDaEmail!=null) {
			String result = randomPassword();
			utenteRecuperatoDaEmail.setPassword(passwordEncoder.encode(result));
			System.out.println(utenteRecuperatoDaEmail.toString()+" Password chiara: "+result);
			utenteRepository.save(utenteRecuperatoDaEmail);
			inviaEmailCredenziali(result,utenteRecuperatoDaEmail);
			return true;
		}		
		return false;
	}
	
	private void inviaEmailCredenziali(String passwordChiara, Utente utenteRecuperato) {
		String destinatario=utenteRecuperato.getEmail();
		String corpo="";
		String oggetto="Recupero Credenziali 2EuroCommemorativi";
		corpo="Ciao "+utenteRecuperato.getNome()+",\n\necco le tue credenziali di accesso al sito 2EuroCommemorativi:"+
				 "\n\nUsername:"+utenteRecuperato.getUsername()+"\nPassword:"+passwordChiara+"\n\nQualora non "
						+ "fossi stato tu a richiedere i dati, ti invitiamo ad accedere al sito con le credenziali appena"
						+ " ricevute e modificare Password ed Email.\nPer qualsiasi comunicazione utilizzare la sezione"
						+ " contatti presente sul sito.\n\n\nSaluti\n\n2EuroCommemorativi";
		EmailDto email=new EmailDto();
		email.setDestinatario(destinatario);
		email.setOggetto(oggetto);
		email.setTesto(corpo);
		emailSender.inviaEmail(email);		
	}
	
	private void inviaEmailModificaDati(Utente utente) {
		String destinatario=utente.getEmail();
		String corpo="";
		String oggetto="Modifica account per 2EuroCommemorativi";
		corpo="Ciao "+utente.getNome()+",\n\ncome richiesto, i dati per il tuo account sul sito 2EuroCommemorativi, sono stati modificati."+
				 "\n\nQualora non fossi stato tu a richiedere la modifica dei dati, ti invitiamo ad accedere al sito e modificare Password ed Email.\nSe non riuscissi più ad accedere contattaci al più presto.\n\nPer qualsiasi comunicazione utilizzare la sezione"
						+ " Contatti presente sul sito.\n\n\nSaluti\n\n2EuroCommemorativi";
		EmailDto email=new EmailDto();
		email.setDestinatario(destinatario);
		email.setOggetto(oggetto);
		email.setTesto(corpo);
		emailSender.inviaEmail(email);		
	}
	
	public static String randomPassword() {
		int leftLimit = 65; // letter 'A'
		int rightLimit = 90; // letter 'Z'
		int targetStringLength = 8;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		return generatedString;
	}


}
