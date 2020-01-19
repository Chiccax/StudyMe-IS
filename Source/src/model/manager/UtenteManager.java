
package model.manager;

import java.util.ArrayList;
import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.bean.UtenteBean;
import model.dao.GestoreDao;
import model.dao.UtenteDao;

public class UtenteManager {
	/***
	 * Costruttore vuoto. 
	 */
	public UtenteManager(){
		
	}
	/**
	 * Preleva gli utenti.
	 * @param String nomeUtente
	 * @param String passwordBase64format
	 * @return UtenteBean user
	 **/
	public UtenteBean getUtente(String  nomeUtente, String passwordBase64format){
		UtenteDao u;
		if(daoUtente != null) {
			u = daoUtente;
		} else {
			u = new UtenteDao();
		}
		
		UtenteBean user = u.login(nomeUtente, passwordBase64format);
		return user;
	}

	/**
	 * Modifica l'email.
	 * @param String emailUtente
	 * @param String nuovaEmailUtente
	 * @return true se l'operazione va a buon fine, false altrimenti
	 **/
	public boolean setEmail(String emailUtente, String nuovaEmailUtente){
		UtenteDao u;
		if(daoUtente != null) {
			u = daoUtente;
		} else {
			u = new UtenteDao();
		}
		
		boolean res= u.updateEmail(emailUtente, nuovaEmailUtente);
		return res;
	}
	/**
	 * Modifica la password.
	 * @param String emailUtente,
	 * @param String passwordBase64format
	 * @return true se l'operazione va a buon fine, false altrimenti
	 **/
	public boolean setPassword(String emailUtente, String passwordBase64format){
		UtenteDao u;
		if(daoUtente != null) {
			u = daoUtente;
		} else {
			u = new UtenteDao();
		}
		
		boolean res= u.updatePassword(emailUtente, passwordBase64format);
		return res;
	}
	/**
	 * Effettua la registrazione
	 * @param String email
	 * @param String nomeUtente
	 * @param String passwordBase64format
	 * @return true se l'operazione va a buon fine, false altrimenti
	 **/
	public boolean registrazione(String email,String nomeUtente,String passwordBase64format){
		UtenteDao u;
		if(daoUtente != null) {
			u = daoUtente;
		} else {
			u = new UtenteDao();
		}
		
		boolean res= u.registration(email, nomeUtente, passwordBase64format);
		return res;
	}
	/**
	 * Effettua il login
	 * @param String nomeUtente
	 * @param String passwordBase64format
	 * @return UtenteBean utente
	 **/
	public UtenteBean login(String nomeUtente,String passwordBase64format){
		UtenteDao u;
		if(daoUtente != null) {
			u = daoUtente;
		} else {
			u = new UtenteDao();
		}
		
		UtenteBean utente= u.login(nomeUtente, passwordBase64format);
		return utente;
	}
	/**
	 * Visualizza i pacchetti da approvare
	 * @return ArrayList<PacchettoBean> pacchettiDaApprovare
	 **/
	public ArrayList<PacchettoBean> visualizzaPacchettiDaApprovare(){
		 ArrayList<PacchettoBean> pacchettiDaApprovare = gestoreDao.visualizzaPacchettiDaApprovare();
		 return pacchettiDaApprovare;
	}
	/**
	 * Visualizzale lezioni da approvare
	 * @return ArrayList<LezioniBean> lezioniDaApprovare
	 **/
	public ArrayList<LezioniBean> visualizzaLezioniDaApprovare(){
		GestoreDao g;
		if(dao != null) {
			g = dao;
		} else {
			g = new GestoreDao();
		}
		
		ArrayList<LezioniBean> lezioniDaApprovare = g.visualizzaLezioniDaApprovare();
		 return lezioniDaApprovare;
	}
	/**
	 * Approva intero pacchetto
	 * @param String codicePacchetto
	 **/
	public void approvaInteroPacchetto(String codicePacchetto){
		GestoreDao g;
		if(dao != null) {
			g = dao;
		} else {
			g= new GestoreDao();
		}
		
		g.approvaInteroPacchetto(codicePacchetto);
	}
	/**
	 * Disapprova intero pacchetto
	 * @param String codicePacchetto
	 **/
	public void disapprovaInteroPacchetto(String codicePacchetto){
		GestoreDao g;
		if(dao != null) {
			g = dao;
		} else {
			g= new GestoreDao();
		}
		
		g.disapprovaInteroPacchetto(codicePacchetto);
	}
	/**
	 * Approva la singola lezione
	 * @param String urlLezione
	 **/
	public void approvaSingolaLezione(String urlLezione){
		GestoreDao g;
		if(dao != null) {
			g = dao;
		} else {
			g = new GestoreDao();
		}
		
		g.approvaSingolaLezione(urlLezione);
	}
	/**
	 * Disapprova  la singola lezione
	 * @param String urlLezione
	 **/
	public void disapprovaSingolaLezione(String urlLezione){
		GestoreDao g;
		if(dao != null) {
			g = dao;
		} else {
			g= new GestoreDao();
		}
		
		g.disapprovaSingolaLezione(urlLezione);
	}
	
	public void setDao(GestoreDao g) {
		this.dao = g;
	}
	
	public void setDaoUtente(UtenteDao u) {
		this.daoUtente = u;
	}
	
	private GestoreDao dao;
	private UtenteDao daoUtente;
	UtenteDao utenteDao = new UtenteDao();
	GestoreDao gestoreDao = new GestoreDao();
}
