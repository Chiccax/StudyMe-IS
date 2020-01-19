package test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.bean.UtenteBean;
import model.dao.UtenteDao;

class TC_UtenteDao {
	UtenteDao utente;
	String email;
	
	@BeforeEach
	void setUp() throws Exception{ 
		utente = new UtenteDao();
	}

	@Test
	void testLogin() {
		String nomeUtente = "Claudia";
		String password = "claudiabuono";
		
		String passwordBase64format  = Base64.getEncoder().encodeToString(password.getBytes()); 
		
		UtenteBean u = utente.login(nomeUtente, passwordBase64format);
		assertNotNull(u);
		assertEquals(nomeUtente, u.getNomeUtente());
	}
	
	@Test
	void testRegistrazione() {
		boolean resp = utente.registration("eugenio@live.it", "Eugenio", "Eugenio98");
		assertEquals(true, resp);
	}
	
	@Test
	void testModificaPassword() {
		String passwordNuova = "Mariarosaria";
		String passwordBase64format  = Base64.getEncoder().encodeToString(passwordNuova.getBytes()); 
		boolean resp = utente.updatePassword("mesposito@gmail.com", passwordBase64format);
		assertEquals(true, resp);
	}
	
	@Test
	void testModificaEmail() {
		boolean resp = utente.updateEmail("mesposito@gmail.com", "chiccaesp98@libero.com");
		assertEquals(true, resp);
	}

	@Test
	void testGetAllAcquirenti() {
		List<UtenteBean> acquirenti = new ArrayList<UtenteBean>();
		acquirenti = utente.getAllAcquirenti();
		assertNotNull(acquirenti);
	}
}
