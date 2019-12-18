package modelBean;

public class RecensioneBean {

	public RecensioneBean() {
		
	}
	public String getCliente() {
		return Cliente;
	}

	public String getPacchetto() {
		return Pacchetto;
	}

	public String getCommento() {
		return commento;
	}

	public String getIdRecensione() {
		return idRecensione;
	}
	
	public String getTitolo() {
		return titolo;
	}

	public void setCliente(String cliente) {
		Cliente = cliente;
	}

	public void setPacchetto(String pacchetto) {
		Pacchetto = pacchetto;
	}

	public void setCommento(String commento) {
		this.commento = commento;
	}

	public void setIdRecensione(String idRecensione) {
		this.idRecensione = idRecensione;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	//VARIABILI D'ISTANZA
	private String Cliente, Pacchetto, commento, idRecensione, titolo;

}
