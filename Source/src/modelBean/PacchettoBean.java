package modelBean;

public class PacchettoBean {

	public PacchettoBean() {
	}
	
	public String getCodicePacchetto() {
		return codicePacchetto;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	public String getTitolo() {
		return titolo;
	}
	public String getCatagoria() {
		return catagoria;
	}
	public String getSottocategoria() {
		return sottocategoria;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public String getFoto() {
		return foto;
	}
	public  int getApprovato() {
		return approvato;
	}
	

	public void setCodicePacchetto(String codicePacchetto) {
		this.codicePacchetto = codicePacchetto;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public void setCatagoria(String catagoria) {
		this.catagoria = catagoria;
	}

	public void setSottocategoria(String sottocategoria) {
		this.sottocategoria = sottocategoria;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public void setFoto(String foto) {
		this.foto=foto;
	}
	
	public void setApprovato(int approvato) {
		this.approvato=approvato;
	}
	//VARIABILI D'ISTANZA
	private String codicePacchetto, descrizione, titolo, catagoria, sottocategoria,foto;
	private double prezzo;
	//nw
	private int approvato;	//default=0

}