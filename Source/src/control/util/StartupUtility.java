package control.util;

import java.sql.SQLException;
import java.util.*;

import modelBean.*;
import modelDao.OrdineAcquistoDao;
import modelDao.OrdineDao;
import modelDao.PacchettoDao;
import modelDao.UtenteDao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupUtility implements ServletContextListener {

	//Mappa lezioni subject
	  private static Map<String, LezioniBean> map = new HashMap<String, LezioniBean>();
	
	  @Override
	  public void contextDestroyed(ServletContextEvent arg0) {
	    //Quando il server si arresta  
	  }

	  @Override
	  public void contextInitialized(ServletContextEvent arg0) {
		  //Quando il server si avvia
		  
		  System.out.println("Mi sto avviando");
		  
		  UtenteDao utenteDao = new UtenteDao();
		  List<UtenteBean> utenti = utenteDao.getAllAcquirenti();
		  
		  PacchettoDao pacchettoDao = new PacchettoDao();
		  List<PacchettoBean> pacchetti = pacchettoDao.getAllPacchetti();
		  
		  OrdineAcquistoDao ordineDao = new OrdineAcquistoDao();
		  
		  try {
			  for(UtenteBean utente : utenti) {
				  List<OrdineAcquistoBean> pacchettiUtente = ordineDao.findByNomeCliente(utente.getNomeUtente());
				  for(int k = 0; k < pacchettiUtente.size(); k++) {
					  OrdineAcquistoBean ordine = pacchettiUtente.get(k);
					  List<PacchettoBean> pacchettiAttuali = ordine.getPacchettiAcquistati();
					  for(int i = 0; i < pacchettiAttuali.size(); i++) {
						  List<LezioniBean> lezioni = pacchettoDao.getLezioni(pacchettiAttuali.get(i).getCodicePacchetto());
						  for(int j = 0; j < lezioni.size(); j++) {
							  LezioniBean lezioneCorrente = lezioni.get(j);
							  
							  if(lezioneCorrente.getApprovato() == 0) {
								  map.put(lezioneCorrente.getUrl(), lezioneCorrente);
								  utente.attach(lezioneCorrente);
								  System.out.println(utente.getNomeUtente() + " " + lezioneCorrente.getTitolo());
							  }
								  
						  }
					  }
				  }
				  
			  }
		  } catch(SQLException e) {
			  e.printStackTrace();
			  return;
		  }
	  }
	  
	  public static void addLezioneSubject(LezioniBean lezione) {
		  map.put(lezione.getUrl(), lezione);
	  }

}
