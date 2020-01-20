package com.dueeuro.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class UtenteServiceImpl implements UserDetailsService, UtenteService{
	
	Logger log = LoggerFactory.getLogger(UtenteServiceImpl.class);
	
	@Autowired private UtenteRepository utenteRepository;
	@Autowired private RuoloRepository ruoloRepository;
	@Autowired private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Utente utente = utenteRepository.findByUsername(username);
		
		if(utente == null) {
			log.error("UTENTE NON TROVATO NEL DB");
			throw new UsernameNotFoundException("UTENTE NON TROVATO");
		}
		
		List<GrantedAuthority> authorities = utente.getRuoli()
				.stream()
				.map(ruoli -> new SimpleGrantedAuthority(ruoli.getNomeRuolo()))
				.collect(Collectors.toList());
		
		return new User(utente.getUsername(), utente.getPassword(), utente.getEnable(),
				true, true, true, authorities);
	}

	@Override
	public Utente aggiungiUtente(Utente utente) {
		Utente usernameGiaInserito=utenteRepository.findByUsername(utente.getUsername());
		if(usernameGiaInserito!=null) {
			Utente u=new Utente();
			u.setUsername("Username già inserito, prova con uno differente");
			return u;
		}
		Utente emailGiaInserita=utenteRepository.findUtenteByEmail(utente.getEmail());
		if(emailGiaInserita!=null) {
			Utente u=new Utente();
			u.setUsername("Email già inserita, prova con uno differente");
			return u;
		}
		Ruolo r=ruoloRepository.findRuoloByNomeRuolo("ROLE_USER");
		utente.setRuoli(Arrays.asList(r));
		utente.setPassword(passwordEncoder.encode(utente.getPassword()));
		utente.setEnable(true);
		System.out.println(utente.toString());
		Utente salvato = utenteRepository.save(utente);
		if(salvato.getNome().equals(utente.getNome())) {
			//Salvataggio effettuato con successo;
			//Opzionale inviare email
			
			return salvato;
		}
		return null;
	}

	@Override
	public Utente modificaRuoli(Utente utente) {
		Utente presente= utenteRepository.findByUsername(utente.getUsername());
		if(presente!=null) {
			presente.setRuoli(utente.getRuoli());
			return utenteRepository.save(presente);
			//inviare mail (Opzionale)
		}
		return null;
	}
	
	@Override
	//RUOLI E USERNAME NON MODIFICABILI
	public Utente modificaUtente(Utente utente) {
		Utente utenteDaModificare=utenteRepository.findByUsername(utente.getUsername());
		if(utenteDaModificare!=null) {
			utenteDaModificare.setCognome(utente.getCognome());
			utenteDaModificare.setEmail(utente.getEmail());
			utenteDaModificare.setNome(utente.getNome());
			utenteDaModificare.setPassword(passwordEncoder.encode(utente.getPassword()));			
			return utenteRepository.save(utenteDaModificare);
		}
		return null;
		
	}

	@Override
	public boolean disattivaUtente(Utente utente) {
		Utente utenteDaDisattivare=utenteRepository.findByUsername(utente.getUsername());
		if(utenteDaDisattivare!=null) {
			utenteDaDisattivare.setEnable(false);
			utenteRepository.save(utenteDaDisattivare);
			return true;
		}
		return false;
	}

	@Override
	//(SOLVED)NON FUNZIONANTE VIOLAZIONE SQL NELLA TABELLA UTENTI_RUOLI
	public boolean rimuoviUtente(Utente utente) {		
		 Utente u=utenteRepository.findByUsername(utente.getUsername());
		 u.getRuoli().removeAll(u.getRuoli());
		 utenteRepository.delete(u);
		 u=utenteRepository.findByUsername(utente.getUsername());

		 if(u==null) return true;
		return false;
	}

	@Override
	public List<Utente> leggiUtenti() {
		return utenteRepository.findAll();
		
	}

	@Override
	public Utente trovaUtenteDaUsername(HttpEntity<String> richiesta) {
		String username=richiesta.getBody();
		return utenteRepository.findByUsername(username);
		
	}
	
	

}
