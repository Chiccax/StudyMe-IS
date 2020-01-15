package model.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.SottocategoriaBean;
import model.dao.SottocategoriaDao;

public class SottocategoriaManager {
	SottocategoriaDao manager = new SottocategoriaDao();
	
	public SottocategoriaManager(){
		
	}
	
	public ArrayList<SottocategoriaBean> selezionaSottocagorieInsegnante(String utente)
	{
		ArrayList<SottocategoriaBean> sottocategorie = manager.selezionaSottocagorieInsegnante(utente);
		return sottocategorie;
	}
	
	public Object findByKey(String nuovaSottocategoria) throws SQLException
	{
		Object sottocategorie = manager.findByKey(nuovaSottocategoria);
		return sottocategorie;
	}
	
	
}
