package it.unibs.controller;

import java.util.ArrayList;

import it.unibs.model.ElencoComprensori;
import it.unibs.model.ElencoGerarchie;
import it.unibs.model.Utente;
import it.unibs.model.ValoreDominio;
import it.unibs.view.ConfiguratoreView;

	//Ho spostato configuratore in controller perchè i suoi metodi invocano metodi della view(inserisciNomeComprensorio...)

public class ConfiguratoreController extends Utente {//Da tenere extends Utente? 
	
	
	public ConfiguratoreController(String nome, String password, String ruolo) {
		super(nome, password, ruolo);
	}
	
	public void creaGerarchia() {
		String nomeRadice=ConfiguratoreView.inserisciNomeRadiceGerarchia();
		while(!ElencoGerarchie.verificaOriginalita(nomeRadice)) {
			ConfiguratoreView.nomegiaPresente();
			nomeRadice = ConfiguratoreView.inserisciNomeRadiceGerarchia();
		}
		String campo = ConfiguratoreView.inserisciCampo();
		ArrayList<ValoreDominio> dominio = ConfiguratoreView.inserisciDominio();
		ElencoGerarchie.aggiungiGerarchia(nomeRadice, campo, dominio);
	}
	
	public void creaComprensorio() {
		String nomeComprensorio=ConfiguratoreView.inserisciNomeComprensorio();
		while(!ElencoComprensori.verificaOriginalita(nomeComprensorio)) {//Verifica se il nome non è già presente nella lista dei comprensori
			ConfiguratoreView.nomegiaPresente();
			nomeComprensorio=ConfiguratoreView.inserisciNomeComprensorio();
		}
		ArrayList<String> elencoComuni=ConfiguratoreView.inserisciComuniComprensorio();
		ElencoComprensori.aggiungiComprensorio(nomeComprensorio, elencoComuni);
	}
		
}
