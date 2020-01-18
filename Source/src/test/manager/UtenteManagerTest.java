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

class UtenteManagerTest extends Mockito{
	
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
		String password = "Giorgio92";
		String passwordBase64format  = Base64.getEncoder().encodeToString(password.getBytes()); 
		boolean res;
		when(utenteMock.registration("giorgio@gmail.com", "Giorgio", passwordBase64format)).thenReturn(true);
		res = utenteManager.registrazione("giorgio@gmail.com", "Giorgio", passwordBase64format);
		assertEquals(res, true);
	}

	@Test
	void testVisualizzazionePacchettiDaApprovare() throws IOException{
		gestoreMock = (GestoreDao)Mockito.mock(GestoreDao.class);
		utenteManager = new UtenteManager();
		
		ArrayList<PacchettoBean> pacchettiMock = new ArrayList<PacchettoBean>();
		when(gestoreMock.visualizzaPacchettiDaApprovare()).thenReturn(pacchettiMock);
		
		ArrayList<PacchettoBean> pacchetti = new ArrayList<PacchettoBean>();
		pacchetti = utenteManager.visualizzaPacchettiDaApprovare();
		assertEquals(pacchetti.size(), pacchettiMock.size());
 	}
	
	@Test
	void testVisualizzaLezioniDaApprovare() throws IOException{
		gestoreMock = (GestoreDao)Mockito.mock(GestoreDao.class);
		utenteManager = new UtenteManager();
		
		ArrayList<LezioniBean> lezioniMock = new ArrayList<LezioniBean>();
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
		utenteManager.approvaInteroPacchetto("pac001");
		verify(gestoreMock).approvaInteroPacchetto("pac001");
	}
	
	@Test
	void testDisapprovaInteroPacchetto() throws IOException{
		gestoreMock = (GestoreDao)Mockito.mock(GestoreDao.class);
		utenteManager = new UtenteManager();
		utenteManager.setDao(gestoreMock);
		utenteManager.disapprovaInteroPacchetto("pac001");
		verify(gestoreMock).disapprovaInteroPacchetto("pac001");
	}
	
	@Test
	void testApprovaSingolaLezione() throws IOException{
		gestoreMock = (GestoreDao)Mockito.mock(GestoreDao.class);
		utenteManager = new UtenteManager();
		utenteManager.setDao(gestoreMock);
		utenteManager.approvaSingolaLezione("https://www.youtube.com/embed/_2gmtVuenfc");
		verify(gestoreMock).approvaSingolaLezione("https://www.youtube.com/embed/_2gmtVuenfc");
	}
	
	@Test
	void testDisapprovaSingolaLezione() throws IOException{
		gestoreMock = (GestoreDao)Mockito.mock(GestoreDao.class);
		utenteManager = new UtenteManager(); 
		utenteManager.setDao(gestoreMock);
		utenteManager.disapprovaSingolaLezione("https://www.youtube.com/embed/_2gmtVuenfc");
		verify(gestoreMock).disapprovaSingolaLezione("https://www.youtube.com/embed/_2gmtVuenfc");
	}
}