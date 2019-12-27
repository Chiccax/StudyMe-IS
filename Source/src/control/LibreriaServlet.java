package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelBean.LezioniBean;
import modelBean.OrdineAcquistoBean;
import modelBean.PacchettoBean;
import modelBean.UtenteBean;
import modelDao.OrdineAcquistoDao;
import modelDao.PacchettoDao;
/**
 * La servlet Libreria  gestisce la visualizzazione dei pacchetti acquistati.
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
		
		String nomeUtente=user.getNomeUtente();
		
		ArrayList<ArrayList<LezioniBean>> lezioni= new ArrayList<ArrayList<LezioniBean>>();
		ArrayList<PacchettoBean> pacchetti = new ArrayList<PacchettoBean>();
		ArrayList<OrdineAcquistoBean> pacchettiAcquistati= new ArrayList<OrdineAcquistoBean>(); //Array che contiene i pacchetti acquistati
		OrdineAcquistoDao dao = new OrdineAcquistoDao();
		
		PacchettoDao pacchetto=new PacchettoDao();
		
		try {			
			pacchettiAcquistati = dao.findByNomeCliente(nomeUtente); // prende i pacchetti acquistati da un utente  e   ritorna un array di pacchetti ascquistati dall'utente		
			for(OrdineAcquistoBean e: pacchettiAcquistati) {
				ArrayList<PacchettoBean> pacchettiOrdineAttuale = e.getPacchettiAcquistati();
				pacchetti.addAll(pacchettiOrdineAttuale);// chiama il metodo getLezioni per prendere le lezioni del pacchetto e gli passa il codice			
				for(PacchettoBean p : pacchettiOrdineAttuale) {
					//Per ogni pacchetto ottengo tutte le lezioni
					ArrayList<LezioniBean> lezione = new ArrayList<LezioniBean>();
					lezione = pacchetto.getLezioni(p.getCodicePacchetto());// aggiunge le lezioni all'arrayList lezioni 	
					lezioni.add(lezione);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("pacchetti", pacchetti);
		request.setAttribute("lezioni", lezioni);
		
		RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/Libreria.jsp");
		dispatcher.forward(request, response);
	}

}
