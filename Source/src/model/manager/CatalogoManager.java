package model.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import model.bean.CategoriaBean;
import model.bean.PacchettoBean;
import model.dao.CategoriaDao;
import model.dao.PacchettoDao;

public class CatalogoManager {

	CategoriaDao daoCategoria= new CategoriaDao();
	PacchettoDao dao = new PacchettoDao();
	CategoriaBean fotoCat= null;
	CategoriaBean categoriaBean= null;
	String insegnante= null;
	Map<String,ArrayList<PacchettoBean>> pacchetti = null;
	
	public CatalogoManager(){
		
	}
	
	public CategoriaBean getFotoCat(String categoria)
	{
		fotoCat= dao.getBeanCategoria(categoria);
		return fotoCat;
	}
	
	public String getInsegnante()
	{
		insegnante=  categoriaBean.getInsegnante();
		return insegnante;
	}
	
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
}
