package model.manager;

import java.util.ArrayList;

import model.bean.PacchettoBean;
import model.dao.PacchettoDao;

public class RicercaManager {
	/**
	 * Costruttore vuoto 
	 **/
	public RicercaManager(){	
	}
	/**
	 * Ricerca i pacchetti.
	 * @param String argument
	 * @return ArrayList<PacchettoBean> pacchettiRicercati
	 **/
	public ArrayList<PacchettoBean> ricercaPacchetto(String argument){
		pacchettiRicercati = manager.searchPackage(argument);
		return pacchettiRicercati;
	}
	ArrayList<PacchettoBean> pacchettiRicercati = null;
	PacchettoDao manager = new PacchettoDao();
}
