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
	/**
	 * Inserisce un nuovo pacchetto 
	 * @param nuovoCodice codice del pacchetto
	 * @param nomeUtente nome utente
	 * @param nuovaSottocategoria sottocategoria del pacchetto
	 * @param nuovoPrezzo prezzo del pacchettp
	 * @param nuovaDescrzione descrzione del pacchetto
	 * @param nuovoTitolo titolo del pacchetto
	 * @param nuovaFoto foro del pacchetto
	 * @return PacchettoBean pacchetto
	 * context InsegnanteDao::inserPacchetto(String nuovoCodice, String nomeUtente, String nuovaSottocategoria, double nuovoPrezzo, String nuovaDescrizione, String nuovoTitolo, String nuovaFoto)
	 * @pre nuovoCodice !=null && nuovoCodice non presente nel db && nuovaSottocategoria presente nel db && nuovaDescrizione !=null  && nuovoTitolo !=null && nuovaFoto!= null
	 * @post pacchetto presente nel db
	 **/
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
			
			stm = conn.prepareStatement("SELECT codicePacchetto FROM pacchetto WHERE codicePacchetto = ?");
			stm.setString(1, nuovoCodice);
			res = stm.executeQuery();
			if(res.next()){
				return null;
			}else {
				stm = conn.prepareStatement("SELECT idSottocat FROM sottoCategoria WHERE idSottocat = ?");
				stm.setString(1, nuovaSottocategoria);
				res = stm.executeQuery();
				if(!res.next()){
					return null;
				}else {
			
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
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();			
		}
		return null;
	}
	/***
	 * Elimina il pacchetto
	 * @param codicePacchetto codice del pacchetto
	 * @return 
	 * context InsegnanteDao::deletePacchetto(String codicePacchetto)
	 * @pre codicePacchetto != null
	 * @post pacchetto non presente nel db
	 **/
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
	/***
	 * Modifica il codice del pacchetto
	 * @param vecchioCodice vecchio codice del pacchetto
	 * @param nuovoCodice nuovo codice del pacchetto
	 * @return
	 * context InsegnanteDao::updateCode(String vecchioCodice, String nuovoCodice) 
	 * @pre nuovoCodice != null && nuovoCodice non presente nel db
	 * @post codice del pacchetto aggiornato
	 **/
	public void updateCode(String vecchioCodice, String nuovoCodice) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM pacchetto WHERE codicePacchetto = ?");
			stm.setString(1, vecchioCodice);
			ResultSet res = stm.executeQuery();
			
			if(res.next()) {
				stm = conn.prepareStatement("UPDATE pacchetto SET codicePacchetto = ? , approvato = 0 WHERE codicePacchetto = ?");
				stm.setString(1, nuovoCodice);
				stm.setString(2, vecchioCodice);
				stm.executeUpdate();
				
				conn.commit();		
			}			
		}catch (SQLException e) {
			e.printStackTrace();			
		}
		
	}
	/***
	 * Modifica il titolo del pacchetto
	 * @param vecchioCodice vecchio codice del pacchetto
	 * @param nuovoTitolo nuovo titolo del pacchetto
	 * @return
	 * context InsegnanteDao::updateTitle(String vecchioCodice, String nuovoTitolo)
	 * @pre nuovoTitolo != null && nuovoTitolo non è presente nel db
	 * @post titolo del pacchetto aggiornato
	 **/
	public void updateTitle(String vecchioCodice, String nuovoTitolo) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM pacchetto WHERE codicePacchetto = ?");
			stm.setString(1, vecchioCodice);
			ResultSet res = stm.executeQuery();
			if(res.next()) {
				stm = conn.prepareStatement("UPDATE pacchetto SET titolo = ? , approvato = 0 WHERE codicePacchetto = ?");
				stm.setString(1, nuovoTitolo);
				stm.setString(2, vecchioCodice);
				stm.executeUpdate();
				
				conn.commit();			
			}			
		}catch (SQLException e) {
			e.printStackTrace();			
		}
	}
	/***
	 * Modifica il prezzo del pacchetto
	 * @param vecchioCodice vecchio codice del pacchetto
	 * @param nuovoPrezzo nuovo prezzo del pacchetto
	 * @return
	 * context InsegnanteDao::updatePrice(String vecchioCodice, double nuovoPrezzo)
	 * @pre nuovoPrezzo != 0
	 * @post prezzo del pacchetto aggiornato
	 **/
	public void updatePrice(String vecchioCodice, double nuovoPrezzo) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM pacchetto WHERE codicePacchetto = ?");
			stm.setString(1, vecchioCodice);
			ResultSet res = stm.executeQuery();
			if(res.next()) {
				stm = conn.prepareStatement("UPDATE pacchetto SET prezzo = ? , approvato = 0 WHERE codicePacchetto = ?");
				stm.setDouble(1, nuovoPrezzo);
				stm.setString(2, vecchioCodice);
				stm.executeUpdate();
				
				conn.commit();	
			}			
		}catch (SQLException e) {
			e.printStackTrace();			
		}
	}
	/***
	 * Modifica la descrizione del pacchetto
	 * @param vecchioCodice vecchio codice del pacchetto
	 * @param nuovaDescrzione nuova descrizione del pacchetto
	 * @return
	 * context InsegnanteDao::updateDescr(String vecchioCodice, String nuovaDescrizione)
	 * @pre nuovaDescrizone != null
	 * @post descrizione del pacchetto aggiornata
	 **/
	public void updateDescr(String vecchioCodice, String nuovaDescrizione) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM pacchetto WHERE codicePacchetto = ?");
			stm.setString(1, vecchioCodice);
			ResultSet res = stm.executeQuery();
			if(res.next()) {
				stm = conn.prepareStatement("UPDATE pacchetto SET descrizione = ? , approvato = 0 WHERE codicePacchetto = ?");
				stm.setString(1, nuovaDescrizione);
				stm.setString(2, vecchioCodice);
				stm.executeUpdate();
				
				conn.commit();			
			}				
		}catch (SQLException e) {
			e.printStackTrace();			
		}
	}
	/**
	 * Inserisce una lezione
	 * @param codiceP codice della lezione
	 * @param url url della lezione
	 * @param titolo titolo della lezione
	 * @param durata durata della lezione
	 * @return LezioniBean lezione
	 * @pre codiceP != null && codiceP non presente nel db && url != null && url non presente nel db && titolo!= null && durata != null
	 * @post lezione inserita nel db
	 * **/
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
	/***
	 * Modifica il titolo della lezione
	 * @param vecchioTitolo vecchio titolo della lezione
	 * @param nuovoTitolo nuovo titolo della lezione
	 * @return
	 * @pre nuovoTitolo != null && nuovoTitolo non presente nel db
	 **/
	public void updateTitleLesson(String vecchioTitolo, String nuovoTitolo) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("UPDATE lezioni SET titolo = ? , approvato = 0 WHERE titolo = ? ");
			stm.setString(1, nuovoTitolo);
			stm.setString(2, vecchioTitolo);
			stm.executeUpdate();
				
				conn.commit();				
		}catch (SQLException e) {
			e.printStackTrace();			
		}
	}
	/***
	 * Modifica l' url della lezione
	 * @param vecchioTitolo vecchio titolo della lezione
	 * @param nuovoUrl nuovo url della lezione
	 * @return
	 * @pre nuovoUrl != null && nuovoUrl non presente nel db
	 **/
	public void updateUrlLesson(String vecchioTitolo, String nuovoUrl) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("UPDATE lezioni SET url = ? , approvato = 0 WHERE titolo = ?");
			stm.setString(1, nuovoUrl);
			stm.setString(2, vecchioTitolo);
			stm.executeUpdate();
				
			conn.commit();				
		}catch (SQLException e) {
			e.printStackTrace();			
		}
	}
	/***
	 * Modifica ladurata della lezione
	 * @param vecchioTitolo vecchio titolo della lezione
	 * @param nuovaDurata nuovo url della lezione
	 * @return
	 * @pre nuovaDurata != null
	 **/
	public void updateDurationLesson(String vecchioTitolo, String nuovaDurata) {
		try {
			Connection conn = DriverManagerConnectionPool.getConnection();
			PreparedStatement stm = conn.prepareStatement("UPDATE lezioni SET durata = ? , approvato = 0 WHERE titolo = ?");
			stm.setString(1, nuovaDurata);
			stm.setString(2, vecchioTitolo);
			stm.executeUpdate();
				
			conn.commit();				
		}catch (SQLException e) {
			e.printStackTrace();			
		}
	}
	/**
	 * Cancella la lezione
	 * @param titolo
	 * @return 
	 * @pre titolo != null
	 **/
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
	/**
	 *Restitusice l'ordine del cliente
	 *@param nomeCliente nome del cliente
	 *@return ArrayList<OrdineBean> 
	 *@pre nomeCliente != null
	 **/
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