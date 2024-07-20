package it.unibs.view;

import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;

public class ConfiguratoreView {

	//Non sono sicuro sia static
	public static String inserisciNomeComprensorio() {
		return InputDati.leggiStringaNonVuota("Inserisci un nuovo comprensorio geografico\n");
	}
	
	public static ArrayList<String> inserisciComuniComprensorio() {
		ArrayList<String> comuni = new ArrayList<>();
		InputDati.leggiStringaNonVuota("Inserisci i comuni appartententi al nuovo comprensorio\n");
		while(InputDati.yesOrNo("Vuoi aggiungere un altro comune?\n")) {
			comuni.add(InputDati.leggiStringaNonVuota("Inserisci il nome del comune\n"));
		}
		return comuni;
	}
	
	//Manco questo
	public static boolean rispostaConfiguratore() {
		return InputDati.yesOrNo("Vuoi inserire un nuovo comprensorio geografico?");
	}
	
	public static String inserisciNomeRadiceGerarchia() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della radice appartenente alla nuova gerarchia\n");
	}
	
	public static String inserisciCampo() {
		return InputDati.leggiStringaNonVuota("Inserisci il campo della categoria\n");
	}
	
	public static void nomegiaPresente() {
		System.out.println("Il nome che hai scelto e' gia' presente\n");
	}
}
