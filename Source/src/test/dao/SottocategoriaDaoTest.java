package test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.bean.SottocategoriaBean;
import model.dao.SottocategoriaDao;

class SottocategoriaDaoTest {
	SottocategoriaDao sottocategoria;
	
	@BeforeEach
	void sutup() throws Exception {
		sottocategoria =new SottocategoriaDao();
	}
	@Test
	void testfindByKey() throws SQLException {
		Object codiceS="fot003";
		
		sottocategoria.findByKey(codiceS);
		assertEquals(codiceS, sottocategoria.findByKey(codiceS));
	}
	@Test
	void testselezionaSottocagorieInsegnante() {
		String utente="Rachele";
		
		sottocategoria.selezionaSottocagorieInsegnante(utente);
		assertEquals(utente,sottocategoria.selezionaSottocagorieInsegnante(utente));
		
	}	
}
