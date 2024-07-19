package it.unibs.view;

import it.unibs.fp.mylib.InputDati;

public class ConfiguratoreView {

	//Non sono sicuro sia static
	public static String inserisciComprensorio() {
		return InputDati.leggiStringaNonVuota("Inserisci un nuovo comprensorio geografico\n");
	}
	
	//Manco questo
	public static boolean rispostaConfiguratore() {
		return InputDati.yesOrNo("Vuoi inserire un nuovo comprensorio geografico?");
	}
}
