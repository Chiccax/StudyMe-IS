package test.dao;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.bean.AcquistoBean;
import model.dao.AcquistoDao;



class TC_AcquistoDao {
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
		
		acquisto.insertAcquisto(bean);
		
		Boolean res = acquisto.existingPurchase(bean.getNumOrdine());
		assertTrue(res);
	}
	
	@Test
	void testEsistenzaAcquisto() {
		Boolean res = acquisto.existingPurchase(1);
		assertTrue(res);
	}
}