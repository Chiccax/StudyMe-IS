package modelBean;

public class CategoriaBean {

	public CategoriaBean() {
	}
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public String getFotoCategoria() {
		return fotoCat;
	}
	
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	public void setFotoCategoria(String fotoCat) {
		this.fotoCat= fotoCat;
	}

	//VARIABILI D'ISTANZA
	private String nomeCategoria,fotoCat;
}