package modelBean;
/**
 * Classe identificante una classe Pacchetto
 * @author Claudia Buono 
 * @version 1.1
 * @since  18/12/2019 
 */
public class PacchettoBean {
	/**
	 * Costruttore generico del Pacchetto
	 * 
	 */
	public PacchettoBean() {
	}
	/**
	 * Preleva il codice del pacchetto.
	 * @return String: codicePacchetto 
	 */
	public String getCodicePacchetto() {
		return codicePacchetto;
	}
	/**
	 * Preleva la descrzione del pacchetto.
	 * @return String: descrzione 
	 */
	public String getDescrizione() {
		return descrizione;
	}
	/**
	 * Preleva iltitolo del pacchetto.
	 * @return String: titolo 
	 */
	public String getTitolo() {
		return titolo;
	}
	/**
	 * Preleva la categoria del pacchetto.
	 * @return String: categoria 
	 */
	public String getCatagoria() {
		return catagoria;
	}
	/**
	 * Preleva la sottocategoria del pacchetto.
	 * @return String: sottocategoria 
	 */
	public String getSottocategoria() {
		return sottocategoria;
	}
	/**
	 * Preleva il prezzo del pacchetto.
	 * @return Double: prezzo 
	 */
	public double getPrezzo() {
		return prezzo;
	}
	/**
	 * Preleva la foto del pacchetto
	 * @return String: foto
	 */
	public String getFoto() {
		return foto;
	}
	/**
	 * Preleva il valore dell'approvazione del pacchetto.
	 * @return int: approvato 
	 */
	public  int getApprovato() {
		return approvato;
	}
	/**
	 * Modifica il codice del pacchetto con il valore del parametro
	 * @param String codicePacchetto
	 */
	public void setCodicePacchetto(String codicePacchetto) {
		this.codicePacchetto = codicePacchetto;
	}
	/**
	 * Modifica la descrizione del pacchetto con il valore del parametro
	 * @param String descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	/**
	 * Modifica il titolo del pacchetto con il valore del parametro
	 * @param String titolo
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	/**
	 * Modifica la categria del pacchetto con il valore del parametro
	 * @param String categoria
	 */
	public void setCatagoria(String catagoria) {
		this.catagoria = catagoria;
	}
	/**
	 * Modifica la sottocategoria del pacchetto con il valore del parametro
	 * @param String sottocategoria
	 */
	public void setSottocategoria(String sottocategoria) {
		this.sottocategoria = sottocategoria;
	}
	/**
	 * Modifica il prezzo del pacchetto con il valore del parametro
	 * @param Double prezzo
	 */
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	/**
	 * Modifica la foto del pacchetto con il valore del parametro
	 * @param String foto
	 */
	public void setFoto(String foto) {
		this.foto=foto;
	}
	/**
	 * Modifica l'approvazione del pacchetto con il valore del parametro
	 * @param int approvato
	 */
	public void setApprovato(int approvato) {
		this.approvato=approvato;
	}
	
	private String codicePacchetto, descrizione, titolo, catagoria, sottocategoria,foto;
	private double prezzo;
	//nw
	private int approvato;	//Valore di default=0

}