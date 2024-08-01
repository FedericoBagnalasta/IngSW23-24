package it.unibs.model;

import java.util.ArrayList;

public class ElencoScambi {

	private static ArrayList<Scambio> elencoScambi = new ArrayList<>();
	
	public static void aggiungiScambio(Scambio scambio) {
		elencoScambi.add(scambio);
	}
	
	public static Scambio trovaScambioComplementare(Scambio scambio) {
		for(Scambio scambioSalvato : elencoScambi) {
			if(scambio.verificaUguaglianzaScambio(scambio)) {
				return scambioSalvato;
			}
		}
		return null;
	}

	public static ArrayList<Scambio> getElencoScambi() {
		return elencoScambi;
	}
}