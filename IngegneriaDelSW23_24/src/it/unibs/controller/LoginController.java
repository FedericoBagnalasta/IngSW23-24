package it.unibs.controller;

import it.unibs.model.*;
import it.unibs.view.*;

public class LoginController {
	
	public static void login() {
		String ruolo = LoginView.inserisciRuolo();
		String nome = LoginView.inserisciNome();
		String password = LoginView.inserisciPassword();
		Utente utente/* = LoginModel.creaUtente(nome, password, ruolo)*/;	//Ogni volta che avviene un login, si crea un utente. DA CAMBIARE
		
		//Inserire i 2 login in 2 metodi diversi richimati da questo login generico?
		
		if(ruolo.equals("Configuratore")) {
			boolean isPrimoAccesso = ElencoUtenti.isPrimoAccesso(nome, password);
			
			if(isPrimoAccesso) {
				utente = cambiaCredenziali();
				//cambiaCredenz(utente);
			}
			
			boolean isAccountValido = LoginModel.verificaAccount(nome, password);
			
			if(isAccountValido) {
				
				//fase login configuratore finita. ora servono le opzioni
				
			}
		}
		
		//else ruolo = fruitore
	
	}
	
	public static Utente cambiaCredenziali() {
		String nome;
		String password;
		String ruolo = "Configuratore";
		
		do {
			nome = LoginView.inserisciNome();
		} while(LoginModel.verificaDuplicati(nome));	//oppure si usa direttamente ElencoUtenti.controllaDuplicati()
		password = LoginView.inserisciPassword();
		
		return LoginModel.creaUtente(nome, password, ruolo);
	}
	
	/*
	//Seconda versione del metodo sopra
	public static void cambiaCredenz(Utente utente) {
		String nome;
		String password;
		
		do {
			nome = LoginView.inserisciNome();
		} while(ElencoUtenti.isDuplicato(nome));
		
		utente.setNome(nome);
		password = LoginView.inserisciPassword();
		utente.setPassword(password);
	}
	*/
	
}