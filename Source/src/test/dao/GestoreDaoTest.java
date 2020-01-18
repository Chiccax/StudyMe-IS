package test.dao;



import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.dao.GestoreDao;

class GestoreDaoTest {

	GestoreDao gestore;
	
	@BeforeEach 
	void setup() throws Exception{
		gestore=new GestoreDao();
	}
	@Test
	void testapprovaInteroPacchetto() {
		String codicePacchetto="pac200";
		
		gestore.approvaInteroPacchetto(codicePacchetto);
		assertEquals(codicePacchetto,gestore.approvaInteroPacchetto(codicePacchetto));
	}
	@Test
	void disapprovaInteroPacchetto() {
		String codicePacchetto="pac200";
		
		gestore.disapprovaInteroPacchetto(codicePacchetto);
		assertEquals(codicePacchetto,gestore.disapprovaInteroPacchetto(codicePacchetto));
	}
	@Test
	void testapprovaSingolaLezione() {
		String url="https://www.youtube.com/embed/tsxwGnDfvWE";
		
		gestore.approvaSingolaLezione(url);;
		assertEquals(url,gestore.approvaSingolaLezione(url));
	}
	@Test
	void testdisapprovaSingolaLezione() {
		String url="https://www.youtube.com/embed/tsxwGnDfvWE";
		
		gestore.disapprovaSingolaLezione(url);
		assertEquals(url, gestore.disapprovaSingolaLezione(url));
	}
}
