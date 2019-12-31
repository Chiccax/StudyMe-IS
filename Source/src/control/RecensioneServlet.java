package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import control.util.JSONResponse;
import modelDao.RecensioneDao;

@WebServlet("/RecensioneServlet")
public class RecensioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public RecensioneServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		String nomeUtente = request.getParameter("utente");
		String codicePacchetto = request.getParameter("codicePacchetto");
		String titoloRecensione = request.getParameter("titoloRecensione");
		String testoRecensione = request.getParameter("testoRecensione");
		
		if(nomeUtente == null || codicePacchetto == null || titoloRecensione == null || testoRecensione == null) {
			JSONResponse jsonResponse = new JSONResponse(false, NO_ARGUMENT);
			out.print(gson.toJson(jsonResponse));
			return;
		}
		
		RecensioneDao recensionedao = new RecensioneDao();	
		recensionedao.aggiungiRecensione(nomeUtente, codicePacchetto, titoloRecensione, testoRecensione);
		JSONResponse jsonResponse = new JSONResponse(true);
		out.print(gson.toJson(jsonResponse));
	}
	
	private static final String NO_ARGUMENT = "Tutti i parametri devono essere passati";
}