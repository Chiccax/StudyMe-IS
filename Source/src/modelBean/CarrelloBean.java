package modelBean;

import java.util.ArrayList;
/**
 * Classe identidicante una classe Carrello
 * @author Claudia Buono 
 * @version 1.1
 * @since  18/12/2019 
 */
public class CarrelloBean {

	 ArrayList<PacchettoBean> oggetti_carrello;
		/**
		 * Costruttore generico del Carrello 
		 * 
		 */
	    public CarrelloBean() {
	            oggetti_carrello = new ArrayList<PacchettoBean>();
	    }
	    /**
	     * Restituisce la rappresentazione tesutale in forma di stringa
	     * */     
	    @Override
		public String toString() {
			return "Carrello [oggetti_carrello=" + oggetti_carrello + "]";
		}
	    /**
	     * Aggiunge un pacchetto al carrello.
	     * */
		public void aggiungi(PacchettoBean pacchetto) {
	            oggetti_carrello.add(pacchetto);
	    }
		/**
		 *Rimuove un oggetto dal carrello.
		 * */
	    public void rimuovi(int i) {
	            oggetti_carrello.remove(i);
	    } 
	    /**
	     * Preleva gli oggetti del carrello.
	     * @return ArrayList<PacchettoBean>: oggetti_carrello 
	     */
	    public ArrayList<PacchettoBean> getOggettiCarrello() {
	            return oggetti_carrello;
	    }
	    /**
	     * Rimuove tutti gli oggetti dal carrello.
	     * */
	    public void rimuovitutto(){
	   // 	oggetti_carrello.removeAll(oggetti_carrello);
	    	oggetti_carrello.clear();
	    }


}
