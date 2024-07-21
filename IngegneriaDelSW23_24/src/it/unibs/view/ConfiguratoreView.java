package it.unibs.view;

import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;

public class ConfiguratoreView {

	public static String inserisciNomeComprensorio() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome del nuovo comprensorio geografico\n");
	}
	
	public static ArrayList<String> inserisciComuniComprensorio() {
		ArrayList<String> comuni = new ArrayList<>();
		InputDati.leggiStringaNonVuota("Inserisci il nome del comune appartenente al nuovo comprensorio\n");
		while(InputDati.yesOrNo("Vuoi aggiungere un altro comune?\n")) {
			comuni.add(InputDati.leggiStringaNonVuota("Inserisci il nome del comune\n"));
		}
		return comuni;
	}
}
