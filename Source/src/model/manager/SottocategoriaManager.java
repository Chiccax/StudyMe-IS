package model.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.SottocategoriaBean;
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
		sottocategorie = manager.selezionaSottocagorieInsegnante(utente);
		return sottocategorie;
	}
	
	/**
	 * Seleziona la sottocategoria dell'insegnante
	 * @param String nuovaSottocategoria
	 * @return Object sottocategorie
	 * @throws SQLException
	 **/
	public Object findByKey(Object codiceS) throws SQLException{
		sottocategoria = manager.findByKey(codiceS);
		return sottocategoria;
	}
	
	SottocategoriaDao manager = new SottocategoriaDao();
	Object sottocategoria = null;
	ArrayList<SottocategoriaBean> sottocategorie = new ArrayList<SottocategoriaBean>();
}
