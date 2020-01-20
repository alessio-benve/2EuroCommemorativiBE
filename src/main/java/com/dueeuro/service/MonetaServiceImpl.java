package com.dueeuro.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dueeuro.dto.MonetaDto;
import com.dueeuro.entities.Moneta;
import com.dueeuro.entities.Nazione;
import com.dueeuro.repository.MonetaRepository;
import com.dueeuro.repository.NazioneRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MonetaServiceImpl implements MonetaService {

	@Autowired NazioneRepository nazioneRepository;
	@Autowired MonetaRepository monetaRepository;
	
	@Override
	public Object aggiungiMoneta(MultipartFile file, String moneta) {
		

		ObjectMapper mapper = new ObjectMapper();
		try {
			
			MonetaDto monetaDto = mapper.readValue(moneta, MonetaDto.class);			
			Moneta m=new Moneta(monetaDto.codiceMoneta, monetaDto.descrizione,monetaDto.emissione,
					monetaDto.tiratura,file.getContentType(),file.getBytes());
			Optional<Moneta> monetaGiaInserita = monetaRepository.findById(monetaDto.codiceMoneta);
			//SALVARE NAZIONE
			Nazione n= nazioneRepository.findNazioneByNomeNazione(monetaDto.nazione);
			if(monetaGiaInserita.isPresent()) {
				Moneta mgi=new Moneta();
				mgi.setDescrizione("CODICE MONETA GIA' INSERITO");
				return mgi;
			}
			if(n==null) {
				Moneta err=new Moneta();
				err.setDescrizione("NAZIONE NON TROVATA, INSERIRE PRIMA LA NAZIONE");
				return err;
			}
			System.out.println(n.toString());
			m.setNazione(n);
			//SALVARE MONETA
			return monetaRepository.save(m);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Moneta();
	}
	
	@Override
	public Moneta modificaMoneta(Moneta moneta) {
		Nazione n=nazioneRepository.findNazioneByNomeNazione(moneta.getNazione().getNomeNazione());
		if(monetaRepository.findById(moneta.getCodiceMoneta()).isPresent()) {
			moneta.setNazione(n);			
			return monetaRepository.save(moneta);			
		}
		Moneta m=new Moneta();
		m.setDescrizione("MONETA NON PRESENTE SUL DB, IMPOSSIBILE MODIFICARE");
		return m;
	}

	@Override
	public boolean rimuoviMoneta(String idMoneta) {
		monetaRepository.deleteById(idMoneta);
		return !monetaRepository.findById(idMoneta).isPresent();
		
	}

	@Override
	public List<Moneta> leggiMonete() {
		return monetaRepository.findAll();		
	}

	@Override
	public List<Moneta> leggiMoneteDaAnno(String annoFine) {
		String emissioneInizio = annoFine+"-01-01";
		String emissioneFine = annoFine+"-12-31";
		try {
			return monetaRepository.findAllByEmissioneBetween(
					new SimpleDateFormat("yyyy-MM-dd").parse(emissioneInizio),
					new SimpleDateFormat("yyyy-MM-dd").parse(emissioneFine));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Moneta> leggiMoneteDaAnnoENazione(String annoFine, String nazione) {
		Nazione n= nazioneRepository.findNazioneByNomeNazione(nazione);
		if(n==null) return null;
		String emissioneInizio = annoFine+"-01-01";
		String emissioneFine = annoFine+"-12-31";
		try {
			return monetaRepository.findAllByEmissioneBetweenAndNazione(
					new SimpleDateFormat("yyyy-MM-dd").parse(emissioneInizio),
					new SimpleDateFormat("yyyy-MM-dd").parse(emissioneFine), n);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Moneta> leggiMoneteDaNazione(String nazione) {
		Nazione n= nazioneRepository.findNazioneByNomeNazione(nazione);
		return monetaRepository.findMonetaByNazione(n);
		
	}
	
	@Override
	public Map<String,String> trovaUltimoIdInserito () {
		Moneta ultima = monetaRepository.findTopByOrderByCodiceMonetaDesc();
		String idUltimo=ultima.getCodiceMoneta();
		int decine=Integer.parseInt(idUltimo.substring(idUltimo.lastIndexOf("Z")+1));
		//System.out.println(idUltimo.substring(idUltimo.length()-2));
		decine++;
		//int centinaia= Integer.parseInt(idUltimo.substring(idUltimo.length()-3,idUltimo.length()-2));
		if(decine<100) {
			String id="AZ0"+decine;
			HashMap<String,String> mappa= new HashMap<>();
			mappa.put("id", id);
			return mappa;			
		}
		
		String id="AZ"+decine;
		HashMap<String,String> mappa= new HashMap<>();
		mappa.put("id", id);
		//System.out.println(id);
		return mappa;
	}

	@Override
	public List<Moneta> prova(String annoFine, String nazione) {
		if((annoFine == null||annoFine=="") && (nazione==null||nazione=="") ) return monetaRepository.findAll();
		if((annoFine != null && annoFine!="") && (nazione!= null && nazione!="")) {
			String emissioneInizio = annoFine+"-01-01";
			String emissioneFine = annoFine+"-12-31";
			Nazione n= nazioneRepository.findNazioneByNomeNazione(nazione);
			try {
				return monetaRepository.findAllByEmissioneBetweenAndNazione(new SimpleDateFormat("yyyy-MM-dd").parse(emissioneInizio),
						new SimpleDateFormat("yyyy-MM-dd").parse(emissioneFine),n);
			} catch (ParseException e) {				
				e.printStackTrace();
			}
		}
		if(annoFine!=null && annoFine!="") {
			String emissioneInizio = annoFine+"-01-01";
			String emissioneFine = annoFine+"-12-31";
			try {
				//System.out.println("solo anno");
				return monetaRepository.findAllByEmissioneBetween(new SimpleDateFormat("yyyy-MM-dd").parse(emissioneInizio),
						new SimpleDateFormat("yyyy-MM-dd").parse(emissioneFine));
			} catch (ParseException e) {				
				e.printStackTrace();
			}
		}
		if(nazione!=null && nazione!="") {
			Nazione n= nazioneRepository.findNazioneByNomeNazione(nazione);
			return monetaRepository.findMonetaByNazione(n);
		}
		return null;
	}

	@Override
	public Moneta leggiMonetaDaId(String idMoneta) {
		return monetaRepository.findMonetaByCodiceMoneta(idMoneta);	 
		
	}


	

}
