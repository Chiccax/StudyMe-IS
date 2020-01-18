package model.manager;

import model.dao.GestoreDao;
import model.dao.RecensioneDao;

public class RecensioneManager {
	/**
	 * Costruttore vuoto. 
	 **/
	public RecensioneManager(){	
	}
	/**
	 * Aggiungi una recesnione.
	 * @param String nomeUtente
	 * @param String codicePacchetto
	 * @param String titoloRecensione
	 * @param String testoRecensione
	 * @return 
	 **/
	public void aggiungiRecensione(String nomeUtente, String codicePacchetto,String titoloRecensione,String testoRecensione){
		RecensioneDao r;
		if(dao != null) {
			r = dao;
		} else {
			r = new RecensioneDao();
		}
		r.aggiungiRecensione(nomeUtente, codicePacchetto, titoloRecensione, testoRecensione);
	}
	
	public void setDao(RecensioneDao r) {
		this.dao = r;
	}
	
	private RecensioneDao dao;
}
