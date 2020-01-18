package test.integration.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.bean.UtenteBean;
import model.manager.InsegnanteManager;

class TestInsegnanteManager {

	@Test
	void testInserisciPacchetto() {
		PacchettoBean pacchettoInserito = new PacchettoBean();
		pacchettoInserito = manager.inserPacchetto("pac052", "Mariarosaria", "inf002", 53, "Routing e VLAN", "Corso base", "https://www.linkedin.com/media-proxy/ext?w=1200&h=675&hash=2msjBgcdOZJwVExdeOODms9IqJs%3D&ora=1%2CaFBCTXdkRmpGL2lvQUFBPQ%2CxAVta5g-0R6plxVUzgUv5K_PrkC9q0RIUJDPBy-gUyau_NWfYX_qfcHfZLSiol4ReisIlAYzeOqvSDbjFI69LcLmY4Yx3A");
		
		assertEquals("pac052", pacchettoInserito.getCodicePacchetto());
	}
	
	@Test
	void testGetPacchetto() {
		PacchettoBean pacchettoRestituito = new PacchettoBean();
		pacchettoRestituito = manager.getPacchetto("pac004");
		
		assertEquals("pac004", pacchettoRestituito.getCodicePacchetto());
	}

	@Test 
	void testGetPacchettoByTitolo() {
		PacchettoBean pacchettoRestituito = new PacchettoBean();
		pacchettoRestituito = manager.getPacchettoByTitolo("Programmazione C per principianti");
		
		System.out.println(pacchettoRestituito.getCodicePacchetto());
		assertEquals("pac001", pacchettoRestituito.getCodicePacchetto());
	}
	
	@Test
	void testUpdateCode() {
		manager.updateCode("pac005", "pac055");
		
		PacchettoBean pacchettoModificato = new PacchettoBean();
		pacchettoModificato = manager.getPacchetto("pac055");
		
		assertNotNull(pacchettoModificato);
		
	}
	
	@Test
	void testUpdateTitle() {
		manager.updateTitle("pac001", "Programmazione C");
		
		PacchettoBean pacchettoModificato = new PacchettoBean();
		pacchettoModificato = manager.getPacchettoByTitolo("Programmazione C");
		
		assertNotNull(pacchettoModificato);
	}
	
	@Test
	void testUpdatePrice() {
		manager.updatePrice("pac001", 98);
		
		PacchettoBean pacchettoModificato = new PacchettoBean();
		pacchettoModificato = manager.getPacchetto("pac001");
		
		assertEquals(98, pacchettoModificato.getPrezzo());	
	}
	
	@Test
	void testUpdateDescription() {
		manager.updateDescr("pac001", "Capire i fondamenti del linguaggio C");
		
		PacchettoBean pacchettoModificato = new PacchettoBean();
		pacchettoModificato = manager.getPacchetto("pac001");
		
		assertEquals("Capire i fondamenti del linguaggio C", pacchettoModificato.getDescrizione());
	}

	@Test
	void testDeletePacchetto() {
		manager.deletePacchetto("pac003");
		
		PacchettoBean pacchettoEliminato = new PacchettoBean();
		pacchettoEliminato = manager.getPacchetto("pac003");
		
		assertEquals(0, pacchettoEliminato.getNelCatalogo());
	}
	
	@Test
	void testInsertLesson(){
		LezioniBean lezioneInserita = new LezioniBean();
		lezioneInserita = manager.insertLesson("pac001", "https://www.youtube.com/embed/65fCTkSA4EA", "Operatori", "19:00");
		
		assertNotNull(lezioneInserita);
	}
	
	@Test
	void testGetLezioniByTitolo() {
		ArrayList<LezioniBean> lezioni = new ArrayList<LezioniBean>();
		lezioni = manager.getLezioniByTitolo("Operatori");
		
		assertNotNull(lezioni);
	}
	
	@Test
	void testGetLezioniByUrl() {
		ArrayList<LezioniBean> lezioni = new ArrayList<LezioniBean>();
		lezioni = manager.getLezioniByURl("https://www.youtube.com/embed/65fCTkSA4EA");
		
		assertNotNull(lezioni);
	}
	
	@Test
	void testUpdateTitleLesson() {
		manager.updateTitleLesson("Operatori", "Operatori, if");
		
		ArrayList<LezioniBean> lezioneModificata = new ArrayList<LezioniBean>();
		lezioneModificata = manager.getLezioniByTitolo("Operatori, if");
		
		assertNotNull(lezioneModificata);
	}
	
	@Test
	void testUpdateDurationLesson() {
		manager.updateDurationLesson("Corso Photoshop : Introduzione al corso", "1:98");
		
		ArrayList<LezioniBean> lezioneModificata = new ArrayList<LezioniBean>();
		lezioneModificata = manager.getLezioniByTitolo("Corso Photoshop : Introduzione al corso");
		String durata = null;
		for(LezioniBean l : lezioneModificata) {
			durata = l.getDurata();
		}
		
		assertEquals("1:98", durata);
	}
	
	@Test
	void testDeleteLesson() {
		manager.deleteLesson("Corso di spagnolo parte 4");
		
		ArrayList<LezioniBean> lezioneModificata = new ArrayList<LezioniBean>();
		lezioneModificata = manager.getLezioniByTitolo("Corso di spagnolo parte 4");

		assertEquals(0, lezioneModificata.size());
	}
	
	@Test
	void testGetAcquirenti() {
		ArrayList<UtenteBean> acquirenti = new ArrayList<UtenteBean>();
		acquirenti = manager.getAcquirenti("pac001");
		
		assertEquals(0, acquirenti.size());
	}
	
	@Test
	void testUpdateUrlLesson() {
		manager.updateUrlLesson("Tutorial: Violino", "https://www.youtube.com/embed/QFKVHhW_d1A");
		
		ArrayList<LezioniBean> lezione = new ArrayList<LezioniBean>();
		lezione = manager.getLezioniByURl("https://www.youtube.com/embed/QFKVHhW_d1A");
		
		assertNotNull(lezione);
	}
	
	private InsegnanteManager manager = new InsegnanteManager();
}
