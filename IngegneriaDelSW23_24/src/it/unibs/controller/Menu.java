package it.unibs.controller;

import it.unibs.fp.mylib.MyMenu;
import it.unibs.model.GestioneGeneraleXML;

public class Menu {

	private static final String CREA_COMPRENSORIO = "Crea un nuovo comprensorio geografico";
	private static final String CREA_GERARCHIA = "Crea una nuova gerarchia di categorie";
	private static final String VISUALIZZA_COMPRENSORI = "Visualizza tutti i comprensori";
	private static final String VISUALIZZA_GERARCHIE = "Visualizza tutte le gerarchie";
	private static final String VISUALIZZA_FDC = "Visualizza tutti i fattori di conversione di una certa foglia";
	private static final String SALVA_SU_XML = "Salva i dati sul file xml";
	private static final String FORMATTA_XML = "Formatta i file xml";
	private static final String SCELTA = "Scegli l'opzione";
	private static final String NAVIGA_GERARCHIA = "Naviga all'interno di una gerarchia a scelta per trovare una foglia";
	
	public static void menuFruitore(ComprensorioController comprensorioController, GerarchiaController gerarchiaController) {
		String[] vociFruitore = {NAVIGA_GERARCHIA};
		int scelta = 0;
		MyMenu menu = new MyMenu(SCELTA, vociFruitore);

		do {
			scelta = menu.scegli();
			switch(scelta) {
			case 1:
				//METODO PER NAVIGARE IN GERARCHIA
				//gerarchiaController.navigaGerarchiaFinoAFoglia();
				break;
			default:
				break;
			}
		} while(scelta != 0);
	}

	public static void menuConfiguratore(ComprensorioController comprensorioController, GerarchiaController gerarchiaController) {
		String[] vociConfiguratore = {CREA_COMPRENSORIO, CREA_GERARCHIA, VISUALIZZA_COMPRENSORI, 
				VISUALIZZA_GERARCHIE, VISUALIZZA_FDC, SALVA_SU_XML, FORMATTA_XML};
		int scelta = 0;
		MyMenu menu = new MyMenu(SCELTA, vociConfiguratore);

		do {
			scelta = menu.scegli();
			switch(scelta) {
			case 1:
				comprensorioController.creaComprensorio();
				break;
			case 2:
				gerarchiaController.creaGerarchia();
				break;
			case 3:
				ComprensorioController.visualizzaComprensori();
				break;
			case 4:
				GerarchiaController.visualizzaGerarchie();
				break;
			case 5:
				GerarchiaController.visualizzaGerarchie();
				GerarchiaController.visualizzaFattoriDiConversione(
						gerarchiaController.selezionaCategoriaFogliaPerFDC());
				break;
			case 6:
				GestioneGeneraleXML.salvataggioCompleto();
				break;
			case 7:
				GestioneGeneraleXML.formattazioneCompleta();
				break;
			default:
				break;
			}
		} while(scelta != 0);
	}
}