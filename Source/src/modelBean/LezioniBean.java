package modelBean;

public class LezioniBean {

	public LezioniBean() {}
	public String getUrl() {
		return url;
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public String getPacchetto() {
		return pacchetto;
	}
	
	public String getDurata() {
		return durata;
	}
	public  int getApprovato() {
		return approvato;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public void setPacchetto(String pacchetto) {
		this.pacchetto = pacchetto;
	}

	public void setDurata(String durata) {
		this.durata = durata;
	}
	public void setApprovato(int approvato) {
		this.approvato=approvato;
	}

	//VARIABILI D'ISTANZA
	private String url, titolo, pacchetto, durata;
	//nw
	private int approvato;	//default=0
}