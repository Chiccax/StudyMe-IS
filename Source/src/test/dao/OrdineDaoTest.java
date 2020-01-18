package test.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.bean.OrdineBean;
import model.dao.OrdineDao;

class OrdineDaoTest {
	OrdineDao ordine;
	
	@BeforeEach
	void setup() throws Exception{
		ordine=new OrdineDao();
	}
	
	@Test
	void testinsert() {
		OrdineBean ordine;
		
	}
}
