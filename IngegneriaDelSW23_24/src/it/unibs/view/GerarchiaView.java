package it.unibs.view;

import it.unibs.fp.mylib.InputDati;

public class GerarchiaView {
	
	public static void visualizzaNomeFiglioCategoria(String nomePadre, String nomeFiglio, String tipo) {
		System.out.println("Figlio di " + nomePadre + ": " + nomeFiglio + " [" + tipo + "]");
	}
	
	public static boolean richiestaContinuazioneStruttura() {
		return InputDati.yesOrNo("Desideri aggiungere un altro figlio alla Gerarchia?\n");
	}
	
	public static void visualizzaCategoria(String nome, String tipo) {
		System.out.println("Categoria: " + nome + " di tipo " + tipo);
	}
	
	public static void msgCategoriaGiaEsistente() {
		System.out.println("Esiste gia' una Categoria con questo nome in questa Gerarchia");
	}

	//PARTE CATEGORIA RADICE DELLA GERARCHIA =====================================================================================

	public static String inserisciNomeRadiceGerarchia() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della Categoria Radice della Gerarchia\n");
	}

	public static void msgRadiceGiaPresente() {
		System.out.println("Esiste gia' una Categoria Radice con questo nome");
	}
	
	public static void visualizzaNomeRadiceGerarchia(String nomeRadice) {
		System.out.println("\nRadice Gerarchia: " + nomeRadice);
	}
	
	public static String inserisciNomeRadiceRicerca() {
		return InputDati.leggiStringaNonVuota("\nInserisci il nome della Categoria Radice che desideri selezionare\n");
	}
	
	public static void introduzioneElencoGerarchie() {
		System.out.println("\nElenco delle gerarchie disponibili:");
	}

	public static void msgRadiceNonEsiste() {
		System.out.println("La radice selezionata non esiste");
	}
	
	public static void msgGerarchieAssenti() {
		System.out.println("Non e' stata creata alcuna Gerarchia dai Configuratori");
	}

	//PARTE CAMPO-DOMINIO DELLA CATEGORIA

	public static String inserisciCampo() {
		return InputDati.leggiStringaNonVuota("Inserisci il Campo della Categoria\n");
	}

	public static String inserisciNomeValoreDominio() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome del Valore del Dominio\n");
	}

	public static void msgEsisteGiaNomeValoreDominio() {
		System.out.println("Esiste gia' un Valore del Dominio con questo nome");
	}

	public static boolean richiestaNomeValoreDominio() {
		return InputDati.yesOrNo("Desideri aggiungere un altro Valore al Dominio?\n");
	}

	public static String inserisciDescrizioneValoreDominio() {
		return InputDati.leggiStringaNonVuota("Inserisci la Descrizione del Valore del Dominio\n");
	}

	public static boolean richiestaDescrizioneValoreDominio() {
		return InputDati.yesOrNo("Desideri aggiungere una Descrizione al Valore del Dominio?\n");
	}
	
	public static void presentazioneValoriDiCampo(String nomeCampo) {
		System.out.println("I Valori disponibili relativi al campo " + nomeCampo + " sono:\n");
	}

	public static void visualizzaNomeValore(String valore, String nomeCategoria, String tipo) {
		System.out.println("Valore: "+ valore + ", Associato a: " + nomeCategoria + " [" + tipo + "]");
	}
	
	public static void visualizzaNomeValoreSenzaCategoria(String valore) {
		System.out.println("Valore: " + valore + ", Non associato a nessuna Categoria");
	}
	
	public static String inserisciValoreScelto() {
		return InputDati.leggiStringaNonVuota("\nInserisci il valore che desideri selezionare\n");
	}

	public static void msgValoreNonTrovato() {
		System.out.println("Il valore selezionato non esiste");
	}

	public static void msgValoreNonAssociatoACategoria() {
		System.out.println("Questo valore non e' associato a nessuna Categoria");
	}

	//PARTE CATEGORIA FOGLIA ======================================================================================================

	public static String inserisciNomeFogliaGerarchia() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della Categoria Foglia\n");
	}
	
	public static boolean richiestaAggiuntaCategoriaFoglia(String dominioNome) {
		return InputDati.yesOrNo("Desideri aggiungere una Categoria Foglia a " + dominioNome + "\n");
	}
	
	public static String inserisciNomeFogliaRicerca() {
		return InputDati.leggiStringaNonVuota("\nInserisci il nome della Categoria Foglia che desideri selezionare\n");
	}
	
	public static void msgFogliaNonTrovata() {
		System.out.println("La Categoria Foglia selezionata non esiste");
	}
	
	//PARTE CATEGORIA NONFOGLIA ====================================================================================================

	public static String inserisciNomeNonFogliaGerarchia() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della Categoria Non Foglia\n");
	}

	public static boolean richiestaAggiuntaCategoriaNonFoglia(String dominioNome) {
		return InputDati.yesOrNo("Desideri aggiungere una Categoria Non Foglia a " + dominioNome + "\n");
	}
}