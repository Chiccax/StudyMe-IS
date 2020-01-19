package test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.bean.RecensioneBean;
import model.dao.PacchettoDao;
import model.dao.RecensioneDao;

class TC_RecensioneDao {
	
	RecensioneDao recensione;
		
	@BeforeEach
	void setUp() throws Exception{
		recensione = new RecensioneDao();
	}

	@Test
	void testAggiungiRecensione() {
		String nomeUtente= "Damiana";
		String codicePacchetto="pac002";
		String titoloRecensione="Programmazione";
		String testoRecensione="Questo corso è stato interessante";

		recensione.aggiungiRecensione(nomeUtente, codicePacchetto, titoloRecensione, testoRecensione);
		ArrayList<RecensioneBean> recensioni = new ArrayList<RecensioneBean>();
		
		PacchettoDao p = new PacchettoDao();
		recensioni = p.getRecensioni("pac002");
		
		for(RecensioneBean r : recensioni) {
			if((r.getCliente()).equals("Damiana")) {
				String res = r.getCliente();
				assertNotNull(res);
			}
		}
	}
	
	@Test
	void isAlwreadyReviewed() throws SQLException {
		String nomeUtente= "Damiana";
		String codicePacchetto = "pac002";
			
		boolean resp= recensione.isAlwreadyReviewed(nomeUtente,codicePacchetto);
		assertEquals(true, resp);
	}
}