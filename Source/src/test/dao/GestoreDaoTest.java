package test.dao;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.dao.GestoreDao;
import model.dao.InsegnanteDao;
import model.dao.PacchettoDao;

class GestoreDaoTest {

	GestoreDao gestore;
	
	@BeforeEach 
	void setup() throws Exception{
		gestore=new GestoreDao();
	}
	@Test
	void testapprovaInteroPacchetto() {
		
		 PacchettoBean p= new PacchettoBean();
		 PacchettoDao pacchettodao= new PacchettoDao();
		 p.setCodicePacchetto("pac005");
		
		 gestore.approvaInteroPacchetto(p.getCodicePacchetto());
		 PacchettoBean pacchettoEsistente = pacchettodao.getPacchetto(p.getCodicePacchetto());
		 System.out.println(pacchettoEsistente.getCodicePacchetto());
		 assertNotNull(pacchettoEsistente);
		 assertEquals(1, pacchettoEsistente.getApprovato());
		
	}
	@Test
	void disapprovaInteroPacchetto() {
		PacchettoDao pacchettodao= new PacchettoDao();
		PacchettoBean p= new PacchettoBean();
		p.setCodicePacchetto("pac005");
		gestore.disapprovaInteroPacchetto(p.getCodicePacchetto());
		PacchettoBean pacchettoEsistente = pacchettodao.getPacchetto(p.getCodicePacchetto());
		assertNotNull(pacchettoEsistente);	
		assertEquals(-1, pacchettoEsistente.getApprovato());
	}
	@Test
	void testapprovaSingolaLezione() {
		PacchettoDao pacchettodao= new PacchettoDao();
		LezioniBean p= new LezioniBean();
		p.setUrl("https://www.youtube.com/embed/WWWJHIjj00U");
		gestore.approvaSingolaLezione(p.getUrl());
		ArrayList<LezioniBean> lezioneEsistente = pacchettodao.getLezioniByURl("https://www.youtube.com/embed/-H5XKA_YDhA");
		
		assertEquals(1, lezioneEsistente.get(0).getApprovato());
			}
	@Test
	void testdisapprovaSingolaLezione() {
		LezioniBean p= new LezioniBean();
		PacchettoDao pacchettodao= new PacchettoDao();
			
		p.setUrl("https://www.youtube.com/embed/WWWJHIjj00U");
		gestore.disapprovaSingolaLezione(p.getUrl());
		ArrayList<LezioniBean> lezioneEsistente = pacchettodao.getLezioniByURl("https://www.youtube.com/embed/-H5XKA_YDhA");
		assertEquals(1, lezioneEsistente.get(0).getApprovato());

	}
}
