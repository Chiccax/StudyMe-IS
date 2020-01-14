package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import model.bean.CategoriaBean;
import model.bean.LezioniBean;
import model.bean.OrdineAcquistoBean;
import model.bean.PacchettoBean;
import model.bean.RecensioneBean;
import model.bean.UtenteBean;
import model.dao.CategoriaDao;
import model.dao.OrdineAcquistoDao;
import model.dao.PacchettoDao;
import model.dao.RecensioneDao;
import model.manager.LezioneManager;
/** 
 * Gestisce le lezioni
 **/

@WebServlet("/LezioneServlet")
public class LezioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LezioneServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String codicePacchetto = request.getParameter("codicePacchetto");
		
		String categoria= null;
		boolean recensito = false;
		
		boolean comprato = false;
		boolean nelCarrello = false;
		String tipo= null;
		String nomeUtente= null;
		
		LezioneManager lezioneManager= new LezioneManager();
		PacchettoBean pacchetto = lezioneManager.getPacchetto(codicePacchetto);
		if(pacchetto == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		ArrayList<RecensioneBean> recensioni= lezioneManager.getRecensioni(codicePacchetto);
		HttpSession session = request.getSession();
		UtenteBean user = (UtenteBean) session.getAttribute("User");
		ArrayList<PacchettoBean> cart = (ArrayList<PacchettoBean>) session.getAttribute("carrello") ;
		if(cart == null) {
			cart = new ArrayList<PacchettoBean>();
			session.setAttribute("carrello", cart);
		}
		//se utente non loggato
		if(user==null){
			tipo= "nonLoggato";
		}else {//se utente loggato
			tipo= user.getTipo();
			nomeUtente = user.getNomeUtente();
			ArrayList<PacchettoBean> pacchettiAcquistati = null;
			
			ArrayList<OrdineAcquistoBean> ordiniCliente= lezioneManager.getOrdiniCliente(nomeUtente);
			for(OrdineAcquistoBean o : ordiniCliente) {
				pacchettiAcquistati = o.getPacchettiAcquistati();
				for(PacchettoBean p : pacchettiAcquistati) {
					if(p.getCodicePacchetto().equals(codicePacchetto)) {
						comprato = true;
						break;
					}
				}
			}
			if(!comprato) {
				//Controlla che sia nel carrello
				for(PacchettoBean product : cart) {
					if(product.getCodicePacchetto().equalsIgnoreCase(codicePacchetto)) {
						nelCarrello = true;
						break;
					}
				}
			}else {
				try {
					recensito= lezioneManager.getRecensito(nomeUtente, codicePacchetto);
				} catch (SQLException e) {
					e.printStackTrace();
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
					return;
				}
			}
		}
		CategoriaBean categoriaBean= lezioneManager.getCategoria(pacchetto);
		String insegnante= lezioneManager.getInsegnante(categoriaBean);
		ArrayList<LezioniBean> lezioni= lezioneManager.getLezioni(codicePacchetto,nomeUtente);
		
		request.setAttribute("lezioni", lezioni);
		request.setAttribute("pacchetto", pacchetto);
		request.setAttribute("recensioni", recensioni);
		request.setAttribute("comprato", comprato);
		request.setAttribute("nelCarrello", nelCarrello);
		request.setAttribute("recensito", recensito);
		request.setAttribute("tipo",tipo);
	
		if(insegnante.equals(nomeUtente)) {
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/LezioneInsegnante.jsp");
			dispatcher.forward(request, response);	
		} else {
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/Lezione.jsp");
			dispatcher.forward(request, response);	
			}	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
