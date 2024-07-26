package it.unibs.model;

import java.util.ArrayList;

public class ElencoFattoriDiConversione {

	private static ArrayList<FattoreDiConversione> elencoFattoriDiConversione = new ArrayList<>();

	public static void aggiungiFDC(FattoreDiConversione fdC) {
		elencoFattoriDiConversione.add(fdC);
		elencoFattoriDiConversione.add(fdC.creaSimmetrico());
	}

	public static boolean verificaEsistenzaFDC(FattoreDiConversione fdC) {
		for(FattoreDiConversione f : elencoFattoriDiConversione) {
			if(fdC.verificaUguaglianzaFattoriDiConversione(f)) {
				return true;
			}
		}
		return false;
	}

	//Tenere ciclo for non-enhanced per evitare ConcurrentModificationException
	public static void creaFDC_Deducibili(FattoreDiConversione fdcNuovo) {
		FattoreDiConversione fdcDedotto;
		double valore;

		for(int i = 0; i < elencoFattoriDiConversione.size(); i++) {
			//Crea FDC con primo elemento quello del nuovo fdc e secondo quello di tutti quelli che
			//hanno al primo elemento lo stesso del secondo elemeto del fdc nuovo
			// FDCNuovo(c1, C2) fdcvecchio(C2, cx) Risultato: (c1, cx)
			valore = fdcNuovo.getValore() * elencoFattoriDiConversione.get(i).getValore();

			valore = limitaValoreFDC(valore);

			fdcDedotto = new FattoreDiConversione(fdcNuovo.getC1(), 
					elencoFattoriDiConversione.get(i).getC2(), 
					valore);

			//Controlla che non esista già e che non sia un fdc solo su una foglia
			if(elencoFattoriDiConversione.get(i).getC1().verificaUguaglianzaFoglie(fdcNuovo.getC2())
					&& !verificaEsistenzaFDC(fdcDedotto) && !fdcDedotto.getC1().verificaUguaglianzaFoglie(fdcDedotto.getC2())) {
				aggiungiFDC(fdcDedotto);
			}
		}
	}

	private static double limitaValoreFDC(double valore) {
		if(valore < 0.5) {
			valore = 0.5;
		}
		if(valore > 2.0) {
			valore = 2.0;
		}
		return valore;
	}
	
	public static ArrayList<FattoreDiConversione> getElencoFattoriDiConversione() {
		return elencoFattoriDiConversione;
	}
}