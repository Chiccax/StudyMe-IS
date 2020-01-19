package test.dao;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

import model.bean.CategoriaBean;
import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.bean.RecensioneBean;
import model.bean.UtenteBean;
import model.dao.PacchettoDao;

class TC_PacchettoDao {

	PacchettoDao pacchetto;
	
	@BeforeEach
	void setUp() throws Exception{
		pacchetto= new PacchettoDao();
	}
	
	@Test
	void testGetAllPacchetti() {
		List<PacchettoBean> pacchetti= new ArrayList<PacchettoBean>();
		pacchetti = pacchetto.getAllPacchetti();
		assertNotNull(pacchetti);
	}
	
	@Test
	void testGetPacchetto() {
		String codiceP="pac001";
		
		PacchettoBean pac = new PacchettoBean();
		pac = pacchetto.getPacchetto(codiceP);
		
		assertNotNull(pac);
	}
	
	@Test
	void testGetPacchettoByTitolo() {
		String titolo = "Programmazione C per principianti";
		PacchettoBean p = new PacchettoBean();
		p = pacchetto.getPacchettoByTitolo(titolo);
		assertNotNull(p);
	}
	
	/*@Test
	void testDeletePacchetto() {
		InsegnanteDao dao = new InsegnanteDao();
		String codiceP="pac004";

		dao.deletePacchetto(codiceP);
		
		PacchettoBean p = pacchetto.getPacchetto(codiceP);
		assertEquals(0, p.getNelCatalogo());
	}*/ 
	
	@Test
	void testSearchPackage() {
		String word = "Programmazione";
		
		List<PacchettoBean> pacchetti= new ArrayList<PacchettoBean>();
		pacchetti = pacchetto.searchPackage(word);
		assertNotNull(pacchetti);
	}
	
	@Test 
	void testGetCategoriaRaggruppato() {
		String categoria="Sviluppo";
		
		Map<String, ArrayList<PacchettoBean>> result = new HashMap<String, ArrayList<PacchettoBean>>();
		result = pacchetto.getCategoriaRaggruppato(categoria);
		assertNotNull(result);
	}
	
	@Test 
	void testGetCategoriaRaggruppatoApprovato() {
		String categoria="Sviluppo";
	
		Map<String, ArrayList<PacchettoBean>> result = new HashMap<String, ArrayList<PacchettoBean>>();
		result = pacchetto.getCategoriaRaggruppatoApprovato(categoria);
		assertNotNull(result);
	}
	
	@Test
	void testGetBeanCategoria() {
		String categoria="Sviluppo";
		
		CategoriaBean cat = new CategoriaBean();
		cat = pacchetto.getBeanCategoria(categoria);
		assertNotNull(cat);
	}
	
	@Test
	void testGetLezioni() {
		String codicePacchetto="pac005";

		List<LezioniBean> lezioni= new ArrayList<LezioniBean>();
	
		lezioni = pacchetto.getLezioni(codicePacchetto);
		assertNotNull(lezioni);
	}
	
	@Test 
	void testGetLezioniByURl() {
		String url="https://www.youtube.com/embed/G-smnUJNvnc";
		
		List<LezioniBean> lezioni= new ArrayList<LezioniBean>();
		
		lezioni = pacchetto.getLezioniByURl(url);
		assertNotNull(lezioni);
	}
	
	@Test
	void testGetLezioniByTitolo() {
		String titolo="Compilatori ed interpreti";
		List<LezioniBean> lezioni= new ArrayList<LezioniBean>();
		
		lezioni = pacchetto.getLezioniByTitolo(titolo);
		assertNotNull(lezioni);
	}
	
	@Test
	void testGetLezioniApprovate() {
		String codicePacchetto="pac002";
		List<LezioniBean> lezioni= new ArrayList<LezioniBean>();
		
		lezioni = pacchetto.getLezioniApprovate(codicePacchetto);
		assertNotNull(lezioni);
	}
	
	@Test
	void testGetRecensioni() {
		String codicePacchetto="pac001";
		ArrayList<RecensioneBean> r = new ArrayList<RecensioneBean>();
		r = pacchetto.getRecensioni(codicePacchetto);
		assertNotNull(r);
	}
	@Test 
	void testGetAcquirenti() {
		String codicePacchetto="pac002";
		
		ArrayList<UtenteBean> utenti = new ArrayList<UtenteBean>();
		utenti = pacchetto.getAcquirenti(codicePacchetto);
		assertNotNull(utenti);
	}
}
