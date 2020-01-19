package test.manager;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.dao.RecensioneDao;
import model.manager.RecensioneManager;

class TC_RecensioneManager extends Mockito {

	RecensioneDao recensioneMock;
	RecensioneManager recensioneManager;
	
	@Test
	void testAggiungiRecensione() {
		recensioneMock = (RecensioneDao)Mockito.mock(RecensioneDao.class);
		RecensioneManager manager = new RecensioneManager();
		manager.setDao(recensioneMock);
		manager.aggiungiRecensione("Damiana", "pac001", "Ottimo corso", "Corso consigliato a tutti!");
		verify(recensioneMock).aggiungiRecensione("Damiana", "pac001", "Ottimo corso", "Corso consigliato a tutti!");
	}

}
