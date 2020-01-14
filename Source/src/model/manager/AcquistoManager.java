package model.manager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import model.bean.AcquistoBean;
import model.bean.OrdineBean;
import model.bean.PacchettoBean;
import model.dao.AcquistoDao;
import model.dao.OrdineDao;

public class AcquistoManager {
	
	OrdineBean ordineBean = new OrdineBean();
	AcquistoBean acquistoBean = new AcquistoBean();
	OrdineDao ordine = new OrdineDao();
	AcquistoDao acquisto = new AcquistoDao();

	
	public AcquistoManager(){
		
	}
	
	public String getDataOdierna(){
		GregorianCalendar gc = new GregorianCalendar();
		int ggoggi = gc.get(Calendar.DAY_OF_MONTH);
		int mmoggi = gc.get(Calendar.MONTH) + 1;
		int aaoggi = gc.get(Calendar.YEAR);

		String dataOdierna = aaoggi + "-" + mmoggi + "-" + ggoggi;
		return dataOdierna;
		
	}
	
	public void getOrdine(String userName, ArrayList<PacchettoBean> carrello){
		
		ordineBean.setCliente(userName);
		ordineBean.setData(Date.valueOf(getDataOdierna()));
		int numOrd = 0;
		numOrd = ordine.insert(ordineBean);
		
		for (PacchettoBean p : carrello) {
			String codiceP = p.getCodicePacchetto();
			String titoloPacchetto = p.getTitolo();
			double prezzo = p.getPrezzo();

			acquistoBean.setNumOrdine(numOrd);
			acquistoBean.setCodiceP(codiceP);
			acquistoBean.setTitoloPacchetto(titoloPacchetto);
			acquistoBean.setImporto(prezzo);
			acquisto.insertAcquisto(acquistoBean);
		}
	}
}
