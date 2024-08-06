package it.unibs.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import it.unibs.controller.GerarchiaController;
import it.unibs.model.Categoria;
import it.unibs.model.CategoriaFoglia;
import it.unibs.model.CategoriaRadice;
import it.unibs.model.ValoreDominio;

class CreaFogliaTest {
	
	GerarchiaController gerarchiaController = new GerarchiaController();

	@Test
	void creaFogliaTest() {
		String simulatedInput = "nomeF1\nnomeF1\nradiceF1\nfdc\nnomeF2\nradiceF2\n";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
		System.setIn(inputStream);
		
		String nomeRadice = "radiceF1";
		String campo = "campo";
		ArrayList<ValoreDominio> dominio = new ArrayList<>();
		Categoria padre = new CategoriaRadice(nomeRadice, campo, dominio);
		
		String nomeValore = "nomeV";
		String descrizione = "descrizione";
		ValoreDominio valoreDominio = new ValoreDominio(nomeValore, descrizione);
		
		CategoriaFoglia foglia = gerarchiaController.creaFoglia(padre, valoreDominio);
		
		assertEquals("nomeF1", foglia.getNome());
		assertTrue(foglia.getValoreDominio().verificaUguaglianza(valoreDominio));
		assertEquals("radiceF1", foglia.getRadice().getNome());
		assertEquals("campo", foglia.getRadice().getCampo());
		
		System.setIn(System.in);
	}
}