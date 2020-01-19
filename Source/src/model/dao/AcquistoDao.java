package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DriverManagerConnectionPool;
import model.bean.AcquistoBean;

public class AcquistoDao {
	
	public AcquistoDao() {
		
	}
	/**
	 * Aggiunge un acquisto
	 * @param bean bean acquisto
	 * @return 
	 * context AcquistoDao::insertAcquisto(AcquistoBean bean)
	 * @pre bean!=null 
	 * **/
	public void insertAcquisto(AcquistoBean bean) {
		 try {
			 	Connection conn = DriverManagerConnectionPool.getConnection();
				PreparedStatement stm = conn.prepareStatement("INSERT INTO acquisto(numOrdine, codiceP,titoloPacchetto,prezzo) values(?,?,?,?)");
				stm.setInt(1, bean.getNumOrdine());
				stm.setString(2, bean.getCodiceP());
				stm.setString(3, bean.getTitoloPacchetto());
				stm.setDouble(4, bean.getImporto());
				
				stm.executeUpdate();
				conn.commit();		
		 }catch (SQLException e) {
			 e.printStackTrace();
		 }
	}
	
	public boolean existingPurchase(int idAcquisto) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM acquisto WHERE numAcquisto = ? ");
			stm.setInt(1, idAcquisto);
			ResultSet res = stm.executeQuery();
			if(res.next()) {
				return true;
			} else
				return false;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}

