package model.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.OrdineAcquistoBean;
import model.dao.OrdineAcquistoDao;

public class OrdineManager {
	OrdineAcquistoDao ordineAcquistoDao = new OrdineAcquistoDao();
	
	public OrdineManager(){
		
	}
	
	public ArrayList<OrdineAcquistoBean> RicercaNomeCliente(String nomeUtente) throws SQLException
	{
		ArrayList<OrdineAcquistoBean> listaOrdine = ordineAcquistoDao.findByNomeCliente(nomeUtente);
		return listaOrdine;
	}
	
}
