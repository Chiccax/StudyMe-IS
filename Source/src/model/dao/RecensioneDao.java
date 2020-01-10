package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DriverManagerConnectionPool;


public class RecensioneDao {

	public RecensioneDao() {}
	/**
	 * Aggiunge una recensione al pacchetto se il video è gia stato visto
	 * @param nomeUtente nome utente dell'acquirente 
	 * @param codicePacchetto codice del pacchetto
	 * @param titoloRecensione titolo della recensione
	 * @param testoRecensione testo della recensione
	 * @return 
	 * context RecensioneDao::aggiungiRecensione(String nomeUtente, String codicePacchetto, String titoloRecensione, String testoRecensione)
	 * @pre isAlwreadyReviewed == true
	 * @post recensione presente nel db
	 **/
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
	/**
	 * Verifica che un corso è già stato visto dall'acquirente 
	 * @param nomeUtente nome utente dell'acquirente
	 * @param codicePacchetto codice del pacchetto
	 * @return false
	 * @throws SQLException
	 * context RecensioneDao::isAlwreadyReviewed(String nomeUtente, String codicePacchetto)
	 * @pre nomeUtente != null && codicePacchetto != null
	 **/
	public boolean isAlwreadyReviewed(String nomeUtente, String codicePacchetto) throws SQLException{
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM recensione WHERE userCliente = ? AND codiceP = ?");
			
			stm.setString(1, nomeUtente);
			stm.setString(2, codicePacchetto);
			ResultSet res = stm.executeQuery();
			
			return res.next();	
	}
}
