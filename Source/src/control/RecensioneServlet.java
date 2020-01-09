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
/** 
 * Gestisce l'inserimento di una recensione
 **/
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
		if(titoloRecensione.length() < 5 || titoloRecensione.length() > 30 ) {
			JSONResponse jsonResponse = new JSONResponse(false, INVALID_TITLE);
			out.print(gson.toJson(jsonResponse));
			return;
		}
		if(testoRecensione.length() < 10 || testoRecensione.length() > 30 ) {
			JSONResponse jsonResponse = new JSONResponse(false, INVALID_TEXT);
			out.print(gson.toJson(jsonResponse));
			return;
		}
		
		RecensioneDao recensionedao = new RecensioneDao();	
		recensionedao.aggiungiRecensione(nomeUtente, codicePacchetto, titoloRecensione, testoRecensione);
		JSONResponse jsonResponse = new JSONResponse(true);
		out.print(gson.toJson(jsonResponse));
	}
	
	private static final String NO_ARGUMENT = "Tutti i parametri devono essere passati";
	private static final String INVALID_TITLE = "il titolo deve essere tra i 5 e i 30 caratteri";
	private static final String INVALID_TEXT = "il testo deve essere tra i 10 e i 30 caratteri";
}