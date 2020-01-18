package test.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.dao.PacchettoDao;

class PacchettoDaoTest {

	PacchettoDao pacchetto;
	
	@BeforeEach
	void setUp() throws Exception{
		pacchetto= new PacchettoDao();
	}
	
	@Test
	void testgetAllPacchetti() {
		List<PacchettoBean> pac= new ArrayList<PacchettoBean>();
		List<PacchettoBean> pacchetti= new ArrayList<PacchettoBean>();
		pacchetti = pacchetto.getAllPacchetti();
		assertEquals(pac,pacchetti);
	}
	@Test
	void testgetPacchetto() {
		String codiceP="pac455";
		
		pacchetto.getPacchetto(codiceP);
		assertEquals(codiceP,pacchetto.getPacchetto(codiceP));	
	}
	@Test
	void testgetPacchettoByTitolo() {
		String titolo="Corso di spagnolo per principianti - Impara la lingua spagnola";
		
		pacchetto.getLezioniByTitolo(titolo);
		assertEquals(titolo,pacchetto.getLezioniByTitolo(titolo));
	}
	@Test
	void testdeletePacchetto() {
		String codiceP="pac458";

		boolean resp=pacchetto.delPacchetto(codiceP);
		assertEquals(true, resp);
	}
	@Test
	void testsearchPackage() {
		String word="programmazione";
		
		List<PacchettoBean> pacchetti= new ArrayList<PacchettoBean>();
		pacchetti= pacchetto.searchPackage(word);
	}
	@Test 
	void getCategoriaRaggruppato() {
		String categoria="Sviluppo";
		
		pacchetto.getCategoriaRaggruppato(categoria);
		assertEquals(categoria,pacchetto.getCategoriaRaggruppato(categoria));
	}
	@Test 
	void testgetCategoriaRaggruppatoApprovato() {
		String categoria="Sviluppo";
	
		pacchetto.getCategoriaRaggruppatoApprovato(categoria);
		assertEquals(categoria,pacchetto.getCategoriaRaggruppatoApprovato(categoria));
	}
	@Test
	void testgetBeanCategoria() {
		String categoria="Sviluppo";
		
		pacchetto.getBeanCategoria(categoria);
		assertEquals(categoria, pacchetto.getBeanCategoria(categoria));
	}
	@Test
	void testgetLezioni() {
		String codicePacchetto="pac005";
		
		List<LezioniBean> lez= new ArrayList<LezioniBean>();
		List<LezioniBean> lezioni= new ArrayList<LezioniBean>();
	
		pacchetto.getLezioni(codicePacchetto);
		assertEquals(lez, lezioni);
	}
	@Test 
	void testgetLezioniByURl() {
		String url="https://www.youtube.com/embed/G-smnUJNvnc";
		
		pacchetto.getLezioniByURl(url);
		assertEquals(url, pacchetto.getLezioniByURl(url));
	}
	@Test
	void testgetLezioniByTitolo() {
		String titolo="Compilatori ed interpreti";
		
		pacchetto.getLezioniByTitolo(titolo);
		assertEquals(titolo,pacchetto.getLezioniByTitolo(titolo));
	}
	@Test
	void testgetLezioniApprovate() {
		String codicePacchetto="pac002";
		
		pacchetto.getLezioniApprovate(codicePacchetto);
		assertEquals(codicePacchetto,pacchetto.getLezioniApprovate(codicePacchetto));
	}
	@Test
	void testgetRecensioni() {
		String codicePacchetto="pac001";
		
		pacchetto.getRecensioni(codicePacchetto);
		assertEquals(codicePacchetto,pacchetto.getRecensioni(codicePacchetto));
	}
	@Test 
	void testgetAcquirenti() {
		String codicePacchetto="pac002";
		
		pacchetto.getAcquirenti(codicePacchetto);
		assertEquals(codicePacchetto,pacchetto.getAcquirenti(codicePacchetto));
	}
}
