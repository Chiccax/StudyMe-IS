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

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;

import control.GestoreServlet;
import control.util.JSONResponse;
import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.bean.UtenteBean;
import model.dao.GestoreDao;
import model.manager.UtenteManager;

class Test_GestoreServlet extends Mockito{
	
	HttpSession session;
	static UtenteBean user;
	Gson gson = new Gson();
	JSONResponse jsonResponse = new JSONResponse(false);
	PrintWriter out;
	HttpServletRequest request = (HttpServletRequest)Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = (HttpServletResponse)Mockito.mock(HttpServletResponse.class);
    StringWriter stringWriter = new StringWriter();
    PrintWriter writer = new PrintWriter(stringWriter);
    GestoreServlet servlet = new GestoreServlet();
    static PacchettoBean e= new PacchettoBean();
    static LezioniBean l= new LezioniBean();
    static LezioniBean l1= new LezioniBean();
    static LezioniBean l2= new LezioniBean();
    static LezioniBean l3= new LezioniBean();
    static LezioniBean l4= new LezioniBean();
	
    @BeforeAll
	public static void init () {
    	
    	ArrayList<PacchettoBean> pacchettiDaApprovare = new ArrayList<PacchettoBean>();;
		ArrayList<LezioniBean> lezioniDaApprovare = new ArrayList<LezioniBean>();
		GestoreDao gestore= new GestoreDao();
		pacchettiDaApprovare= gestore.visualizzaPacchettiDaApprovare();
		lezioniDaApprovare=gestore.visualizzaLezioniDaApprovare();
	}
   
  
	@Test
	void mostraPacchettiDaApprovare() throws ServletException, IOException {
		
		when(request.getParameter("action")).thenReturn("mostraPacchettiDaApprovare");
	    when(response.getWriter()).thenReturn(writer);
	    
		servlet.doGet(request, response);
		String result = stringWriter.getBuffer().toString().trim();
		assertNotNull(result);
	}
	
	@Test
	void approvaInteroPacchetto() throws ServletException, IOException {
		when(request.getParameter("action")).thenReturn("approvaInteroPacchetto");
		when(request.getParameter("codicePacchetto")).thenReturn("pac121");
		when(response.getWriter()).thenReturn(out);
		when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}"));
	}
	@Test
	void disapprovaInteroPacchetto() throws ServletException, IOException {
		when(request.getParameter("action")).thenReturn("disapprovaInteroPacchetto");
		when(request.getParameter("codicePacchetto")).thenReturn("pac121");
		when(response.getWriter()).thenReturn(out);
		when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}"));
		}
	@Test
	void approvaSingolaLezione() throws ServletException, IOException {
		when(request.getParameter("action")).thenReturn("approvaSingolaLezione");
		when(request.getParameter("urlLezione")).thenReturn("https://www.youtube.com/embed/_2gmtVuenfc");
		when(response.getWriter()).thenReturn(out);
		when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}"));
	}
	@Test
	void disapprovaSingolaLezione() throws ServletException, IOException {
		when(request.getParameter("action")).thenReturn("disapprovaSingolaLezione");
		when(request.getParameter("urlLezione")).thenReturn("https://www.youtube.com/embed/_2gmtVuenfc");
		when(response.getWriter()).thenReturn(out);
		when(response.getWriter()).thenReturn(writer);
		servlet.doGet(request, response);
		
		String result = stringWriter.getBuffer().toString().trim();
		assertTrue(result.equals("{\"ok\":true,\"message\":\"\"}"));
		}
}
