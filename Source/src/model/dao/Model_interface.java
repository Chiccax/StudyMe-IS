package model.dao;

import java.sql.SQLException;
import java.util.Collection;
/**
 * Interfaccia  
 **/
public interface Model_interface<A> {
	/**
	 * Inserisce
	 * @param A entity
	 * @return 
	 * @throws SQLEXception
	 **/
	public void  insert(A entity) throws SQLException;
	/**
	 * Modifica
	 * @param A entity
	 * @return 
	 * @throws SQLEXception
	 **/
	public void update(A entity) throws SQLException;
	/**
	 * Rimuove
	 * @param codiceP codice del pacchetto
	 * @throws SQLEXception
	 **/
	public boolean remove(Object codiceP) throws SQLException;
	/**
	 * Ricerca trsmite il codiceP
	 * @param codiceP codice del pacchetto
	 * @return A
	 * @throws SQLEXception
	 **/
	public A findByKey(Object codiceP) throws SQLException;
	/**
	 * Ricerca totale
	 * @param 
	 * @return Collection<A> 
	 * @throws SQLEXception
	 **/
	public Collection<A> findAll() throws SQLException;

}