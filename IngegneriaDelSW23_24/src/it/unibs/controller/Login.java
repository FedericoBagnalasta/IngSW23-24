package it.unibs.controller;

import it.unibs.model.*;
import it.unibs.view.*;

public class Login {

	private String nome;
	private String password;
	private String ruolo;
	/*
	 * Metodo per eseguire il login
	 */
	public void login(String nome, String password) {
		ruolo=LoginView.chiediRuolo();
		//La view restituisce il nome e la password (inseriti dall'utente) che si utilizzano di seguito
		do {
			//Si richiama un metodo della view che richiede all'utente di inserire le nuove credenziali
			nome=LoginView.chiediNome();//Richiede un nome all'utente e lo restituisce com String(View)
			password=LoginView.chiediPswd();
			
		} while(verificaDuplicati(nome,ruolo));
		if(ElencoCredenziali.verificaPrimoAccesso(nome, password)) {//Verifica se sono credenziali predefinite
			cambiaCredenziali();
		}
		
	}
	
	/*
	 * Metodo per impostare le credenziali del configuratore
	 */
	public void cambiaCredenziali() {
		
		
		do {
			//Si richiama un metodo della view che richiede all'utente di inserire le nuove credenziali
			nome=LoginView.chiediNome();//Richiede un nome all'utente e lo restituisce com String(View)
			password=LoginView.chiediPswd();
			
		} while(verificaDuplicati(nome,ruolo));
		//Crea un oggetto credenziali e lo aggiunge al loro elenco
		Credenziali newCredenziali=new Credenziali(nome, password, ruolo);
		ElencoCredenziali.aggiungiCredenziali(newCredenziali);
	}
	
	/*
	 * Metodo per la verifica dell'unicità delle nuove credenziali,
	 * e non utilizzo di credenziali base(anche solo nome) se FRUITORE
	 */
	public boolean verificaDuplicati(String nome,String ruolo) {
		//Serve un ciclo che controlla il nome di tutte le persone (conf e fruit) che saranno salvati su un arraylist
		//Oppure facciamo un hashmap di tutte le credenziali e in questo caso guardiamo solo il nome (chiave)
		if(ElencoCredenziali.erratoUsoCredenzialiBase(nome,ruolo)) {
			return true;
		}
		if(ElencoCredenziali.controllaDuplicati(nome)) {
			return true;
		}
		return false;
	}

	public String getNome() {
		return nome;
	}

	public String getPassword() {
		return password;
	}
	
	
}
