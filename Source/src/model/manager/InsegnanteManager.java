package model.manager;

import java.util.ArrayList;

import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.bean.UtenteBean;
import model.dao.InsegnanteDao;
import model.dao.PacchettoDao;

public class InsegnanteManager {
	/**
	 * Costruttore vuoto 
	 **/
	public InsegnanteManager(){	
	}
	/**
	 * Inserisce un nuovo pcchetto
	 * @param String nuovoCodice
	 * @param String nomeUtente
	 * @param String nuovaSottocategoria,
	 * @param double nuovoPrezzo
	 * @param String nuovaDescrizione
	 * @param String nuovoTitolo
	 * @param String nuovaFoto
	 * @return  PacchettoBean pacchettoDaInserire
	 * **/
	public PacchettoBean inserPacchetto(String nuovoCodice,String nomeUtente,String nuovaSottocategoria, double nuovoPrezzo,String nuovaDescrizione,String nuovoTitolo,String nuovaFoto){
		pacchettoDaInserire = insegnanteManager.inserPacchetto(nuovoCodice, nomeUtente, nuovaSottocategoria, nuovoPrezzo, nuovaDescrizione, nuovoTitolo, nuovaFoto);
		return pacchettoDaInserire;
	}
	/**
	 * Preleva un pacchetto
	 * @param String nuovoCodice
	 * @return PacchettoBean pacchetto
	 **/
	public PacchettoBean getPacchetto(String nuovoCodice){
		pacchetto = pacchettoDao.getPacchetto(nuovoCodice);
		return  pacchetto;
	}
	/**
	 * Preleva un pacchetto a seconda del titolo
	 * @param String nuovoTitolo
	 * @return PacchettoBean pacchettoEsistente
	 **/
	public PacchettoBean getPacchettoByTitolo(String nuovoTitolo){
		pacchettoEsistente = pacchettoDao.getPacchettoByTitolo(nuovoTitolo);
		return  pacchettoEsistente;
	}
	/**
	 * Modifica il codice del pacchetto
	 * @param String vecchioCodice
	 * @param String nuovoCodice
	 * @return 
	 * **/
	public void updateCode(String vecchioCodice, String nuovoCodice){
		insegnanteManager.updateCode(vecchioCodice, nuovoCodice);
	}
	/**
	 * Modifica il titolo del pacchetto
	 * @param String vecchioCodice
	 * @param String nuovoTitolo
	 * @return 
	 * **/
	public void updateTitle(String vecchioCodice, String nuovoTitolo){
		insegnanteManager.updateTitle(vecchioCodice, nuovoTitolo);
	}
	/**
	 * Modifica il prezzo  del pacchetto
	 * @param String vecchioCodice
	 * @param String nuovoPrezzo
	 * @return 
	 * **/
	public void updatePrice(String vecchioCodice, double nuovoPrezzo){
		insegnanteManager.updatePrice(vecchioCodice, nuovoPrezzo);
	}
	/**
	 * Modifica la descrzione  del pacchetto
	 * @param String vecchioCodice
	 * @param String nuovaDescrzione
	 * @return 
	 * **/
	public void updateDescr(String vecchioCodice, String nuovaDescrizione){
		insegnanteManager.updateDescr(vecchioCodice, nuovaDescrizione);
	}
	/**
	 * Cancella il  pacchetto
	 * @param String vecchioCodice
	 * @return 
	 * **/
	public void deletePacchetto(String vecchioCodice){
		insegnanteManager.deletePacchetto(vecchioCodice);
	}
	/**
	 * Inserisce una nuova lezione
	 * @param String codicePacchettoAttuale
	 * @param String url
	 * @param String titolo
	 * @param String durata
	 * @return LezioniBean lezioniBean 
	 * **/
	public LezioniBean insertLesson(String codicePacchettoAttuale,String url,String titolo,String durata){
		lezioniBean =insegnanteManager.insertLesson(codicePacchettoAttuale, url, titolo, durata);
		return lezioniBean;
	}
	/**
	 * Preleva la lezione a seconda del titolo
	 * @param String titolo
	 * @return  ArrayList<LezioniBean> lezioneTitoloEsistente
	 **/
	public ArrayList<LezioniBean> getLezioniByTitolo(String titolo){
		lezioneTitoloEsistente = pacchettoDao.getLezioniByTitolo(titolo);
		return   lezioneTitoloEsistente;
	}
	/**
	 * Preleva la lezione a seconda dell'url
	 * @param String url
	 * @return  ArrayList<LezioniBean> lezioneEsistente
	 **/
	public ArrayList<LezioniBean> getLezioniByURl(String url){
		lezioneEsistente = pacchettoDao.getLezioniByURl(url);
		return   lezioneEsistente;
	}
	/**
	 * Modifica il titolo della lezione
	 * @param String vecchioTitolo
	 * @param String nuovoNomeLezione
	 * @return 
	 * **/
	public void updateTitleLesson(String vecchioTitolo, String nuovoNomeLezione){
		insegnanteManager.updateTitleLesson(vecchioTitolo, nuovoNomeLezione);
	}
	/**
	 * Modifica la durata della lezione
	 * @param String vecchioTitolo
	 * @param String nuovaDurataLezione
	 * @return 
	 * **/
	public void updateDurationLesson(String vecchioTitolo, String nuovaDurataLezione){
		insegnanteManager.updateDurationLesson(vecchioTitolo, nuovaDurataLezione);
	}
	/**
	 * Cancella la  lezione
	 * @param String vecchioTitolo
	 * @return 
	 * **/
	public void deleteLesson(String titolo){
		insegnanteManager.deleteLesson(titolo);
	}
	/**
	 * Preleva gli acquirenti
	 * @param String vecchioCodice
	 * @return  ArrayList<UtenteBean> acquirenti
	 **/
	public ArrayList<UtenteBean> getAcquirenti(String vecchioCodice){
		acquirenti = pacchettoDao.getAcquirenti(vecchioCodice);
		return  acquirenti;
	}
	
	/**
	 * Modifica l'url  della lezione
	 * @param String vecchioCodice
	 * @param String nuovoUrlLezione
	 * @return 
	 * **/
	public void updateUrlLesson(String vecchioCodice, String nuovoUrlLezione){
		insegnanteManager.updateUrlLesson(vecchioCodice, nuovoUrlLezione);
	}
	PacchettoDao pacchettoDao = new PacchettoDao();
	InsegnanteDao insegnanteManager = new InsegnanteDao();
	PacchettoBean pacchetto= new PacchettoBean();
	PacchettoBean pacchettoEsistente= new PacchettoBean();
	ArrayList<LezioniBean> lezioneEsistente = new ArrayList<LezioniBean>() ;
	ArrayList<LezioniBean> lezioneTitoloEsistente= new ArrayList<LezioniBean>();
	PacchettoBean pacchettoDaInserire= new PacchettoBean();
	LezioniBean lezioniBean= new LezioniBean();
	ArrayList<UtenteBean> acquirenti= new ArrayList<UtenteBean>();
	
}
