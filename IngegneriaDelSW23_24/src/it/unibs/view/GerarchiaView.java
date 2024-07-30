package it.unibs.view;

import it.unibs.fp.mylib.InputDati;

public class GerarchiaView {

	//PARTE CATEGORIA RADICE DELLA GERARCHIA

	public static String inserisciNomeRadiceGerarchia() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della Categoria Radice della Gerarchia\n");
	}

	public static void visualizzaNomeRadiceGerarchia(String nomeRadice) {
		System.out.println("\nRadice Gerarchia: " + nomeRadice);
	}

	public static void visualizzaNomeFiglioCategoria(String nomePadre, String nomeFiglio, String tipo) {
		System.out.println("Figlio di " + nomePadre + ": " + nomeFiglio + " [" + tipo + "]");
	}

	public static void msgRadiceGiaPresente() {
		System.out.println("Esiste già una Categoria Radice con questo nome");
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

	//PARTE CATEGORIA

	/*public static void visualizzaNomeFiglioCategoria(Categoria categoria, Gerarchia gerarchia) {
		System.out.println("Figlio di " + gerarchia.getRadice().getNome() + ": " + categoria.getNome());
	}*/

	public static boolean richiestaContinuazioneStruttura() {
		return InputDati.yesOrNo("Desideri aggiungere un altro figlio alla Gerarchia?\n");
	}

	public static boolean richiestaVerificaCategoriaFoglia() {
		return InputDati.yesOrNo("Desideri aggiungere una Categoria Foglia?\n");
	}

	public static String inserisciNomeFogliaGerarchia() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della Categoria Foglia\n");
	}	

	public static String inserisciNomeNonFogliaGerarchia() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della Categoria Non Foglia\n");
	}

	public static boolean richiestaAggiuntaCategoriaNonFoglia(String dominioNome) {
		return InputDati.yesOrNo("Desideri aggiungere una Categoria Non Foglia a " + dominioNome + "\n");
	}

	public static boolean richiestaAggiuntaCategoriaFoglia(String dominioNome) {
		return InputDati.yesOrNo("Desideri aggiungere una Categoria Foglia a " + dominioNome + "\n");
	}

	public static String inserisciNomeFogliaRicerca() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della Categoria Foglia che desideri selezionare\n");
	}	

	public static String inserisciNomeRadiceRicerca() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della Categoria Radice che desideri selezionare\n");
	}

	public static void fogliaNonTrovata() {
		System.out.println("La Categoria Foglia selezionata non esiste");
	}

	public static void introduzioneElencoGerarchie() {
		System.out.println("Elenco delle gerarchie disponibili:");	
	}

	public static void radiceNonEsiste() {
		System.out.println("La radice selezionata non esiste");
	}
	
	public static void presentazioneValoriDiCampo(String nomeCampo) {
		System.out.println("I Valori disponibili relativi al campo " + nomeCampo + " sono:");
	}

	public static void visualizzaNomeValore(String valore, String nomeCategoria, String tipo) {
		System.out.println("Valore: "+ valore + ", Associato a: " + nomeCategoria + " [" + tipo + "]");
	}
	
	public static void visualizzaNomeValoreSenzaCategoria(String valore) {
		System.out.println("Valore: " + valore + ", Non associato a nessuna Categoria");
		
	}
	
	public static String inserisciValoreScelto() {
		return InputDati.leggiStringaNonVuota("Inserisci il valore che desideri selezionare");
	}

	public static void valoreNonTrovato() {
		System.out.println("Il valore selezionato non esiste");
	}

	public static void valoreNonAssociatoACategoria() {
		System.out.println("Questo valore non è associato a nessuna categoria");
	}

	public static void visualizzaCategoria(String nome, String tipo) {
		System.out.println("Categoria: " + nome + " di tipo " + tipo);
	}

	public static void gerarchieAssenti() {
		System.out.println("Non sono state create Gerarchie dai Configuratori");
	}
}