package it.unibs.view;

import it.unibs.fp.mylib.InputDati;

public class ComprensorioView {

	//PARTE COMPRENSORIO

	public static String inserisciComprensorio() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome del Comprensorio geografico\n");
	}

	public static void msgComprensorioGiaPresente() {
		System.out.println("Esiste già un Comprensorio con questo nome");
	}

	public static void visualizzaNomeComprensorio(String nome) {
		System.out.println("\nNome Comprensorio: " + nome);
	}
	
	public static void msgComprensorioNonEsistente() {
		System.out.println("Il Comprensorio inserito non compare nell'elenco");
	}

	//PARTE COMUNE DEL COMPRENSORIO

	public static String inserisciComune() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome del Comune\n");
	}

	public static boolean inserisciAltroComune() {
		return InputDati.yesOrNo("Vuoi inserire un altro Comune?\n");
	}

	public static void msgComuneGiaPresente() {
		System.out.println("Il Comune inserito è già presente in questo Comprensorio");
	}

	public static void visualizzaNomeComune(String nome) {
		System.out.println("Nome Comune: " + nome);
	}
}