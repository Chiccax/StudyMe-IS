package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelBean.LezioniBean;
import modelBean.PacchettoBean;
import modelDao.GestoreDao;

public class GestoreServlet {
	
	public GestoreServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<PacchettoBean> pacchettiDaApprovare = new ArrayList<PacchettoBean>();
		ArrayList<LezioniBean> lezioniDaApprovare = new ArrayList<LezioniBean>();
		
		GestoreDao manager = new GestoreDao();
		pacchettiDaApprovare = manager.visualizzaPacchettiDaApprovare();
		lezioniDaApprovare = manager.visualizzaLezioniDaApprovare();
		
		
		HttpSession session = request.getSession();
		session.setAttribute("pacchettiDaApprovare", pacchettiDaApprovare);
		session.setAttribute("lezioniDaApprovare", lezioniDaApprovare);
		
		Map<String, ArrayList<LezioniBean>> lezioniPacchettoDaApprovare = new HashMap<String, ArrayList<LezioniBean>>();
		
	}
}