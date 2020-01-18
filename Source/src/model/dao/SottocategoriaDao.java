package model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.DriverManagerConnectionPool;
import model.bean.SottocategoriaBean;

public class SottocategoriaDao implements Model_interface<SottocategoriaBean> {

	public SottocategoriaDao() {}

	@Override
	public void insert(SottocategoriaBean entity) throws SQLException {}

	@Override
	public void update(SottocategoriaBean entity) throws SQLException {}

	@Override
	public boolean remove(Object codiceP) throws SQLException {
		return false;
	}
	/**
	 * Recupera la sottocategoria 
	 * @param codiceP codice del pacchetto
	 * @return SottocategoriaBean sottocategoria
	 * @throws SQLEXception
	 * context  SottocategoriaDao::findByKey(Object codiceP)
	 * @pre codiceP != null && codiceP presente nel database
	 **/
	@Override
	public SottocategoriaBean findByKey(Object codiceS) throws SQLException {
		if(!(codiceS instanceof String))
			throw new IllegalArgumentException("La chiave primaria deve essere di tipo stringa");
		
		String key = (String)codiceS;
		String sql = "SELECT * FROM sottocategoria WHERE idSottocat = ?";
		
		Connection conn = DriverManagerConnectionPool.getConnection();
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, key);
		
		ResultSet ris = stm.executeQuery();
		
		SottocategoriaBean bean = null;
		
		if(ris.next()) {
			bean = new SottocategoriaBean();
			bean.setIdSottoCat(ris.getString(1));
			bean.setNomeSott(ris.getString(2));
		}
		return bean;
	}
	/**
	 * Recupera la sottocategoria dell'insegnante
	 * @param utente utente
	 * @return ArrayList<SottocategoriaBean> array di sottocategoria
	 * context  SottocategoriaDao:: selezionaSottocagorieInsegnante(String utente)
	 * @pre utente != null
	 **/
	public ArrayList<SottocategoriaBean> selezionaSottocagorieInsegnante(String utente){
		try {
			
			ArrayList<SottocategoriaBean> sottocategorie = new ArrayList<SottocategoriaBean>();
			
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("SELECT nomeCategoria FROM categoria WHERE insegnante = ?");
			stm.setString(1, utente);
			ResultSet res = stm.executeQuery();
			
			if(!res.next()) 
				return null;
			
			stm = conn.prepareStatement("SELECT DISTINCT idSott FROM pacchetto WHERE categoria = ?");
			stm.setString(1, res.getString(1));
			res = stm.executeQuery();
			
			while(res.next()) {
				stm = conn.prepareStatement("SELECT * FROM sottocategoria WHERE idSottocat = ?");
				stm.setString(1, res.getString(1));
				ResultSet res2 = stm.executeQuery();
				
				while(res2.next()) {	
					SottocategoriaBean s = new SottocategoriaBean();
					s.setIdSottoCat(res2.getString(1));
					s.setNomeSott(res2.getString(2));
					
					sottocategorie.add(s);			
				}
			}
			return sottocategorie;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
	@Override
	public Collection<SottocategoriaBean> findAll() throws SQLException {
		
		return null;
	}
}