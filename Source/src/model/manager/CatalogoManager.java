package model.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import model.bean.CategoriaBean;
import model.bean.PacchettoBean;
import model.dao.CategoriaDao;
import model.dao.PacchettoDao;

public class CatalogoManager {
	/**
	 * Costruttore vuoto. 
	 **/
	public CatalogoManager(){
		
	}
	/**
	 * Preleva la foto della categoria.
	 * @param String categoria
	 * @return CategoriaBean fotoCat
	 **/
	public CategoriaBean getFotoCat(String categoria)
	{
		fotoCat= dao.getBeanCategoria(categoria);
		return fotoCat;
	}
	/**
	 * Preleva l'insegnante.
	 * @return String insegnante
	 **/
	public String getInsegnante()
	{
		insegnante=  categoriaBean.getInsegnante();
		return insegnante;
	}
	/**
	 * Preleva una lista di pacchetti per categoria.
	 * @param String categoria
	 * @param String userName
	 * @return  Map<String,ArrayList<PacchettoBean>> pacchetti
	 **/
	public Map<String,ArrayList<PacchettoBean>> getPacchettiPerCategoria(String categoria, String userName){
		String docente="";
		try {
			categoriaBean= daoCategoria.findByKey(categoria);
			
			docente= getInsegnante();//insegnante categoria
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		if(docente.equals(userName)){
			pacchetti = dao.getCategoriaRaggruppato(categoria);
		}else{
			pacchetti= dao.getCategoriaRaggruppatoApprovato(categoria);
		}
		return pacchetti;
	}
	CategoriaDao daoCategoria= new CategoriaDao();
	PacchettoDao dao = new PacchettoDao();
	CategoriaBean fotoCat= null;
	CategoriaBean categoriaBean= null;
	String insegnante= null;
	Map<String,ArrayList<PacchettoBean>> pacchetti = null;
}
