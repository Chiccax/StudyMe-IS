package test.integration.servlet;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;

import control.InsegnanteServlet;
import control.util.JSONResponse;
import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.bean.UtenteBean;
import model.dao.InsegnanteDao;
import model.manager.InsegnanteManager;
import model.manager.SottocategoriaManager;
import model.manager.UtenteManager;

class Test_InsegnanteServlet extends Mockito{

	 InsegnanteServlet servlet= new InsegnanteServlet();
	 PacchettoBean pacchettoEsistente1 =new  PacchettoBean ();
	 PacchettoBean pacchettoEsistente2 =null;
	 String nuovoCodice ="pac120";
	 
	 InsegnanteServlet insegnanteServlet= new InsegnanteServlet();
	 HttpServletRequest request = (HttpServletRequest)Mockito.mock(HttpServletRequest.class);
	 HttpServletResponse response = (HttpServletResponse)Mockito.mock(HttpServletResponse.class);
	    Gson gson = new Gson();
		JSONResponse jsonResponse = new JSONResponse(true);
		StringWriter stringWriter = new StringWriter();
	    PrintWriter writer = new PrintWriter(stringWriter);
	    PacchettoBean pacchettoEsistente =new PacchettoBean();
	    PacchettoBean pacchettoEsistenteConCodice= new PacchettoBean ();
	   
		@AfterEach
		protected void tearDown() throws Exception {
			UtenteManager manager= new UtenteManager();
			manager.setEmail("claudia@alice.it", "claudiabuono99@gmail.com");
			manager.setPassword("claudiabuono99@gmail.com", "Y2xhdWRpYWJ1b25v");
			InsegnanteDao insegnanteDao= new InsegnanteDao();
			insegnanteDao.updateTitleLesson("corso foto","Fotografia di matrimonio");
			insegnanteDao.deletePacchetto("p111");
			insegnanteDao.updateUrlLesson("https://www.youtube.com/embed/2s0d0U33gNQ","https://www.youtube.com/embed/_2gmtVuenfc");
			insegnanteDao.deleteLesson("p111");
			insegnanteDao.updateTitle("pachetto1", "titolo2");
			insegnanteDao.deletePacchetto("pachetto1");
		}
	  
	    Double prezzo= 21.7;
	    String vecchioCodice= "pac121";
	    String nuovoPrezzo= "21.1";
	    String nuovoTitolo= "prova2";
	    String nuovaDescrizione="bla bla bla bla ba bla ba";
	    String sottocategoria= "svi002";
	    String titolo= "java 8";
	    String nuovaFoto="http://qui";
	    String url="http://qui";
	    String durata="11:11";
	  
