package com.dueeuro;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dueeuro.entities.Ruolo;
import com.dueeuro.entities.Utente;
import com.dueeuro.repository.RuoloRepository;
import com.dueeuro.repository.UtenteRepository;
import com.dueeuro.service.UtenteServiceImpl;


@SpringBootApplication
public class Application  implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Autowired UtenteServiceImpl usi;
	@Autowired RuoloRepository rr;
	@Autowired UtenteRepository ur;
	@Override
	public void run(String... args) throws Exception {
		/*System.out.println("kjhgfc");
		Ruolo admin = new Ruolo();
		admin.setNomeRuolo("ROLE_ADMIN");
		Ruolo user = new Ruolo();
		user.setNomeRuolo("ROLE_USER");
		Ruolo tester = new Ruolo();
		tester.setNomeRuolo("ROLE_TESTER");
		admin= rr.save(admin);
		user = rr.save(user);
		tester = rr.save(tester);
		
		
		Utente utenteAdmin = new Utente("admin",
				"$2y$12$BKix40hq1Yc9WndrE1zBl.WtUz18SosRCgUSmfkBiAFe.KlHqSj8m",
				"admin@admin.admin","admin","admin",true, Arrays.asList(tester,user,admin));
		Utente utenteTester = new Utente("tester",
				"$2y$12$aSRJoSO/r19m6AgzMLqFrO6kd6KUj6NnAfFgCNxDpB0TzXa3HbnG2",
				"test@test.test","tester","tester",true, Arrays.asList(tester,user));
		Utente utenteUser = new  Utente("user",
				"$2y$12$XUGahnIBIl8qDYGeOYH8mu2k0fzb8RfjobS8zPB2ujiG1wJKyy2sG",
				"user@user.user","user","user",true, Arrays.asList(user));
		
		ur.save(utenteAdmin);
		ur.save(utenteTester);
		ur.save(utenteUser);
		*/
		
	}
	
	

}
