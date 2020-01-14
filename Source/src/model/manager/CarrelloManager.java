package model.manager;

import java.util.ArrayList;

import model.bean.PacchettoBean;
import model.dao.PacchettoDao;

public class CarrelloManager {
	private PacchettoDao dao = new PacchettoDao();
	
	public CarrelloManager(){
		
	}
	
	public void aggiungiAlCarrello(ArrayList<PacchettoBean> carrello,String codiceP){
		boolean nelCarrello = false;
		PacchettoBean pacchetto = new PacchettoBean();
		pacchetto = dao.getPacchetto(codiceP);
		for (PacchettoBean p : carrello) {
			if (p.getCodicePacchetto().equalsIgnoreCase(pacchetto.getCodicePacchetto())) {
				nelCarrello = true;
				break;
			}
		}
		if (!nelCarrello) {
			carrello.add(pacchetto);
		}
		
	}
	
}