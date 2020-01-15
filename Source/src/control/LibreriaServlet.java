package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.bean.UtenteBean;
import model.manager.LibreriaManager;
/**
 * Gestisce la visualizzazione dei pacchetti acquistati dall'acquirente
 */

@WebServlet("/LibreriaServlet")
public class LibreriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LibreriaServlet() {
        super();
       
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UtenteBean user =(UtenteBean)session.getAttribute("User");
		
		if(user == null) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		LibreriaManager libreriaManager= new LibreriaManager();
		ArrayList<PacchettoBean> pacchetti= libreriaManager.getPacchetti(user);
		ArrayList<ArrayList<LezioniBean>> lezioni= libreriaManager.getLezioni();
		request.setAttribute("pacchetti", pacchetti);
		request.setAttribute("lezioni", lezioni);
		
		RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/Libreria.jsp");
		dispatcher.forward(request, response);
	}
}