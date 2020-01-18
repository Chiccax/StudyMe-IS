package test.dao;



import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.bean.LezioniBean;
import model.bean.OrdineBean;
import model.bean.PacchettoBean;
import model.dao.InsegnanteDao;
import model.dao.PacchettoDao;

class InsegnanteDaoTest {
	InsegnanteDao insegnante;
	
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
		assertEquals(nuovoCodice, pacchetto.getCodicePacchetto());
	}
	
	@Test 
	void testdeletePacchetto() {
		String codicePacchetto= "pac456";
	
		PacchettoDao pacchetto= new PacchettoDao();
		insegnante.deletePacchetto(codicePacchetto);
		assertEquals(0, pacchetto.getPacchetto(codicePacchetto));
	}
	
	@Test 
	void testupdateCode() {
		String vecchioCodice= "pac600";
		String nuovoCodice="pac001";
		insegnante.updateCode(vecchioCodice, nuovoCodice);
		
	}
	
	@Test
	void testupdateTitolo() {
		String vecchioCodice= "pac001";
		String nuovoTitolo="Programmazione C per principianti2";
		insegnante.updateTitle(vecchioCodice, nuovoTitolo);
	}

	@Test 
	void testupdatePrice() {
		String vecchioCodice= "pac001";
		double nuovoPrezzo=89.00;
		insegnante.updatePrice(vecchioCodice, nuovoPrezzo);
	}
	

	@Test 
	void testupdateDesc() {
		String vecchioCodice= "pac001";
		String nuovaDescrizione="C &egrave; un linguaggio utile per quasi tutti i programmatori di computer. Alla fine di questo corso, capirai i fondamenti del linguaggio di programmazione C e ti renderai pi&ugrave; commerciabile per le posizioni di programmazione entry level2";
		insegnante.updateDescr(vecchioCodice, nuovaDescrizione);
	}
	
	@Test
	void testinsertLesson() {
		String codiceP="pac003";
		String url="https://www.youtube.com/embed/tsxwGnDfvWE";
		String titolo="provatest25"; 
		String durata="12:00";
		
		LezioniBean lezione=new LezioniBean();
		lezione= insegnante.insertLesson(codiceP, url, titolo, durata);
		assertEquals(url,lezione.getUrl());
	}

	@Test
	void testupdateTitleLesson() {
		String vecchioTitolo= "Corso Photoshop : Creare, salvare e aprire un file";
		String nuovoTitolo="Corso Photoshop : Creare, salvare un file";
		insegnante.updateTitleLesson(vecchioTitolo, nuovoTitolo);
	}
	
	@Test
	void testupdateUrlLesson() {
		String vecchioTitolo="Corso Photoshop : Creare, salvare e aprire un file";
		String nuovoUrl="https://www.youtube.com/embed/_2gmtVuenfc";
		
		insegnante.updateUrlLesson(vecchioTitolo, nuovoUrl);
	}
	
	@Test 
	void testupdateDurationLesson() {
		String vecchioTitolo="Corso Photoshop : Creare, salvare e aprire un file";
		String nuovaDurata="12:00";
		
		insegnante.updateDurationLesson(vecchioTitolo, nuovaDurata);
	}
	
	@Test
	void testDeleteLesson() {
		String titolo="provatest";
	
		insegnante.deleteLesson(titolo);
		
	}
	
	@Test
	void testgetOrdine() {
		String nomeCliente="Pasquale";
		
		List<OrdineBean> ord = new ArrayList<OrdineBean>();
		List<OrdineBean> ordini = new ArrayList<OrdineBean>();
		ordini = insegnante.getOrdine(nomeCliente);
		assertEquals(ord, ordini);
		
	}
	
};
