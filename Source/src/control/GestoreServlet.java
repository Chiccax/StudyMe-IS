package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import control.util.JSONResponse;
import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.manager.UtenteManager;
/** 
 * Gestisce i pacchetti (approvazione e disapprovazione dei pacchetti)
 **/
@WebServlet("/GestoreServlet")
public class GestoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public GestoreServlet() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String azione = request.getParameter("action");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		UtenteManager utenteManager= new UtenteManager();
		
		if(azione.equals("mostraPacchettiDaApprovare")) {
			ArrayList<PacchettoBean> pacchettiDaApprovare = new ArrayList<PacchettoBean>();
			ArrayList<LezioniBean> lezioniDaApprovare = new ArrayList<LezioniBean>();
			ArrayList<LezioniBean> lezioniPacchetto = null;
			
			pacchettiDaApprovare = utenteManager.visualizzaPacchettiDaApprovare();
			lezioniDaApprovare = utenteManager.visualizzaLezioniDaApprovare();

			if(pacchettiDaApprovare == null) {
				JSONResponse jsonResponse = new JSONResponse(false);
				out.print(gson.toJson(jsonResponse));
			}
			Map<String, ArrayList<LezioniBean>> lezioniPacchettoDaApprovare = new HashMap<String, ArrayList<LezioniBean>>();
			for(PacchettoBean pacchetto : pacchettiDaApprovare) {
				lezioniPacchetto = new ArrayList<LezioniBean>();
				for(LezioniBean lezioni : lezioniDaApprovare) {
						if(lezioni.getPacchetto().equals(pacchetto.getCodicePacchetto())) {
							lezioniPacchetto.add(lezioni);
		
						}
				}
				lezioniPacchettoDaApprovare.put(pacchetto.getCodicePacchetto(), lezioniPacchetto);
		}
			Map<String, Object> pacchettiELezioniDaApprovare = new HashMap<String, Object>();
			pacchettiELezioniDaApprovare.put("pacchettiDaApprovare", pacchettiDaApprovare);
			pacchettiELezioniDaApprovare.put("lezioniPacchettoDaApprovare", lezioniPacchettoDaApprovare); 
	
			JSONResponse jsonResponse = new JSONResponse(true, "ok", pacchettiELezioniDaApprovare);
			out.print(gson.toJson(jsonResponse));
		} else if (azione.equals("approvaInteroPacchetto")) {
			String codicePacchetto = request.getParameter("codicePacchetto");
			utenteManager.approvaInteroPacchetto(codicePacchetto);
			JSONResponse jsonResponse = new JSONResponse(true);
			out.print(gson.toJson(jsonResponse));
		} else if (azione.equals("disapprovaInteroPacchetto")) {
			String codicePacchetto = request.getParameter("codicePacchetto");
			utenteManager.disapprovaInteroPacchetto(codicePacchetto);
			JSONResponse jsonResponse = new JSONResponse(true);
			out.print(gson.toJson(jsonResponse));
		} else if (azione.equals("approvaSingolaLezione")) {
			String urlLezione = request.getParameter("urlLezione");
			utenteManager.approvaSingolaLezione(urlLezione);
			JSONResponse jsonResponse = new JSONResponse(true);
			out.print(gson.toJson(jsonResponse));
		}else if(azione.equals("disapprovaSingolaLezione")) {
			String urlLezione = request.getParameter("urlLezione");
			utenteManager.disapprovaSingolaLezione(urlLezione);
			JSONResponse jsonResponse = new JSONResponse(true);
			out.print(gson.toJson(jsonResponse));
		}
	}
}