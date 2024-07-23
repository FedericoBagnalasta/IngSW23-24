package it.unibs.model;

import it.unibs.view.FruitoreView;

//SARA' DA SPOSTARE NEL CONTROLLER COME ABBIAMO FATTO PER CONFIGURATORE

public class Fruitore extends Utente {
	
	private Comprensorio comprensorio;

	public Fruitore(String nome, String password, String ruolo) {
		super(nome, password, ruolo);
		this.comprensorio = scegliComprensorio();
	}
	
	public Comprensorio scegliComprensorio() {
		FruitoreView.visualizzaNomiComprensori();
		
		String nomeComprensorio;
		do {
			nomeComprensorio = FruitoreView.inserisciComprensorio();
		} while(!controllaComprensorio(nomeComprensorio));
		
		return recuperaComprensorio(nomeComprensorio);
	}
	
	public boolean controllaComprensorio(String nomeComprensorio) {
		//Il foreach dà errore
		for(int i = 0; i < ElencoComprensori.numeroComprensori(); i++) {
			if(ElencoComprensori.getElencoComprensori().get(i).getNome().equals(nomeComprensorio)) {
				return true;
			}
		}
		return false;
	}
	
	//Probabilmente c'è già un metodo in java che lo fa
	public Comprensorio recuperaComprensorio(String nomeComprensorio) {
		for(Comprensorio c : ElencoComprensori.getElencoComprensori()) {
			if(c.getNome().equals(nomeComprensorio)) {
				return c;
			}
		}
		return null;	//eccezione o messaggio di errore
	}
	
	public Comprensorio getComprensorio() {
		return comprensorio;
	}

	public void setComprensorio(Comprensorio comprensorio) {
		this.comprensorio = comprensorio;
	}
}
