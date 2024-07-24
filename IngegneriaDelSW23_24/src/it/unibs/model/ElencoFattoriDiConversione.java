package it.unibs.model;

import java.util.ArrayList;

public class ElencoFattoriDiConversione {
	
	private static ArrayList<FattoreDiConversione> elencoFattoriDiConversione = new ArrayList<>();
	
	public static void aggiungiFDC(FattoreDiConversione fdC) {
		elencoFattoriDiConversione.add(fdC);
		elencoFattoriDiConversione.add(fdC.creaSimmetrico());
	}
	
	public static boolean verificaEsistenzaFDC(FattoreDiConversione fdC) {
		for(FattoreDiConversione f: elencoFattoriDiConversione) {
			if(fdC.verificaUguaglianzaFattoriDiConversione(f)) {
				return true;
			}
		}
		return false;
	}

	public static ArrayList<FattoreDiConversione> getElencoFattoriDiConversione() {
		return elencoFattoriDiConversione;
	}
	
	public static void creaFDC_Deducibili(FattoreDiConversione fdcNuovo) {
		elencoFattoriDiConversione.add(fdcNuovo.creaSimmetrico());	//PRIMA CREO E AGGIUNGO IL SIMMETRICO
		for(FattoreDiConversione fdc: elencoFattoriDiConversione) {
			if(fdc.getC1().verificaUguaglianzaFoglie(fdcNuovo.getC2())) {
				aggiungiFDC(new FattoreDiConversione(fdcNuovo.getC1(), fdc.getC2(), fdcNuovo.getValore()*fdc.getValore()));	
			}
		}
	}
}
