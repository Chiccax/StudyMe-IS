package model.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.LezioniBean;
import model.bean.OrdineAcquistoBean;
import model.bean.PacchettoBean;
import model.bean.UtenteBean;
import model.dao.OrdineAcquistoDao;
import model.dao.PacchettoDao;

public class LibreriaManager {

	ArrayList<ArrayList<LezioniBean>> lezioni= new ArrayList<ArrayList<LezioniBean>>();
	ArrayList<PacchettoBean> pacchetti = new ArrayList<PacchettoBean>();
	ArrayList<OrdineAcquistoBean> pacchettiAcquistati= new ArrayList<OrdineAcquistoBean>(); //Array che contiene i pacchetti acquistati
	OrdineAcquistoDao dao = new OrdineAcquistoDao();
	
	PacchettoDao pacchetto=new PacchettoDao();
	/**
	 *Costruttore vuoto 
	 **/
	public LibreriaManager(){
	}
	/**
	 * Preleva i pacchetti dell'utente.
	 * @param UtenteBean user
	 * @return ArrayList<PacchettoBean> pacchetti
	 **/
	public ArrayList<PacchettoBean> getPacchetti(UtenteBean user){
		String nomeUtente=user.getNomeUtente();
		try {			
			pacchettiAcquistati = dao.findByNomeCliente(nomeUtente); // prende i pacchetti acquistati da un utente  e   ritorna un array di pacchetti ascquistati dall'utente		
			for(OrdineAcquistoBean e: pacchettiAcquistati) {
				ArrayList<PacchettoBean> pacchettiOrdineAttuale = e.getPacchettiAcquistati();
				pacchetti.addAll(pacchettiOrdineAttuale);// chiama il metodo getLezioni per prendere le lezioni del pacchetto e gli passa il codice			
				for(PacchettoBean p : pacchettiOrdineAttuale) {
					//Per ogni pacchetto ottengo tutte le lezioni
					ArrayList<LezioniBean> lezione = new ArrayList<LezioniBean>();
					lezione = pacchetto.getLezioni(p.getCodicePacchetto());// aggiunge le lezioni all'arrayList lezioni 	
					lezioni.add(lezione);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pacchetti;
	}
	/**
	 * Preleva i pacchetti dell'utente.
	 * @return ArrayList<ArrayList<LezioniBean>> lezioni
	 **/
	public ArrayList<ArrayList<LezioniBean>> getLezioni(){
		return lezioni;
	}
}
