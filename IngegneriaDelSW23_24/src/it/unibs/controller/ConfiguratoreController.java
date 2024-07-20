package it.unibs.controller;

import java.util.ArrayList;

import it.unibs.model.Comprensorio;
import it.unibs.model.Utente;
import it.unibs.view.ConfiguratoreView;

	//Ho spostato configuratore in controller perchè i suoi metodi invocano metodi della view(inserisciNomeComprensorio...)

public class ConfiguratoreController extends Utente {//Da tenere extends Utente? 
	
	public ConfiguratoreController(String nome, String password, String ruolo) {
		super(nome, password, ruolo);
	}
	
	public void creaGerarchia() {
		
	}
	
	public Comprensorio creaComprensorio() {
		String nomeComprensorio=ConfiguratoreView.inserisciNomeComprensorio();
		ArrayList<String> elencoComuni=ConfiguratoreView.inserisciComuniComprensorio();
		return (new Comprensorio(nomeComprensorio, elencoComuni));
	}
		
}
