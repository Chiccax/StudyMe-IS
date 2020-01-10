package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
import control.util.StartupUtility;
import modelBean.LezioniBean;
import modelBean.PacchettoBean;
import modelBean.UtenteBean;
import modelDao.InsegnanteDao;
import modelDao.PacchettoDao;
import modelDao.SottocategoriaDao;
/** 
 * Gestisce l' inserimento pacchetti e lezioni da parte dell'insegnnante
 **/ 
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
		//Cambia codice
				if(action.equalsIgnoreCase("cambiaCodice")) { 
					String nuovoCodice = request.getParameter("nuovoCodice");
					
					PacchettoDao pacchettoDao = new PacchettoDao();
					PacchettoBean pacchettoEsistente = pacchettoDao.getPacchetto(nuovoCodice);
					
					//pacchetto già esistente con tale codice
					if(pacchettoEsistente != null) {
						JSONResponse jsonResponse = new JSONResponse(false, INVALID_CODE);
						out.print(gson.toJson(jsonResponse));
						return;
					}
					
					pacchetto.setCodicePacchetto(nuovoCodice);
					InsegnanteDao manager = new InsegnanteDao();
					manager.updateCode(vecchioCodice, nuovoCodice);		
					JSONResponse jsonResponse = new JSONResponse(true, "OK");
					out.print(gson.toJson(jsonResponse));
				}//Cambia titolo
				else if(action.equalsIgnoreCase("cambiaTitolo")){
					String nuovoTitolo = request.getParameter("nuovoTitolo");
					
					//controllo se è tra i 5 e i 40 caratteri
					if(nuovoTitolo.length() < 5 || nuovoTitolo.length() > 40 ) {
						JSONResponse jsonResponse = new JSONResponse(false, INVALID_TITLE);
						out.print(gson.toJson(jsonResponse));
						return;
					}
					
					PacchettoDao pacchettoDao = new PacchettoDao();
					PacchettoBean pacchettoEsistente = pacchettoDao.getPacchettoByTitolo(nuovoTitolo);
					
					//pacchetto già esistente con tale titolo
					if(pacchettoEsistente != null) {
						JSONResponse jsonResponse = new JSONResponse(false, INVALID_TITLE2);
						out.print(gson.toJson(jsonResponse));
						return;
					}
					
					pacchetto.setTitolo(nuovoTitolo);
					InsegnanteDao manager = new InsegnanteDao();
					manager.updateTitle(vecchioCodice, nuovoTitolo);
					JSONResponse jsonResponse = new JSONResponse(true, "OK");
					out.print(gson.toJson(jsonResponse));
				}//Cambia prezzo
				else if(action.equalsIgnoreCase("cambiaPrezzo")){
					double nuovoPrezzo = 0;
					try {
						nuovoPrezzo = Double.parseDouble(request.getParameter("nuovoPrezzo"));
					} catch(NumberFormatException e) {
						JSONResponse jsonResponse = new JSONResponse(false, INVALID_PRICE);
						out.print(gson.toJson(jsonResponse));
						return;
					}
					pacchetto.setPrezzo(nuovoPrezzo);
					InsegnanteDao manager = new InsegnanteDao();
					manager.updatePrice(vecchioCodice, nuovoPrezzo);
					JSONResponse jsonResponse = new JSONResponse(true, "OK");
					out.print(gson.toJson(jsonResponse));
				}//Cambia descrizione
				else if(action.equalsIgnoreCase("cambiaDescrizione")){
					String nuovaDescrizione = request.getParameter("nuovaDescrizione");
					//controllo se è tra i 10 e i 30 caratteri
					if(nuovaDescrizione.length() < 10 || nuovaDescrizione.length() > 30 ) {
						JSONResponse jsonResponse = new JSONResponse(false, INVALID_DES);
						out.print(gson.toJson(jsonResponse));
						return;
					}

					pacchetto.setDescrizione(nuovaDescrizione);
					InsegnanteDao manager = new InsegnanteDao();
					manager.updateDescr(vecchioCodice, nuovaDescrizione);	
					JSONResponse jsonResponse = new JSONResponse(true, "OK");
					out.print(gson.toJson(jsonResponse));
				}//Rimuovi pacchetto
				else if(action.equalsIgnoreCase("rimuovi")) {
					if(vecchioCodice == null || vecchioCodice.length() == 0) {
						JSONResponse jsonResponse = new JSONResponse(false, NO_CODE);
						out.print(gson.toJson(jsonResponse));
						return;	
					}
					InsegnanteDao manager = new InsegnanteDao();
					manager.deletePacchetto(vecchioCodice);
					JSONResponse jsonResponse = new JSONResponse(true, "OK");
					out.print(gson.toJson(jsonResponse));
				}
				//Aggiungi pacchetto
				else if(action.equalsIgnoreCase("aggiungiPacchetto")) {
					String nuovoCodice = request.getParameter("nuovoCodice");
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
			
					if(nuovoCodice == null || nuovaSottocategoria == null || nuovoPrezzo == 0 || nuovaDescrizione == null || nuovoTitolo == null || nuovaFoto == null) {
						JSONResponse jsonResponse = new JSONResponse(false, NO_ARGUMENT);
						out.print(gson.toJson(jsonResponse));
						return;
					}
					PacchettoDao pacchettoDao = new PacchettoDao();
					PacchettoBean pacchettoEsistente = pacchettoDao.getPacchettoByTitolo(nuovoTitolo);
					
					//pacchetto già esistente con tale titolo
					if(pacchettoEsistente != null) {
						JSONResponse jsonResponse = new JSONResponse(false, INVALID_TITLE2);
						out.print(gson.toJson(jsonResponse));
						return;
					}
					//controllo se è tra i 5 e i 20 caratteri
					if(nuovoTitolo.length() < 5 || nuovoTitolo.length() > 20 ) {
						JSONResponse jsonResponse = new JSONResponse(false, INVALID_TITLE);
						out.print(gson.toJson(jsonResponse));
						return;
					}
					SottocategoriaDao sottocategoriaDao = new SottocategoriaDao();
			
					//Controllo che i codici di categoria e sottocategoria siano validi
					try {
						Object sottocategoria = sottocategoriaDao.findByKey(nuovaSottocategoria);
				
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
					
					HttpSession session = request.getSession();
					UtenteBean utente = (UtenteBean) session.getAttribute("User");
					String nomeUtente = utente.getNomeUtente();
					
			
					InsegnanteDao manager = new InsegnanteDao();
					PacchettoBean pacchettoDaInserire = manager.inserPacchetto(nuovoCodice, nomeUtente, nuovaSottocategoria, nuovoPrezzo, nuovaDescrizione, nuovoTitolo, nuovaFoto);
			
					if(pacchettoDaInserire == null){
						JSONResponse jsonResponse = new JSONResponse(false);
						out.print(gson.toJson(jsonResponse));
						return;	
					}else {
						JSONResponse jsonResponse = new JSONResponse(true, COMPLETE);
						out.print(gson.toJson(jsonResponse));
					}	
					session.setAttribute("PacchettoAttuale", pacchettoDaInserire);
				}//Aggiungi lezione
				else if(action.equalsIgnoreCase("aggiungiLezione")) {
					String url = request.getParameter("url");
					String titolo = request.getParameter("titolo");
					String durata = request.getParameter("durata");
			 
					if(url == null || url.length() == 0 || titolo == null || titolo.length() == 0 || durata == null || durata.length() == 0) {
						JSONResponse jsonResponse = new JSONResponse(false, NO_ARGUMENT);
						out.print(gson.toJson(jsonResponse));
						return;
					}
					//titolo compreso tra 5 e 20
					if(titolo.length() < 5 || titolo.length() > 20 ) {
						JSONResponse jsonResponse = new JSONResponse(false, INVALID_TITLE);
						out.print(gson.toJson(jsonResponse));
						return;
					}
					PacchettoDao pacchettoDao = new PacchettoDao();
					ArrayList<LezioniBean> lezioneTitoloEsistente = pacchettoDao.getLezioniByTitolo(titolo);
					
					//lezione già esistente con tale titolo
					if(!lezioneTitoloEsistente.isEmpty()) {
						JSONResponse jsonResponse = new JSONResponse(false, INVALID_TITLE2);
						out.print(gson.toJson(jsonResponse));
						return;
					}
					Pattern pattern = Pattern.compile("https:\\/\\/www.youtube.com\\/embed\\/\\w+");
					Matcher matcher = pattern.matcher(url);
					pacchettoDao = new PacchettoDao();
					ArrayList<LezioniBean> lezioneEsistente = pacchettoDao.getLezioniByURl(url);
					
					//lezione già esistente con tale url
					if(!lezioneEsistente.isEmpty()) {
						JSONResponse jsonResponse = new JSONResponse(false, INVALID_URL);
						out.print(gson.toJson(jsonResponse));
						return;
					}
					
					//controllo validità url
					if(!matcher.find()) {
						JSONResponse jsonResponse = new JSONResponse(false, NO_URL);
						out.print(gson.toJson(jsonResponse));
						return;	
					}
			
					HttpSession session = request.getSession();
			
					PacchettoBean pacchettoAtt = (PacchettoBean) session.getAttribute("PacchettoAttuale");
					InsegnanteDao manager = new InsegnanteDao();
					LezioniBean res =null;
					if(pacchettoAtt!=null)
					{	
						String codicePacchettoAttuale = pacchettoAtt.getCodicePacchetto();
						res = manager.insertLesson(codicePacchettoAttuale, url, titolo, durata);
					}else if(vecchioCodice!=null && vecchioCodice.length() != 0 ) {
							res = manager.insertLesson(vecchioCodice, url, titolo, durata);
							LezioniBean l = new LezioniBean();
							l.setPacchetto(vecchioCodice);
							l.setUrl(url);
							l.setDurata(durata);
							l.setTitolo(titolo);
							
							ArrayList<UtenteBean> acquirenti = new ArrayList<UtenteBean>();
							acquirenti = pacchettoDao.getAcquirenti(vecchioCodice);
							
							for(UtenteBean u : acquirenti) {
								l.addObserver(u);
							}
												
							StartupUtility.addLezioneSubject(l);
					}
			
					if(res == null){
						JSONResponse jsonResponse = new JSONResponse(false, NO_INSERT);
						out.print(gson.toJson(jsonResponse));
						return;	
					}else {
						JSONResponse jsonResponse = new JSONResponse(true, "OK");
						out.print(gson.toJson(jsonResponse));		
					}
			
					session.setAttribute("LezioneAttuale", res);
				}//Modifica lezione
				else if(action.equalsIgnoreCase("modificaNomeLezione")){
					String nuovoNomeLezione = request.getParameter("nuovoNomeLezione");
					//titolo compreso tra 5 e 20
					if(nuovoNomeLezione.length() < 5 || nuovoNomeLezione.length() > 20 ) {
						JSONResponse jsonResponse = new JSONResponse(false, INVALID_TITLE);
						out.print(gson.toJson(jsonResponse));
						return;
					}
					PacchettoDao pacchettoDao = new PacchettoDao();
					ArrayList<LezioniBean> lezioneTitoloEsistente =pacchettoDao.getLezioniByTitolo(nuovoNomeLezione);
					
					//lezione già esistente con tale titolo
					if(!lezioneTitoloEsistente.isEmpty()) {
						JSONResponse jsonResponse = new JSONResponse(false, INVALID_TITLE2);
						out.print(gson.toJson(jsonResponse));
						return;
					}
					lezione.setTitolo(nuovoNomeLezione);
					InsegnanteDao  manager = new InsegnanteDao();
					manager.updateTitleLesson(vecchioTitolo, nuovoNomeLezione);
					JSONResponse jsonResponse = new JSONResponse(true, "OK");
					out.print(gson.toJson(jsonResponse));
				}//Cambia url lezione
				else if(action.equalsIgnoreCase("modificaVideoLezione")){
					String nuovoUrlLezione = request.getParameter("nuovoUrlLezione");
					lezione.setUrl(nuovoUrlLezione);
			
					Pattern pattern = Pattern.compile("https:\\/\\/www.youtube.com\\/embed\\/\\w+");
					Matcher matcher = pattern.matcher(nuovoUrlLezione);
					
			
					if(!matcher.find()) {
						JSONResponse jsonResponse = new JSONResponse(false, NO_URL);
						out.print(gson.toJson(jsonResponse));
						return;	
					}
					
					PacchettoDao pacchettoDao = new PacchettoDao();
					ArrayList<LezioniBean> lezioneEsistente = pacchettoDao.getLezioniByURl(nuovoUrlLezione);
					
					//lezione già esistente con tale url
					if(!lezioneEsistente.isEmpty()) {
						JSONResponse jsonResponse = new JSONResponse(false, INVALID_URL);
						out.print(gson.toJson(jsonResponse));
						return;
					}
						
					InsegnanteDao manager = new InsegnanteDao();
					manager.updateUrlLesson(vecchioTitolo, nuovoUrlLezione);
					JSONResponse jsonResponse = new JSONResponse(true, "OK");
					out.print(gson.toJson(jsonResponse));
				}//Cambia durata
				else if(action.equalsIgnoreCase("modificaDurataLezione")){
					String nuovaDurataLezione = request.getParameter("nuovaDurataLezione");
					lezione.setDurata(nuovaDurataLezione);
					InsegnanteDao manager = new InsegnanteDao();
					manager.updateDurationLesson(vecchioTitolo, nuovaDurataLezione);
					JSONResponse jsonResponse = new JSONResponse(true, "OK");
					out.print(gson.toJson(jsonResponse));
				}//rimuovi lezione
				else if(action.equalsIgnoreCase("rimuoviLezione")){
					InsegnanteDao manager = new InsegnanteDao();
					manager.deleteLesson(vecchioTitolo);
					JSONResponse jsonResponse = new JSONResponse(true, "OK");
					out.print(gson.toJson(jsonResponse));		
				}

	}
	private static final String INVALID_TITLE = "Inserire un titolo compreso tra i 5 e 20 caratteri";
	private static final String INVALID_TITLE2 = "titolo gi&agrave esistente";
	private static final String INVALID_DES = "inserire una descrizione compresa tra i 10 e i 30 caratteri";
	private static final String INVALID_URL = "Url già esistente";
  
	private static final String NO_URL = "Url non valido!";
	private static final String NO_INSERT = "Inserimento non riuscito!";
	private static final String COMPLETE = "Pacchetto inserito con successo!";
	private static final String INVALID_PRICE = "Prezzo non valido";
	
	private static final String INVALID_CODE = "Codice pacchetto gi&agrave; in uso";
	private static final String NO_CODE = "Inserire codice per proseguire!";
	private static final String NO_ARGUMENT = "Tutti i parametri devono essere compilati";
	private static final String NO_SOTTOCATEGORY = "Codice sottocategoria non valido";
}