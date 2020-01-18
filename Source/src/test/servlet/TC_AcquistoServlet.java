package test.servlet;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import control.AcquistoServlet;
import model.bean.PacchettoBean;
import model.bean.UtenteBean;
import model.manager.AcquistoManager;
class TC_AcquistoServlet extends Mockito{
	
	AcquistoManager manager;
	HttpSession session;
	static UtenteBean user;
	@BeforeAll
	public static void init () {
		user = new UtenteBean();
		user.setNomeUtente("Marta");
	}
	@Test
	void acquisto() throws ServletException, IOException {
		AcquistoServlet servlet = new AcquistoServlet();

		HttpServletRequest request = (HttpServletRequest)Mockito.mock(HttpServletRequest.class);
	    HttpServletResponse response = (HttpServletResponse)Mockito.mock(HttpServletResponse.class);
	    session = (HttpSession)Mockito.mock(HttpSession.class);
	    ArrayList<PacchettoBean> list = new ArrayList<PacchettoBean>();
	    when(request.getSession()).thenReturn(session);
	    when(session.getAttribute("carrello")).thenReturn(list);
	    when(session.getAttribute("User")).thenReturn(user);
	    
	    servlet.doGet(request, response);
	    
		UtenteBean lol = (UtenteBean)session.getAttribute("User");
		
	    assertEquals(user.getNomeUtente(), lol.getNomeUtente());
	}
}
