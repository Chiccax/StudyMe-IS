package control;

import java.io.IOException;

import java.util.regex.Matcher;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Base64;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import control.util.JSONResponse;
import modelBean.UtenteBean;
import modelDao.UtenteDao;

/** 
 * Gestisce la registrazione di un nuovo utente
 **/

@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegistrazioneServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter(); 
		Gson gson = new Gson();
		response.setContentType("text/html");	
		
		String nomeUtente = request.getParameter("NomeUtente");
		String email = request.getParameter("Email");
		String password = request.getParameter("Password");
		String confPassword = request.getParameter("ConfermaPassword");
		
		String pattern = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
		
		if (nomeUtente == null || email == null ||password == null || confPassword == null) {
			JSONResponse jsonResponse = new JSONResponse(false, NO_ARGUMENT);
			out.print(gson.toJson(jsonResponse));
			return;
		}
		
		if(nomeUtente.length() < 4) {
			JSONResponse jsonResponse = new JSONResponse(false, INVALID_USER);
			out.print(gson.toJson(jsonResponse));
			return;
		}
		
		if(password.length() < 8) {
			JSONResponse jsonResponse = new JSONResponse(false, INVALID_PASSWORD);
			out.print(gson.toJson(jsonResponse));
			return;
		}
		
		if(!password.equals(confPassword)) {
			JSONResponse jsonResponse = new JSONResponse(false, NO_PASSWORD);
			out.print(gson.toJson(jsonResponse));
			return;	
		}
		
		if(!email.matches(pattern)){
			JSONResponse jsonResponse = new JSONResponse(false, NO_EMAILVALIDATE);
			out.print(gson.toJson(jsonResponse));
			return;	
		}else {
			String passwordBase64format  = Base64.getEncoder().encodeToString(password.getBytes()); 
			UtenteDao manager = new UtenteDao();
			boolean res = manager.registration(email, nomeUtente, passwordBase64format);
	
			if(!res) {
				JSONResponse jsonResponse = new JSONResponse(false, NO_USER);
				out.print(gson.toJson(jsonResponse));
				return;			
			}else {
				UtenteBean user = manager.login(nomeUtente, passwordBase64format);
				HttpSession session = request.getSession();
				session.setAttribute("User", user);
				JSONResponse jsonResponse = new JSONResponse(true, "OK");
				out.print(gson.toJson(jsonResponse));
			}
		}
	}
	private static final String INVALID_PASSWORD = "Inserire una password da almeno 8 caratteri";
	private static final String NO_ARGUMENT = "Tutti i parametri devono essere passati";
	private static final String NO_PASSWORD = "Le password non coincidono";
	private static final String NO_USER = "Utente già esistente";
	private static final String NO_EMAILVALIDATE = "Formato email non valido";
	private static final String INVALID_USER = "Inserire un nome utente da almeno 4 caratteri";
}