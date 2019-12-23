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

import modelBean.CarrelloBean;
import modelBean.LezioniBean;
import modelBean.OrdineAcquistoBean;
import modelBean.PacchettoBean;
import modelBean.RecensioneBean;
import modelBean.UtenteBean;
import modelDao.OrdineAcquistoDao;
import modelDao.PacchettoDao;//ds
import modelDao.RecensioneDao;
import modelDao.UtenteDao;


@WebServlet("/LezioneServlet")
public class LezioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LezioneServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String codicePacchetto = request.getParameter("codicePacchetto");
		String nomeAmministratore = null;

		ArrayList<OrdineAcquistoBean> ordiniCliente = null;
		OrdineAcquistoDao dao = new OrdineAcquistoDao();
		ArrayList<LezioniBean> lezioni = null;
		PacchettoBean pacchetto = null;
		ArrayList<RecensioneBean> recensioni = null;
		boolean comprato = false;
		boolean nelCarrello = false;
		boolean recensito = false;
		String tipo= null;
		
		PacchettoDao manager = new PacchettoDao();
		lezioni = manager.getLezioni(codicePacchetto);
		pacchetto = manager.getPacchetto(codicePacchetto);
		if(pacchetto == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		recensioni = manager.getRecensioni(codicePacchetto);
		
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
			String nomeUtente = user.getNomeUtente();
			ArrayList<PacchettoBean> pacchettiAcquistati = null;
			try {
				ordiniCliente = dao.findByNomeCliente(nomeUtente);
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
				RecensioneDao recensionedao = new RecensioneDao();
				try {
					recensito = recensionedao.isAlwreadyReviewed(nomeUtente, codicePacchetto);
				} catch (SQLException e) {
					e.printStackTrace();
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
					return;
				}
				
			}
		}
		
		request.setAttribute("lezioni", lezioni);
		request.setAttribute("pacchetto", pacchetto);
		request.setAttribute("recensioni", recensioni);
		request.setAttribute("comprato", comprato);
		request.setAttribute("nelCarrello", nelCarrello);
		request.setAttribute("recensito", recensito);
		request.setAttribute("tipo",tipo);
	
		RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/Lezione.jsp");
		dispatcher.forward(request, response);	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
