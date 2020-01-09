package modelBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import control.util.Observer;
import control.util.Subject;
import utility.EmailSender;

/**
 * Classe identificante una classe Utente
 * @author Claudia Buono 
 * @version 1.1
 * @since  18/12/2019 
 */
public class UtenteBean implements Observer {
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
	
	public void attach(Subject s) {
		if (s != null) {
			this.s = s;
			this.s.addObserver(this);
		}
	}
	
	public void detach() {
		if(s != null) {
			s.removeObserver(this);
			s = null;
		}
	}
	
	@Override
	public void update() {
		EmailSender emailSender = EmailSender.GetInstance();
		emailSender.SendEmail("Nuova Lezione", "Gentile " + this.nomeUtente + ", è stata aggiunta una nuova lezione per uno dei pacchetti che hai acquistato. Ti invitiamo a scoprire qual è! Distinti saluti", this.email);
	}	
	
	String nomeUtente, password, email, tipo;
	Subject s;

}
