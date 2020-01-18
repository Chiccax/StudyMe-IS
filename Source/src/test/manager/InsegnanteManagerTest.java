package test.manager;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.bean.UtenteBean;
import model.dao.InsegnanteDao;
import model.dao.PacchettoDao;
import model.manager.InsegnanteManager;

class InsegnanteManagerTest extends Mockito {

	InsegnanteDao insegnanteMock;
	PacchettoDao pacchettoMock;
	InsegnanteManager insegnanteManager;
	
	@Test
	void testInserimentoPacchetto() {
		insegnanteMock = (InsegnanteDao)Mockito.mock(InsegnanteDao.class);
		insegnanteManager = new InsegnanteManager();
		
		PacchettoBean pacchettoMock = new PacchettoBean();
		pacchettoMock.setCodicePacchetto("pac333");
		pacchettoMock.setDescrizione("Corso sulla gestione dei processi");
		pacchettoMock.setSottocategoria("Sistema operativo");
		pacchettoMock.setPrezzo(33);
		pacchettoMock.setTitolo("Processi");
		pacchettoMock.setFoto("https://cdn01.alison-static.net/courses/1052/alison_courseware_intro_1052.jpg");
		
		PacchettoBean pacchetto = new PacchettoBean();
		
		when(insegnanteMock.inserPacchetto("pac333", "Mariarosaria", "inf001", 33 , "Corso sulla gestione processi", "Processi", "https://cdn01.alison-static.net/courses/1052/alison_courseware_intro_1052.jpg")).thenReturn(pacchettoMock);
		pacchetto = insegnanteManager.inserPacchetto("pac333", "Mariarosaria", "inf001", 33 , "Corso sulla gestione processi", "Processi", "https://cdn01.alison-static.net/courses/1052/alison_courseware_intro_1052.jpg");
		assertEquals("pac333", pacchetto.getCodicePacchetto());
	}
	
	@Test
	void testGetPacchetto() {
		pacchettoMock = (PacchettoDao)Mockito.mock(PacchettoDao.class);
		insegnanteManager = new InsegnanteManager();
		
		PacchettoBean pacchettoFake = new PacchettoBean();
		pacchettoFake.setCodicePacchetto("pac001");
		
		when(pacchettoMock.getPacchetto("pac001")).thenReturn(pacchettoFake);
		
		PacchettoBean pacchettoReal = new PacchettoBean();
		pacchettoReal = insegnanteManager.getPacchetto("pac001");
		assertEquals(pacchettoFake.getCodicePacchetto(), pacchettoReal.getCodicePacchetto());
	}
	
	@Test
	void testGetPacchettoByTitolo() {
		pacchettoMock = (PacchettoDao)Mockito.mock(PacchettoDao.class);
		insegnanteManager = new InsegnanteManager();
		
		PacchettoBean pacchettoFake = new PacchettoBean();
		pacchettoFake.setCodicePacchetto("pac001");
		
		when(pacchettoMock.getPacchettoByTitolo("Programmazione C per principianti")).thenReturn(pacchettoFake);
		
		PacchettoBean pacchettoReal = new PacchettoBean();
		pacchettoReal = insegnanteManager.getPacchettoByTitolo("Programmazione C per principianti");
		
		assertEquals(pacchettoFake.getCodicePacchetto(), pacchettoReal.getCodicePacchetto());
	}
	
	@Test
	void testUpdateCode() {
		insegnanteMock = (InsegnanteDao)Mockito.mock(InsegnanteDao.class);
			
		insegnanteMock.updateCode("pac004", "pac444");
		verify(insegnanteMock).updateCode("pac004", "pac444");
	}
	
	@Test
	void testUpdateTitle() {
		insegnanteMock = (InsegnanteDao)Mockito.mock(InsegnanteDao.class);
		
		insegnanteMock.updateTitle("pac002", "Programmazione in C ++");
		verify(insegnanteMock).updateTitle("pac002", "Programmazione in C ++");
	}
	
	@Test
	void testUpdatePrice() {
		insegnanteMock = (InsegnanteDao)Mockito.mock(InsegnanteDao.class);
		insegnanteMock.updatePrice("pac002", 50);
		verify(insegnanteMock).updatePrice("pac002", 50);
	}
	
	@Test
	void testUpdateDesc() {
		insegnanteMock = (InsegnanteDao)Mockito.mock(InsegnanteDao.class);
		insegnanteMock.updateDescr("pac002", "Imparare come usare C ++.");
		verify(insegnanteMock).updateDescr("pac002", "Imparare come usare C ++.");
	}
	
	@Test
	void testDeletePacchetto() {
		insegnanteMock = (InsegnanteDao)Mockito.mock(InsegnanteDao.class);
		insegnanteMock.deletePacchetto("pac003");
		verify(insegnanteMock).deletePacchetto("pac003");
	}
	
