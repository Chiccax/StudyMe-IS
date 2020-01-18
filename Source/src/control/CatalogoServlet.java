package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.CategoriaBean;
import model.bean.PacchettoBean;
import model.bean.UtenteBean;
import model.manager.CatalogoManager;

/**
 * Gestisce la visualizzazione dei pacchetti nelle varie categorie
 */
@WebServlet("/CatalogoServlet")
public class CatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected CatalogoServlet() {
        super();
      
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoria=request.getParameter("categoria");	
		String userName= null;
		HttpSession session = request.getSession();
		UtenteBean user = (UtenteBean) session.getAttribute("User");
		
		if(user==null){
			userName= "nonLoggato";
		}else{
			userName = user.getNomeUtente();//nomeUtente della sessione
		}
		
		CatalogoManager catalogoManager= new CatalogoManager();
		Map<String,ArrayList<PacchettoBean>> pacchetti= catalogoManager.getPacchettiPerCategoria(categoria, userName);
		if(pacchetti == null || pacchetti.size()==0) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		CategoriaBean foto= catalogoManager.getFotoCat(categoria);
		String insegnante= catalogoManager.getInsegnante();
		request.setAttribute("categoria", categoria);
		request.setAttribute("pacchetti", pacchetti);
		request.setAttribute("fotoCat", foto);
		request.setAttribute("insegnante", insegnante);
		request.setAttribute("userName", userName);
		
		RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/Catalogo.jsp");
		dispatcher.forward(request, response);
	 }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
		

}
