package test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.bean.UtenteBean;
import model.dao.RecensioneDao;

class RecensioneDaoTest {
	
	RecensioneDao recensione;
		
	@BeforeEach
	void setUp() throws Exception{
		recensione = new RecensioneDao();
	}
	
	@Test
	void isAlwreadyReviewed() throws SQLException {
		String nomeUtente="Pasquale";
		String codicePacchetto="pac002";
			
		boolean resp= recensione.isAlwreadyReviewed(nomeUtente,codicePacchetto);
		assertEquals(true, resp);
		}
	
	@Test
	void testAggiungiRecensione() {
		String nomeUtente= "Pasquale";
		String codicePacchetto="pac002";
		String titoloRecensione="Programmazione";
		String testoRecensione="questo corso è stato interessante";
			
		//UtenteBean utente= new UtenteBean();
		recensione.aggiungiRecensione(nomeUtente, codicePacchetto, titoloRecensione, testoRecensione);
		//assertEquals(nomeUtente, utente.getNomeUtente());
	}

}
	
