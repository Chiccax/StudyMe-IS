package test.integration.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Base64;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.bean.UtenteBean;
import model.dao.PacchettoDao;
import model.dao.UtenteDao;
import model.manager.UtenteManager;

class TestUtenteManager {
	
	@AfterEach
	protected void tearDown() throws Exception {
		manager.setEmail("mesposito@gmail.com", "chiccaesp98@gmail.com");
		
	}
	
	@Test
	public void testLogin() {
		UtenteBean utenteLoggato = new UtenteBean();
		
		String passwordNuova = "chicca";
		//String passwordBase64format  = Base64.getEncoder().encodeToString(passwordNuova.getBytes()); 
		utenteLoggato = manager.getUtente("Mariarosaria", "TWFyaWFyb3Nhcmlh");
		assertNotNull(utenteLoggato);
	}
	
	@Test
	public void testModificaEmail() {
		boolean res = manager.setEmail("chiccaesp98@gmail.com", "mesposito@gmail.com");
		assertTrue(res);
	}
	
	@Test
	public void testModificaPassword() {
		String passwordNuova = "passMariarosaria";
		String passwordBase64format  = Base64.getEncoder().encodeToString(passwordNuova.getBytes()); 
		Boolean res = manager.setPassword("chiccaesp98@gmail.com", passwordBase64format);
		assertTrue(res);
	}
	
	@Test
	public void testRegistrazione() {
		String password = "Maria Ausiliatrice";
		String passwordBase64format  = Base64.getEncoder().encodeToString(password.getBytes()); 
		boolean res = manager.registrazione("mariaA@live.it", "MariaAus", passwordBase64format);
		
		assertTrue(res);
	}
	
	@Test
	public void testVisualizzaPacchettiDaApprovare() {
		ArrayList<PacchettoBean> pacchettiDaApprovare = new ArrayList<PacchettoBean>();
		pacchettiDaApprovare = manager.visualizzaPacchettiDaApprovare();
		
		assertNotNull(pacchettiDaApprovare);
	}
	
	@Test
	public void testVisualizzaLezioniDaApprovare() {
		ArrayList<LezioniBean> lezioniDaApprovare = new ArrayList<LezioniBean>();
		lezioniDaApprovare = manager.visualizzaLezioniDaApprovare();
		
		assertNotNull(lezioniDaApprovare);
	}
	
	@Test
	public void testApprovaInteroPacchetto() {
		manager.approvaInteroPacchetto("pac050");
		
		PacchettoBean pacchettoApprovato = new PacchettoBean();
		
		PacchettoDao pacchetto = new PacchettoDao();
		pacchettoApprovato = pacchetto.getPacchetto("pac050");
		
		assertEquals(1, pacchettoApprovato.getApprovato());
	}
	

	@Test
	public void testDisapprovaInteroPacchetto() {
		manager.disapprovaInteroPacchetto("pac050");
		
		PacchettoBean pacchettoDisapprovato = new PacchettoBean();
		
		PacchettoDao pacchetto = new PacchettoDao();
		pacchettoDisapprovato = pacchetto.getPacchetto("pac050");
		assertEquals(-1, pacchettoDisapprovato.getApprovato());
	}
	
	@Test
	public void testApprovaSingolaLezione() {
		manager.approvaSingolaLezione("https://www.youtube.com/embed/_2gmtVuenfc");
	
		PacchettoDao pacchetto = new PacchettoDao();
		ArrayList<LezioniBean> l = pacchetto.getLezioniByURl("https://www.youtube.com/embed/_2gmtVuenfc");
		
		for(LezioniBean lezioni : l) {
			if((lezioni.getUrl()).equals("https://www.youtube.com/embed/_2gmtVuenfc")) {
				assertEquals(1, lezioni.getApprovato());
			}
		}
	}
	
	@Test
	public void testDisapprovaSingolaLezione() {
		manager.disapprovaSingolaLezione("https://www.youtube.com/embed/_2gmtVuenfc");
		
		PacchettoDao pacchetto = new PacchettoDao();
		ArrayList<LezioniBean> l = pacchetto.getLezioniByURl("https://www.youtube.com/embed/_2gmtVuenfc");
		
		for(LezioniBean lezioni : l) {
			if((lezioni.getUrl()).equals("https://www.youtube.com/embed/_2gmtVuenfc")) {
				assertEquals(-1, lezioni.getApprovato());
			}
		}
	}
	
	private UtenteManager manager = new UtenteManager();
}
