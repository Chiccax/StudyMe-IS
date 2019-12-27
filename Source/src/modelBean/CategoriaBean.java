package modelBean;
/**
 * Classe identificante una classe Categoria
 * @author Claudia Buono 
 * @version 1.1
 * @since  18/12/2019 
 */
public class CategoriaBean {
	/**
	 * Costruttore generico della Categoria 
	 * 
	 */
	public CategoriaBean() {
	}
	/**
	 * Preleva il nome della categoria.
	 * @return String: nomeCategoria 
	 */
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	/**
	 * Prelevo l'insegnante.
	 * @return String: insegnante 
	 */
	public String getInsegnante(){
		return insegnante;
	}
	/**
	 * Preleva il valore della foto della categoria.
	 * @return String: fotoCat 
	 */
	public String getFotoCategoria() {
		return fotoCat;
	}
	/**
	 * Modifica l'insegnante con il valore del parametro
	 * @param String insegnante
	 */
	public void setInsegnante(String insegnante){
		this.insegnante= insegnante;
	}
	/**
	 * Modifica il nome della categoria con il valore del parametro
	 * @param String nomeCategoria
	 */
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	/**
	 * Modifica il valore della foto categoria con il valore del parametro
	 * @param String fotoCat
	 */
	public void setFotoCategoria(String fotoCat) {
		this.fotoCat= fotoCat;
	}

	private String nomeCategoria,fotoCat,insegnante;
}