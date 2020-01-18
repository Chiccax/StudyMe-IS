package test.integration.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Base64;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.bean.UtenteBean;
import model.dao.UtenteDao;
import model.manager.UtenteManager;

class TestUtenteManager {
	
	@AfterEach
	protected void tearDown() throws Exception {
		manager.setEmail("chiccaesp98@libero.com", "mesposito@gmail.com");
		
	}
	
	@Test
	public void testLogin() {
		UtenteBean utenteLoggato = new UtenteBean();
		
		String passwordNuova = "chicca";
		//String passwordBase64format  = Base64.getEncoder().encodeToString(passwordNuova.getBytes()); 
		utenteLoggato = manager.getUtente("Mariarosaria", "cGFzc01hcmlhcm9zYXJpYQ==");
		
		assertEquals("Mariarosaria", utenteLoggato.getNomeUtente());
	}
	
	@Test
	public void testModificaEmail() {
		boolean res = manager.setEmail("mesposito@gmail.com", "chiccaesp98@libero.com");
		assertTrue(res);
	}
	
	@Test
	public void testModificaPassword() {
		String passwordNuova = "passMariarosaria";
		String passwordBase64format  = Base64.getEncoder().encodeToString(passwordNuova.getBytes()); 
		Boolean res = manager.setPassword("mesposito@gmail.com", passwordBase64format);
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
	
	/*@Test
	public void testApprovaInteroPacchetto() {
		manager.approvaInteroPacchetto("pac051");
		
		UtenteDao utente = new UtenteDao();
		verify(utente).approvaInteroPacchetto("pac051");
	}
	
	@Test
	public void testDisapprovaInteroPacchetto() {
		manager.disapprovaInteroPacchetto("pac050");
		
		UtenteDao utente = new UtenteDao();
		verify(utente).disapprovaInteroPacchetto("pac050");
	}*/
	
	private UtenteManager manager = new UtenteManager();
}
