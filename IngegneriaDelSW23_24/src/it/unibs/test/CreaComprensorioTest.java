package it.unibs.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import it.unibs.controller.ComprensorioController;
import it.unibs.model.Comprensorio;
import it.unibs.model.ElencoComprensori;

class CreaComprensorioTest {
	
	ComprensorioController comprensorioController = new ComprensorioController();

	@Test
	void creaComprensorioTest() {
		String simulatedInput = "comprensorio\ncomune1\ns\ncomune2\nn\n";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
		System.setIn(inputStream);
		
		comprensorioController.creaComprensorio();
		
		ArrayList<Comprensorio> elencoComprensori = ElencoComprensori.getElencoComprensori();
		
		ArrayList<String> comuni = new ArrayList<>();
		comuni.add("comune1");
		comuni.add("comune2");
		
		assertEquals(elencoComprensori.get(0).getNome(), "comprensorio");
		assertEquals(elencoComprensori.get(0).getComuniComprensorio(), comuni);
		
		System.setIn(System.in);
	}
}