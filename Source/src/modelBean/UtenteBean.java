package modelBean;
/**
 * Classe identificante una classe Utente
 * @author Claudia Buono 
 * @version 1.1
 * @since  18/12/2019 
 */
public class UtenteBean {
	/**
	 * Costruttore generico dell'Utente
	 * 
	 */
	public UtenteBean() {}
	/**
	 * Preleva il nome dell'utente.
	 * @return String: nomeUtente
	 */
	public String getNomeUtente() {
		return nomeUtente;
	}
	/**
	 * Preleva la password dell'utente.
	 * @return String: password
	*/
	public String getPassword() {
		return password;
	}
	/**
	 * Preleva l'email dell'utente.
	 * @return String: email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Preleva il tipo  a cui appartiene l'utente.
	 * @return String: tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * Modifica il nome dell'utente con il valore del parametro
	 * @param String nomeUtente
	 */
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	/**
	 * Modifica la password dell'utente con il valore del parametro
	 * @param String password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Modifica l'email dell'utente con il valore del parametro
	 * @param String email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Modifica il tipo dell'utente con il valore del parametro
	 * @param String tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	String nomeUtente, password, email, tipo;

}
