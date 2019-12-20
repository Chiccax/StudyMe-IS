package control;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Map;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelBean.CategoriaBean;
import modelBean.PacchettoBean;
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
		
		PacchettoDao dao = new PacchettoDao();
		pacchetti = dao.getCategoriaRaggruppato(categoria);
		fotoCat= dao.getBeanCategoria(categoria);
		
		
		if(pacchetti == null || pacchetti.size()==0) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		request.setAttribute("categoria", categoria);
		request.setAttribute("pacchetti", pacchetti);
		request.setAttribute("fotoCat", fotoCat);

		RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/Catalogo.jsp");
		dispatcher.forward(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
