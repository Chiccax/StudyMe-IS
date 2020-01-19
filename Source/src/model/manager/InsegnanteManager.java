package model.manager;

import java.util.ArrayList;

import model.bean.LezioniBean;
import model.bean.PacchettoBean;
import model.bean.UtenteBean;
import model.dao.GestoreDao;
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
		InsegnanteDao i;
		if(dao != null) {
			i = dao;
		} else {
			i = new InsegnanteDao();
		}
		
		pacchettoDaInserire = i.inserPacchetto(nuovoCodice, nomeUtente, nuovaSottocategoria, nuovoPrezzo, nuovaDescrizione, nuovoTitolo, nuovaFoto);
		return pacchettoDaInserire;
	}
	/**
	 * Preleva un pacchetto
	 * @param String nuovoCodice
	 * @return PacchettoBean pacchetto
	 **/
	public PacchettoBean getPacchetto(String nuovoCodice){
		PacchettoDao p;
		if(daoPacchetto != null) {
			p = daoPacchetto;
		} else {
			p = new PacchettoDao();	
		}
		
		pacchetto = p.getPacchetto(nuovoCodice);
		return  pacchetto;
	}
	/**
	 * Preleva un pacchetto a seconda del titolo
	 * @param String nuovoTitolo
	 * @return PacchettoBean pacchettoEsistente
	 **/
	public PacchettoBean getPacchettoByTitolo(String nuovoTitolo){
		PacchettoDao p;
		if(daoPacchetto != null) {
			p = daoPacchetto;
		} else {
			p = new PacchettoDao();	
		}
		
		pacchettoEsistente = p.getPacchettoByTitolo(nuovoTitolo);
		return  pacchettoEsistente;
	}
	/**
	 * Modifica il codice del pacchetto
	 * @param String vecchioCodice
	 * @param String nuovoCodice
	 * @return 
	 * **/
	public void updateCode(String vecchioCodice, String nuovoCodice){
		InsegnanteDao i;
		if(dao != null) {
			i = dao;
		} else {
			i = new InsegnanteDao();
		}
		i.updateCode(vecchioCodice, nuovoCodice);
	}
	/**
	 * Modifica il titolo del pacchetto
	 * @param String vecchioCodice
	 * @param String nuovoTitolo
	 * @return 
	 * **/
	public void updateTitle(String vecchioCodice, String nuovoTitolo){
		InsegnanteDao i;
		if(dao != null) {
			i = dao;
		} else {
			i = new InsegnanteDao();
		}
		i.updateTitle(vecchioCodice, nuovoTitolo);
	}
	/**
	 * Modifica il prezzo  del pacchetto
	 * @param String vecchioCodice
	 * @param String nuovoPrezzo
	 * @return 
	 * **/
	public void updatePrice(String vecchioCodice, double nuovoPrezzo){
		InsegnanteDao i;
		if(dao != null) {
			i = dao;
		} else {
			i = new InsegnanteDao();
		}
		i.updatePrice(vecchioCodice, nuovoPrezzo);
	}
	/**
	 * Modifica la descrzione  del pacchetto
	 * @param String vecchioCodice
	 * @param String nuovaDescrzione
	 * @return 
	 * **/
	public void updateDescr(String vecchioCodice, String nuovaDescrizione){
		InsegnanteDao i;
		if(dao != null) {
			i = dao;
		} else {
			i = new InsegnanteDao();
		}
		i.updateDescr(vecchioCodice, nuovaDescrizione);
	}
	/**
	 * Cancella il  pacchetto
	 * @param String vecchioCodice
	 * @return 
	 * **/
	public void deletePacchetto(String vecchioCodice){
		InsegnanteDao i;
		if(dao != null) {
			i = dao;
		} else {
			i = new InsegnanteDao();
		}
		i.deletePacchetto(vecchioCodice);
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
		InsegnanteDao i;
		if(dao != null) {
			i = dao;
		} else {
			i = new InsegnanteDao();
		}
		
		lezioniBean = i.insertLesson(codicePacchettoAttuale, url, titolo, durata);
		return lezioniBean;
	}
	/**
	 * Preleva la lezione a seconda del titolo
	 * @param String titolo
	 * @return  ArrayList<LezioniBean> lezioneTitoloEsistente
	 **/
	public ArrayList<LezioniBean> getLezioniByTitolo(String titolo){
		PacchettoDao p;
		if(daoPacchetto != null) {
			p = daoPacchetto;
		} else {
			p = new PacchettoDao();	
		}
		
		lezioneTitoloEsistente = p.getLezioniByTitolo(titolo);
		return   lezioneTitoloEsistente;
	}
	/**
	 * Preleva la lezione a seconda dell'url
	 * @param String url
	 * @return  ArrayList<LezioniBean> lezioneEsistente
	 **/
	public ArrayList<LezioniBean> getLezioniByURl(String url){
		PacchettoDao p;
		if(daoPacchetto != null) {
			p = daoPacchetto;
		} else {
			p = new PacchettoDao();	
		}
		
		lezioneEsistente = p.getLezioniByURl(url);
		return   lezioneEsistente;
	}
	/**
	 * Modifica il titolo della lezione
	 * @param String vecchioTitolo
	 * @param String nuovoNomeLezione
	 * @return 
	 * **/
	public void updateTitleLesson(String vecchioTitolo, String nuovoNomeLezione){
		InsegnanteDao i;
		if(dao != null) {
			i = dao;
		} else {
			i = new InsegnanteDao();
		}
		i.updateTitleLesson(vecchioTitolo, nuovoNomeLezione);
	}
	/**
	 * Modifica la durata della lezione
	 * @param String vecchioTitolo
	 * @param String nuovaDurataLezione
	 * @return 
	 * **/
	public void updateDurationLesson(String vecchioTitolo, String nuovaDurataLezione){
		InsegnanteDao i;
		if(dao != null) {
			i = dao;
		} else {
			i = new InsegnanteDao();
		}
		i.updateDurationLesson(vecchioTitolo, nuovaDurataLezione);
	}
	/**
	 * Cancella la  lezione
	 * @param String vecchioTitolo
	 * @return 
	 * **/
	public void deleteLesson(String titolo){
		InsegnanteDao i;
		if(dao != null) {
			i = dao;
		} else {
			i = new InsegnanteDao();
		}
		i.deleteLesson(titolo);
	}
	/**
	 * Preleva gli acquirenti
	 * @param String vecchioCodice
	 * @return  ArrayList<UtenteBean> acquirenti
	 **/
	public ArrayList<UtenteBean> getAcquirenti(String vecchioCodice){
		PacchettoDao p;
		if(daoPacchetto != null) {
			p = daoPacchetto;
		} else {
			p = new PacchettoDao();	
		}
		
		acquirenti = p.getAcquirenti(vecchioCodice);
		return  acquirenti;
	}
	
	/**
	 * Modifica l'url  della lezione
	 * @param String vecchioCodice
	 * @param String nuovoUrlLezione
	 * @return 
	 * **/
	public void updateUrlLesson(String vecchioCodice, String nuovoUrlLezione){
		InsegnanteDao i;
		if(dao != null) {
			i = dao;
		} else {
			i = new InsegnanteDao();
		}
		i.updateUrlLesson(vecchioCodice, nuovoUrlLezione);
	}
	
	public void setDao(InsegnanteDao i) {
		this.dao = i;
	}
	
	public void setDaoPacchetto(PacchettoDao p) {
		this.daoPacchetto = p;
	}
	
	InsegnanteDao insegnanteManager = new InsegnanteDao();
	PacchettoDao pacchettoDao = new PacchettoDao();
	InsegnanteDao dao;
	PacchettoDao daoPacchetto;
	PacchettoBean pacchetto= new PacchettoBean();
	PacchettoBean pacchettoEsistente= new PacchettoBean();
	ArrayList<LezioniBean> lezioneEsistente = new ArrayList<LezioniBean>() ;
	ArrayList<LezioniBean> lezioneTitoloEsistente= new ArrayList<LezioniBean>();
	PacchettoBean pacchettoDaInserire= new PacchettoBean();
	LezioniBean lezioniBean= new LezioniBean();
	ArrayList<UtenteBean> acquirenti= new ArrayList<UtenteBean>();
	
}
