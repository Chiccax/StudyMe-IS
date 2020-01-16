package model.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.OrdineAcquistoBean;
import model.dao.OrdineAcquistoDao;

public class OrdineManager {
	/**
	 * Costruttore vuoto 
	 **/
	public OrdineManager(){	
	}
	/**
	 * Ricerca il nome del cliente 
	 * @param String nomeUtente
	 * @return  ArrayList<OrdineAcquistoBean> listaOrdine
	 * @throws SQLException
	 **/
	public ArrayList<OrdineAcquistoBean> RicercaNomeCliente(String nomeUtente) throws SQLException{
		ArrayList<OrdineAcquistoBean> listaOrdine = ordineAcquistoDao.findByNomeCliente(nomeUtente);
		return listaOrdine;
	}
	OrdineAcquistoDao ordineAcquistoDao = new OrdineAcquistoDao();
}
