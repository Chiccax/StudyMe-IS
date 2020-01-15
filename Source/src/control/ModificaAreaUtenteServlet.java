package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import control.util.JSONResponse;
import model.bean.UtenteBean;
import model.manager.UtenteManager;

/**
 * Gestisce la modifica dei dati personali
 */
@WebServlet("/ModificaAreaUtenteServlet")
public class ModificaAreaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ModificaAreaUtenteServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		Gson gson = new Gson();
		UtenteManager utenteManager= new UtenteManager();
		String nuovaEmailUtente = request.getParameter("NuovaEmailUtente");
		String nuovaPasswordUtente = request.getParameter("NuovaPasswordUtente");
		String confermaNuovaPasswordUtente = request.getParameter("ConfermaNuovaPasswordUtente");
		UtenteBean loggedUser = (UtenteBean) request.getSession().getAttribute("User");
		if(loggedUser == null) {
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/HomePage.jsp");
			dispatcher.forward(request, response);
		}else {
			String emailUtente = loggedUser.getEmail();
			if(nuovaEmailUtente != null && nuovaPasswordUtente == null) {
				if(nuovaEmailUtente.length() <= 0) {
					JSONResponse jsonResponse = new JSONResponse(false, NO_EMAIL);
					out.print(gson.toJson(jsonResponse));
					return;	
				}
				
				
				boolean res = utenteManager.setEmail(emailUtente, nuovaEmailUtente);
				if(res == false) {
					JSONResponse jsonResponse = new JSONResponse(false, NO_USER);
					out.print(gson.toJson(jsonResponse));
					return;	
				}else {
					loggedUser.setEmail(nuovaEmailUtente);
					JSONResponse jsonResponse = new JSONResponse(true, "OK");
					out.print(gson.toJson(jsonResponse));
				}
			} else if(nuovaEmailUtente == null && nuovaPasswordUtente != null) {
				if(nuovaPasswordUtente.equals(confermaNuovaPasswordUtente)) {
					if(nuovaPasswordUtente.length() < 8) {
						JSONResponse jsonResponse = new JSONResponse(false, NO_VALIDEPASSWORD);
						out.print(gson.toJson(jsonResponse));
						return;	
					}
					if(nuovaPasswordUtente.length() <= 0){
						JSONResponse jsonResponse = new JSONResponse(false, EMPTY_PASSWORD);
						out.print(gson.toJson(jsonResponse));
						return;	
					}
					String passwordBase64format  = Base64.getEncoder().encodeToString(nuovaPasswordUtente.getBytes()); 
					boolean res = utenteManager.setPassword(emailUtente, passwordBase64format);
					if(res == false) {
						JSONResponse jsonResponse = new JSONResponse(false, NO_USER);
						out.print(gson.toJson(jsonResponse));
						return;	
					}else {
						JSONResponse jsonResponse = new JSONResponse(true, "OK");
						out.print(gson.toJson(jsonResponse));
					}
				} else {
					JSONResponse jsonResponse = new JSONResponse(false, NO_PASSWORD);
					out.print(gson.toJson(jsonResponse));
					return;	
				}
			}
		}
	}
	
	private static final String NO_EMAIL = "Campo email vuoto";
	private static final String NO_USER = "Utente non esistente";
	private static final String NO_VALIDEPASSWORD = "Inserire password di almeno 8 caratteri";
	private static final String NO_PASSWORD = "Le password non coincidono";
	private static final String EMPTY_PASSWORD = "Campo password vuoto";
}