package test.servlet;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;

import control.ModificaAreaUtenteServlet;
import control.util.JSONResponse;
import model.bean.PacchettoBean;
import model.bean.UtenteBean;
import model.manager.UtenteManager;

public class TC_ModificaAreaUtenteServlet  extends Mockito{
	Gson gson = new Gson();
	JSONResponse jsonResponse = new JSONResponse(true);
	StringWriter stringWriter = new StringWriter();
	PrintWriter writer = new PrintWriter(stringWriter);
	  
	UtenteManager utenteManager= new UtenteManager();
	HttpServletRequest request = (HttpServletRequest)Mockito.mock(HttpServletRequest.class);
	HttpServletResponse response = (HttpServletResponse)Mockito.mock(HttpServletResponse.class);
	static UtenteBean loggedUser= new UtenteBean();
	
	ModificaAreaUtenteServlet servlet= new ModificaAreaUtenteServlet();
	@BeforeAll
	public static void init () {
		loggedUser.setNomeUtente("Claudia");
		loggedUser.setEmail("claudia@alice.it");
		loggedUser.setPassword("claudiabuono");
		
	}
	
	@Test
	void modificaEmail() throws ServletException, IOException {
		String nuovaEmailUtente = "claudiabuono99@gmail.com";
		String emailUtente="claudia@alice.it";
		String nuovaPasswordUtente = null;
		String confermaNuovaPasswordUtente=null;
		when(request.getParameter("NuovaEmailUtente")).thenReturn(nuovaEmailUtente);
		when(request.getParameter("NuovaPasswordUtente")).thenReturn(nuovaPasswordUtente);
		when(request.getParameter("ConfermaNuovaPasswordUtente")).thenReturn(confermaNuovaPasswordUtente);
		HttpSession session = (HttpSession)Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("User")).thenReturn(loggedUser);
		when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));
		
		servlet.doGet(request, response);
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}{\"ok\":true,\"message\":\"OK\"}"));
	}
	@Test
	void modificaPassword() throws ServletException, IOException {
		String nuovaEmailUtente = null;
		String emailUtente=null;
		String nuovaPasswordUtente = "claudia99" ;
		String confermaNuovaPasswordUtente="claudia99";
		when(request.getParameter("NuovaEmailUtente")).thenReturn(nuovaEmailUtente);
		when(request.getParameter("NuovaPasswordUtente")).thenReturn(nuovaPasswordUtente);
		when(request.getParameter("ConfermaNuovaPasswordUtente")).thenReturn(confermaNuovaPasswordUtente);
		HttpSession session = (HttpSession)Mockito.mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("User")).thenReturn(loggedUser);
		when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));

		servlet.doGet(request, response);
		String result = stringWriter.getBuffer().toString().trim();
		System.out.println(result);
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}{\"ok\":true,\"message\":\"OK\"}"));
	}
	
}
