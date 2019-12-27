package modelBean;
/**
 * Classe identificante una classe Recensione
 * @author Claudia Buono 
 * @version 1.1
 * @since  18/12/2019 
 */
public class RecensioneBean {
	/**
	 * Costruttore generico delle Recensioni
	 * 
	 */
	public RecensioneBean() {
		
	}
	/**
	 * Preleva il nome del cliente.
	 * @return String: Cliente 
	 */
	public String getCliente() {
		return Cliente;
	}
	/**
	 * Preleva il nome del pacchetto.
	 * @return String: Pacchetto
	 */
	public String getPacchetto() {
		return Pacchetto;
	}
	/**
	 * Preleva il cliente del cliente.
	 * @return String: commento 
	 */
	public String getCommento() {
		return commento;
	}
	/**
	 * Preleva l'id della recensione.
	 * @return String: idRecensione 
	 */
	public String getIdRecensione() {
		return idRecensione;
	}
	/**
	 * Preleva il titolo della recensione
	 * @return String: titolo
	 */
	public String getTitolo() {
		return titolo;
	}
	/**
	 * Modifica il nome del cliente della recensione con il valore del parametro
	 * @param String cliente
	 */
	public void setCliente(String cliente) {
		Cliente = cliente;
	}
	/**
	 * Modifica il pacchetto della recensione con il valore del parametro
	 * @param String pacchetto
	 */
	public void setPacchetto(String pacchetto) {
		Pacchetto = pacchetto;
	}
	/**
	 * Modifica il commento della recensione con il valore del parametro
	 * @param String commento
	 */
	public void setCommento(String commento) {
		this.commento = commento;
	}
	/**
	 * Modifica l' id della recensione con il valore del parametro
	 * @param String idRecensione
	 */
	public void setIdRecensione(String idRecensione) {
		this.idRecensione = idRecensione;
	}
	/**
	 * Modifica il titolo della recensione con il valore del parametro
	 * @param String titolo
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	
	private String Cliente, Pacchetto, commento, idRecensione, titolo;

}
