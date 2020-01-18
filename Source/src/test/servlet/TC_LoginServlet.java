package test.servlet;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;

import control.LoginServlet;
import control.util.JSONResponse;
import model.bean.UtenteBean;
import model.manager.InsegnanteManager;
import model.manager.UtenteManager;

public class TC_LoginServlet extends Mockito{
	
	static private LoginServlet servlet;
	static UtenteBean user= new UtenteBean();
	@BeforeAll
	public static void init () {
		user = new UtenteBean();
		user.setNomeUtente("Claudia");
		user.setPassword("claudiabuono");
	}
	
	@Test
	public void testLogin()  throws Exception {
		servlet = new LoginServlet();	
		String nomeUtente= "Claudia";
		String password= "claudiabuono";
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
       
        UtenteManager utenteManager=(UtenteManager) Mockito.mock(UtenteManager.class);
        String passwordBase64format  = Base64.getEncoder().encodeToString(password.getBytes()); 
		when(utenteManager.getUtente(nomeUtente, passwordBase64format)).thenReturn(user1);
        HttpSession session = (HttpSession)Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        session.setAttribute("User", user1);
        
        //verify(utenteManager).login(nomeUtente, passwordBase64format);
	    when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));
		servlet.doGet(request, response);
		
		UtenteBean lol = (UtenteBean)session.getAttribute("User");
		
		assertEquals(user1.getNomeUtente(), null);
		
		
	}
	@Test
	public void testLoginOk()  throws Exception {
		servlet = new LoginServlet();	
		String nomeUtente= "Claudia";
		String password= "claudiabuono";
		String isChecked= "true";
		UtenteBean usermio= new UtenteBean();
		usermio.setNomeUtente("Claudia");
		usermio.setPassword("claudiabuono");
		
		HttpServletRequest request = (HttpServletRequest)Mockito.mock(HttpServletRequest.class);
	    HttpServletResponse response = (HttpServletResponse)Mockito.mock(HttpServletResponse.class);
	    
	    Gson gson = new Gson();
		JSONResponse jsonResponse = new JSONResponse(false);
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        
        when(request.getParameter("NomeUtente")).thenReturn(nomeUtente);
        when(request.getParameter("Password")).thenReturn(password);
        when(request.getParameter("Ricordami")).thenReturn(isChecked);
      
        UtenteManager utenteManager=(UtenteManager) Mockito.mock(UtenteManager.class);
        String passwordBase64format  = Base64.getEncoder().encodeToString(password.getBytes()); 
		when(utenteManager.getUtente(nomeUtente, passwordBase64format)).thenReturn(user);
        HttpSession session = (HttpSession)Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
		
		when(response.getWriter()).thenReturn(writer);
		writer.print(gson.toJson(jsonResponse));
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":false,\"message\":\"\"}{\"ok\":true,\"message\":\"OK\",\"content\":\"Claudia\"}"));
	}
}