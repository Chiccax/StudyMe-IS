package model.manager;

import model.dao.RecensioneDao;

public class RecensioneManager {
	
	RecensioneDao recensionedao = new RecensioneDao();
	
	public RecensioneManager(){
		
	}
	public void aggiungiRecensione(String nomeUtente, String codicePacchetto,String titoloRecensione,String testoRecensione){
		recensionedao.aggiungiRecensione(nomeUtente, codicePacchetto, titoloRecensione, testoRecensione);
	}
}
