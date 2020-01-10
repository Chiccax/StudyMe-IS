package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import control.util.JSONResponse;
import model.bean.PacchettoBean;
import model.bean.UtenteBean;
/** 
 * Gestisce la conclusione di un ordine 
 **/

@WebServlet("/TerminaOrdineServlet")
public class TerminaOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public TerminaOrdineServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<PacchettoBean> carrello=(ArrayList<PacchettoBean>)session.getAttribute("carrello");
		UtenteBean user=(UtenteBean)session.getAttribute("User");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		if( user== null || carrello == null || carrello.size() == 0) {
			JSONResponse jsonResponse = new JSONResponse(false);
			out.print(gson.toJson(jsonResponse));
			return;
		}

		HashMap<String, Object> content = new HashMap<String, Object>();
		
		String total = "";
		double totale = 0;
		for(PacchettoBean pacchettoAcquistato : carrello) {
			totale = (pacchettoAcquistato.getPrezzo() + totale);
		}
		
		total += totale;
		
		content.put("totale", total);
		content.put("pacchetti", carrello);
		
		JSONResponse jsonResponse = new JSONResponse(true, "OK", content);
		out.print(gson.toJson(jsonResponse));
        }
}	