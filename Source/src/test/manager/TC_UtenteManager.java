package test.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.bean.UtenteBean;
import model.dao.GestoreDao;
import model.dao.UtenteDao;
import model.manager.UtenteManager;

class TC_UtenteManager extends Mockito{
	
	GestoreDao gestoreMock;
	UtenteDao utenteMock;
	UtenteManager utenteManager;
	
	@Test
	void testLogin() throws IOException{
		utenteMock = (UtenteDao)Mockito.mock(UtenteDao.class);
		utenteManager = new UtenteManager();
		String password = "cuoca";
		String passwordBase64format  = Base64.getEncoder().encodeToString(password.getBytes()); 
		UtenteBean utenteLoggato = new UtenteBean();
		utenteLoggato.setNomeUtente("Martina");
		utenteLoggato.setPassword(passwordBase64format);
		
		UtenteBean utenteM = new UtenteBean();
	
		when(utenteMock.login("Martina", passwordBase64format)).thenReturn(utenteLoggato);
		utenteM = utenteManager.login("Martina", passwordBase64format);
		assertEquals(utenteM.getNomeUtente(), utenteLoggato.getNomeUtente());
	}
	
	@Test
	void testModificaEmail() throws IOException{
		utenteMock = (UtenteDao)Mockito.mock(UtenteDao.class);
		utenteManager = new UtenteManager();
		
		boolean res;
		when(utenteMock.updateEmail("mesposito@gmail.com", "chiccaesp98@gmail.com")).thenReturn(true);
		res = utenteManager.setEmail("mesposito@gmail.com", "chiccaesp98@gmail.com");
		assertEquals(res, true);
	}
	
	@Test 
	void testModificaPassword() throws IOException{
		utenteMock = (UtenteDao)Mockito.mock(UtenteDao.class);
		utenteManager = new UtenteManager();
		
		String password = "Mariarosaria";
		String passwordBase64format  = Base64.getEncoder().encodeToString(password.getBytes()); 
		
		boolean res;
		when(utenteMock.updatePassword("mesposito@gmail.com", passwordBase64format)).thenReturn(true);
		res = utenteManager.setPassword("mesposito@gmail.com", passwordBase64format);
		assertEquals(res, true);
	}
	
	@Test
	void testRegistrazione()throws IOException{
		utenteMock = (UtenteDao)Mockito.mock(UtenteDao.class);
		utenteManager = new UtenteManager();
		String password = "Fulgione";
		String passwordBase64format  = Base64.getEncoder().encodeToString(password.getBytes()); 
		boolean res;
		when(utenteMock.registration("anna@live.it", "AnnaF", passwordBase64format)).thenReturn(true);
		res = utenteManager.registrazione("anna@live.it", "AnnaF", passwordBase64format);
		assertEquals(res, true);
	}

	@Test
	void testVisualizzazionePacchettiDaApprovare() throws IOException{
		gestoreMock = (GestoreDao)Mockito.mock(GestoreDao.class);
		utenteManager = new UtenteManager();
		
		ArrayList<PacchettoBean> pacchettiMock = new ArrayList<PacchettoBean>();
		PacchettoBean pacchetto = new PacchettoBean();
		pacchettiMock.add(pacchetto);
		
		when(gestoreMock.visualizzaPacchettiDaApprovare()).thenReturn(pacchettiMock);
		
		ArrayList<PacchettoBean> pacchetti = new ArrayList<PacchettoBean>();
		pacchetti = utenteManager.visualizzaPacchettiDaApprovare();
		assertEquals( pacchettiMock.size(), pacchetti.size());
 	}
	
	@Test
	void testVisualizzaLezioniDaApprovare() throws IOException{
		gestoreMock = (GestoreDao)Mockito.mock(GestoreDao.class);
		utenteManager = new UtenteManager();
		
		ArrayList<LezioniBean> lezioniMock = new ArrayList<LezioniBean>();
		LezioniBean lezione = new LezioniBean();
		lezioniMock.add(lezione);
		when(gestoreMock.visualizzaLezioniDaApprovare()).thenReturn(lezioniMock);
		
		ArrayList<LezioniBean> lezioni = new ArrayList<LezioniBean>();
		lezioni = utenteManager.visualizzaLezioniDaApprovare();
		assertEquals(lezioni.size(), lezioniMock.size());
	}
	
	@Test
	void testApprovaInteroPacchetto() throws IOException{
		gestoreMock = (GestoreDao)Mockito.mock(GestoreDao.class);
		utenteManager = new UtenteManager();
		utenteManager.setDao(gestoreMock);
		utenteManager.approvaInteroPacchetto("pac051");
		verify(gestoreMock).approvaInteroPacchetto("pac051");
	}
	
	@Test
	void testDisapprovaInteroPacchetto() throws IOException{
		gestoreMock = (GestoreDao)Mockito.mock(GestoreDao.class);
		utenteManager = new UtenteManager();
		utenteManager.setDao(gestoreMock);
		utenteManager.disapprovaInteroPacchetto("pac051");
		verify(gestoreMock).disapprovaInteroPacchetto("pac051");
	}
	
	@Test
	void testApprovaSingolaLezione() throws IOException{
		gestoreMock = (GestoreDao)Mockito.mock(GestoreDao.class);
		utenteManager = new UtenteManager();
		utenteManager.setDao(gestoreMock);
		utenteManager.approvaSingolaLezione("https://www.youtube.com/embed/ScVr-Kyq6Ws");
		verify(gestoreMock).approvaSingolaLezione("https://www.youtube.com/embed/ScVr-Kyq6Ws");
	}
	
	@Test
	void testDisapprovaSingolaLezione() throws IOException{
		gestoreMock = (GestoreDao)Mockito.mock(GestoreDao.class);
		utenteManager = new UtenteManager(); 
		utenteManager.setDao(gestoreMock);
		utenteManager.disapprovaSingolaLezione("https://www.youtube.com/embed/05C9JNZAUdM");
		verify(gestoreMock).disapprovaSingolaLezione("https://www.youtube.com/embed/05C9JNZAUdM");
	}
}