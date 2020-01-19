package test.dao;



import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.bean.LezioniBean;
import model.bean.OrdineBean;
import model.bean.PacchettoBean;
import model.dao.InsegnanteDao;
import model.dao.PacchettoDao;
import model.manager.UtenteManager;

class InsegnanteDaoTest {
	InsegnanteDao insegnante;
	PacchettoDao pacchetto= new PacchettoDao();
	@AfterEach
	protected void tearDown() throws Exception {
		insegnante.updateCode("PAC048","pac048");
		insegnante.updateDescr("pac001", "C &egrave; un linguaggio utile per quasi tutti i programmatori di computer. Alla fine di questo corso, capirai i fondamenti del linguaggio di programmazione C e ti renderai pi&ugrave; commerciabile per le posizioni di programmazione entry level2");	
		insegnante.updateTitle("Programmazione C per principianti2", "Programmazione C per principianti");
		insegnante.updateTitleLesson("nuovo nome", "Facebook per il business : Differenza tra profilo e pagina");
		insegnante.updateUrlLesson("https://www.youtube.com/embed/-H5XKA_YDhA", "https://www.youtube.com/embed/05C9JNZAUdM");
		
	}
	@BeforeEach
	void setUp() throws Exception{
		insegnante = new InsegnanteDao();
	}

	@Test
	void testInsertPacchetto() {
		String nuovoCodice= "pac455";
		String nomeUtente="Rachele";
		String nuovaSottocategoria="fot003";
		double nuovoPrezzo= 10.00;
		String nuovaDescrizione="Corso di fotografia avanzato2"; 
		String nuovoTitolo="la fotografia "; 
		String nuovaFoto="img/pacchetti/autocad.jpg";
		
		PacchettoBean pacchetto=new PacchettoBean();
		pacchetto= insegnante.inserPacchetto(nuovoCodice, nomeUtente, nuovaSottocategoria, nuovoPrezzo, nuovaDescrizione, nuovoTitolo, nuovaFoto);
		assertNotNull(pacchetto);
	}
	
	@Test 
	void testdeletePacchetto() {
		String codicePacchetto= "pac004";
		insegnante.deletePacchetto(codicePacchetto);
		
		PacchettoDao pacchetto=new PacchettoDao();
		insegnante.deletePacchetto(codicePacchetto);
		PacchettoBean p= pacchetto.getPacchetto(codicePacchetto);
		assertEquals(0,p.getNelCatalogo());	
	}

	@Test 
	void testupdateCode() {
		PacchettoBean p= new PacchettoBean();
		p.setCodicePacchetto("pac048");
		insegnante.updateCode(p.getCodicePacchetto(), "PAC048");
		PacchettoBean pacchettoEsistente1 = pacchetto.getPacchetto("PAC048");
		assertNotNull(pacchettoEsistente1);
	}
	
	@Test
	void testupdateTitolo() {
		PacchettoBean p= new PacchettoBean();
		p.setCodicePacchetto("pac001");
		insegnante.updateTitle("pac001", "Programmazione C per principianti2");
		PacchettoBean pacchettoEsistente= pacchetto.getPacchettoByTitolo("Programmazione C per principianti2");
		assertNotNull(pacchettoEsistente);
	}

	@Test 
	void testupdatePrice() {
		PacchettoBean p= new PacchettoBean();
		p.setCodicePacchetto("pac001");
		p.setTitolo("Programmazione C per principianti");
		p.setPrezzo(21.9);
		
		insegnante.updatePrice(p.getCodicePacchetto(), 21.9);
		PacchettoBean pacchettoEsistente1 = pacchetto.getPacchettoByTitolo(p.getTitolo());
		assertEquals(p.getPrezzo(),pacchettoEsistente1.getPrezzo());
	}
	@Test 
	void testUpdateDesc() {
		PacchettoBean p= new PacchettoBean();
		p.setCodicePacchetto("pac001");
		
		insegnante.updateDescr(p.getCodicePacchetto(), "bla bla bla bla bla");	
		PacchettoBean pacchettoEsistente1 = pacchetto.getPacchetto(p.getCodicePacchetto());
		assertEquals("bla bla bla bla bla", pacchettoEsistente1.getDescrizione());

	}
	
	@Test
	void testInsertLesson() {
		String codiceP="pac003";
		String url="https://www.youtube.com/embed/tsxwGnDfvWE";
		String titolo="provatest25"; 
		String durata="12:00";
		LezioniBean lezione=new LezioniBean();
		lezione= insegnante.insertLesson(codiceP, url, titolo, durata);
		assertEquals(url,lezione.getUrl());
	}

	@Test
	void testUpdateTitleLesson() {
		LezioniBean p= new LezioniBean();
		p.setTitolo("\"Facebook per il business : Differenza tra profilo e pagina\"");
		insegnante.updateTitleLesson(p.getTitolo(), "nuovo nome");
		ArrayList<LezioniBean> lezioneTitoloEsistente = pacchetto.getLezioniByTitolo("nuovo nome");
		assertNotNull(lezioneTitoloEsistente);
	}
	
	@Test
	void testUpdateUrlLesson() {
		LezioniBean p= new LezioniBean();
		p.setUrl("https://www.youtube.com/embed/05C9JNZAUdM");
		insegnante.updateUrlLesson("https://www.youtube.com/embed/05C9JNZAUdM", "https://www.youtube.com/embed/-H5XKA_YDhA");
		ArrayList<LezioniBean> lezioneEsistente = pacchetto.getLezioniByURl("https://www.youtube.com/embed/-H5XKA_YDhA");
		assertNotNull(lezioneEsistente);

	}
	@Test 
	void testUpdateDurationLesson() {
		LezioniBean p= new LezioniBean();
		p.setTitolo("Facebook per il business : Differenza tra profilo e pagina");
		
		insegnante.updateDurationLesson(p.getTitolo(),"11:11");
		ArrayList<LezioniBean> lezione = pacchetto.getLezioniByTitolo(p.getTitolo());
		
		assertEquals("11:11",lezione.get(0).getDurata());	
		}
	
	@Test
	void testGetOrdine() {
		String nomeCliente="Pasquale";
		
		List<OrdineBean> ordini = new ArrayList<OrdineBean>();
		ordini = insegnante.getOrdine(nomeCliente);
		assertNotNull(ordini);
		
	}
	
}
