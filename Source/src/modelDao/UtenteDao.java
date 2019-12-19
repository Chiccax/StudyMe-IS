package modelDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DriverManagerConnectionPool;
import modelBean.PacchettoBean;
import modelBean.UtenteBean;


public class UtenteDao {

	public UtenteDao() {
		
	}
	public UtenteBean login(String nomeUtente, String password) {
		UtenteBean user = null;
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
	
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM utente WHERE nomeUtente = ? AND password = ?");
			stm.setString(1, nomeUtente);
			stm.setString(2, password);
			ResultSet res = stm.executeQuery();
			
			user = new UtenteBean();
			
			//Se esiste l'utente
			if(res.next()) {
				user.setNomeUtente(res.getString(1));
				user.setPassword(res.getString(2));
				user.setEmail(res.getString(3));
				user.setTipo(res.getString(4));
			}
			
			else
				return null;
					
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
	public boolean registration(String email, String nomeUtente, String password){
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM utente WHERE email = ? OR nomeUtente = ?");
			stm.setString(1, email);
			stm.setString(2, nomeUtente);
			ResultSet res = stm.executeQuery();
			if(!res.next()) {
				stm = conn.prepareStatement("INSERT INTO  utente(nomeUtente, password, email, tipo) VALUES (?, ?, ?, ?)");
				stm.setString(1, nomeUtente);
				stm.setString(2, password);
				stm.setString(3, email);
				stm.setString(4, "acquirente");
				stm.executeUpdate();	
				
				conn.commit();
			}else {
				return false;
			}
		}catch (SQLException e) {
			e.printStackTrace();			
		}	
		return true;
	}
	
	public boolean updatePassword(String email, String password) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM utente WHERE email = ?");
			stm.setString(1, email);
			ResultSet res = stm.executeQuery();
			if(res.next()) {
				stm = conn.prepareStatement("UPDATE utente SET password = ? WHERE email = ?");
				stm.setString(1, password);
				stm.setString(2, email);
				stm.executeUpdate();
				
				conn.commit();
				return true;
			}			
			else
				return false;
		}catch (SQLException e) {
			e.printStackTrace();			
		}
		return false;
	}
	
	public boolean updateEmail(String email, String newEmail) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM utente WHERE email = ?");
			stm.setString(1, email);
			
			ResultSet res = stm.executeQuery();
			if(res.next()) {
				stm = conn.prepareStatement("UPDATE utente SET email = ? WHERE email = ?");
				stm.setString(1, newEmail);
				stm.setString(2, email);
				stm.executeUpdate();
				
				conn.commit();
				return true;
			}
				else
					return false;
			}catch (SQLException e) {
				e.printStackTrace();			
			}
			return false;
		}

}
