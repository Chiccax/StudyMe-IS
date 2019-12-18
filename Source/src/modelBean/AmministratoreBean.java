package modelBean;

public class AmministratoreBean {

	public AmministratoreBean() {}
	
	public String getNomeAmministratore() {
		return nomeAmministratore;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getTipo() {
		return tipo;
	}

	public void setNomeAmministratore(String nomeAmministratore) {
		this.nomeAmministratore = nomeAmministratore;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	//Variabili d'istanza
	String nomeAmministratore, password, email, tipo;
}
