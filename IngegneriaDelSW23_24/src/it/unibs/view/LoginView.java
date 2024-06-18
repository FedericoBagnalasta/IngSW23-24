package it.unibs.view;

import it.unibs.fp.mylib.*;

public class LoginView {

	public static String chiediNome() {
		return InputDati.leggiStringa("Inserisci un nome");
	}
	
	public static String chiediPswd() {
		return InputDati.leggiStringa("Inserisci una password");
	}
	
	public static String chiediRuolo() {
		int ruolo;
		boolean risposta;
		do {
			ruolo=InputDati.leggiInteroRange("Inserisci 1 per Configuratore e 2 per Fruitore",1,2);
			risposta=InputDati.yesOrNo("Sei sicuro della tua scelta?");
		}while(!risposta);
		if(ruolo==1) {
			return "Configuratore";
		}
		return "Fruitore"; 
	}
}
