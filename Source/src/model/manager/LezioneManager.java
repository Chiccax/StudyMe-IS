package model.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.CategoriaBean;
import model.bean.LezioniBean;
import model.bean.OrdineAcquistoBean;
import model.bean.PacchettoBean;
import model.bean.RecensioneBean;
import model.dao.CategoriaDao;
import model.dao.OrdineAcquistoDao;
import model.dao.PacchettoDao;
import model.dao.RecensioneDao;

public class LezioneManager 
{
	RecensioneDao recensionedao = new RecensioneDao();
	PacchettoDao manager = new PacchettoDao();
	CategoriaDao daoCategoria= new CategoriaDao();
	String insegnante= "";
	OrdineAcquistoDao dao = new OrdineAcquistoDao();
	
	
	
	public boolean getRecensito(String nomeUtente,String codicePacchetto) throws SQLException{
		  boolean recensito= recensionedao.isAlwreadyReviewed(nomeUtente, codicePacchetto);
		  return recensito;
	}
	
	public PacchettoBean getPacchetto(String codicePacchetto){
		PacchettoBean pacchetto= manager.getPacchetto(codicePacchetto);
		return pacchetto;
	}
	
	public String getInsegnante(CategoriaBean categoriaBean){
		insegnante= categoriaBean.getInsegnante();
		return insegnante;
	}
	
	public CategoriaBean getCategoria(PacchettoBean pacchetto)
	{
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
	public ArrayList<RecensioneBean> getRecensioni(String codicePacchetto){
		ArrayList<RecensioneBean> recensioni=manager.getRecensioni(codicePacchetto);
		return recensioni;
	}
	public ArrayList<LezioniBean> getLezioni(String codicePacchetto,String nomeUtente){
		ArrayList<LezioniBean> lezioni= new ArrayList<LezioniBean>();
		if(insegnante.equals(nomeUtente)) {
			lezioni = manager.getLezioni(codicePacchetto);
		}else{
			lezioni = manager.getLezioniApprovate(codicePacchetto);
		}
		return lezioni;
	}
	
	public ArrayList<OrdineAcquistoBean> getOrdiniCliente(String nomeUtente){
		ArrayList<OrdineAcquistoBean> ordiniCliente= new ArrayList<OrdineAcquistoBean>();
		try {
			ordiniCliente = dao.findByNomeCliente(nomeUtente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordiniCliente;
	}
	
	
	
	
	
	
}
