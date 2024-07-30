package it.unibs.controller;

import java.util.ArrayList;

import it.unibs.model.Comprensorio;
import it.unibs.model.ElencoComprensori;
import it.unibs.view.ComprensorioView;

public class ComprensorioController {

	public void creaComprensorio() {
		Comprensorio comprensorio = new Comprensorio(creaNomeComprensorio(), creaComuniComprensorio());
		ElencoComprensori.aggiungiComprensorio(comprensorio);
	}

	public static void visualizzaComprensori() {
		for(Comprensorio comprensorio : ElencoComprensori.getElencoComprensori()) {
			ComprensorioView.visualizzaNomeComprensorio(comprensorio.getNome());
			for(String comune : comprensorio.getComuniComprensorio()) {
				ComprensorioView.visualizzaNomeComune(comune);
			}
		}
	}

	public String creaNomeComprensorio() {
		String nomeComprensorio = ComprensorioView.inserisciNomeComprensorio();

		while(ElencoComprensori.verificaEsistenzaComprensorio(nomeComprensorio)) {
			ComprensorioView.comuneGiaPresente();
			nomeComprensorio = ComprensorioView.inserisciNomeComprensorio();
		}

		return nomeComprensorio;
	}

	public ArrayList<String> creaComuniComprensorio() {
		ArrayList<String> elencoComuni = new ArrayList<>();

		do {
			String nuovoComune = ComprensorioView.inserisciComune();

			if(elencoComuni.contains(nuovoComune)) {
				ComprensorioView.comuneGiaPresente();
			}
			else elencoComuni.add(nuovoComune);
		} while(ComprensorioView.inserisciAltroComune());

		return elencoComuni;
	}


	public Comprensorio scegliComprensorio() {
		//FruitoreView.visualizzaNomiComprensori();	DA CAMBIARE

		String nomeComprensorio;
		do {
			nomeComprensorio = ComprensorioView.inserisciNomeComprensorio();
		} while(!verificaEsistenzaComprensorio(nomeComprensorio));

		return recuperaComprensorio(nomeComprensorio);
	}

	public boolean verificaEsistenzaComprensorio(String nomeComprensorio) {
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
}