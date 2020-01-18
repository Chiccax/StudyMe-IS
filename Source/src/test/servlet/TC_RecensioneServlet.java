package test.servlet;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;

import control.RecensioneServlet;
import control.util.JSONResponse;

public class TC_RecensioneServlet extends Mockito{

	@Test
	void recensione() throws ServletException, IOException {
		 HttpServletRequest request = (HttpServletRequest)Mockito.mock(HttpServletRequest.class);
		 HttpServletResponse response = (HttpServletResponse)Mockito.mock(HttpServletResponse.class);
		 Gson gson = new Gson();
		 JSONResponse jsonResponse = new JSONResponse(true);
		 StringWriter stringWriter = new StringWriter();
		 PrintWriter writer = new PrintWriter(stringWriter);
		 RecensioneServlet servlet= new  RecensioneServlet(); 
		
		 when(request.getParameter("utente")).thenReturn("Damiana");
		 when(request.getParameter("codicePacchetto")).thenReturn("pac047");
		 when(request.getParameter("titoloRecensione")).thenReturn("spagnolo");
		 when(request.getParameter("testoRecensione")).thenReturn("corso molto appassionante");
		 
		
		 when(response.getWriter()).thenReturn(writer);
		 writer.print(gson.toJson(jsonResponse));
		 servlet.doGet(request, response);
		
		 String result = stringWriter.getBuffer().toString().trim();
		 System.out.println(result);
		 assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}{\"ok\":true,\"message\":\"\"}"));
	
	}
	
}
