package it.unibs.controller;

import it.unibs.model.*;
import it.unibs.view.*;

public class LoginController {
	
	private static final String FRUITORE = "Fruitore";
	private static final String CONFIGURATORE = "Configuratore";

	public Utente loginGenerale() {
		Utente utente;
		String ruolo = inserisciRuolo();
		
		do {
			String nome = LoginView.inserisciNome();
			
			while(ElencoUtenti.erratoUsoCredenzialiBase(nome, ruolo)) {
				LoginView.msgErratoUsoCredenziali();
				nome = LoginView.inserisciNome();
			}
			
			String password = LoginView.inserisciPassword();
			
			if(ruolo.equals(CONFIGURATORE)) {
				utente = loginConfiguratore(nome, password);
			}
			else {
				utente = loginFruitore(nome, password);
			}
		} while(utente == null);
		return utente;
	}

	public Utente loginConfiguratore(String nome, String password) {
		Utente utente;
		boolean isPrimoAccesso = ElencoUtenti.isPrimoAccesso(nome, password);

		if(isPrimoAccesso) {
			utente = new Utente(nome, password, CONFIGURATORE);
			cambiaCredenziali(utente);
			ElencoUtenti.aggiungiUtente(utente);
			return utente;
		}
		utente = ElencoUtenti.trovaUtente(nome, password);
		if(utente != null && utente.getRuolo() == CONFIGURATORE) {
			return utente;
		}
		else {
			LoginView.msgConfiguratoreInesistente();
			return null;
		}
	}
	
	public Utente loginFruitore(String nome, String password) {
		Utente utente = ElencoUtenti.trovaUtente(nome, password);
		
		/////////
		if(utente.getRuolo() == CONFIGURATORE) {
			utente = null;
		}
		
		if(utente == null) {
			Comprensorio comprensorio = scegliComprensorio();
			
			String indirizzo = inserisciIndirizzo();
			
			utente = new Utente(nome, password, FRUITORE, comprensorio, indirizzo);
			ElencoUtenti.aggiungiUtente(utente);
		}
		return utente;
	}
	
	public String inserisciIndirizzo() {
		return LoginView.inserisciIndirizzo();
	}
	
	public Comprensorio scegliComprensorio() {
		Comprensorio comprensorio;
		
		ComprensorioController.visualizzaComprensori();
		String nomeComprensorio = ComprensorioView.inserisciComprensorio();
		comprensorio = ComprensorioController.trovaComprensorio(nomeComprensorio);
		
		while(comprensorio == null) {
			ComprensorioView.comprensorioNonEsistente();
			ComprensorioController.visualizzaComprensori();
			nomeComprensorio = ComprensorioView.inserisciComprensorio();
			comprensorio = ComprensorioController.trovaComprensorio(nomeComprensorio);
		}
		return comprensorio;
	}

	public void cambiaCredenziali(Utente utente) {
		String nome;
		String password;
		LoginView.msgCambiamentoCredenziali();

		nome = LoginView.inserisciNome();
		while(ElencoUtenti.isDuplicato(nome)) {
			LoginView.msgConfiguratoreGiaEsistente();
			nome = LoginView.inserisciNome();
		}
		utente.setNome(nome);
		password = LoginView.inserisciPassword();
		utente.setPassword(password);
	}

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