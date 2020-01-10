package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import control.util.JSONResponse;
import model.bean.OrdineAcquistoBean;
import model.bean.UtenteBean;
import model.dao.OrdineAcquistoDao;
/** 
 * Gestisce l'ordine dell'acquirente 
 **/
@WebServlet("/OrdiniServlet")
public class OrdiniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public OrdiniServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<OrdineAcquistoBean> listaOrdine = new ArrayList<OrdineAcquistoBean>();
		
		OrdineAcquistoDao ordineAcquistoDao = new OrdineAcquistoDao();
		HttpSession session = request.getSession();
		UtenteBean user =(UtenteBean)session.getAttribute("User");
		
		String nomeUtente=user.getNomeUtente();
		
		JSONResponse result;
		
		try {	
			listaOrdine = ordineAcquistoDao.findByNomeCliente(nomeUtente);
			result = new JSONResponse(true, "ok" , listaOrdine);
			if(listaOrdine.size() == 0) {
				result = new JSONResponse(false, "Non ci sono ordini");
			}
		}
		catch(SQLException e) {
			result = new JSONResponse(false, "Problema con il database");
		}
		
		response.getWriter().print(new Gson().toJson(result));
	}
}