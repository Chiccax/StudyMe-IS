package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelBean.CategoriaBean;
import modelBean.PacchettoBean;
import modelBean.UtenteBean;
import modelDao.CategoriaDao;
import modelDao.PacchettoDao;



/**
 * Servlet implementation class CatalogoServlet
 */
@WebServlet("/CatalogoServlet")
public class CatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CatalogoServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoria=request.getParameter("categoria");	
		
		Map<String,ArrayList<PacchettoBean>> pacchetti = null;
		CategoriaBean fotoCat= null;
		CategoriaBean categoriaBean= null;
		String insegnante= null;
		String userName= null;
		
		HttpSession session = request.getSession();
		UtenteBean user = (UtenteBean) session.getAttribute("User");
		
		if(user==null){
			userName= "nonLoggato";
		}else{
			userName = user.getNomeUtente();//nomeUtente della sessione
		}
		CategoriaDao daoCategoria= new CategoriaDao();
		PacchettoDao dao = new PacchettoDao();
		
		fotoCat= dao.getBeanCategoria(categoria);
		try {
			categoriaBean= daoCategoria.findByKey(categoria);
			insegnante= categoriaBean.getInsegnante();//insegnante categoria
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(insegnante.equals(userName)){
			pacchetti = dao.getCategoriaRaggruppato(categoria);
		}else{
			pacchetti= dao.getCategoriaRaggruppatoApprovato(categoria);
		}
		
		if(pacchetti == null || pacchetti.size()==0) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		request.setAttribute("categoria", categoria);
		request.setAttribute("pacchetti", pacchetti);
		request.setAttribute("fotoCat", fotoCat);
		request.setAttribute("insegnante", insegnante);
		request.setAttribute("userName", userName);
		
		

		RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/Catalogo.jsp");
		dispatcher.forward(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
