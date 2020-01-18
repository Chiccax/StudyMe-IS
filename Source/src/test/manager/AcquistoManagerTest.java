package test.manager;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.dao.AcquistoDao;
import model.dao.OrdineDao;
import model.manager.AcquistoManager;
import model.bean.*;
class AcquistoManagerTest extends Mockito{

	AcquistoManager manager;
	OrdineDao ordineMock;
	AcquistoDao acquistoMock;
	
	@Test
	void testGetDataOdierna() {
		manager = new AcquistoManager();
		
		String data;
		data = manager.getDataOdierna();
		assertNotNull(data);
	}
	
	@Test
	void testGetOrdine() {
		ordineMock = (OrdineDao)Mockito.mock(OrdineDao.class);
		acquistoMock = (AcquistoDao)Mockito.mock(AcquistoDao.class);
		ArrayList<PacchettoBean> carrello = new ArrayList<PacchettoBean>();
		
	
		OrdineBean ordine = new OrdineBean();
		AcquistoBean acquisto = new AcquistoBean();
		
		when(ordineMock.insert(ordine)).thenReturn(1);

		manager.setDao(acquistoMock);
		manager.getOrdine("Annarella", carrello);
		verify(acquistoMock).insertAcquisto(acquisto);
	}
}