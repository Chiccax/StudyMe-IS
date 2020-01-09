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
	/**
	 * Effettua il login di un Account
	 * @param nomeUtente nomeUtente dell'utente
	 * @param password password dell'utente
	 * @return L'utente
	 * context UtenteDao::login(String nomeUtente, String password) 
	 * @pre nomeUtente !null && nomeutente presente nel db password !null && password presente nel db
	 **/
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
	/**
	 * Registra un nuovo utente
	 * @param email email del nuovo utente
	 * @param nomeUtente nomeUtente del nuovo utente
	 * @param password password del nuovo utente
	 * @return true
	 * context UtenteDao::registration(String email, String nomeUtente, String password)
	 * @pre email != null && email non presente nel db && nomeUtente != null && nomeUtente non presente nel db && password != null
	 * @post nuovo utente presente nel db
	 **/
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
	/**
	 * Modifica la password di un Account
	 * @param email 
	 * @param password 
	 * @return false
	 * context UtenteDao::updatePassword(String email, String password)
	 * @pre email!=null && password!=null
	 * @post password aggiornata
	 **/
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
	/**
	 * Modifica la password di un Account
	 * @param email 
	 * @param newEmail 
	 * @return false
	 * context UtenteDao::updateEmail(String email, String newEmail) 
	 * @pre mail!=null && email presente nel database && newMail != null && newEmail non presente nel database
	 * @post email aggiornata
	 **/
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
