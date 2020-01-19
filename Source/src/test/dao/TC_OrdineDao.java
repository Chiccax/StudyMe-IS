package test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.bean.OrdineBean;
import model.dao.OrdineDao;

class TC_OrdineDao {
	OrdineDao ordine;
	
	@BeforeEach
	void setup() throws Exception{
		ordine=new OrdineDao();
	}
	
	@Test
	void testinsert() {
		Date d= new Date(12, 12, 12);

		OrdineBean ordine= new OrdineBean();
		ordine.setCliente("Damiana");
		ordine.setNumOrdine(1);
		ordine.setData(d);
		
		OrdineDao dao= new OrdineDao();
		int n=dao.insert(ordine);
		
		assertNotEquals(-1, n);
		
		
	}
}
