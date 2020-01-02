package modelDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DriverManagerConnectionPool;
import modelBean.LezioniBean;
import modelBean.PacchettoBean;

public class GestoreDao {
	
	public GestoreDao() {}
	
	//Pacchetti da approvare
	public ArrayList<PacchettoBean> visualizzaPacchettiDaApprovare(){
		PacchettoBean pacchettoDaApprovare;
		ArrayList<PacchettoBean> pacchetti = new ArrayList<PacchettoBean>();
		
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM pacchetto WHERE approvato = 0");
			ResultSet res = stm.executeQuery();
			conn.commit();
			
			while(res.next()) {
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
			}	
			return pacchetti;
		} catch(SQLException e){
			e.printStackTrace();
		}
			
			return null;
	}
	
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

	public void approvaInteroPacchetto(String codicePacchetto) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("UPDATE pacchetto SET approvato = 1 WHERE codicePacchetto = ?");
			stm.setString(1, codicePacchetto);
			stm.executeUpdate();
			conn.commit();	
			
			stm = conn.prepareStatement("UPDATE lezioni SET approvato = 1 WHERE codiceP = ?");
			stm.setString(1, codicePacchetto);
			stm.executeUpdate();
			conn.commit();	
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
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
	
	public void approvaSingolaLezione(String url) {
		try {	
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("UPDATE lezioni SET approvato = 1 WHERE url = ?");
			stm.setString(1, url);
			stm.executeUpdate();
			conn.commit();	
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
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