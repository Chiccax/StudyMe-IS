package model.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import model.bean.CategoriaBean;
import model.bean.LezioniBean;
import model.bean.OrdineAcquistoBean;
import model.bean.PacchettoBean;
import model.bean.RecensioneBean;
import model.dao.CategoriaDao;
import model.dao.OrdineAcquistoDao;
import model.dao.PacchettoDao;
import model.dao.RecensioneDao;

public class LezioneManager {
	/**
	 * Preleva la recensione
	 * @param String nomeUtente
	 * @param String codicePacchetto
	 * @throws SQLException
	 * @return true va a buon fine, false altrimenti
	 **/
	public boolean getRecensito(String nomeUtente,String codicePacchetto) throws SQLException{
		  boolean recensito= recensionedao.isAlwreadyReviewed(nomeUtente, codicePacchetto);
		  return recensito;
	}
	/**
	 * Preleva il pacchetto.
	 * @param String codicePacchetto
	 * @return PacchettoBean pacchetto
	 **/
	public PacchettoBean getPacchetto(String codicePacchetto){
		PacchettoBean pacchetto= manager.getPacchetto(codicePacchetto);
		return pacchetto;
	}
	/**
	 * Preleva l'insegnante.
	 * @param CategoriaBean categoriaBean
	 * @return String insegnante
	 **/
	public String getInsegnante(CategoriaBean categoriaBean){
		insegnante= categoriaBean.getInsegnante();
		return insegnante;
	}
	/**
	 * Preleva la categoria.
	 * @param PacchettoBean pacchetto
	 * @return CategoriaBean categoriaBean
	 **/
	public CategoriaBean getCategoria(PacchettoBean pacchetto){
		CategoriaBean categoriaBean= new CategoriaBean();
		try {
			String categoria= pacchetto.getCatagoria();
			categoriaBean= daoCategoria.findByKey(categoria);
			//String insegnante= getInsegnante(categoriaBean);//insegnante categoria
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoriaBean;
	}
	/**
	 * Preleva le recensioni.
	 * @param String codicePacchetto
	 * @return ArrayList<RecensioneBean> recensioni
	 **/
	public ArrayList<RecensioneBean> getRecensioni(String codicePacchetto){
		ArrayList<RecensioneBean> recensioni=manager.getRecensioni(codicePacchetto);
		return recensioni;
	}
	/**
	 * Preleva le lezioni.
	 * @param String codicePacchetto
	 * @param String nomeUtente
	 * @return ArrayList<LezioniBean> lezioni
	 **/
	public ArrayList<LezioniBean> getLezioni(String codicePacchetto,String nomeUtente){
		ArrayList<LezioniBean> lezioni= new ArrayList<LezioniBean>();
		if(insegnante.equals(nomeUtente)) {
			lezioni = manager.getLezioni(codicePacchetto);
		}else{
			lezioni = manager.getLezioniApprovate(codicePacchetto);
		}
		return lezioni;
	}
	/**
	 * Preleva gli ordini del cliente.
	 * @param String nomeUtente
	 * @return ArrayList<OrdineAcquistoBean> ordiniCliente
	 **/
	public ArrayList<OrdineAcquistoBean> getOrdiniCliente(String nomeUtente){
		ArrayList<OrdineAcquistoBean> ordiniCliente= new ArrayList<OrdineAcquistoBean>();
		try {
			ordiniCliente = dao.findByNomeCliente(nomeUtente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordiniCliente;
	}
	RecensioneDao recensionedao = new RecensioneDao();
	PacchettoDao manager = new PacchettoDao();
	CategoriaDao daoCategoria= new CategoriaDao();
	String insegnante= "";
	OrdineAcquistoDao dao = new OrdineAcquistoDao();
}
