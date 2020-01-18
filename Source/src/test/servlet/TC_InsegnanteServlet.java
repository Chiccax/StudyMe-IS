package test.servlet;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;

import control.InsegnanteServlet;
import control.util.JSONResponse;
import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.manager.InsegnanteManager;
import model.manager.SottocategoriaManager;
import model.manager.UtenteManager;

public class TC_InsegnanteServlet extends Mockito{

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
		
	  
	    Double prezzo= 21.7;
	    String vecchioCodice= "pac121";
	    String nuovoPrezzo= "21.1";
	    String nuovoTitolo= "prova2";
	    String nuovaDescrizione="bla bla bla bla ba bla ba";
	    String sottocategoria= "Informatica";
	    String titolo= "java 8";
	    String nuovaFoto="http://qui";
	    String url="http://qui";
	    String durata="11:11";
	  
	 @Test  
	void azioneNulla() throws ServletException, IOException {
		when(request.getParameter("azione")).thenReturn(null);
	    when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));
		servlet.doGet(request, response);
	}
	 
   
	@Test
	void cambiaCodice() throws ServletException, IOException {
		InsegnanteManager insegnanteManager=(InsegnanteManager) Mockito.mock(InsegnanteManager.class);
		
		when(request.getParameter("azione")).thenReturn("cambiaCodice");
		when(request.getParameter("nuovoCodice")).thenReturn(nuovoCodice);
		when(insegnanteManager.getPacchetto(nuovoCodice)).thenReturn(pacchettoEsistente2);
		when(response.getWriter()).thenReturn(writer);
	
		servlet.doGet(request, response);
		String result = stringWriter.getBuffer().toString().trim();
		
		assertTrue(result.equals("{\"ok\":false,\"message\":\"Codice pacchetto gi\\u0026agrave; in uso\"}"));
	}
	
	@Test
	void cambiaCodiceNullo() throws ServletException, IOException {
		InsegnanteManager insegnanteManager=(InsegnanteManager) Mockito.mock(InsegnanteManager.class);
		
		when(request.getParameter("azione")).thenReturn("cambiaCodice");
	    when(request.getParameter("nuovoCodice")).thenReturn(nuovoCodice);
	    when(insegnanteManager.getPacchetto(nuovoCodice)).thenReturn(pacchettoEsistente1);
	    when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":false,\"message\":\"Codice pacchetto gi\\u0026agrave; in uso\"}"));
	}
	
	@Test
	void aggiungiPacchettoNullo() throws ServletException, IOException{
		InsegnanteManager insegnanteManager=(InsegnanteManager) Mockito.mock(InsegnanteManager.class);
		
		when(request.getParameter("azione")).thenReturn("aggiungiPacchetto");
	    when(request.getParameter("nuovoCodice")).thenReturn(nuovoCodice);
	    when(request.getParameter("sottocategoria")).thenReturn(sottocategoria);
	    when(request.getParameter("titolo")).thenReturn(titolo);
	    when(request.getParameter("foto")).thenReturn(nuovaFoto);
	    when(insegnanteManager.getPacchetto(nuovoCodice)).thenReturn(null);
	    when(request.getParameter("nuovoPrezzo")).thenReturn(nuovoPrezzo);
		when(insegnanteManager.getPacchettoByTitolo(nuovoTitolo)).thenReturn(pacchettoEsistente);
		when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}{\"ok\":false,\"message\":\"Codice pacchetto gi\\u0026agrave; in uso\"}"));
	
	}
	
	@Test
	void cambiaTitolo() throws ServletException, IOException 
	{
		InsegnanteManager insegnanteManager=(InsegnanteManager) Mockito.mock(InsegnanteManager.class);
		
		when(request.getParameter("azione")).thenReturn("cambiaTitolo");
		when(request.getParameter("nuovoTitolo")).thenReturn(nuovoTitolo);
		when(response.getWriter()).thenReturn(writer);
		JSONResponse jsonResponse = new JSONResponse(true, "OK");
		
		writer.print(gson.toJson(jsonResponse));
		when(insegnanteManager.getPacchettoByTitolo(nuovoTitolo)).thenReturn(pacchettoEsistente1);
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"OK\"}{\"ok\":true,\"message\":\"OK\"}"));
	}
	@Test
	void cambiaTitoloLunghezzaInferiore() throws ServletException, IOException 
	{
		InsegnanteManager insegnanteManager=(InsegnanteManager) Mockito.mock(InsegnanteManager.class);
		
		when(request.getParameter("azione")).thenReturn("cambiaTitolo");
		when(request.getParameter("nuovoTitolo")).thenReturn("q");
		when(response.getWriter()).thenReturn(writer);
		JSONResponse jsonResponse = new JSONResponse(false, "Inserire un titolo compreso tra i 5 e 35 caratteri");
		writer.print(gson.toJson(jsonResponse));
		when(insegnanteManager.getPacchettoByTitolo(nuovoTitolo)).thenReturn(pacchettoEsistente1);
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":false,\"message\":\"Inserire un titolo compreso tra i 5 e 35 caratteri\"}{\"ok\":false,\"message\":\"Inserire un titolo compreso tra i 5 e 35 caratteri\"}"));
	
		
	}
	@Test
	void aggiungiPacchettoInUso() throws ServletException, IOException 
	{
		InsegnanteManager insegnanteManager=(InsegnanteManager) Mockito.mock(InsegnanteManager.class);
		
		when(request.getParameter("azione")).thenReturn("aggiungiPacchetto");
	    when(request.getParameter("nuovoCodice")).thenReturn(nuovoCodice);
	    when(request.getParameter("sottocategoria")).thenReturn(sottocategoria);
	    when(request.getParameter("titolo")).thenReturn(titolo);
	    when(request.getParameter("foto")).thenReturn(nuovaFoto);
	    when(insegnanteManager.getPacchetto(nuovoCodice)).thenReturn(pacchettoEsistenteConCodice);
		when(request.getParameter("nuovoPrezzo")).thenReturn(nuovoPrezzo);
		when(insegnanteManager.getPacchettoByTitolo(nuovoTitolo)).thenReturn(pacchettoEsistente);
		when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}{\"ok\":false,\"message\":\"Codice pacchetto gi\\u0026agrave; in uso\"}"));
	}
	@Test
	void aggiungiPacchetto() throws ServletException, IOException 
	{
		InsegnanteManager insegnanteManager=(InsegnanteManager) Mockito.mock(InsegnanteManager.class);
		SottocategoriaManager sottocategoriaManager = new SottocategoriaManager();
		
		when(request.getParameter("azione")).thenReturn("aggiungiPacchetto");
	    when(request.getParameter("nuovoCodice")).thenReturn("1");
	    when(request.getParameter("sottocategoria")).thenReturn(sottocategoria);
	    when(request.getParameter("titolo")).thenReturn(titolo);
	    when(request.getParameter("foto")).thenReturn(nuovaFoto);
	    when(insegnanteManager.getPacchetto("1")).thenReturn(null);
		when(request.getParameter("prezzo")).thenReturn(nuovoPrezzo);
		when(request.getParameter("descrizione")).thenReturn(nuovaDescrizione);
		Object sottocategoria = new Object();
		when(insegnanteManager.getPacchettoByTitolo(nuovoTitolo)).thenReturn(pacchettoEsistente);
		when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}{\"ok\":false,\"message\":\"Codice sottocategoria non valido\"}"));
	}
	@Test
	void aggiungiPacchettoCodiceCategoriaNonValido() throws ServletException, IOException 
	{
		InsegnanteManager insegnanteManager=(InsegnanteManager) Mockito.mock(InsegnanteManager.class);
		
		when(request.getParameter("azione")).thenReturn("aggiungiPacchetto");
	    when(request.getParameter("nuovoCodice")).thenReturn("1");
	    when(request.getParameter("sottocategoria")).thenReturn(sottocategoria);
	    when(request.getParameter("titolo")).thenReturn(titolo);
	    when(request.getParameter("foto")).thenReturn(nuovaFoto);
	    when(insegnanteManager.getPacchetto("1")).thenReturn(null);
		when(request.getParameter("prezzo")).thenReturn(nuovoPrezzo);
		when(request.getParameter("descrizione")).thenReturn(nuovaDescrizione);
		when(insegnanteManager.getPacchettoByTitolo(nuovoTitolo)).thenReturn(pacchettoEsistente);
		when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}{\"ok\":false,\"message\":\"Codice sottocategoria non valido\"}"));
	}
	@Test
	void aggiungiPacchettoPrezzoNonValido() throws ServletException, IOException 
	{
		InsegnanteManager insegnanteManager=(InsegnanteManager) Mockito.mock(InsegnanteManager.class);
		
		when(request.getParameter("azione")).thenReturn("aggiungiPacchetto");
	    when(request.getParameter("nuovoCodice")).thenReturn("1");
	    when(request.getParameter("sottocategoria")).thenReturn(sottocategoria);
	    when(request.getParameter("titolo")).thenReturn(titolo);
	    when(request.getParameter("foto")).thenReturn(nuovaFoto);
	    when(insegnanteManager.getPacchetto("1")).thenReturn(null);
		when(request.getParameter("prezzo")).thenReturn("A");
		when(insegnanteManager.getPacchettoByTitolo(nuovoTitolo)).thenReturn(pacchettoEsistente);
		when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}{\"ok\":false,\"message\":\"Prezzo non valido\"}"));
	}
	@Test
	void rimuovi() throws ServletException, IOException{
		when(request.getParameter("azione")).thenReturn("rimuovi");
		when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}{\"ok\":false,\"message\":\"Inserire codice per proseguire!\"}"));
	}
	@Test
	void cambiaDescrizione() throws ServletException, IOException{
		when(request.getParameter("azione")).thenReturn("cambiaDescrizione");
		when(request.getParameter("nuovaDescrizione")).thenReturn(nuovaDescrizione);
		when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}{\"ok\":true,\"message\":\"OK\"}"));
	}
	@Test
	void cambiaPrezzo() throws ServletException, IOException{
		when(request.getParameter("azione")).thenReturn("cambiaPrezzo");
		when(request.getParameter("nuovoPrezzo")).thenReturn(nuovoPrezzo);
		when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));
		servlet.doGet(request, response);

		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}{\"ok\":true,\"message\":\"OK\"}"));
	}
	@Test
	void aggiungilezioneUrlNonValido() throws ServletException, IOException{
		when(request.getParameter("azione")).thenReturn("aggiungiLezione");
		when(request.getParameter("url")).thenReturn(url);
		when(request.getParameter("titolo")).thenReturn(titolo);
		when(request.getParameter("durata")).thenReturn(durata);
		   
		when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));
		servlet.doGet(request, response);

		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}{\"ok\":false,\"message\":\"Url non valido!\"}"));
	}
	@Test
	void aggiungilezioneUrlEsistente() throws ServletException, IOException{
		when(request.getParameter("azione")).thenReturn("aggiungiLezione");
		when(request.getParameter("url")).thenReturn("https://www.youtube.com/embed/_zFpM0zcssU");
		when(request.getParameter("titolo")).thenReturn(titolo);
		when(request.getParameter("durata")).thenReturn(durata);
		   
		when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));
		servlet.doGet(request, response);

		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}{\"ok\":false,\"message\":\"Url gi\\u0026agrave esistente\"}"));
	}
	@Test
	void aggiungilezione() throws ServletException, IOException{
		InsegnanteManager insegnanteManager=(InsegnanteManager) Mockito.mock(InsegnanteManager.class);
		ArrayList<LezioniBean> lezioneTitoloEsistente= new ArrayList<LezioniBean>();
		ArrayList<LezioniBean> lezioneEsistente= new ArrayList<LezioniBean> ();
		PacchettoBean pacchettoAtt= new PacchettoBean();
		HttpSession session = (HttpSession)Mockito.mock(HttpSession.class);
	      
		when(request.getParameter("azione")).thenReturn("aggiungiLezione");
		when(request.getParameter("url")).thenReturn("https://www.youtube.com/embed/4CB7k6XokTY");
		when(request.getParameter("titolo")).thenReturn("corso extra plus");
		when(insegnanteManager.getLezioniByTitolo(titolo)).thenReturn(lezioneTitoloEsistente);
		when(insegnanteManager.getLezioniByURl(url)).thenReturn(lezioneEsistente);
		when(request.getParameter("durata")).thenReturn(durata);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("PacchettoAttuale")).thenReturn(pacchettoAtt);
		   
		when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));
		servlet.doGet(request, response);

		String result = stringWriter.getBuffer().toString().trim();
		System.out.println(result);
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}{\"ok\":true,\"message\":\"OK\"}"));
	}
	@Test
	void modificaNomeLezione() throws ServletException, IOException{
		String nuovoNomeLezione="corso extra violino";
		InsegnanteManager insegnanteManager=(InsegnanteManager) Mockito.mock(InsegnanteManager.class);
		ArrayList<LezioniBean> lezioneTitoloEsistente = new ArrayList<LezioniBean> ();
		
		when(request.getParameter("nuovoNomeLezione")).thenReturn(nuovoNomeLezione);
		when(insegnanteManager.getLezioniByTitolo(nuovoNomeLezione)).thenReturn(lezioneTitoloEsistente );
		when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));
		servlet.doGet(request, response);

		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}"));
	}
	@Test
	void modificaVideoLezione() throws ServletException, IOException{
		InsegnanteManager insegnanteManager=(InsegnanteManager) Mockito.mock(InsegnanteManager.class);
		String nuovoUrlLezione="https://www.youtube.com/embed/q-WGLfMReQ4";
		ArrayList<LezioniBean> lezioneEsistente= new ArrayList<LezioniBean> ();
		
		when(request.getParameter("nuovoUrlLezione")).thenReturn(nuovoUrlLezione);
		when(insegnanteManager.getLezioniByURl(nuovoUrlLezione)).thenReturn(lezioneEsistente);
		when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));
		servlet.doGet(request, response);

		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}"));
	}
	@Test
	void modificaDurataLezione() throws ServletException, IOException{
		InsegnanteManager insegnanteManager=(InsegnanteManager) Mockito.mock(InsegnanteManager.class);
		String nuovaDurata= "11:45";
		when(request.getParameter("nuovaDurataLezione")).thenReturn(nuovaDurata);
		when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));
		servlet.doGet(request, response);

		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}"));
	}
	@Test
	void rimuoviLezione() throws ServletException, IOException{
		InsegnanteManager insegnanteManager=(InsegnanteManager) Mockito.mock(InsegnanteManager.class);
		when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));
		servlet.doGet(request, response);

		String result = stringWriter.getBuffer().toString().trim();
		System.out.println(result);
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}"));
	}

	
	
	
	
	
	
}
