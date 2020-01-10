package model.bean;

import java.util.ArrayList;
/**
 * Classe identificante una classe OrdineAcquisto
 * @author Claudia Buono 
 * @version 1.1
 * @since  18/12/2019 
 */
public class OrdineAcquistoBean extends OrdineBean{
	/**
	 * Costruttore generico dell' OrdineAcquisto 
	 */
	public OrdineAcquistoBean() {}
	/**
	 * Preleva i pacchetti acquistati.
	 * @return ArrayList<PacchettoBean>:pacchettiAcquistati 
	 */
	public ArrayList<PacchettoBean> getPacchettiAcquistati() {
		return pacchettiAcquistati;
	}
	/**
	 * Modifica i pacchetti acquistati con il valore del parametro
	 * @param  ArrayList<PacchettoBean>:pacchettiAcquistati
	 */
	public void setPacchettiAcquistati(ArrayList<PacchettoBean> pacchettiAcquistati) {
		this.pacchettiAcquistati = pacchettiAcquistati;
	}

	private ArrayList<PacchettoBean> pacchettiAcquistati = new ArrayList<PacchettoBean>();
}