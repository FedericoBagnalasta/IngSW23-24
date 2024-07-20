package it.unibs.view;

import it.unibs.fp.mylib.InputDati;
import it.unibs.model.Comprensorio;
import it.unibs.model.ElencoComprensori;

public class FruitoreView {
	
	public static String inserisciComprensorio() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome del comprensorio geografico\n");
	}

	public static void visualizzaNomiComprensori() {
		for(Comprensorio c : ElencoComprensori.getElencoComprensori()) {
			System.out.println(c.getNome());
		}
	}
}
