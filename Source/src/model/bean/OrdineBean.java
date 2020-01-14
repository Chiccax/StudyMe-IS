package model.bean;

import java.sql.Date;
/**
 * Classe identificante una classe Ordine
 * @author Claudia Buono 
 * @version 1.1
 * @since  18/12/2019 
 */
public class OrdineBean {
	/**
	 * Costruttore generico dell' Ordine 
	 */
	public OrdineBean() {
	}
	/**
	 * Preleva nome  del cliente dell'ordine.
	 * @return String: cliente 
	 */
	public String getCliente() {
		return cliente;
	}
	/**
	 * Preleva il valore del numero dell'ordine.
	 * @return int: numOrdine 
	 */
	public int getNumOrdine() {
		return numOrdine;
	}
	/**
	 * Preleva il valore della data dell'ordine.
	 * @return Date: data 
	 */
	public Date getData() {
		return data;
	}
	/**
	 * Modifica il nome del cliente dell'ordine con il valore del parametro
	 * @param String cliente
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	/**
	 * Modifica il valore della data dell'ordine con il valore del parametro
	 * @param Date data
	 */
	public void setData(Date data) {
		this.data = data;
	}
	/**
	 * Modifica il valore del numero dell'ordine con il valore del parametro
	 * @param int numOrdine
	 */
	public void setNumOrdine(int numOrdine) {
		this.numOrdine = numOrdine;
	}


	private String cliente;
	private int numOrdine;
	private Date data;


}
