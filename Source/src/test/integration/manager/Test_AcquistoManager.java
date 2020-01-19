package test.integration.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.manager.AcquistoManager;
import model.manager.OrdineManager;
import model.bean.*;
import model.dao.AcquistoDao;
import model.dao.OrdineDao;
import model.dao.PacchettoDao;

class Test_AcquistoManager {

	@Test
	void testGetDataOdierna() {
		String data = manager.getDataOdierna();
		
		assertNotNull(data);
	}
	
	@Test
	void testGetOrdine() throws SQLException { 
		ArrayList<PacchettoBean> carrello = new ArrayList<PacchettoBean>();
		PacchettoBean p = new PacchettoBean();
		p.setCodicePacchetto("pac001");
		p.setTitolo("Programmazione C");
		p.setCatagoria("Sviluppo");
		p.setSottocategoria("svi001");
		p.setPrezzo(98);
		p.setDescrizione("Capire i fondamenti del linguaggio C");
		p.setFoto("img/pacchetti/C1.jpg");
		carrello.add(p);
		
		manager.getOrdine("Damiana", carrello);
		
		ArrayList<OrdineAcquistoBean> ordini = new ArrayList<OrdineAcquistoBean>();
		OrdineManager ordine = new OrdineManager();
		ordini = ordine.RicercaNomeCliente("Damiana");
		
		assertNotNull(ordini);
	}

	AcquistoManager manager = new AcquistoManager();
}
