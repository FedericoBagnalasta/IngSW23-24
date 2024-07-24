package it.unibs.view;

import it.unibs.fp.mylib.*;

public class LoginView {
	
	public static String inserisciRuolo() {
		int ruolo;
		boolean risposta;
		
		do {
			ruolo = InputDati.leggiInteroRange("Inserisci 1 per Configuratore e 2 per Fruitore\n", 1, 2);
			risposta = InputDati.yesOrNo("Sei sicuro della tua scelta?\n");
		} while(!risposta);
		
		if(ruolo == 1) {
			return "Configuratore";
		}
		return "Fruitore"; 
	}
	
	public static String inserisciNome() {
		return InputDati.leggiStringaNonVuota("Inserisci un nome\n");
	}
	
	public static String inserisciPassword() {
		return InputDati.leggiStringaNonVuota("Inserisci una password\n");
	}
	
	public static void messaggioErrore() {
		System.out.println("Le credenziali inserite non corrispondono a nessun configuratore esistente");
	}
}