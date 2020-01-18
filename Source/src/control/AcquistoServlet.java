package control;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.PacchettoBean;
import model.bean.UtenteBean;
import model.manager.AcquistoManager;

/**
 * Gestisce l'acquisto di un corso
 */
@WebServlet("/AcquistoServlet")
public class AcquistoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AcquistoManager acquistoManager= new AcquistoManager();
	
	public AcquistoServlet() {
		super();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		HttpSession session = request.getSession();
		UtenteBean user = (UtenteBean) session.getAttribute("User");
		
		@SuppressWarnings("unchecked")
		ArrayList<PacchettoBean> carrello = (ArrayList<PacchettoBean>) session.getAttribute("carrello");
		
		
		String userName = user.getNomeUtente();
		acquistoManager.getOrdine(userName, carrello);
		
		carrello.clear();
		session.setAttribute("carrello", carrello);
	}
}