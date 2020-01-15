package model.manager;

import java.util.ArrayList;

import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.bean.UtenteBean;
import model.dao.InsegnanteDao;
import model.dao.PacchettoDao;

public class InsegnanteManager {
	PacchettoDao pacchettoDao = new PacchettoDao();
	InsegnanteDao insegnanteManager = new InsegnanteDao();
	PacchettoBean pacchetto= new PacchettoBean();
	PacchettoBean pacchettoEsistente= new PacchettoBean();
	ArrayList<LezioniBean> lezioneEsistente = new ArrayList<LezioniBean>() ;
	ArrayList<LezioniBean> lezioneTitoloEsistente= new ArrayList<LezioniBean>();
	PacchettoBean pacchettoDaInserire= new PacchettoBean();
	LezioniBean lezioniBean= new LezioniBean();
	ArrayList<UtenteBean> acquirenti= new ArrayList<UtenteBean>();
	
	public InsegnanteManager(){
		
	}
	
	public PacchettoBean getPacchetto(String nuovoCodice){
		pacchetto = pacchettoDao.getPacchetto(nuovoCodice);
		return  pacchetto;
	}
	public PacchettoBean getPacchettoByTitolo(String nuovoTitolo){
		pacchettoEsistente = pacchettoDao.getPacchettoByTitolo(nuovoTitolo);
		return  pacchettoEsistente;
	}
	public ArrayList<LezioniBean> getLezioniByTitolo(String titolo){
		lezioneTitoloEsistente = pacchettoDao.getLezioniByTitolo(titolo);
		return   lezioneTitoloEsistente;
	}

	public ArrayList<LezioniBean> getLezioniByURl(String url){
		lezioneEsistente = pacchettoDao.getLezioniByURl(url);
		return   lezioneEsistente;
	}
	public ArrayList<UtenteBean> getAcquirenti(String vecchioCodice){
		acquirenti = pacchettoDao.getAcquirenti(vecchioCodice);
		return  acquirenti;
	}
	
	
	
	public void updateCode(String vecchioCodice, String nuovoCodice){
		insegnanteManager.updateCode(vecchioCodice, nuovoCodice);
	}
	public void updateTitle(String vecchioCodice, String nuovoTitolo){
		insegnanteManager.updateTitle(vecchioCodice, nuovoTitolo);
	}
	public void updateUrlLesson(String vecchioCodice, String nuovoUrlLezione){
		insegnanteManager.updateUrlLesson(vecchioCodice, nuovoUrlLezione);
	}
	public void updatePrice(String vecchioCodice, double nuovoPrezzo){
		insegnanteManager.updatePrice(vecchioCodice, nuovoPrezzo);
	}
	public void updateDescr(String vecchioCodice, String nuovaDescrizione){
		insegnanteManager.updateDescr(vecchioCodice, nuovaDescrizione);
	}
	public void deletePacchetto(String vecchioCodice){
		insegnanteManager.deletePacchetto(vecchioCodice);
	}
	public PacchettoBean inserPacchetto(String nuovoCodice,String nomeUtente,String nuovaSottocategoria, double nuovoPrezzo,String nuovaDescrizione,String nuovoTitolo,String nuovaFoto){
		pacchettoDaInserire = insegnanteManager.inserPacchetto(nuovoCodice, nomeUtente, nuovaSottocategoria, nuovoPrezzo, nuovaDescrizione, nuovoTitolo, nuovaFoto);
		return pacchettoDaInserire;
	}
	public LezioniBean insertLesson(String codicePacchettoAttuale,String url,String titolo,String durata){
		lezioniBean =insegnanteManager.insertLesson(codicePacchettoAttuale, url, titolo, durata);
		return lezioniBean;
	}
	public void updateTitleLesson(String vecchioTitolo, String nuovoNomeLezione){
		insegnanteManager.updateTitleLesson(vecchioTitolo, nuovoNomeLezione);
	}
	public void updateDurationLesson(String vecchioTitolo, String nuovaDurataLezione){
		insegnanteManager.updateDurationLesson(vecchioTitolo, nuovaDurataLezione);
	}
	public void deleteLesson(String titolo){
		insegnanteManager.deleteLesson(titolo);
	}

	
	
	
	
}
