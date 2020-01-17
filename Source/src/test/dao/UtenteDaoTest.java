package test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.junit.AfterClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.bean.UtenteBean;
import model.dao.UtenteDao;

class UtenteDaoTest {
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
		String passwordNuova = "chicca";
		String passwordBase64format  = Base64.getEncoder().encodeToString(passwordNuova.getBytes()); 
		boolean resp = utente.updatePassword("chiccaesp98@libero.com", passwordBase64format);
		assertEquals(true, resp);
	}
	
	@Test
	void testModificaEmail() {
		boolean resp = utente.updateEmail("chiccaesp98@libero.com", "mesposito@gmail.com");
		assertEquals(true, resp);
	}

	@Test
	void testGetAllAcquirenti() {
		List<UtenteBean> acq = new ArrayList<UtenteBean>();
		List<UtenteBean> acquirenti = new ArrayList<UtenteBean>();
		acquirenti = utente.getAllAcquirenti();
		assertEquals(acq, acquirenti);
		
	}
}
