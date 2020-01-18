package test.integration.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.manager.SottocategoriaManager;
import model.bean.*;
class testSottocategoriaManager {

	@Test
	void testSelezionaSottocategorieInsegnante() {
		ArrayList<SottocategoriaBean> sottocategorie = new ArrayList<SottocategoriaBean>();
		sottocategorie = manager.selezionaSottocagorieInsegnante("Mariarosaria");
		
		assertNotNull(sottocategorie);	
	}
	
	@Test
	void testFindByKey() throws SQLException {
		SottocategoriaBean s = new SottocategoriaBean();
		s = (SottocategoriaBean) manager.findByKey("ins001");
		
		assertNotNull(s);
	}
	
	SottocategoriaManager manager = new SottocategoriaManager();
}
