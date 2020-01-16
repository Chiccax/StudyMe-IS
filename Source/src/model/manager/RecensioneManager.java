package model.manager;

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
		recensionedao.aggiungiRecensione(nomeUtente, codicePacchetto, titoloRecensione, testoRecensione);
	}
	
	RecensioneDao recensionedao = new RecensioneDao();
}
