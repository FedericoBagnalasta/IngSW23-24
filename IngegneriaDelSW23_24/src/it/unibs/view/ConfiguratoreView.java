package it.unibs.view;

import it.unibs.fp.mylib.InputDati;
import it.unibs.model.Categoria;
import it.unibs.model.FattoreDiConversione;
import it.unibs.model.Gerarchia;
import it.unibs.model.ValoreDominio;

public class ConfiguratoreView {
	
	//PARTE COMPRENSORIO

	public static String inserisciNomeComprensorio() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome del nuovo Comprensorio\n");
	}
	
	public static void comprensorioGiaPresente() {
		System.out.println("Esiste già un Comprensorio con questo nome");
	}
	
	public static void visualizzaNomeComprensorio(String nome) {
		System.out.println("\nNome Comprensorio: " + nome);
	}
		
	//PARTE COMUNE DEL COMPRENSORIO
	
	public static String inserisciComune() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome del Comune\n");
	}
	
	public static boolean inserisciAltroComune() {
		return InputDati.yesOrNo("Vuoi inserire un altro Comune?\n");
	}
	
	public static void comuneGiaPresente() {
		System.out.println("Il Comune inserito è già presente in questo Comprensorio");
	}
	
	public static void visualizzaNomeComune(String nome) {
		System.out.println("Nome Comune: " + nome);
	}
	
	//PARTE CATEGORIA RADICE DELLA GERARCHIA
	
	public static String inserisciNomeRadiceGerarchia() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della Categoria Radice della Gerarchia\n");
	}
	

	public static void visualizzaNomeRadiceGerarchia(Gerarchia gerarchia) {
		System.out.println("\nRadice Gerarchia: " + gerarchia.getRadice().getNome());
	}

	public static void visualizzaNomeRadiceGerarchia(String nomeRadice) {
		System.out.println("\nRadice gerarchia: " + nomeRadice);
	}
	
	public static void visualizzaNomeFiglioCategoria(String nomePadre, String nomeFiglio, String tipo) {
		System.out.println("Figlio di " + nomePadre + ": " + nomeFiglio + " [" + tipo + "]");
	}
	
	public static void radiceGiaPresente() {
		System.out.println("Esiste già una Categoria Radice con questo nome");
	}
	
	//PARTE CAMPO-DOMINIO DELLA CATEGORIA
	
	public static String inserisciCampo() {
		return InputDati.leggiStringaNonVuota("Inserisci il Campo della Categoria\n");
	}

	public static String inserisciNomeValoreDominio() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome del valore del Dominio\n");
	}
	
	public static boolean richiestaNomeValoreDominio() {
		return InputDati.yesOrNo("Desideri aggiungere un altro valore al Dominio?\n");
	}
	
	public static String inserisciDescrizioneValoreDominio() {
		return InputDati.leggiStringaNonVuota("Inserisci la descrizione del valore del Dominio\n");
	}
	
	public static boolean richiestaDescrizioneValoreDominio() {
		return InputDati.yesOrNo("Desideri aggiungere una descrizione al valore del dominio?\n");
	}
	
	//PARTE CATEGORIA
	
	public static void visualizzaNomeFiglioCategoria(Categoria categoria, Gerarchia gerarchia) {
		System.out.println("Figlio di " + gerarchia.getRadice().getNome() + ": " + categoria.getNome());
	}

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
	
	//PARTE FATTORI DI CONVERSIONE
	
	//Si potrebbero passare gli estremi al metodo in modo da permettere un cambio di range
	public static double inserisciValoreFDC() {
		return InputDati.leggiDoubleRange("Inserisci un valore tra 0.5 e 2.0 per il Fattore di Conversione\n", 0.5, 2.0);
	}
	
	public static void visualizzaFattoreDiConversione(String nomeClasse1, String nomeClasse2, double valore) {
		System.out.println("Il fattore di conversione tra " + nomeClasse1 + " e " + nomeClasse2 + " vale: " + valore);
	}
	
	public static String inserisciFogliaPerFDC() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della Categoria Foglia di cui vuoi conoscere tutti i Fattori di Conversione\n");
	}
	
	public static String inserisciRadicePerFDC() {
		return InputDati.leggiStringaNonVuota("Inserisci il nome della Categoria Radice della Gerarchia a cui appartiene la Foglia\n");
	}

	public static void fogliaDiGerarchiaVecchia(String nomeRadice) {
		System.out.println("Attenzione: Devi inserire il nome di una categoria foglia che abbia come radice " + nomeRadice);
	}

	public static void inserimentoPrimaFoglia() {
		System.out.println("INSERIMENTO PRIMA FOGLIA");
	}

	public static void inserimentoSecondaFoglia() {
		System.out.println("INSERIMENTO SECONDA FOGLIA");
	}
	
	//PARTE FILE XML (forse è il caso di creare un'altra classe)
	
	public static void salvataggioFileXML() {
		System.out.println("Il file e' stato salvato");
	}
}