package modelDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DriverManagerConnectionPool;


public class RecensioneDao {

	public RecensioneDao() {}
	
	public void aggiungiRecensione(String nomeUtente, String codicePacchetto, String titoloRecensione, String testoRecensione) {
		try {
			
			if(isAlwreadyReviewed(nomeUtente, codicePacchetto)) {
				return;
			}
			
		 	Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("INSERT INTO recensione(userCliente, codiceP, commento, titolo) values(?,?,?,?)");
			
			stm.setString(1, nomeUtente);
			stm.setString(2, codicePacchetto);
			stm.setString(3, testoRecensione);
			stm.setString(4, titoloRecensione);
			
			stm.executeUpdate();
			conn.commit();		
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isAlwreadyReviewed(String nomeUtente, String codicePacchetto) throws SQLException{
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM recensione WHERE userCliente = ? AND codiceP = ?");
			
			stm.setString(1, nomeUtente);
			stm.setString(2, codicePacchetto);
			ResultSet res = stm.executeQuery();
			
			return res.next();	
	}


}
