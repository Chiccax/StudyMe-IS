package modelDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DriverManagerConnectionPool;
import modelBean.LezioniBean;
import modelBean.OrdineBean;
import modelBean.PacchettoBean;

public class InsegnanteDao {

	public InsegnanteDao() {}
	
	//Aggiungi pacchetto
	public PacchettoBean inserPacchetto(String nuovoCodice, String nomeUtente, String nuovaSottocategoria, double nuovoPrezzo, String nuovaDescrizione, String nuovoTitolo, String nuovaFoto) {
			String categoria;
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			
			PreparedStatement stm = conn.prepareStatement("SELECT nomeCategoria FROM categoria WHERE insegnante = ?");
			stm.setString(1, nomeUtente);
			ResultSet res = stm.executeQuery();
			if(res.next()) {
				categoria = res.getString(1);
			} else {
				return null;
			}
			
			stm = conn.prepareStatement("INSERT into pacchetto (codicePacchetto, categoria, idSott, prezzo, descrizione, titolo, foto, nelCatalogo) VALUES (?,?,?,?,?,?,?, true)");
			
			stm.setString(1, nuovoCodice);
			stm.setString(2, categoria);
			stm.setString(3, nuovaSottocategoria);
			stm.setDouble(4, nuovoPrezzo);
			stm.setString(5, nuovaDescrizione);
			stm.setString(6, nuovoTitolo);
			stm.setString(7, nuovaFoto);
			
			stm.executeUpdate();
			
			stm = conn.prepareStatement("SELECT * FROM pacchetto WHERE codicePacchetto= ?");
			stm.setString(1, nuovoCodice);
			res = stm.executeQuery();
			conn.commit();
			
			if(res.next()) {
				PacchettoBean pacchettoDaInserire = new PacchettoBean();
				pacchettoDaInserire.setCodicePacchetto(nuovoCodice);
				pacchettoDaInserire.setPrezzo(nuovoPrezzo);
				pacchettoDaInserire.setDescrizione(nuovaDescrizione);
				pacchettoDaInserire.setTitolo(nuovoTitolo);
				pacchettoDaInserire.setFoto(nuovaFoto);
				pacchettoDaInserire.setCatagoria(categoria);
				pacchettoDaInserire.setSottocategoria(nuovaSottocategoria);
				return pacchettoDaInserire;
			} else
				return null;
		}catch (SQLException e) {
			e.printStackTrace();			
		}
		return null;
		
	}
	
	//Rimuovi pacchetto
	public void  deletePacchetto(String codicePacchetto) {	
		try {
			Connection conn= DriverManagerConnectionPool.getConnection();
			
			PreparedStatement stm= conn.prepareStatement("SELECT * FROM pacchetto WHERE codicePacchetto = ? ");
			stm.setString(1,codicePacchetto);
			ResultSet res = stm.executeQuery();
			if(res.next()) {
				stm = conn.prepareStatement("UPDATE pacchetto SET nelCatalogo = ? WHERE codicePacchetto = ?");
				stm.setBoolean(1, false);
				stm.setString(2, codicePacchetto);
				stm.execute();
				
				conn.commit();
			}			
		}catch (SQLException e) {
			e.printStackTrace();			
		}
	}
	
	//ModificaPacchetto
	public void updateCode(String vecchioCodice, String nuovoCodice) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM pacchetto WHERE codicePacchetto = ?");
			stm.setString(1, vecchioCodice);
			ResultSet res = stm.executeQuery();
			
			if(res.next()) {
				stm = conn.prepareStatement("UPDATE pacchetto SET codicePacchetto = ? WHERE codicePacchetto = ?");
				stm.setString(1, nuovoCodice);
				stm.setString(2, vecchioCodice);
				stm.executeUpdate();
				
				conn.commit();		
			}			
		}catch (SQLException e) {
			e.printStackTrace();			
		}
		
	}
	
	public void updateTitle(String vecchioCodice, String nuovoTitolo) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM pacchetto WHERE codicePacchetto = ?");
			stm.setString(1, vecchioCodice);
			ResultSet res = stm.executeQuery();
			if(res.next()) {
				stm = conn.prepareStatement("UPDATE pacchetto SET titolo = ? WHERE codicePacchetto = ?");
				stm.setString(1, nuovoTitolo);
				stm.setString(2, vecchioCodice);
				stm.executeUpdate();
				
				conn.commit();			
			}			
		}catch (SQLException e) {
			e.printStackTrace();			
		}
	}
	
	public void updatePrice(String vecchioCodice, double nuovoPrezzo) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM pacchetto WHERE codicePacchetto = ?");
			stm.setString(1, vecchioCodice);
			ResultSet res = stm.executeQuery();
			if(res.next()) {
				stm = conn.prepareStatement("UPDATE pacchetto SET prezzo = ? WHERE codicePacchetto = ?");
				stm.setDouble(1, nuovoPrezzo);
				stm.setString(2, vecchioCodice);
				stm.executeUpdate();
				
				conn.commit();	
			}			
		}catch (SQLException e) {
			e.printStackTrace();			
		}
	}
	
	public void updateDescr(String vecchioCodice, String nuovaDescrizione) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM pacchetto WHERE codicePacchetto = ?");
			stm.setString(1, vecchioCodice);
			ResultSet res = stm.executeQuery();
			if(res.next()) {
				stm = conn.prepareStatement("UPDATE pacchetto SET descrizione = ? WHERE codicePacchetto = ?");
				stm.setString(1, nuovaDescrizione);
				stm.setString(2, vecchioCodice);
				stm.executeUpdate();
				
				conn.commit();			
			}				
		}catch (SQLException e) {
			e.printStackTrace();			
		}
	}

	//Aggiungi lezione
	public LezioniBean insertLesson(String codiceP, String url, String titolo, String durata) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			
			PreparedStatement stm = conn.prepareStatement("INSERT into lezioni (url, titolo, durata, codiceP) VALUES (?,?,?,?)");
			
			stm.setString(1, url);
			stm.setString(2, titolo);
			stm.setString(3, durata);
			stm.setString(4, codiceP);
			
			stm.executeUpdate();
			
			stm = conn.prepareStatement("SELECT * FROM lezioni WHERE titolo = ?");
			stm.setString(1, titolo);
			ResultSet res = stm.executeQuery();
			conn.commit();
			
			LezioniBean lezioneDaInserire = new LezioniBean();
			lezioneDaInserire.setPacchetto(codiceP);
			lezioneDaInserire.setUrl(url);
			lezioneDaInserire.setTitolo(titolo);
			lezioneDaInserire.setDurata(durata);
			
			if(res.next()) {
				return lezioneDaInserire;
			} else
				return null;
		}catch (SQLException e) {
			e.printStackTrace();			
		}
		return null;
	}
	
	//Modifica lezione
	public void updateTitleLesson(String vecchioTitolo, String nuovoTitolo) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("UPDATE lezioni SET titolo = ? WHERE titolo = ? ");
			stm.setString(1, nuovoTitolo);
			stm.setString(2, vecchioTitolo);
			stm.executeUpdate();
				
				conn.commit();				
		}catch (SQLException e) {
			e.printStackTrace();			
		}
	}
	
	public void updateUrlLesson(String vecchioTitolo, String nuovoUrl) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("UPDATE lezioni SET url = ? WHERE titolo = ?");
			stm.setString(1, nuovoUrl);
			stm.setString(2, vecchioTitolo);
			stm.executeUpdate();
				
			conn.commit();				
		}catch (SQLException e) {
			e.printStackTrace();			
		}
	}
	
	public void updateDurationLesson(String vecchioTitolo, String nuovaDurata) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("UPDATE lezioni SET durata = ? WHERE titolo = ?");
			stm.setString(1, nuovaDurata);
			stm.setString(2, vecchioTitolo);
			stm.executeUpdate();
				
			conn.commit();				
		}catch (SQLException e) {
			e.printStackTrace();			
		}
	}
	
	//Cancella lezione
	public void deleteLesson(String titolo) {	
		try {
			Connection conn= DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("DELETE FROM lezioni WHERE titolo = ?");
			stm.setString(1, titolo);
			stm.execute();
				
			conn.commit();
		}catch (SQLException e) {
			e.printStackTrace();			
		}
	}
	
	public ArrayList<OrdineBean> getOrdine(String nomeCliente) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();

			String sql = "SELECT * " + "FROM ordine " + "WHERE nomeCliente = ?";
			PreparedStatement stm = (PreparedStatement) conn.prepareStatement(sql);
			stm.setString(1, nomeCliente);
			ResultSet res = stm.executeQuery();
			conn.commit();
			
			ArrayList <OrdineBean> ordini= new ArrayList<OrdineBean>();
		
			
			
			return ordini;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	
	}
}