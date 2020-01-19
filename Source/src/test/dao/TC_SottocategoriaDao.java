package test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.bean.SottocategoriaBean;
import model.dao.SottocategoriaDao;

class TC_SottocategoriaDao {
	SottocategoriaDao sottocategoria;
	
	@BeforeEach
	void sutup() throws Exception {
		sottocategoria = new SottocategoriaDao();
	}
	
	@Test
	void testfindByKey() throws SQLException {
		Object codiceS="fot003";
		
		SottocategoriaBean s = new SottocategoriaBean();
		s = sottocategoria.findByKey(codiceS);
		assertEquals(codiceS, s.getIdSottoCat());
	}
	
	@Test
	void testselezionaSottocagorieInsegnante() {
		String utente = "Rachele";
		
		ArrayList<SottocategoriaBean> sottocategorie = new ArrayList<SottocategoriaBean>();
		sottocategorie = sottocategoria.selezionaSottocagorieInsegnante(utente);
		
		assertNotNull(sottocategorie);
	}	
}
