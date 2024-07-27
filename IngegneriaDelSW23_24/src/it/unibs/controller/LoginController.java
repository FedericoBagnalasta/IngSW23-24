package it.unibs.controller;

import it.unibs.model.*;
import it.unibs.view.*;

public class LoginController {
	
	private static final String FRUITORE = "Fruitore";
	private static final String CONFIGURATORE = "Configuratore";

	public Utente loginGenerale() {
		Utente utente;
		do {
			String nome = LoginView.inserisciNome();
			String password = LoginView.inserisciPassword();

			utente = loginConfiguratore(nome, password);

		} while(utente == null);
		return utente;
	}

	//Si potrebbe distinguere tra accesso e registrazione
	public Utente loginConfiguratore(String nome, String password) {
		boolean isPrimoAccesso = ElencoUtenti.isPrimoAccesso(nome, password);
		Utente utente;

		if(isPrimoAccesso) {
			utente = new Utente(nome, password, CONFIGURATORE);
			cambiaCredenziali(utente);
			ElencoUtenti.aggiungiUtente(utente);
			return utente;
		}
		utente = ElencoUtenti.trovaUtente(nome, password);
		if(utente != null) {
			return utente;
		}
		else {
			LoginView.msgConfiguratoreInesistente();
			return null;
		}
	}

	public void cambiaCredenziali(Utente utente) {
		String nome;
		String password;
		LoginView.faseCambiamentoCredenziali();

		nome = LoginView.inserisciNome();
		while(ElencoUtenti.isDuplicato(nome)) {
			LoginView.msgConfiguratoreGiaEsistente();
			nome = LoginView.inserisciNome();
		}
		
		utente.setNome(nome);
		password = LoginView.inserisciPassword();
		utente.setPassword(password);
	}

	//Nella prima versione non è usato
	public static String inserisciRuolo() {
		int ruolo;
		boolean risposta;

		do {
			ruolo = LoginView.sceltaRuolo();
			risposta = LoginView.confermaScelta();
		} while(!risposta);

		if(ruolo == 1) {
			return CONFIGURATORE;
		}
		return FRUITORE; 
	}
}