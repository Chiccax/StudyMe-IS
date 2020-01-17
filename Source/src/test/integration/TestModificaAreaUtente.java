package test.integration;

import static org.junit.Assert.assertTrue;

import java.util.Base64;

import org.junit.After;
import org.junit.jupiter.api.Test;

import model.manager.UtenteManager;

class TestModificaAreaUtente {
	
	@After
	protected void tearDown() throws Exception {
		manager.setEmail("chiccaesp98@libero.com", "mesposito@gmail.com");
	}
	
	@Test
	public void testModificaEmail() {
		boolean res = manager.setEmail("chiccaesp98@libero.com", "mesposito@gmail.com");
		assertTrue(res);
	}
	
	@Test
	public void testModificaPassword() {
		String passwordNuova = "passMariarosaria";
		String passwordBase64format  = Base64.getEncoder().encodeToString(passwordNuova.getBytes()); 
		Boolean res = manager.setPassword("chiccaesp98@gmail.com", passwordBase64format);
		assertTrue(res);
	}
	
	
	private UtenteManager manager = new UtenteManager();
}
