package it.unibs.controller;

import it.unibs.fp.mylib.MyMenu;
import it.unibs.model.GestioneGeneraleXML;

public class MenuFruitore {
	
	private static final String VISUALIZZA_GERARCHIE = "Visualizza tutte le gerarchie";
	private static final String NAVIGA_GERARCHIA = "Naviga nella gerarchia selezionata";
	private static final String SALVA_SU_XML = "Salva i dati sul file xml";
	private static final String SCELTA = "Scegli l'opzione";
	
	public static void menuFruitore(ComprensorioController comprensorio, GerarchiaController gerarchia) {
		String[] voci = {VISUALIZZA_GERARCHIE, NAVIGA_GERARCHIA, SALVA_SU_XML};
		int scelta = 0;
		MyMenu menu = new MyMenu(SCELTA, voci);

		do {
			scelta = menu.scegli();
			switch(scelta) {
			case 1:
				//visualizza
				gerarchia.visualizzaRadici();
				break;
			case 2:
				//naviga
				gerarchia.navigaGerarchiaFinoAFoglia();
				break;
			case 3:
				GestioneGeneraleXML.salvataggioCompleto();
				break;
			default:
				break;
			}
		} while(scelta != 0);
	}
}