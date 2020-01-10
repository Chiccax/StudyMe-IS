package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

import com.google.gson.Gson;

import control.util.JSONResponse;
import modelBean.SottocategoriaBean;
import modelDao.SottocategoriaDao;

/**
 * Gestisce la sottocategoria dell'insegnante 
 **/

@WebServlet("/SottocategoriaInsegnanteServlet")
public class SottocategoriaInsegnanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SottocategoriaInsegnanteServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String utente = request.getParameter("utente");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		SottocategoriaDao manager = new SottocategoriaDao();
		ArrayList<SottocategoriaBean> sottocategorie = manager.selezionaSottocagorieInsegnante(utente);
		
		if(sottocategorie != null) {
			JSONResponse jsonResponse = new JSONResponse(true, "ok", sottocategorie);
			out.print(gson.toJson(jsonResponse));
			return;
		}
		
		JSONResponse jsonResponse = new JSONResponse(false);
		out.print(gson.toJson(jsonResponse));
	}

}
