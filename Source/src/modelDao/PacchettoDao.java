package modelDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DriverManagerConnectionPool;
import modelBean.PacchettoBean;

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
}