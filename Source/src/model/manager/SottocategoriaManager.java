package model.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.SottocategoriaBean;
import model.dao.SottocategoriaDao;

public class SottocategoriaManager {
	/**
	 * Costruttore vuoto 
	 **/
	public SottocategoriaManager(){
		
	}
	/**
	 * Seleziona la sottocategoria dell'insegnante
	 * @param String utente
	 * @return ArrayList<SottocategoriaBean> sottocategorie
	 **/
	public ArrayList<SottocategoriaBean> selezionaSottocagorieInsegnante(String utente){
		ArrayList<SottocategoriaBean> sottocategorie = manager.selezionaSottocagorieInsegnante(utente);
		return sottocategorie;
	}
	/**
	 * Seleziona la sottocategoria dell'insegnante
	 * @param String nuovaSottocategoria
	 * @return Object sottocategorie
	 * @throws SQLException
	 **/
	public Object findByKey(String nuovaSottocategoria) throws SQLException{
		Object sottocategorie = manager.findByKey(nuovaSottocategoria);
		return sottocategorie;
	}
	
	SottocategoriaDao manager = new SottocategoriaDao();
}
