package model.bean;
/**
 * Classe identificante una classe Acquisto 
 * @author Claudia Buono 
 * @version 1.1
 * @since  18/12/2019 
 */
public class AcquistoBean {
	/**
	 * Costruttore generico dell' Acquisto 
	 * 
	 */
	public AcquistoBean() {
	}
	/**
	 * Modifica il valore del numero di acquisto con il valore del parametro
	 * @param int numAcquisto
	 */
	public void setNumAcquisto(int numAcquisto) {
		this.numAcquisto = numAcquisto;
	}
	/**
	 * Preleva il valore del numero dell'acquisto.
	 * @return int: numAcquisto 
	 */
	public int getNumAcquisto() {
		return numAcquisto;
	}
	/**
	 * Preleva il valore del numero dell'ordine.
	 * @return int: numOrdine 
	 */
	public int getNumOrdine() {
		return numOrdine;
	}
	/**
	 * Preleva il valore del numero dell'importo.
	 * @return int: importo 
	 */
	public double getImporto() {
		return importo;
	}
	/**
	 * Preleva il valore del numero del codice del pacchetto.
	 * @return String: codiceP
	 */
	public String getCodiceP() {
		return codiceP;
	}
	/**
	 * Modifica il valore del codice del pacchetto con il valore del parametro
	 * @param String codiceP
	 */
	public void setCodiceP(String codiceP) {
		this.codiceP = codiceP;
	}
	/**
	 * Modifica il valore del numero dell'ordine con il valore del parametro
	 * @param int numOrdine
	 */
	public void setNumOrdine(int numOrdine) {
		this.numOrdine = numOrdine;
	}
	/**
	 * Modifica il valore dell'importo con il valore del parametro
	 * @param double importo
	 */
	public void setImporto(double importo) {
		this.importo = importo;
	}
	/**
	 * Preleva il titolo del pacchetto.
	 * @return String: titoloPacchetto
	 */
	public String getTitoloPacchetto() {
		return titoloPacchetto;
	}
	/**
	 * Modifica il titolo del pacchetto con il valore del parametro
	 * @param String titoloPacchetto
	 */
	public void setTitoloPacchetto(String titoloPacchetto) {
		this.titoloPacchetto = titoloPacchetto;
	}
	

	private int numOrdine, numAcquisto;
	private String codiceP,titoloPacchetto;
	private double importo;

}