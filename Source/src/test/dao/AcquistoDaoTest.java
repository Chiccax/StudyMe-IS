package test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mysql.fabric.xmlrpc.base.Data;

import model.bean.AcquistoBean;
import model.bean.OrdineAcquistoBean;
import model.bean.OrdineBean;
import model.bean.PacchettoBean;
import model.dao.AcquistoDao;
import model.dao.OrdineAcquistoDao;
import model.dao.OrdineDao;



class AcquistoDaoTest {
	AcquistoDao acquisto;
	
	@BeforeEach
	void setUp() throws Exception{
		acquisto= new AcquistoDao();
		
	}
	
	@Test
	void testInsertAcquisto() {
		AcquistoBean bean= new AcquistoBean();
		bean.setCodiceP("pac004");
		bean.setImporto(12.8);
		bean.setNumOrdine(1);
		bean.setTitoloPacchetto("Impara Python programming");
		
		Date d= new Date(12, 12, 12);
		
		
		
		OrdineBean ordine= new OrdineBean();
		ordine.setCliente("Damiana");
		ordine.setNumOrdine(1);
		ordine.setData(d);
		ArrayList<OrdineAcquistoBean> ordini= new ArrayList<OrdineAcquistoBean>();
		OrdineAcquistoDao ordinedao= new OrdineAcquistoDao();
		PacchettoBean pacchetto= new PacchettoBean();
		pacchetto.setCodicePacchetto("pac004");
		OrdineDao ordinedaoo= new OrdineDao();
		ordinedaoo.insert(ordine);
		bean.setCodiceP("pac004");
		acquisto.insertAcquisto(bean);
		String code="";
		
		try {
			ordini= ordinedao.findAllClient();
			assertNotNull(ordini);
			
			for(int i=0;i<ordini.size();i++) {
				for(int j=0;j<ordini.get(i).getPacchettiAcquistati().size();j++) {
					if(ordini.get(i).getPacchettiAcquistati().get(j).getCodicePacchetto().equals(pacchetto.getCodicePacchetto())) {
						code= ordini.get(i).getPacchettiAcquistati().get(j).getCodicePacchetto();}
				}
			}
			assertEquals(pacchetto.getCodicePacchetto(),code);
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}