package it.unibs.view;

import it.unibs.fp.mylib.*;

public class LoginView {

	public static String inserisciNome() {
		return InputDati.leggiStringa("Inserisci un nome\n");
	}
	
	public static String inserisciPassword() {
		return InputDati.leggiStringa("Inserisci una password\n");
	}
	
	public static String inserisciRuolo() {
		int ruolo;
		boolean risposta;
		
		do {
			ruolo = InputDati.leggiInteroRange("Inserisci 1 per Configuratore e 2 per Fruitore\n",1,2);
			risposta = InputDati.yesOrNo("Sei sicuro della tua scelta?");
		} while(!risposta);
		
		if(ruolo == 1) {
			return "Configuratore";
		}
		return "Fruitore"; 
	}
}