package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import control.util.StartupUtility;
import model.DriverManagerConnectionPool;
import model.bean.LezioniBean;
import model.bean.PacchettoBean;

public class GestoreDao {
	
	public GestoreDao() {}
	/**
	 * Visualizza i pacchetti da approvare
	 * @param
	 * @return ArrayList<PacchettoBean> array di pacchetti
	 * context GestoreDao::visualizzaPacchettiDaApprovare()
	 * 
	 * **/
	public ArrayList<PacchettoBean> visualizzaPacchettiDaApprovare(){
		PacchettoBean pacchettoDaApprovare;
		ArrayList<PacchettoBean> pacchetti = new ArrayList<PacchettoBean>();
		
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			
			PreparedStatement stm = conn.prepareStatement("SELECT distinct pacchetto.* \r\n" + 
					"FROM studyme.lezioni, studyme.pacchetto\r\n" + 
					"WHERE studyme.lezioni.approvato = 0 AND studyme.pacchetto.approvato = 0 || studyme.lezioni.approvato = 0 AND studyme.pacchetto.approvato = 1 AND studyme.lezioni.codiceP = studyme.pacchetto.codicePacchetto;");
			ResultSet res = stm.executeQuery();
			
			conn.commit();
			
			while(res.next()) {
				stm = conn.prepareStatement("SELECT * FROM lezioni WHERE codiceP = ?");
				stm.setString(1, res.getString(1));
				ResultSet res2 = stm.executeQuery();
				if(res2 == null) {
					return null;
				} else {
				String codicePacchetto = res.getString(1);
				String categoriaPacchetto = res.getString(2);
				String sottocategoriaPacchetto = res.getString(3);
				double prezzoPacchetto = res.getDouble(4);
				String descrizionePacchetto = res.getString(5);
				String titoloPacchetto = res.getString(6);
				String fotoPacchetto = res.getString(7);
			
				pacchettoDaApprovare = new PacchettoBean();
				pacchettoDaApprovare.setCodicePacchetto(codicePacchetto);
				pacchettoDaApprovare.setCatagoria(categoriaPacchetto);
				pacchettoDaApprovare.setSottocategoria(sottocategoriaPacchetto);
				pacchettoDaApprovare.setPrezzo(prezzoPacchetto);
				pacchettoDaApprovare.setDescrizione(descrizionePacchetto);
				pacchettoDaApprovare.setTitolo(titoloPacchetto);
				pacchettoDaApprovare.setFoto(fotoPacchetto);
						
				pacchetti.add(pacchettoDaApprovare);
			}
		}
			return pacchetti;
		} catch(SQLException e){
			e.printStackTrace();
		}
			
			return null;
	}
	/**
	 *Visualizza le lezioni da approvare 
	 *@param
	 *@return ArrayList<LezioniBean>
	 * context GestoreDao::visualizzaLezioniDaApprovare()
	 **/
	public ArrayList<LezioniBean> visualizzaLezioniDaApprovare(){
		LezioniBean lezioniDaApprovare;
		ArrayList<LezioniBean> lezioni = new ArrayList<LezioniBean>();
		
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM lezioni WHERE approvato = 0");
			ResultSet res = stm.executeQuery();
			conn.commit();
			
			while(res.next()) {
				String urlLezione = res.getString(1);
				String titoloLezione = res.getString(2);
				String durataLezione = res.getString(3);
				String codicePacchetto = res.getString(4);
				
				lezioniDaApprovare = new LezioniBean();
				lezioniDaApprovare.setUrl(urlLezione);
				lezioniDaApprovare.setTitolo(titoloLezione);
				lezioniDaApprovare.setDurata(durataLezione);
				lezioniDaApprovare.setPacchetto(codicePacchetto);
				
				lezioni.add(lezioniDaApprovare);
			}
			return lezioni;
		} catch(SQLException e){
			e.printStackTrace();
		}
			return null;
	}
	/**
	 *Approva l'intero pacchetto di lezioni
	 *@param codicePacchetto codice del pacchetto
	 *@return 
	 *context GestoreDao::approvaInteroPacchetto(String codicePacchetto)
	 *@pre codicePacchetto != null && codicePacchetto presente nel db 
	 * 
	 **/
	public void approvaInteroPacchetto(String codicePacchetto) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("UPDATE pacchetto, lezioni SET pacchetto.approvato = 1 AND lezioni.approvato = 1 WHERE pacchetto.codicePacchetto = ? AND lezioni.codiceP = ?");
			stm.setString(1, codicePacchetto);
			stm.setString(2, codicePacchetto);
			stm.executeUpdate();
			conn.commit();		
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	/**
	 * Disapprova l'intero pacchetto di lezioni
	 * @param codicePacchetto codice del pacchetto
	 * @return 
	 * context GestoreDao::disapprovaInteroPacchetto(String codicePacchetto)
	 * @pre codicePacchetto != null && codicePacchetto presente nel db 
	 **/
	public void disapprovaInteroPacchetto(String codicePacchetto) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("UPDATE pacchetto SET approvato = -1 WHERE codicePacchetto = ?");
			stm.setString(1, codicePacchetto);
			stm.executeUpdate();
			conn.commit();	
			
			stm = conn.prepareStatement("UPDATE lezioni SET approvato = -1 WHERE codiceP = ?");
			stm.setString(1, codicePacchetto);
			stm.executeUpdate();
			conn.commit();	
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	/**
	 *Approva la singola lezione
	 *@param url url della lezione
	 *@return 
	 *context GestoreDao::approvaSingolaLezione(String url)
	 *@pre url != null && url non presente nel db
	 * 
	 **/
	public void approvaSingolaLezione(String url) {
		try {	
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("UPDATE lezioni SET approvato = 1 WHERE url = ?");
			stm.setString(1, url);
			stm.executeUpdate();
			LezioniBean l = StartupUtility.getLezione(url);
			l.setApprovato(1);
			conn.commit();	
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	/**
	 *Disapprova la singola lezione
	 *@param url url della lezione
	 *@return 
	 *context GestoreDao::disapprovaSingolaLezione(String url)
	 *@pre url != null && url non presente nel db
	 * 
	 **/
	public void disapprovaSingolaLezione(String url) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("UPDATE lezioni SET approvato = -1 WHERE url = ?");
			stm.setString(1, url);
			stm.executeUpdate();
			conn.commit();	
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
}