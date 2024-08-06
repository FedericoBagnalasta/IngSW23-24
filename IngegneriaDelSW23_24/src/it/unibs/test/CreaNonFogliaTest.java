package it.unibs.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import it.unibs.controller.GerarchiaController;
import it.unibs.model.Categoria;
import it.unibs.model.CategoriaNonFoglia;
import it.unibs.model.CategoriaRadice;
import it.unibs.model.ValoreDominio;

class CreaNonFogliaTest {
	
	GerarchiaController gerarchiaController = new GerarchiaController();

	@Test
	void creaNonFogliaTest() {
		String simulatedInput = "nomeNonFoglia\ncampo\nvalDom\nn\nn\ns\nnomeFoglia\n";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
		System.setIn(inputStream);
		
		String nomeRadice = "radice";
		String campo = "campo";
		ArrayList<ValoreDominio> dominio = new ArrayList<>();
		Categoria padre = new CategoriaRadice(nomeRadice, campo, dominio);
		
		String nomeValore = "nomeV";
		String descrizione = "descrizione";
		ValoreDominio valoreDominio = new ValoreDominio(nomeValore, descrizione);
		
		CategoriaNonFoglia nonFoglia = gerarchiaController.creaNonFoglia(padre, valoreDominio);
		
		assertEquals("nomeNonFoglia", nonFoglia.getNome());
		assertTrue(nonFoglia.getValoreDominio().verificaUguaglianza(valoreDominio));
		assertEquals("campo", nonFoglia.getCampo());
		assertEquals("valDom", nonFoglia.getDominio().get(0).getValore());
		assertEquals("Assente", nonFoglia.getDominio().get(0).getDescrizione());
		//assertEquals("nomeFoglia", nonFoglia.getFigli().get(0).getNome());
		assertEquals("radice", nonFoglia.getRadice().getNome());
		assertEquals("campo", nonFoglia.getRadice().getCampo());
		
		System.setIn(System.in);
	}
}