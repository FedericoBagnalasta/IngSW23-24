package it.unibs.model;

import java.util.ArrayList;

public class ElencoFattoriDiConversione {
	
	private static ArrayList<FattoreDiConversione> elencoFattoriDiConversione = new ArrayList<>();
	
	public void aggiungiFDC(FattoreDiConversione fdC) {
		elencoFattoriDiConversione.add(fdC);
	}
	
	public static boolean verificaEsistenzaFDC(FattoreDiConversione fdC) {
		for(FattoreDiConversione f: elencoFattoriDiConversione) {
			if(fdC.verificaEsistenzaFattoriDiConversione(f)) {
				return true;
			}
		}
		return false;
	}

}
