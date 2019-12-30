package modelDao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.DriverManagerConnectionPool;
import modelBean.CategoriaBean;
import modelBean.LezioniBean;
import modelBean.PacchettoBean;
import modelBean.RecensioneBean;
import modelDao.SottocategoriaDao;

public class PacchettoDao {
	public PacchettoDao() {
		
	}
	
	//prende tutti i pacchetti dal database
	
	public ArrayList<PacchettoBean> getAllPacchetti(){
		try {
			
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM pacchetto");
			stm.setBoolean(1, true);
			ResultSet res = stm.executeQuery();
			ArrayList<PacchettoBean> pacchetti= new ArrayList<PacchettoBean>();
			while(res.next()) {
				PacchettoBean pa= new PacchettoBean();
				pa.setCodicePacchetto(res.getString(1));
				pa.setCatagoria(res.getString(2));
				pa.setSottocategoria(res.getString(3));
				pa.setPrezzo(res.getDouble(4));
				pa.setDescrizione(res.getString(5));
				pa.setTitolo(res.getString(6));
				pa.setApprovato(res.getInt(9));
				
				pacchetti.add(pa);
				
			}
			return pacchetti;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	//prende un singolo pacchetto dal database
	public PacchettoBean getPacchetto(String codiceP) {
		Connection conn;
		try {
			conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM pacchetto WHERE codicePacchetto = ?");
			stm.setString(1, codiceP);
			ResultSet res=stm.executeQuery();
			conn.commit();
			if(res.next()) {
				PacchettoBean pa= new PacchettoBean();
				pa.setCodicePacchetto(res.getString(1));
				pa.setCatagoria(res.getString(2));
				pa.setSottocategoria(res.getString(3));
				pa.setPrezzo(res.getDouble(4));
				pa.setDescrizione(res.getString(5));
				pa.setTitolo(res.getString(6));
				pa.setFoto(res.getString(7));
				
				
				return pa;
			}
			else {
				return null;
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	public boolean delPacchetto(String codiceP) {
		Connection conn;
		try {
			conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("DELETE * FROM pacchetto WHERE codicePacchetto = ?");
			stm.setString(1, codiceP);
			ResultSet res=stm.executeQuery();
			conn.commit();
			if(res.next()) {
				return false;
			}
			else {
				return true;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}	
	}
	public ArrayList<PacchettoBean> searchPackage(String word){
		try {
			java.sql.Connection conn = DriverManagerConnectionPool.getConnection();

			String sql = "SELECT * " + "FROM pacchetto " + "WHERE titolo like ?";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, "%" + word + "%");
			ResultSet res = stm.executeQuery();
			conn.commit();

			ArrayList<PacchettoBean> pacchetti = new ArrayList<PacchettoBean>();

			while (res.next()) {
				PacchettoBean pacchetto = new PacchettoBean();
				pacchetto.setCodicePacchetto(res.getString(1));
				pacchetto.setCatagoria(res.getString(2));
				pacchetto.setSottocategoria(res.getString(3));
				pacchetto.setPrezzo(res.getDouble(4));
				pacchetto.setDescrizione(res.getString(5));
				pacchetto.setTitolo(res.getString(6));
				pacchetto.setFoto(res.getString(7));

				pacchetti.add(pacchetto);
			}
			return pacchetti;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String,ArrayList<PacchettoBean>> getCategoriaRaggruppato(String categoria) {
		Map<String, ArrayList<PacchettoBean>> result = new HashMap<String, ArrayList<PacchettoBean>>();
		Map<String, String>  sottocategorie = new HashMap<String, String>();
		
		try {
			java.sql.Connection conn = DriverManagerConnectionPool.getConnection();

			String sql = "SELECT * " + "FROM pacchetto " + "WHERE categoria = ? AND nelCatalogo = ?";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, categoria);
			stm.setBoolean(2, true);
			ResultSet res = stm.executeQuery();
			conn.commit();

			SottocategoriaDao manager = new SottocategoriaDao();
			
			while (res.next()) {
				PacchettoBean pacchetto1 = new PacchettoBean();
				pacchetto1.setCodicePacchetto(res.getString(1));
				pacchetto1.setCatagoria(res.getString(2));
				pacchetto1.setSottocategoria(res.getString(3));
				pacchetto1.setPrezzo(res.getDouble(4));
				pacchetto1.setDescrizione(res.getString(5));
				pacchetto1.setTitolo(res.getString(6));
				pacchetto1.setFoto(res.getString(7));
				pacchetto1.setApprovato(res.getInt(9));

				String valueSottocategoria = null;
				
				//Se ho già estratto il valore della sottocategoria dal db lo vado a prendere dalla mappa
				//altrimenti lo vado dal db
				if(sottocategorie.containsKey(pacchetto1.getSottocategoria())) {
					valueSottocategoria = sottocategorie.get(pacchetto1.getSottocategoria());
				} else {
					valueSottocategoria = manager.findByKey(pacchetto1.getSottocategoria()).getNomeSott();
				}
				
				if(!result.containsKey(valueSottocategoria)) {
					result.put(valueSottocategoria, new ArrayList<PacchettoBean>());
				}
				
				result.get(valueSottocategoria).add(pacchetto1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return result;
	}
	
	public Map<String,ArrayList<PacchettoBean>> getCategoriaRaggruppatoApprovato(String categoria) {
		Map<String, ArrayList<PacchettoBean>> result = new HashMap<String, ArrayList<PacchettoBean>>();
		Map<String, String>  sottocategorie = new HashMap<String, String>();
		
		try {
			java.sql.Connection conn = DriverManagerConnectionPool.getConnection();

			String sql = "SELECT * " + "FROM pacchetto " + "WHERE categoria = ? AND nelCatalogo = ? AND approvato = 1";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, categoria);
			stm.setBoolean(2, true);
			ResultSet res = stm.executeQuery();
			conn.commit();

			SottocategoriaDao manager = new SottocategoriaDao();
			
			while (res.next()) {
				PacchettoBean pacchetto1 = new PacchettoBean();
				pacchetto1.setCodicePacchetto(res.getString(1));
				pacchetto1.setCatagoria(res.getString(2));
				pacchetto1.setSottocategoria(res.getString(3));
				pacchetto1.setPrezzo(res.getDouble(4));
				pacchetto1.setDescrizione(res.getString(5));
				pacchetto1.setTitolo(res.getString(6));
				pacchetto1.setFoto(res.getString(7));
				pacchetto1.setApprovato(res.getInt(9));

				String valueSottocategoria = null;
				
				//Se ho già estratto il valore della sottocategoria dal db lo vado a prendere dalla mappa
				//altrimenti lo vado dal db
				if(sottocategorie.containsKey(pacchetto1.getSottocategoria())) {
					valueSottocategoria = sottocategorie.get(pacchetto1.getSottocategoria());
				} else {
					valueSottocategoria = manager.findByKey(pacchetto1.getSottocategoria()).getNomeSott();
				}
				
				if(!result.containsKey(valueSottocategoria)) {
					result.put(valueSottocategoria, new ArrayList<PacchettoBean>());
				}
				
				result.get(valueSottocategoria).add(pacchetto1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return result;
	}

	
	public CategoriaBean getBeanCategoria(String categoria) {
		
		try {
			java.sql.Connection conn = DriverManagerConnectionPool.getConnection();

			String sql = "SELECT * " + "FROM categoria " + "WHERE nomeCategoria = ? ";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1,categoria);
			ResultSet res = stm.executeQuery();
			conn.commit();
			
		if(res.next()) {
				CategoriaBean  cate = new CategoriaBean();
				cate.setFotoCategoria(res.getString(2));
					return cate;
		}else 
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<LezioniBean> getLezioni(String codicePacchetto){
		try {
			java.sql.Connection conn = DriverManagerConnectionPool.getConnection();

			String sql = "SELECT * " + "FROM lezioni " + "WHERE codiceP = ?";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, codicePacchetto);
			ResultSet res = stm.executeQuery();
			conn.commit();

			ArrayList<LezioniBean> lezioni = new ArrayList<LezioniBean>();
			
			while (res.next()) {
				LezioniBean lezione = new LezioniBean();
				lezione.setUrl(res.getString(1));
				lezione.setTitolo(res.getString(2));
				lezione.setDurata(res.getString(3));
				lezione.setPacchetto(res.getString(4));
				
				lezione.setApprovato(res.getInt(5));

				lezioni.add(lezione);
			}
			return lezioni;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<LezioniBean> getLezioniApprovate(String codicePacchetto){
		try {
			java.sql.Connection conn = DriverManagerConnectionPool.getConnection();

			String sql = "SELECT * " + "FROM lezioni " + "WHERE codiceP = ? AND approvato = 1";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, codicePacchetto);
			ResultSet res = stm.executeQuery();
			conn.commit();

			ArrayList<LezioniBean> lezioni = new ArrayList<LezioniBean>();
			
			while (res.next()) {
				LezioniBean lezione = new LezioniBean();
				lezione.setUrl(res.getString(1));
				lezione.setTitolo(res.getString(2));
				lezione.setDurata(res.getString(3));
				lezione.setPacchetto(res.getString(4));
				
				lezioni.add(lezione);
			}
			return lezioni;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<RecensioneBean> getRecensioni(String codicePacchetto){
		try {
			java.sql.Connection conn = DriverManagerConnectionPool.getConnection();

			String sql = "SELECT * " + "FROM recensione " + "WHERE codiceP = ?";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, codicePacchetto);
			ResultSet res = stm.executeQuery();
			conn.commit();

			ArrayList<RecensioneBean> recensioni = new ArrayList<RecensioneBean>();
			
			while (res.next()) {
				RecensioneBean recensione = new RecensioneBean();
				recensione.setIdRecensione(res.getString(1));
				recensione.setCliente(res.getString(2));
				recensione.setPacchetto(res.getString(3));
				recensione.setCommento(res.getString(4));
				recensione.setTitolo(res.getString(5));

				recensioni.add(recensione);
			}
			return recensioni;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


}