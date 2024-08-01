package it.unibs.view;

import it.unibs.fp.mylib.InputDati;

public class ScambioView {

	public static void scegliFogliaRichiesta() {
		System.out.println("Scegli la prestazione (Categoria Foglia) a cui sei interessato");
	}
	
	public static int inserisciOreRichiesta() {
		return InputDati.leggiIntero("\nInserisci il numero di ore della prestazione richiesta\n");
	}
	
	public static void scegliFogliaOfferta() {
		System.out.println("Scegli la prestazione (Categoria Foglia) che sei disposto/a ad offrire");
	}
	
	public static void msgStessaFoglia() {
		System.out.println("\nATTENZIONE\nHai selezionato la stessa prestazione per la richiesta e per l'offerta\nSeleziona un'altra prestazione da offrire");
	}
	
	public static void visualizzaScambio(String nomeRichiesta, String nomeOfferta, int oreRichiesta, int oreOfferta) {
		System.out.println("\nRichiesta: [" + nomeRichiesta + ", " + oreRichiesta + " ore]\nOfferta: [" + nomeOfferta + ", " + oreOfferta + " ore]");
	}
	
	public static boolean confermaScambio() {
		return InputDati.yesOrNo("\nVuoi confermare questa proposta di scambio?\n");
	}
	
	public static void msgScambioScartato() {
		System.out.println("Questo scambio e' stato annullato");
	}
	
	public static boolean propostaNuovoScambio() {
		return InputDati.yesOrNo("Vuoi creare un'altra proposta di scambio?\n");
	}
	
	public static void visualizzaScambiFruitore() {
		System.out.println("\nQuesti sono gli scambi che hai creato:");
	}
	
	public static void visualizzaScambiConfiguratore(String nomeFoglia) {
		System.out.println("\nQuesti sono gli scambi che riguardano la foglia " + nomeFoglia + ":");
	}
	
	public static boolean propostaRitiroScambio() {
		return InputDati.yesOrNo("Vuoi ritirare questo scambio?\n");
	}
	
	public static void msgScambioAssente() {
		System.out.println("Non e' ancora stata creata alcuna proposta di Scambio");
	}
	
	public static void msgScambioNonTrovato() {
		System.out.println("\nNon e' stato trovato alcuno Scambio");
	}
}