package model.bean;

import control.util.Subject;

/**
 * Classe identificante una classe Lezioni
 * @author Claudia Buono 
 * @version 1.1
 * @since  18/12/2019 
 */
public class LezioniBean extends Subject {
	/**
	 * Costruttore generico delle Lezioni
	 * 
	 */
	public LezioniBean() {}
	/**
	 * Preleva l'URL della lezione.
	 * @return String: url 
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * Preleva il titolo della lezione.
	 * @return String: titolo 
	 */
	public String getTitolo() {
		return titolo;
	}
	/**
	 * Preleva il pacchetto a cui appartiene la lezione.
	 * @return String: pacchetto 
	 */
	public String getPacchetto() {
		return pacchetto;
	}
	/**
	 * Preleva la durata della lezione.
	 * @return String: durata
	 */
	public String getDurata() {
		return durata;
	}
	/**
	 * Preleva il valore del approvazione.
	 * @return int: approvato 
	 */
	public  int getApprovato() {
		return approvato;
	}
	/**
	 * Modifica l'url della lezione con il valore del parametro
	 * @param String url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * Modifica il titolo della lezione con il valore del parametro
	 * @param String titolo
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	/**
	 * Modifica il pacchetto a cui appartiene la lezione con il valore del parametro
	 * @param String pacchetto
	 */
	public void setPacchetto(String pacchetto) {
		this.pacchetto = pacchetto;
	}
	/**
	 * Modifica la durata della lezione con il valore del parametro
	 * @param String durata
	 */
	public void setDurata(String durata) {
		this.durata = durata;
	}
	/**
	 * Modifica il valore dell'approvazione della lezione con il valore del parametro
	 * @param int approvato
	 */
	public void setApprovato(int approvato) {
		this.approvato=approvato;
		
		if(this.approvato == 1) {
			notifyObservers();
		}
	}

	
	private String url, titolo, pacchetto, durata;
	private int approvato;	 // Valore di default=0; 
	}