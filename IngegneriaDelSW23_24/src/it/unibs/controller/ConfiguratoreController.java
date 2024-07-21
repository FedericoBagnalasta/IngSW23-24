package it.unibs.controller;

import java.util.ArrayList;

import it.unibs.model.Comprensorio;
import it.unibs.model.ElencoUtenti;
import it.unibs.model.Utente;
import it.unibs.view.ConfiguratoreView;
import it.unibs.view.LoginView;

public class ConfiguratoreController extends Utente {//Da tenere extends Utente? 
	
	public ConfiguratoreController(String nome, String password, String ruolo) {
		super(nome, password, ruolo);
	}
	
	public void creaGerarchia() {
		
	}
	
	public Comprensorio creaComprensorio() {
		String nomeComprensorio = ConfiguratoreView.inserisciNomeComprensorio();
		ArrayList<String> elencoComuni = ConfiguratoreView.inserisciComuniComprensorio();
		return new Comprensorio(nomeComprensorio, elencoComuni);
	}
	
	/*
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
	*/
	
	
	//Versione alternativa del metodo sopra
	
	//Se questa classe eredita da Utente, si riesce a rendere questo metodo non-static
	
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
	
	
}