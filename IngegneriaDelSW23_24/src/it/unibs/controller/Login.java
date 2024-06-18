package it.unibs.controller;

import it.unibs.model.*;
import it.unibs.view.*;

public class Login {

	private static final String utentePredefinito = "utenteBase";
	private static final String passwordPredefinita = "passwordBase";
	
	public boolean verificaPrimoAccesso(String nome, String password) {
		if(nome.equals(utentePredefinito) && password.equals(passwordPredefinita))
			return true;
		else return false;
	}
	
	public void login(String nome, String password) {
		//La view restituisce il nome e la password (inseriti dall'utente) che si utilizzano di seguito
		if(verificaPrimoAccesso(nome, password)) {
			cambiaCredenziali();
		}
		
	}
	
	public void cambiaCredenziali() {
		String nome;
		String password;
		String ruolo=LoginView.chiediRuolo();
		
		do {
			//Si richiama un metodo della view che richiede all'utente di inserire le nuove credenziali
			nome=LoginView.chiediNome();//Richiede un nome all'utente e lo restituisce com String(View)
			password=LoginView.chiediPswd();
			
		} while(verificaDuplicati(nome));
		//Crea un oggetto credenziali e lo aggiunge al loro elenco
		Credenziali newCredenziali=new Credenziali(nome, password, ruolo);
		ElencoCredenziali.aggiungiCredenziali(newCredenziali);
	}
	
	public boolean verificaDuplicati(String nome) {
		//Serve un ciclo che controlla il nome di tutte le persone (conf e fruit) che saranno salvati su un arraylist
		//Oppure facciamo un hashmap di tutte le credenziali e in questo caso guardiamo solo il nome (chiave)
		ElencoCredenziali.controllaDuplicati(nome);
		return false;
	}
}
