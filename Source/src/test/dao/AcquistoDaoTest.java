package test.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.bean.AcquistoBean;
import model.dao.AcquistoDao;



class AcquistoDaoTest {
	AcquistoDao acquisto;
	
	@BeforeEach
	void setUp() throws Exception{
		acquisto= new AcquistoDao();
	}
	
	@Test
	void testInsertAcquisto() {
		AcquistoBean bean;
	}

}
