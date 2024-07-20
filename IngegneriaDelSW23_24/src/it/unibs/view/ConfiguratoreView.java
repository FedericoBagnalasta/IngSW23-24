package it.unibs.view;

import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;

public class ConfiguratoreView {

	//Non sono sicuro sia static
	public static String inserisciNomeComprensorio() {
		return InputDati.leggiStringaNonVuota("Inserisci un nuovo comprensorio geografico\n");
	}
	
	public static ArrayList<String> inserisciComuniComprensorio() {
		ArrayList<String> comuni=new ArrayList<>();
		InputDati.leggiStringaNonVuota("Inserisci i comuni appartententi al nuovo comprensorio\n");
		while(InputDati.yesOrNo("Vuoi aggiungere un altro comune?\n")) {
			comuni.add(InputDati.leggiStringaNonVuota("Inserisci il nome del comune\n"));
		}
		return comuni;
	}
	
	//Manco questo
	public static boolean rispostaConfiguratore() {
		return InputDati.yesOrNo("Vuoi inserire un nuovo comprensorio geografico?");
	}
}
