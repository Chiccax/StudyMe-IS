package model.manager;

import java.util.ArrayList;

import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.bean.UtenteBean;
import model.dao.GestoreDao;
import model.dao.UtenteDao;

public class UtenteManager {
	UtenteDao utenteDao = new UtenteDao();
	GestoreDao gestoreDao = new GestoreDao();
	
	public UtenteManager(){
		
	}
	
	public UtenteBean getUtente(String  nomeUtente, String passwordBase64format)
	{
		UtenteBean user = utenteDao.login(nomeUtente, passwordBase64format);
		return user;
	}
	public boolean setEmail(String emailUtente, String nuovaEmailUtente){
		boolean res= utenteDao.updateEmail(emailUtente, nuovaEmailUtente);
		return res;
	}
	public boolean setPassword(String emailUtente, String passwordBase64format){
		boolean res= utenteDao.updatePassword(emailUtente, passwordBase64format);
		return res;
	}
	
	public boolean registrazione(String email,String nomeUtente,String passwordBase64format){
		 boolean res= utenteDao.registration(email, nomeUtente, passwordBase64format);
		 return res;
	}
	public UtenteBean login(String nomeUtente,String passwordBase64format){
		UtenteBean utente= utenteDao.login(nomeUtente, passwordBase64format);
		 return utente;
	}
	public ArrayList<PacchettoBean> visualizzaPacchettiDaApprovare(){
		 ArrayList<PacchettoBean> pacchettiDaApprovare = gestoreDao.visualizzaPacchettiDaApprovare();
		 return pacchettiDaApprovare;
	}
	public ArrayList<LezioniBean> visualizzaLezioniDaApprovare(){
		ArrayList<LezioniBean> lezioniDaApprovare = gestoreDao.visualizzaLezioniDaApprovare();
		 return lezioniDaApprovare;
	}
	public void approvaInteroPacchetto(String codicePacchetto){
		gestoreDao.approvaInteroPacchetto(codicePacchetto);
	}
	public void disapprovaInteroPacchetto(String codicePacchetto){
		gestoreDao.disapprovaInteroPacchetto(codicePacchetto);
	}
	public void approvaSingolaLezione(String urlLezione){
		gestoreDao.approvaSingolaLezione(urlLezione);
	}
	public void disapprovaSingolaLezione(String urlLezione){
		gestoreDao.disapprovaSingolaLezione(urlLezione);
	}
	
}
