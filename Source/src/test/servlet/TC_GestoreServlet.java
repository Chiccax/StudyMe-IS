package test.servlet;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;

import control.AcquistoServlet;
import control.GestoreServlet;
import control.util.JSONResponse;
import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.bean.UtenteBean;
import model.manager.UtenteManager;

public class TC_GestoreServlet extends Mockito{
	
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
    	e.setCodicePacchetto("pac100");
		l.setPacchetto("pac100");
		l1.setPacchetto("pac100");
		l2.setPacchetto("pac100");
		l3.setPacchetto("pac100");
	
	}


	@Test
	void mostraPacchettiDaApprovare() throws ServletException, IOException {
		ArrayList<PacchettoBean> pacchettiDaApprovare = new ArrayList<PacchettoBean>();;
		ArrayList<LezioniBean> lezioniDaApprovare = new ArrayList<LezioniBean>();
		pacchettiDaApprovare.add(e);
		
		lezioniDaApprovare.add(l);
		lezioniDaApprovare.add(l1);
		lezioniDaApprovare.add(l2);
		lezioniDaApprovare.add(l3);
		lezioniDaApprovare.add(l4);
		UtenteManager gestore=(UtenteManager) Mockito.mock(UtenteManager.class);
	    when(request.getParameter("action")).thenReturn("mostraPacchettiDaApprovare");
	    when(gestore.visualizzaPacchettiDaApprovare()).thenReturn(pacchettiDaApprovare);
		when(gestore.visualizzaLezioniDaApprovare()).thenReturn(lezioniDaApprovare);
		when(response.getWriter()).thenReturn(writer);

		servlet.doGet(request, response);
		String result = stringWriter.getBuffer().toString().trim();
		System.out.println(result);
		assertTrue(result.equals("{\"ok\":true,\"message\":\"ok\",\"content\":{\"pacchettiDaApprovare\":[{\"codicePacchetto\":\"Pac100\",\"descrizione\":\"prova cambio 2\",\"titolo\":\"titolo nuovo\",\"catagoria\":\"Insegnamento\",\"sottocategoria\":\"ins001\",\"foto\":\"https://www.sinonimi-contrari.it/includes/images/sharers/sinonimi-di-prova.png\",\"prezzo\":123.0,\"approvato\":1}],\"lezioniPacchettoDaApprovare\":{\"Pac100\":[{\"url\":\"https://www.youtube.com/embed/2s0d0U33gNQ\",\"titolo\":\"corso matematica 12\",\"pacchetto\":\"Pac100\",\"durata\":\"11:11\",\"approvato\":0,\"observers\":[]},{\"url\":\"https://www.youtube.com/embed/ajLqny2-R-E\",\"titolo\":\"prova nuova lezione\",\"pacchetto\":\"Pac100\",\"durata\":\"11:11\",\"approvato\":0,\"observers\":[]},{\"url\":\"https://www.youtube.com/embed/BzgFG32JAdk\",\"titolo\":\"corso matematica bimbi\",\"pacchetto\":\"Pac100\",\"durata\":\"11:11\",\"approvato\":0,\"observers\":[]},{\"url\":\"https://www.youtube.com/embed/WK_hM-Ongbo\",\"titolo\":\"matematicamente\",\"pacchetto\":\"Pac100\",\"durata\":\"11:11\",\"approvato\":0,\"observers\":[]}]}}}"));
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
