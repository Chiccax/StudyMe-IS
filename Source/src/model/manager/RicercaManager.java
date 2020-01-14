package model.manager;

import java.util.ArrayList;

import model.bean.PacchettoBean;
import model.dao.PacchettoDao;

public class RicercaManager {
	
	ArrayList<PacchettoBean> pacchettiRicercati = null;
	PacchettoDao manager = new PacchettoDao();
	
	public RicercaManager(){
		
	}
	
	public ArrayList<PacchettoBean> ricercaPacchetto(String argument){
		pacchettiRicercati = manager.searchPackage(argument);
		return pacchettiRicercati;
	}

}
