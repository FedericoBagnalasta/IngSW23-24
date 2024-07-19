package it.unibs.controller;

import it.unibs.model.*;
import it.unibs.view.*;

public class LoginController {
	
	public static Utente login() {
		String ruolo = LoginView.inserisciRuolo();
		String nome = LoginView.inserisciNome();
		String password = LoginView.inserisciPassword();
		Utente utente = LoginModel.creaUtente(nome, password, ruolo);	//Ogni volta che avviene un login, si crea un utente. DA CAMBIARE
		
		//Inserire i 2 login in 2 metodi diversi richimati da questo login generico?
		
		if(ruolo.equals("Configuratore")) {
			boolean isPrimoAccesso = ElencoUtenti.isPrimoAccesso(nome, password);
			if(isPrimoAccesso) {
				utente = cambiaCredenziali();
			}
			else LoginModel.verificaAccount(nome, password);
			
			//fase login configuratore finita. ora servono le opzioni
			
			return utente;
			
		}
		//else ruolo = fruitore
		
		return utente;
	}
	
	//Metodo per cambiare le credenziali del configuratore (classe apposita per il configuratore ?)
	public static Utente cambiaCredenziali() {
		String nome;
		String password;
		String ruolo = "Configuratore";
		
		do {
			nome = LoginView.inserisciNome();
		} while(LoginModel.verificaDuplicati(nome));	//oppure si usa direttamente ElencoUtenti.controllaDuplicati()
		password = LoginView.inserisciPassword();
		
		return LoginModel.creaUtente(nome, ruolo, password);
	}
}