package test.manager;

import static org.junit.Assert.assertNotNull;

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
		
	
		OrdineBean ordine = new OrdineBean();
		AcquistoBean acquisto = new AcquistoBean();
		ordineMock.insert(ordine);		
		acquistoMock.insertAcquisto(acquisto);
		verify(ordineMock).insert(ordine);
		verify(acquistoMock).insertAcquisto(acquisto);
	}
}