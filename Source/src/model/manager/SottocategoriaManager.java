package model.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.SottocategoriaBean;
import model.dao.InsegnanteDao;
import model.dao.SottocategoriaDao;

public class SottocategoriaManager {
	
	/**
	 * Costruttore vuoto 
	 **/
	public SottocategoriaManager(){}
	
	/**
	 * Seleziona la sottocategoria dell'insegnante
	 * @param String utente
	 * @return ArrayList<SottocategoriaBean> sottocategorie
	 **/
	public ArrayList<SottocategoriaBean> selezionaSottocagorieInsegnante(String utente){
		SottocategoriaDao s;
		if(manager != null) {
			s = manager;
		} else {
			s = new SottocategoriaDao();
		}

		sottocategorie = s.selezionaSottocagorieInsegnante(utente);
		return sottocategorie;
	}
	
	/**
	 * Seleziona la sottocategoria dell'insegnante
	 * @param String nuovaSottocategoria
	 * @return Object sottocategorie
	 * @throws SQLException
	 **/
	public Object findByKey(Object codiceS) throws SQLException{
		SottocategoriaDao s;
		if(manager != null) {
			s = manager;
		} else {
			s = new SottocategoriaDao();
		}
		
		sottocategoria = s.findByKey(codiceS);
		return sottocategoria;
	}
	
	public void setDao(SottocategoriaDao a) {
		manager = a;
	}
	
	SottocategoriaDao manager;
	Object sottocategoria = null;
	ArrayList<SottocategoriaBean> sottocategorie = new ArrayList<SottocategoriaBean>();
}
