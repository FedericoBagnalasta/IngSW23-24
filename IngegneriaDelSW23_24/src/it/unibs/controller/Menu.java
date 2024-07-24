package it.unibs.controller;

import it.unibs.fp.mylib.MyMenu;
import it.unibs.model.CategoriaFoglia;
import it.unibs.model.ElencoGerarchie;
import it.unibs.model.XMLWriter;
import it.unibs.view.ConfiguratoreView;

public class Menu {
	
	private static final String CREA_COMPRENSORIO = "Crea un nuovo comprensorio geografico";
	private static final String CREA_GERARCHIA = "Crea una nuova gerarchia di categorie";	//racchiude i compiti 2-3-4 del configuratore???
	private static final String SALVA_SU_XML = "Salva i dati su file xml";
	private static final String VISUALIZZA_COMPRENSORI = "Visualiza tutti i comprensori";
	private static final String VISUALIZZA_GERARCHIE = "Visualizza tutte le gerarchie";
	private static final String VISUALIZZA_FDC = "Visualizza tutti i fattori di conversione di una certa foglia";
	
	private static final String SCELTA = "Scegli l'opzione";
	

	public static void menu(ConfiguratoreController configuratore) {
		String[] vociConfiguratore = {CREA_COMPRENSORIO, CREA_GERARCHIA, SALVA_SU_XML, VISUALIZZA_COMPRENSORI, VISUALIZZA_GERARCHIE, VISUALIZZA_FDC};
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
					XMLWriter.salvataggioCompleto("/Users/matteokovacic/git/IngSW23-24/IngegneriaDelSW23_24/NewFile.xml");
					break;
				case 4:
					ConfiguratoreController.visualizzaComprensori();
					break;
				case 5:
					ConfiguratoreController.visualizzaGerarchie();
					break;
				case 6:
					ConfiguratoreController.visualizzaGerarchie();
					String nomeFoglia = ConfiguratoreView.inserisciFogliaPerFDC();
					String nomeRadice = ConfiguratoreView.inserisciRadicePerFDC();
					CategoriaFoglia foglia = ElencoGerarchie.selezionaFoglia(nomeFoglia, nomeRadice);
					ConfiguratoreController.visualizzaFattoriDiConversione(foglia);
					break;
				default:
					break;
			}
		} while(scelta != 0);
	}
}