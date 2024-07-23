package it.unibs.view;

import java.util.ArrayList;
import it.unibs.fp.mylib.InputDati;
import it.unibs.model.ValoreDominio;

public class ConfiguratoreView {

	public static String inserisciNomeComprensorio() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome del nuovo comprensorio geografico\n");
	}
		
	public static String inserisciComuniComprensorio() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome comune\n");
	}
	
	public static boolean inserisciAltroComune() {
		return(InputDati.yesOrNo("Vuoi inserire un altro comune?"));
	}
	
	public static void comuneGiaPresente() {
		System.out.println("Il comune inserito è già presente in questo comprensorio");
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

	public static ArrayList<ValoreDominio> inserisciDominio() {
		ArrayList<ValoreDominio> dominio = new ArrayList<>();
		String nome;
		String descrizione = "Assente";
		do {
			nome = InputDati.leggiStringaNonVuota("Inserisci il nome del nuovo elemento del dominio\n");
			if(InputDati.yesOrNo("Desideri aggiungere una descrizione al nuovo elemento?\n")) {
				descrizione = InputDati.leggiStringaNonVuota("Inserisci la descrizione\n");	
			}
			dominio.add(new ValoreDominio(nome, descrizione));
		}while(InputDati.yesOrNo("Desideri aggiungere un altro elemento al dominio?\n"));
		return dominio;
	}

	public static boolean richiestaContinuazioneStruttura() {
		return InputDati.yesOrNo("Desideri aggiungere un altro figlio alla struttura?\n");
	}

	public static boolean richiestaVerificaCategoriaFoglia() {
		return InputDati.yesOrNo("Desideri aggiungere una Categoria Foglia?\n");
	}

	public static String inserisciNomeFoglia() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della nuova Categoria Foglia\n");
	}	
	
	public static String inserisciNomeNonFoglia() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della nuova Categoria Non Foglia\n");
	}
	
	public static boolean richiestaAggiuntaCategoriaNonFoglia(ValoreDominio dominio) {
		return InputDati.yesOrNo("Desideri aggiungere una Categoria Non Foglia a "+dominio.getValore()+"\n");
	}
	
	public static boolean richiestaAggiuntaCategoriaFoglia(ValoreDominio dominio) {
		return InputDati.yesOrNo("Desideri aggiungere una Categoria Foglia a "+dominio.getValore()+"\n");
	}

	public static double inserisciValoreFDC() {
		return InputDati.leggiDoubleRange("Inserisci il valore del fattore di conversione", 0.5, 2.0);
	}

	
}
