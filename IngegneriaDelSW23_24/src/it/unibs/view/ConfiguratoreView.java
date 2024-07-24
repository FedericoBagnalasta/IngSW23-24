package it.unibs.view;

import it.unibs.fp.mylib.InputDati;
import it.unibs.model.Categoria;
import it.unibs.model.FattoreDiConversione;
import it.unibs.model.Gerarchia;
import it.unibs.model.ValoreDominio;

public class ConfiguratoreView {
	
	//PARTE COMPRENSORIO

	public static String inserisciNomeComprensorio() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome del nuovo comprensorio geografico\n");
	}
		
	public static String inserisciComuniComprensorio() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome comune\n");
	}
	
	public static boolean inserisciAltroComune() {
		return InputDati.yesOrNo("Vuoi inserire un altro comune?");
	}
	
	public static void comuneGiaPresente() {
		System.out.println("Il comune inserito è già presente in questo comprensorio\n");
	}
	
	public static void visualizzaNomeComprensorio(String nome) {
		System.out.println("\nNome comprensorio: " + nome);
	}
	
	public static void visualizzaNomeComune(String nome) {
		System.out.println("Nome comune: " + nome);
	}
	
	//PARTE GERARCHIA
	
	public static String inserisciNomeRadiceGerarchia() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della radice appartenente alla nuova gerarchia\n");
	}
	
	public static void visualizzaNomeRadiceGerarchia(Gerarchia gerarchia) {
		System.out.println("\nRadice gerarchia: " + gerarchia.getRadice().getNome());
	}
	
	public static void visualizzaNomeFiglioCategoria(Categoria categoria, Gerarchia gerarchia) {
		System.out.println("Figlio di " + gerarchia.getRadice().getNome() + ": " + categoria.getNome());
	}
	
	public static String inserisciCampo() {
		return InputDati.leggiStringaNonVuota("Inserisci il campo della categoria\n");
	}
	
	public static void radiceGiaPresente() {
		System.out.println("Esiste già una radice con questo nome\n");
	}

	public static String inserisciNomeValoreDominio() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome del valore del dominio\n");
	}
	
	public static boolean richiestaNomeValoreDominio() {
		return InputDati.yesOrNo("Desideri aggiungere un altro valore al dominio?");
	}
	
	public static String inserisciDescrizioneValoreDominio() {
		return InputDati.leggiStringaNonVuota("Inserisci la descrizione del valore del dominio\n");
	}
	
	public static boolean richiestaDescrizioneValoreDominio() {
		return InputDati.yesOrNo("Desideri aggiungere una descrizione al valore del dominio?");
	}

	public static boolean richiestaContinuazioneStruttura() {
		return InputDati.yesOrNo("Desideri aggiungere un altro figlio alla struttura?");
	}

	public static boolean richiestaVerificaCategoriaFoglia() {
		return InputDati.yesOrNo("Desideri aggiungere una Categoria Foglia?");
	}

	public static String inserisciNomeFoglia() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della nuova Categoria Foglia\n");
	}	
	
	public static String inserisciNomeNonFoglia() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della nuova Categoria Non Foglia\n");
	}
	
	public static boolean richiestaAggiuntaCategoriaNonFoglia(ValoreDominio dominio) {
		return InputDati.yesOrNo("Desideri aggiungere una Categoria Non Foglia a " + dominio.getValore() + "\n");
	}
	
	public static boolean richiestaAggiuntaCategoriaFoglia(ValoreDominio dominio) {
		return InputDati.yesOrNo("Desideri aggiungere una Categoria Foglia a " + dominio.getValore() + "\n");
	}
	
	public static String inserisciNomeFogliaRicerca() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della Categoria Foglia che desideri selezionare\n");
	}	
	
	public static String inserisciNomeRadiceRicerca() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della Categoria Radice che desideri selezionare\n");
	}
	
	public static void fogliaNonTrovata() {
		System.out.println("Attenzione: La foglia che hai selezionato non esiste!");
	}
	
	//PARTE FATTORI DI CONVERSIONE
	
	public static double inserisciValoreFDC() {
		return InputDati.leggiDoubleRange("Inserisci il valore del fattore di conversione\n", 0.5, 2.0);
	}
	
	public static void visualizzaFattoreDiConversione(FattoreDiConversione fattore) {
		System.out.println("Il fattore di conversione tra " + fattore.getC1() + "e " + fattore.getC2() + "vale: " + fattore.getValore());
	}
	
	public static String inserisciFogliaPerFDC() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della foglia di cui vuoi conoscere tutti i fattori di conversione\n");
	}
	
	public static String inserisciRadicePerFDC() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della radice della gerarchia a cui appartiene la foglia\n");
	}

	
}