package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import control.util.JSONResponse;
import modelBean.LezioniBean;
import modelBean.PacchettoBean;
import modelDao.InsegnanteDao;
import modelDao.CategoriaDao;
import modelDao.PacchettoDao;
import modelDao.SottocategoriaDao;

@WebServlet("/InsegnanteServlet")
public class InsegnanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsegnanteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		PacchettoBean pacchetto = new PacchettoBean();
		LezioniBean lezione = new LezioniBean();
		Gson gson = new Gson();
		String action = request.getParameter("azione");
		
		if(action == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		String vecchioCodice = request.getParameter("vecchioCodice");
		String vecchioTitolo = request.getParameter("vecchioTitolo");

		//Modifica pacchetto
		//Aggiungi pacchetto
		if(action.equalsIgnoreCase("aggiungiPacchetto")) {
			String nuovoCodice = request.getParameter("nuovoCodice");
			String nuovaCategoria = request.getParameter("categoria");
			String nuovaSottocategoria = request.getParameter("sottocategoria");
			String nuovoTitolo = request.getParameter("titolo");
			String nuovaFoto =  request.getParameter("foto");
			double nuovoPrezzo = 0;
			try {
				nuovoPrezzo = Double.parseDouble(request.getParameter("prezzo"));
			}catch(NumberFormatException e){
				nuovoPrezzo = 0;
			}
			String nuovaDescrizione = request.getParameter("descrizione");
			
			if(nuovoCodice == null || nuovaSottocategoria == null || nuovaCategoria == null || nuovoPrezzo == 0 || nuovaDescrizione == null || nuovoTitolo == null || nuovaFoto == null) {
				JSONResponse jsonResponse = new JSONResponse(false, NO_ARGUMENT);
				out.print(gson.toJson(jsonResponse));
				return;
			}
			
			CategoriaDao categoriaDao = new CategoriaDao();
			SottocategoriaDao sottocategoriaDao = new SottocategoriaDao();
			
			//Controllo che i codici di categoria e sottocategoria siano validi
			try {
				Object categoria = categoriaDao.findByKey(nuovaCategoria);
				Object sottocategoria = sottocategoriaDao.findByKey(nuovaSottocategoria);
				
				if(categoria == null) {
					JSONResponse jsonResponse = new JSONResponse(false, NO_CATEGORY);
					out.print(gson.toJson(jsonResponse));
					return;	
				}
				
				if(sottocategoria == null) {
					JSONResponse jsonResponse = new JSONResponse(false, NO_SOTTOCATEGORY);
					out.print(gson.toJson(jsonResponse));
					return;	
				}
			} catch (SQLException e) {
				JSONResponse jsonResponse = new JSONResponse(false, NO_INSERT);
				out.print(gson.toJson(jsonResponse));
				return;	
			}
     
			PacchettoBean pacchettoDaInserire = new PacchettoBean();
			pacchettoDaInserire.setCodicePacchetto(nuovoCodice);
			pacchettoDaInserire.setCatagoria(nuovaCategoria);
			pacchettoDaInserire.setPrezzo(nuovoPrezzo);
			pacchettoDaInserire.setDescrizione(nuovaDescrizione);
			pacchettoDaInserire.setTitolo(nuovoTitolo);
			pacchettoDaInserire.setFoto(nuovaFoto);
			pacchettoDaInserire.setApprovato(0);
			pacchettoDaInserire.setSottocategoria(nuovaSottocategoria);
			
			HttpSession session = request.getSession();
			session.setAttribute("PacchettoAttuale", pacchettoDaInserire);
			//InsegnanteDao manager = new InsegnanteDao();
			//boolean res = manager.inserPacchetto(nuovoCodice, nuovaCategoria, nuovaSottocategoria, nuovoPrezzo, nuovaDescrizione, nuovoTitolo, nuovaFoto);
			/*if(res == false){
				JSONResponse jsonResponse = new JSONResponse(false);
				out.print(gson.toJson(jsonResponse));
				return;	
			}else {*/
				JSONResponse jsonResponse = new JSONResponse(true, COMPLETE);
				out.print(gson.toJson(jsonResponse));
			//}	
		}//Aggiungi lezione
		else if(action.equalsIgnoreCase("aggiungiLezione")) {
			String url = request.getParameter("url");
			String titolo = request.getParameter("titolo");
			String durata = request.getParameter("durata");
			
			if(vecchioCodice == null || vecchioCodice.length() == 0 || url == null || url.length() == 0 || titolo == null || titolo.length() == 0 || durata == null || durata.length() == 0) {
				JSONResponse jsonResponse = new JSONResponse(false, NO_ARGUMENT);
				out.print(gson.toJson(jsonResponse));
				return;
			}
			
			Pattern pattern = Pattern.compile("https:\\/\\/www.youtube.com\\/embed\\/\\w+");
			Matcher matcher = pattern.matcher(url);
			
			if(!matcher.find()) {
				JSONResponse jsonResponse = new JSONResponse(false, NO_URL);
				out.print(gson.toJson(jsonResponse));
				return;	
			}
			
			LezioniBean lezioneDaInserire = new LezioniBean();
			lezioneDaInserire.setPacchetto(vecchioCodice);
			lezioneDaInserire.setUrl(url);
			lezioneDaInserire.setTitolo(titolo);
			lezioneDaInserire.setDurata(durata);
			
			HttpSession session = request.getSession();
			session.setAttribute("LezioneAttuale", lezioneDaInserire);
			
			/*InsegnanteDao manager = new InsegnanteDao();
			boolean res = manager.insertLesson(vecchioCodice, url, titolo, durata);
			
			if(res == false){
				JSONResponse jsonResponse = new JSONResponse(false, NO_INSERT);
				out.print(gson.toJson(jsonResponse));
				return;	
			}else {*/
				JSONResponse jsonResponse = new JSONResponse(true, "OK");
				out.print(gson.toJson(jsonResponse));		
			//}	
		}//Modifica lezione
	}
	
	private static final String NO_URL = "Url non valido!";
	private static final String NO_INSERT = "Inserimento non riuscito!";
	private static final String COMPLETE = "Pacchetto inserito con successo!";
	private static final String INVALID_PRICE = "Prezzo non valido";
	private static final String INVALID_CODE = "Codice pacchetto gi&agrave; in uso";
	private static final String NO_CODE = "Inserire codice per proseguire!";
	private static final String NO_ARGUMENT = "Tutti i parametri devono essere compilati";
	private static final String NO_CATEGORY = "Categoria non valida";
	private static final String NO_SOTTOCATEGORY = "Codice sottocategoria non valido";
}