package model.manager;

import model.bean.UtenteBean;
import model.dao.UtenteDao;

public class UtenteManager {
	UtenteDao manager = new UtenteDao();
	
	public UtenteManager(){
		
	}
	
	public UtenteBean getUtente(String  nomeUtente, String passwordBase64format)
	{
		UtenteBean user = manager.login(nomeUtente, passwordBase64format);
		return user;
	}
	public boolean setEmail(String emailUtente, String nuovaEmailUtente){
		boolean res= manager.updateEmail(emailUtente, nuovaEmailUtente);
		return res;
	}
	public boolean setPassword(String emailUtente, String passwordBase64format){
		boolean res= manager.updatePassword(emailUtente, passwordBase64format);
		return res;
	}
	
}
