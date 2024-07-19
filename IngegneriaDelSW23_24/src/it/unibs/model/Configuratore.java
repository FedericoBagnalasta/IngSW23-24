package it.unibs.model;

import java.util.ArrayList;

import it.unibs.view.ConfiguratoreView;

public class Configuratore extends Utente {
	
	private static ArrayList<String> listaComprensori = new ArrayList<>();

	public Configuratore(String nome, String password, String ruolo) {
		super(nome, password, ruolo);
	}
	
	public void creaGerarchia() {
		
	}
	
	public void creaComprensorio() {
		boolean risposta;
		do {
			String nuovoComprensorio = ConfiguratoreView.inserisciComprensorio();
			listaComprensori.add(nuovoComprensorio);
			risposta = ConfiguratoreView.rispostaConfiguratore();
		} while(risposta);
		
	}

}
