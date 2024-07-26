package it.unibs.controller;

import it.unibs.fp.mylib.MyMenu;
import it.unibs.model.ClasseXML;

public class Menu {

	private static final String CREA_COMPRENSORIO = "Crea un nuovo comprensorio geografico";
	private static final String CREA_GERARCHIA = "Crea una nuova gerarchia di categorie";
	private static final String VISUALIZZA_COMPRENSORI = "Visualizza tutti i comprensori";
	private static final String VISUALIZZA_GERARCHIE = "Visualizza tutte le gerarchie";
	private static final String VISUALIZZA_FDC = "Visualizza tutti i fattori di conversione di una certa foglia";
	private static final String SALVA_SU_XML = "Salva i dati sul file xml";
	
	private static final String SCELTA = "Scegli l'opzione";
	
	public static void menu(ConfiguratoreController configuratore) {
		String[] vociConfiguratore = {CREA_COMPRENSORIO, CREA_GERARCHIA, VISUALIZZA_COMPRENSORI, VISUALIZZA_GERARCHIE, VISUALIZZA_FDC, SALVA_SU_XML};
		int scelta = 0;
		MyMenu menu = new MyMenu(SCELTA, vociConfiguratore);

		do {
			scelta = menu.scegli();
			switch(scelta) {
			case 1:
				configuratore.creaComprensorio();
				break;
			case 2:
				configuratore.creaGerarchia();
				break;
			case 3:
				ConfiguratoreController.visualizzaComprensori();
				break;
			case 4:
				ConfiguratoreController.visualizzaGerarchie();
				break;
			case 5:
				ConfiguratoreController.visualizzaGerarchie();
				ConfiguratoreController.visualizzaFattoriDiConversione(
						configuratore.selezionaCategoriaFogliaPerFDC());
				break;
			case 6:
				ClasseXML.salvataggioCompleto();
				break;
			default:
				break;
			}
		} while(scelta != 0);
	}
}