	@Test
	void cambiaCodice() throws ServletException, IOException {
		when(request.getParameter("azione")).thenReturn("cambiaCodice");
		when(request.getParameter("nuovoCodice")).thenReturn(nuovoCodice);
		when(response.getWriter()).thenReturn(writer);
	
		servlet.doGet(request, response);
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"OK\"}"));
	}
	
	@Test
	void cambiaCodiceNullo() throws ServletException, IOException {
		when(request.getParameter("azione")).thenReturn("cambiaCodice");
	    when(request.getParameter("nuovoCodice")).thenReturn("");
	    when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"OK\"}"));
	}
	
	@Test
	void cambiaTitolo() throws ServletException, IOException 
	{
		when(request.getParameter("azione")).thenReturn("cambiaTitolo");
		when(request.getParameter("nuovoTitolo")).thenReturn(nuovoTitolo);
		when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"OK\"}"));
	}
	@Test
	void cambiaTitoloLunghezzaInferiore() throws ServletException, IOException 
	{
		when(request.getParameter("azione")).thenReturn("cambiaTitolo");
		when(request.getParameter("nuovoTitolo")).thenReturn("q");
		when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":false,\"message\":\"Inserire un titolo compreso tra i 5 e 35 caratteri\"}"));
	}
	@Test
	void aggiungiPacchettoInUso() throws ServletException, IOException 
	{
		when(request.getParameter("azione")).thenReturn("aggiungiPacchetto");
	    when(request.getParameter("nuovoCodice")).thenReturn("pac003");
	    when(request.getParameter("sottocategoria")).thenReturn(sottocategoria);
	    when(request.getParameter("titolo")).thenReturn(titolo);
	    when(request.getParameter("foto")).thenReturn(nuovaFoto);
	    when(request.getParameter("nuovoPrezzo")).thenReturn(nuovoPrezzo);
		when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":false,\"message\":\"Codice pacchetto gi\\u0026agrave; in uso\"}"));
	}
	@Test
	void aggiungiPacchetto() throws ServletException, IOException 
	{
		UtenteBean utente = new UtenteBean();
		utente.setNomeUtente("Claudia");
		HttpSession session = (HttpSession)Mockito.mock(HttpSession.class);
		when(request.getParameter("azione")).thenReturn("aggiungiPacchetto");
	    when(request.getParameter("nuovoCodice")).thenReturn("pachetto1");
	    when(request.getParameter("sottocategoria")).thenReturn(sottocategoria);
	    when(request.getParameter("titolo")).thenReturn("titoloMio");
	    when(request.getParameter("foto")).thenReturn(nuovaFoto);
	    when(request.getParameter("prezzo")).thenReturn(nuovoPrezzo);
		when(request.getParameter("descrizione")).thenReturn(nuovaDescrizione);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("User")).thenReturn(utente);
		when(response.getWriter()).thenReturn(writer);
	
		servlet.doGet(request, response);
	
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":false,\"message\":\"\"}"));
	}
	@Test
	void aggiungiPacchettoCodiceCategoriaNonValido() throws ServletException, IOException 
	{
		when(request.getParameter("azione")).thenReturn("aggiungiPacchetto");
	    when(request.getParameter("nuovoCodice")).thenReturn("1");
	    when(request.getParameter("sottocategoria")).thenReturn("Informatica");
	    when(request.getParameter("titolo")).thenReturn("titolo titolo");
	    when(request.getParameter("foto")).thenReturn(nuovaFoto);
	    when(request.getParameter("prezzo")).thenReturn(nuovoPrezzo);
		when(request.getParameter("descrizione")).thenReturn(nuovaDescrizione);
		when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":false,\"message\":\"Codice sottocategoria non valido\"}"));
	}
	@Test
	void aggiungiPacchettoPrezzoNonValido() throws ServletException, IOException 
	{
		when(request.getParameter("azione")).thenReturn("aggiungiPacchetto");
	    when(request.getParameter("nuovoCodice")).thenReturn("1");
	    when(request.getParameter("sottocategoria")).thenReturn(sottocategoria);
	    when(request.getParameter("titolo")).thenReturn(titolo);
	    when(request.getParameter("foto")).thenReturn(nuovaFoto);
	    when(request.getParameter("prezzo")).thenReturn("A");
		when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":false,\"message\":\"Prezzo non valido\"}"));
	}
	@Test
	void rimuovi() throws ServletException, IOException{
		when(request.getParameter("azione")).thenReturn("rimuovi");
		when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertNotNull(result);
	}
	@Test
	void cambiaDescrizione() throws ServletException, IOException{
		when(request.getParameter("azione")).thenReturn("cambiaDescrizione");
		when(request.getParameter("nuovaDescrizione")).thenReturn(nuovaDescrizione);
		when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"OK\"}"));
	}
	@Test
	void cambiaPrezzo() throws ServletException, IOException{
		when(request.getParameter("azione")).thenReturn("cambiaPrezzo");
		when(request.getParameter("nuovoPrezzo")).thenReturn(nuovoPrezzo);
		when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);

		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"OK\"}"));
	}
	@Test
	void aggiungilezioneUrlNonValido() throws ServletException, IOException{
		when(request.getParameter("azione")).thenReturn("aggiungiLezione");
		when(request.getParameter("url")).thenReturn(url);
		when(request.getParameter("titolo")).thenReturn(titolo);
		when(request.getParameter("durata")).thenReturn(durata);
		   
		when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);

		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":false,\"message\":\"Url non valido!\"}"));
	}
	@Test
	void aggiungilezioneUrlEsistente() throws ServletException, IOException{
		when(request.getParameter("azione")).thenReturn("aggiungiLezione");
		when(request.getParameter("url")).thenReturn("https://www.youtube.com/embed/_zFpM0zcssU");
		when(request.getParameter("titolo")).thenReturn(titolo);
		when(request.getParameter("durata")).thenReturn(durata);
		   
		when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);

		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":false,\"message\":\"Url gi\\u0026agrave esistente\"}"));
	}
	@Test
	void aggiungilezione() throws ServletException, IOException{
		HttpSession session = (HttpSession)Mockito.mock(HttpSession.class);
	    when(request.getParameter("azione")).thenReturn("aggiungiLezione");
		when(request.getParameter("url")).thenReturn("https://www.youtube.com/embed/4CB7k6XokTY");
		when(request.getParameter("titolo")).thenReturn("corso extra plus");
		when(request.getParameter("durata")).thenReturn(durata);
		when(request.getSession()).thenReturn(session);
		   
		when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);

		String result = stringWriter.getBuffer().toString().trim();
		assertNotNull(result);
	}
	@Test
	void modificaNomeLezione() throws ServletException, IOException{
		String nuovoNomeLezione="corso foto";
		when(request.getParameter("nuovoNomeLezione")).thenReturn(nuovoNomeLezione);
		when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);

		String result = stringWriter.getBuffer().toString().trim();
		assertNotNull(result);
	}
	@Test
	void modificaVideoLezione() throws ServletException, IOException{
		String nuovoUrlLezione="https://www.youtube.com/embed/2s0d0U33gNQ";
		when(request.getParameter("nuovoUrlLezione")).thenReturn(nuovoUrlLezione);
		when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);

		String result = stringWriter.getBuffer().toString().trim();
		assertNotNull(result);
	}
	@Test
	void modificaDurataLezione() throws ServletException, IOException{
		String nuovaDurata= "11:45";
		when(request.getParameter("nuovaDurataLezione")).thenReturn(nuovaDurata);
		when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);

		String result = stringWriter.getBuffer().toString().trim();
		assertNotNull(result);
	}
	@Test
	void rimuoviLezione() throws ServletException, IOException{
		when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);

		String result = stringWriter.getBuffer().toString().trim();
		assertNotNull(result);
	}

}

