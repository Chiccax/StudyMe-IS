package test.integration.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Base64;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.bean.UtenteBean;
import model.dao.GestoreDao;
import model.dao.PacchettoDao;
import model.manager.UtenteManager;

class Test_UtenteManager {
	
	@AfterEach
	protected void tearDown() throws Exception {
		manager.setEmail("chiccaesp98@gmail.com", "mesposito@gmail.com");
		String passwordNuova = "chicca";
		String passwordBase64format  = Base64.getEncoder().encodeToString(passwordNuova.getBytes()); 
		manager.setPassword("mesposito@gmail.com", passwordBase64format);
		
	}
	
	@Test
	public void testLogin() {
		UtenteBean utenteLoggato = new UtenteBean();
		
		String passwordNuova = "chicca";
		String passwordBase64format  = Base64.getEncoder().encodeToString(passwordNuova.getBytes()); 
		utenteLoggato = manager.getUtente("Mariarosaria", passwordBase64format);
		assertNotNull(utenteLoggato);
	}
	
	@Test
	public void testModificaEmail() {
		boolean res = manager.setEmail("mesposito@gmail.com", "chiccaesp98@gmail.com");
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
	
	@Test
	public void testApprovaInteroPacchetto() {
		manager.approvaInteroPacchetto("pac051");
		
		GestoreDao gestore = new GestoreDao();
		ArrayList<PacchettoBean> pacchettiDaApprovare = new ArrayList<PacchettoBean>();
		pacchettiDaApprovare = gestore.visualizzaPacchettiDaApprovare();
		String nomePacchetto = null;
		for(PacchettoBean p : pacchettiDaApprovare) {
			if(p.getCodicePacchetto().equals("pac051")) {
				nomePacchetto = p.getTitolo();
			}
		}
		assertNull(nomePacchetto);
	}
	

	@Test
	public void testDisapprovaInteroPacchetto() {
		manager.disapprovaInteroPacchetto("pac052");
		
		GestoreDao gestore = new GestoreDao();
		ArrayList<PacchettoBean> pacchettiDaApprovare = new ArrayList<PacchettoBean>();
		pacchettiDaApprovare = gestore.visualizzaPacchettiDaApprovare();
		String nomePacchetto = null;
		for(PacchettoBean p : pacchettiDaApprovare) {
			if(p.getCodicePacchetto().equals("pac052")) {
				nomePacchetto = p.getTitolo();
			}
		}
		assertNull(nomePacchetto);
	}
	
	@Test
	public void testApprovaSingolaLezione() {
		manager.approvaSingolaLezione("https://www.youtube.com/embed/2nCeuNPMaoY");
	
		PacchettoDao pacchetto = new PacchettoDao();
		ArrayList<LezioniBean> l = pacchetto.getLezioniByURl("https://www.youtube.com/embed/2nCeuNPMaoY");
		
		for(LezioniBean lezioni : l) {
			if((lezioni.getUrl()).equals("https://www.youtube.com/embed/2nCeuNPMaoY")) {
				assertEquals(1, lezioni.getApprovato());
			}
		}
	}
	
	@Test
	public void testDisapprovaSingolaLezione() {
		manager.disapprovaSingolaLezione("https://www.youtube.com/embed/2nCeuNPMaoY");
		
		PacchettoDao pacchetto = new PacchettoDao();
		ArrayList<LezioniBean> l = pacchetto.getLezioniByURl("https://www.youtube.com/embed/2nCeuNPMaoY");
		
		for(LezioniBean lezioni : l) {
			if((lezioni.getUrl()).equals("https://www.youtube.com/embed/2nCeuNPMaoY")) {
				assertEquals(-1, lezioni.getApprovato());
			}
		}
	}
	
	private UtenteManager manager = new UtenteManager();
}