	@Test
	void testInsertLesson() {
		insegnanteMock = (InsegnanteDao)Mockito.mock(InsegnanteDao.class);
		insegnanteManager = new InsegnanteManager();
		
		LezioniBean lezioneMock = new LezioniBean();
		lezioneMock.setPacchetto("pac333");
		lezioneMock.setTitolo("Lezione 1 di 5");
		lezioneMock.setUrl("https://www.youtube.com/embed/XlCrxbICYcY");
		lezioneMock.setDurata("7:00");
		
		when(insegnanteMock.insertLesson("pac333", "https://www.youtube.com/embed/XlCrxbICYcY", "Lezione 1 di 5", "7:00")).thenReturn(lezioneMock);
		
		LezioniBean lezione = new LezioniBean();
		lezione = insegnanteManager.insertLesson("pac333", "https://www.youtube.com/embed/XlCrxbICYcY", "Lezione 1 di 5", "7:00");
		
		assertEquals(lezioneMock.getUrl(), lezione.getUrl());
	}
	
	@Test
	void testGetLezioniByTitle() {
		pacchettoMock = (PacchettoDao)Mockito.mock(PacchettoDao.class);
		insegnanteManager = new InsegnanteManager();
		
		ArrayList<LezioniBean> lezioniMock = new ArrayList<LezioniBean>();
		LezioniBean lezioneMock = new LezioniBean();
		lezioneMock.setUrl("https://www.youtube.com/embed/XlCrxbICYcY");
		lezioniMock.add(lezioneMock);

		when(pacchettoMock.getLezioniByTitolo("Corso Photoshop : Introduzione al corso")).thenReturn(lezioniMock);
		
		ArrayList<LezioniBean> lezione = new ArrayList<LezioniBean>();
		lezione = insegnanteManager.getLezioniByTitolo("Corso Photoshop : Introduzione al corso");
		
		assertEquals(lezioniMock.size(), lezione.size());
	}
	
	@Test
	void testGetLezioniByUrl() {
		pacchettoMock = (PacchettoDao)Mockito.mock(PacchettoDao.class);
		insegnanteManager = new InsegnanteManager();
		
		ArrayList<LezioniBean> lezioniMock = new ArrayList<LezioniBean>();
		LezioniBean lezioneMock = new LezioniBean();
		lezioneMock.setUrl("https://www.youtube.com/embed/-cyYH6qGNF8");
		lezioniMock.add(lezioneMock);

		when(pacchettoMock.getLezioniByURl("https://www.youtube.com/embed/-cyYH6qGNF8")).thenReturn(lezioniMock);
		
		ArrayList<LezioniBean> lezione = new ArrayList<LezioniBean>();
		lezione = insegnanteManager.getLezioniByURl("https://www.youtube.com/embed/-cyYH6qGNF8");
		
		assertEquals(lezioniMock.size(), lezione.size());
	}
	
	@Test
	void testUpdateTitleLesson() {
		insegnanteMock = (InsegnanteDao)Mockito.mock(InsegnanteDao.class);
		insegnanteMock.updateTitleLesson("Video lezione di Strategia Aziendale ", "Video lezione di Strategia");
		verify(insegnanteMock).updateTitleLesson("Video lezione di Strategia Aziendale ", "Video lezione di Strategia");
	}
	
	@Test
	void testUpdateDurationLesson() {
		insegnanteMock = (InsegnanteDao)Mockito.mock(InsegnanteDao.class);
		insegnanteMock.updateDurationLesson("6:26", "6:00" );
		verify(insegnanteMock).updateDurationLesson("6:26", "6:00");
	}
	
	@Test
	void testUpdateUrlLesson() {
		insegnanteMock = (InsegnanteDao)Mockito.mock(InsegnanteDao.class);
		insegnanteManager = new InsegnanteManager();
		
		insegnanteMock.updateUrlLesson("https://www.youtube.com/embed/_2gmtVuenfc", "https://www.youtube.com/embed/-cyYH6qGNF8");
		verify(insegnanteMock).updateUrlLesson("https://www.youtube.com/embed/_2gmtVuenfc", "https://www.youtube.com/embed/-cyYH6qGNF8");
	}
	
	@Test
	void testDeleteLesson() {
		insegnanteMock = (InsegnanteDao)Mockito.mock(InsegnanteDao.class);
		insegnanteManager = new InsegnanteManager();
		
		insegnanteMock.deleteLesson("Video lezione di Strategia");
		verify(insegnanteMock).deleteLesson("Video lezione di Strategia");
	}

	@Test
	void testGetAcquirenti() {
		pacchettoMock = (PacchettoDao)Mockito.mock(PacchettoDao.class);
		insegnanteManager = new InsegnanteManager();
		
		ArrayList<UtenteBean> acquirentiMock = new ArrayList<UtenteBean>();
		when(pacchettoMock.getAcquirenti("pac001")).thenReturn(acquirentiMock);
		
		ArrayList<UtenteBean> acquirenti = new ArrayList<UtenteBean>();
		acquirenti = insegnanteManager.getAcquirenti("pac001");
	
		assertEquals(acquirentiMock.size(), acquirenti.size());
	}
}