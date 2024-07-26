package it.unibs.view;

import it.unibs.fp.mylib.*;

public class LoginView {

	public static String inserisciNome() {
		return InputDati.leggiStringaNonVuota("Inserisci un nome\n");
	}

	public static String inserisciPassword() {
		return InputDati.leggiStringaNonVuota("Inserisci una password\n");
	}

	public static void messaggioErrore() {
		System.out.println("Le credenziali inserite non corrispondono a nessun configuratore esistente");
	}

	public static int sceltaRuolo() {
		return InputDati.leggiInteroRange("Inserisci 1 per Configuratore e 2 per Fruitore\n", 1, 2);
	}

	public static boolean confermaScelta() {
		return InputDati.yesOrNo("Sei sicuro della tua scelta?\n");
	}
}