package test.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.bean.SottocategoriaBean;
import model.dao.SottocategoriaDao;
import model.dao.UtenteDao;
import model.manager.SottocategoriaManager;

class TC_SottocategoriaManager extends Mockito {

	SottocategoriaDao sottocategoriaMock;
	SottocategoriaManager sottocategoria;
	
	@Test
	void selezionaSottocategorieInsegnanteTest() {
		sottocategoriaMock = (SottocategoriaDao)Mockito.mock(SottocategoriaDao.class); 
		sottocategoria = new SottocategoriaManager();
		
		ArrayList<SottocategoriaBean> sottocategorieMock = new ArrayList<SottocategoriaBean>();
		
		SottocategoriaBean s = new SottocategoriaBean();
		s.setIdSottoCat("inf001");
		s.setNomeSott("Sistema operativo");
		
		SottocategoriaBean s2 = new SottocategoriaBean();
		s.setIdSottoCat("inf002");
		s.setNomeSott("Reti");
		
		sottocategorieMock.add(s);
		sottocategorieMock.add(s2);
		
		ArrayList<SottocategoriaBean> sottocategorie = new ArrayList<SottocategoriaBean>();
		
		when(sottocategoriaMock.selezionaSottocagorieInsegnante("Mariarosaria")).thenReturn(sottocategorieMock);
		sottocategorie = sottocategoria.selezionaSottocagorieInsegnante("Mariarosaria");
		
		assertEquals(sottocategorie.size(), sottocategorieMock.size());
	}
	
	@Test
	void findByKeyTest() throws SQLException {
		sottocategoriaMock = (SottocategoriaDao)Mockito.mock(SottocategoriaDao.class); 
		sottocategoria = new SottocategoriaManager();

		SottocategoriaBean sMock = new SottocategoriaBean();
		sMock.setIdSottoCat("svi001");
		sMock.setNomeSott("Sviluppo");
		SottocategoriaBean s = new SottocategoriaBean();
		
		when(sottocategoriaMock.findByKey("svi001")).thenReturn(sMock);
		
		s = (SottocategoriaBean) sottocategoria.findByKey("svi001");
		
		assertNotNull(s);
	}

}
