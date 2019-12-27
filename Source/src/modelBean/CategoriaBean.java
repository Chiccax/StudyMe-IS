package modelBean;

public class CategoriaBean {

	public CategoriaBean() {
	}
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public String getInsegnante(){
		return insegnante;
	}
	public String getFotoCategoria() {
		return fotoCat;
	}
	public void setInsegnante(String insegnante){
		this.insegnante= insegnante;
	}
	
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	public void setFotoCategoria(String fotoCat) {
		this.fotoCat= fotoCat;
	}

	//VARIABILI D'ISTANZA
	private String nomeCategoria,fotoCat,insegnante;
}