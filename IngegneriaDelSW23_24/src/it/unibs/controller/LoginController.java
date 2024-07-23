package it.unibs.controller;

import it.unibs.model.*;
import it.unibs.view.*;

public class LoginController {
		
	public static void loginGenerale() {
		String ruolo = LoginView.inserisciRuolo();
		String nome = LoginView.inserisciNome();
		String password = LoginView.inserisciPassword();
		
		if(ruolo.equals("Configuratore")) {
			loginConfiguratore(nome, password);
		}
		//else loginFruitore();
	}
	
	
	//Si potrebbe distinguere tra accesso e registrazione
	
	public static void loginConfiguratore(String nome, String password) {
		boolean isPrimoAccesso = ElencoUtenti.isPrimoAccesso(nome, password);
		boolean isAccountVerificato = ElencoUtenti.verificaAccount(nome, password);
		if(isPrimoAccesso) {
			Utente utente = new Utente(nome, password, "Configuratore");
			ConfiguratoreController.cambiaCredenziali(utente);
			//opzioni
		}
		else if(isAccountVerificato) {
			//opzioni
		}
		else {
			LoginView.messaggioErrore();
			
			//Se dividiamo registrazione e login possiamo fargli fare una registrazione se il login non va a buon fine
		}
	}
}