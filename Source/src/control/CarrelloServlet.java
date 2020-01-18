package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import com.google.gson.Gson;
import control.util.JSONResponse;
import model.bean.PacchettoBean;
import model.manager.CarrelloManager;
/** 
 * Gestisce l'acquisto di un pacchetto
 **/
@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
   public CarrelloServlet() {
        super();
       
    }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String codiceP = request.getParameter("codiceP");
		
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();

		@SuppressWarnings("unchecked")
		ArrayList<PacchettoBean> carrello = (ArrayList<PacchettoBean>) session.getAttribute("carrello");

		if (carrello == null) {
			carrello = new ArrayList<PacchettoBean>();
		}

		if(action == null || codiceP == null) {
			JSONResponse jsonResponse = new JSONResponse(false);
			out.print(gson.toJson(jsonResponse));
			return;
		}
			
		if (action.equalsIgnoreCase("aggiungiAlCarrello")) {
				CarrelloManager carrelloManager= new CarrelloManager();
				carrelloManager.aggiungiAlCarrello(carrello, codiceP);

		} else if (action.equalsIgnoreCase("rimuoviDalCarrello")) {
				for (int i = 0; i < carrello.size(); i++) {
					if (codiceP.equalsIgnoreCase(carrello.get(i).getCodicePacchetto())) {
						carrello.remove(i);
						break;
					}
				}

		} else if (action.equalsIgnoreCase("rimuovitutto")) {
				carrello.clear();
		}
		
		Map<String, Object> responseContent = new HashMap<String, Object>();
		responseContent.put("qta", carrello.size());
		responseContent.put("total", getCartTotal(carrello));
		
		JSONResponse jsonResponse = new JSONResponse(true, "ok", responseContent);
		out.print(gson.toJson(jsonResponse));
		session.setAttribute("carrello", carrello);
	}
	
	private double getCartTotal(ArrayList<PacchettoBean> cart) {
		double total = 0;
		
		for(PacchettoBean pacchetto : cart) {
			total += pacchetto.getPrezzo();
		}
		
		return total;
	}

}