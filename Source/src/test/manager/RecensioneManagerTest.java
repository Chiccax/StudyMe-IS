package test.manager;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.dao.RecensioneDao;
import model.manager.RecensioneManager;

class RecensioneManagerTest extends Mockito {

	RecensioneDao recensioneMock;
	RecensioneManager recensioneManager;
	
	@Test
	void testAggiungiRecensione() {
		recensioneMock = (RecensioneDao)Mockito.mock(RecensioneDao.class);
		RecensioneManager manager = new RecensioneManager();
		manager.setDao(recensioneMock);
		manager.aggiungiRecensione("Annarella", "pac001", "Ottimo corso", "Corso consigliato a tutti!");
		verify(recensioneMock).aggiungiRecensione("Annarella", "pac001", "Ottimo corso", "Corso consigliato a tutti!");
	}

}
