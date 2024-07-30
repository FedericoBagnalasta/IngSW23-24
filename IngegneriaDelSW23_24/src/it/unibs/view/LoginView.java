package it.unibs.view;

import it.unibs.fp.mylib.*;

public class LoginView {

	public static String inserisciNome() {
		return InputDati.leggiStringaNonVuota("Inserisci un nome\n");
	}

	public static String inserisciPassword() {
		return InputDati.leggiStringaNonVuota("Inserisci una password\n");
	}

	public static void msgConfiguratoreInesistente() {
		System.out.println("Le credenziali inserite non corrispondono a nessun configuratore esistente");
	}
	
	public static void msgConfiguratoreGiaEsistente() {
		System.out.println("Il nome inserito corrisponde ad un configuratore gia' esistente");
	}

	public static int sceltaRuolo() {
		return InputDati.leggiInteroRange("Inserisci 1 per Configuratore e 2 per Fruitore\n", 1, 2);
	}

	public static boolean confermaScelta() {
		return InputDati.yesOrNo("Sei sicuro della tua scelta?\n");
	}

	public static void msgCambiamentoCredenziali() {
		System.out.println("\nCreazione di credenziali personali:");	
	}
	
	public static String inserisciIndirizzo() {
		return InputDati.leggiStringaNonVuota("Inserisci il tuo indirizzo di posta elettronica\n");
	}
	
	public static void msgErratoUsoCredenziali() {
		System.out.println("Non e' possibile utilizzare questo nome");
	}
}