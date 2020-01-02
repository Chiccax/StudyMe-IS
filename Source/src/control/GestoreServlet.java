package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelBean.LezioniBean;
import modelBean.PacchettoBean;
import modelDao.GestoreDao;

@WebServlet("/GestoreServlet")
public class GestoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public GestoreServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String azione = request.getParameter("azione");
		
		if(azione.equals("mostraPacchettiDaApprovare")) {
			ArrayList<PacchettoBean> pacchettiDaApprovare = new ArrayList<PacchettoBean>();
			ArrayList<LezioniBean> lezioniDaApprovare = new ArrayList<LezioniBean>();
			ArrayList<LezioniBean> lezioniPacchetto = null;
			GestoreDao manager = new GestoreDao();
			pacchettiDaApprovare = manager.visualizzaPacchettiDaApprovare();
			lezioniDaApprovare = manager.visualizzaLezioniDaApprovare();
			
			HttpSession session = request.getSession();
			session.setAttribute("pacchettiDaApprovare", pacchettiDaApprovare);
			session.setAttribute("lezioniDaApprovare", lezioniDaApprovare);
			
			Map<String, ArrayList<LezioniBean>> lezioniPacchettoDaApprovare = new HashMap<String, ArrayList<LezioniBean>>();
			for(PacchettoBean pacchetto : pacchettiDaApprovare) {
				for(LezioniBean lezioni : lezioniDaApprovare) {
					if(lezioni.getPacchetto().equals(pacchetto.getCodicePacchetto())) {
						lezioniPacchetto = new ArrayList<LezioniBean>();
						lezioniPacchetto.add(lezioni);
					} 
				}
				lezioniPacchettoDaApprovare.put(pacchetto.getCodicePacchetto(), lezioniPacchetto);
			}
			
			session.setAttribute("lezioniPacchettoDaApprovare", lezioniPacchettoDaApprovare);
		} else if (azione.equals("approvaInteroPacchetto")) {
			String codicePacchetto = request.getParameter("codicePacchetto");
			GestoreDao manager = new GestoreDao();
			manager.approvaInteroPacchetto(codicePacchetto);
		}
	}
}