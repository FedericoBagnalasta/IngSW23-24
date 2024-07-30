package it.unibs.view;

import it.unibs.fp.mylib.InputDati;

public class ComprensorioView {

	//PARTE COMPRENSORIO

	public static String inserisciNomeNuovoComprensorio() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome del nuovo Comprensorio\n");
	}

	public static void comprensorioGiaPresente() {
		System.out.println("Esiste già un Comprensorio con questo nome");
	}
	
	public static String inserisciNomeComprensorio() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome del Comprensorio che vuoi selezionare");
	}

	public static void visualizzaNomeComprensorio(String nome) {
		System.out.println("\nNome Comprensorio: " + nome);
	}

	//PARTE COMUNE DEL COMPRENSORIO

	public static String inserisciComune() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome del Comune\n");
	}

	public static boolean inserisciAltroComune() {
		return InputDati.yesOrNo("Vuoi inserire un altro Comune?\n");
	}

	public static void comuneGiaPresente() {
		System.out.println("Il Comune inserito è già presente in questo Comprensorio");
	}

	public static void visualizzaNomeComune(String nome) {
		System.out.println("Nome Comune: " + nome);
	}
}