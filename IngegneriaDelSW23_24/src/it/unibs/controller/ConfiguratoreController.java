package it.unibs.controller;

import java.util.ArrayList;

import it.unibs.model.Comprensorio;
import it.unibs.model.ElencoComprensori;
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
	
	public String creaNomeComprensorio() {
		String nomeComprensorio;
		do {
			nomeComprensorio = ConfiguratoreView.inserisciNomeComprensorio();
		} while(ElencoComprensori.verificaComprensorio(nomeComprensorio));
		return nomeComprensorio;
	}
	
	public ArrayList<String> creaComuniComprensorio() {
		ArrayList<String> elencoComuni = new ArrayList<>();
		do {
			String comune = ConfiguratoreView.inserisciComuniComprensorio();
			for(String s : elencoComuni) {
				if(s.equals(comune)) {
					ConfiguratoreView.comuneGiaPresente();
					break;
				}
				else {
					elencoComuni.add(comune);
				}
			}
		} while(ConfiguratoreView.inserisciAltroComune());
		
		return elencoComuni;
	}
	
	public Comprensorio creaComprensorio() {
		return new Comprensorio(creaNomeComprensorio(), creaComuniComprensorio());
	}
	
	//Se questa classe eredita da Utente, si riesce a rendere questo metodo non-static
	
	public static void cambiaCredenziali(Utente utente) {
		String nome;
		String password;
		
		do {
			nome = LoginView.inserisciNome();
		} while(ElencoUtenti.isDuplicato(nome));
		
		utente.setNome(nome);
		password = LoginView.inserisciPassword();
		utente.setPassword(password);
	}
	

	/*
	public static Utente cambiaCredenz() {
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
	
	
}