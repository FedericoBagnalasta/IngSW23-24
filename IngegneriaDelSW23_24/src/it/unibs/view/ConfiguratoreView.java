package it.unibs.view;

import it.unibs.fp.mylib.InputDati;

public class ConfiguratoreView {

	public static String inserisciNomeComprensorio() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome del nuovo comprensorio geografico\n");
	}
		
	public static String inserisciComuniComprensorio() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome comune\n");
	}
	
	public static boolean inserisciAltroComune() {
		return(InputDati.yesOrNo("Vuoi inserire un altro comune?"));
	}
	
	public static void comuneGiaPresente() {
		System.out.println("Il comune inserito è già presente in questo comprensorio");
	}
}
