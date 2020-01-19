package test.integration.servlet;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;

import control.LoginServlet;
import control.util.JSONResponse;
import model.bean.UtenteBean;
import model.manager.UtenteManager;

class Test_LoginServlet extends Mockito{
	
	static private LoginServlet servlet;
	static UtenteBean user= new UtenteBean();
	static String nomeUtente;
	static String password;
	
	
	@BeforeAll
	public static void init () {
		user = new UtenteBean();
		user.setNomeUtente("Rachele");
		user.setPassword("mangiona");
		nomeUtente= "Rachele";
		password= "mangiona";
	}
	
	@Test
	public void testLogin()  throws Exception {
		servlet = new LoginServlet();	
		String isChecked= "true";
	    UtenteBean user1= new UtenteBean();
		
		HttpServletRequest request = (HttpServletRequest)Mockito.mock(HttpServletRequest.class);
	    HttpServletResponse response = (HttpServletResponse)Mockito.mock(HttpServletResponse.class);
	    
	    Gson gson = new Gson();
		JSONResponse jsonResponse = new JSONResponse(false);
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        
        when(request.getParameter("NomeUtente")).thenReturn(nomeUtente);
        when(request.getParameter("Password")).thenReturn(password);
        when(request.getParameter("Ricordami")).thenReturn(isChecked);
       
        HttpSession session = (HttpSession)Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        session.setAttribute("User", user1);
        
        when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));
		servlet.doGet(request, response);
		
		UtenteBean lol = (UtenteBean)session.getAttribute("User");
		assertEquals(user1.getNomeUtente(), null);
	}
	@Test
	public void testLoginOk()  throws Exception {
		servlet = new LoginServlet();	
		String isChecked= "true";
		UtenteBean usermio= new UtenteBean();
		usermio.setNomeUtente("Rachele");
		usermio.setPassword("mangiona");
		
		HttpServletRequest request = (HttpServletRequest)Mockito.mock(HttpServletRequest.class);
	    HttpServletResponse response = (HttpServletResponse)Mockito.mock(HttpServletResponse.class);
	    
	    Gson gson = new Gson();
		JSONResponse jsonResponse = new JSONResponse(false);
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        
        when(request.getParameter("NomeUtente")).thenReturn(nomeUtente);
        when(request.getParameter("Password")).thenReturn(password);
        when(request.getParameter("Ricordami")).thenReturn(isChecked);
      
        HttpSession session = (HttpSession)Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
		
		when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		System.out.print(result);
		assertTrue(result.equals("{\"ok\":true,\"message\":\"OK\",\"content\":\"Rachele\"}"));
	}
}
