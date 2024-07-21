package it.unibs.controller;

import it.unibs.model.*;
import it.unibs.view.*;

public class LoginController {
	
	/*
	public static void login() {
		String ruolo = LoginView.inserisciRuolo();
		String nome = LoginView.inserisciNome();
		String password = LoginView.inserisciPassword();
		Utente utente;
		
		//Inserire i 2 login in 2 metodi diversi richiamati da questo login generico?
		
		if(ruolo.equals("Configuratore")) {
			boolean isPrimoAccesso = ElencoUtenti.isPrimoAccesso(nome, password);
			
			if(isPrimoAccesso) {
				utente = ConfiguratoreController.cambiaCredenziali();
				//cambiaCredenz(utente);
			}
			
			boolean isAccountValido = LoginModel.verificaAccount(nome, password);
			
			if(isAccountValido) {
				
				//fase login configuratore finita. ora servono le opzioni
				
			}
		}
		
		//else ruolo = fruitore
	
	}
	*/
	
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
			ConfiguratoreController.cambiaCredenz(utente);
			//opzioni
		}
		else if(isAccountVerificato) {
			//opzioni
		}
		else {
			LoginView.messaggioErrore();
			
			//Facciamo un ciclo che gli permetta di inserire altre credenziali??
		}
	}
}