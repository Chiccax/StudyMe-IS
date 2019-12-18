package modelBean;

import java.sql.Date;

public class OrdineBean {

	public OrdineBean() {
		
	}
	
	public String getCliente() {
		return cliente;
	}
	
	public int getNumOrdine() {
		return numOrdine;
	}
	
	public Date getData() {
		return data;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}


	public void setData(Date data) {
		this.data = data;
	}

	
	public void setNumOrdine(int numOrdine) {
		this.numOrdine = numOrdine;
	}

	//VARIABILI D'ISTANZA
	private String cliente;
	private int numOrdine;
	private Date data;


}
