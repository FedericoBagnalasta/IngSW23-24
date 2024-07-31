package it.unibs.controller;

import it.unibs.fp.mylib.MyMenu;
import it.unibs.model.GestioneGeneraleXML;
import it.unibs.model.Utente;

public class MenuFruitore {
	
	private static final String VISUALIZZA_RADICI = "Visualizza tutte le radici delle gerarchie presenti";
	private static final String NAVIGA_GERARCHIA = "Naviga nella gerarchia selezionata";
	private static final String PROPONI_SCAMBIO = "Formula una proposta di scambio";
	private static final String SALVA_SU_XML = "Salva i dati sul file xml";
	private static final String SCELTA = "Scegli l'opzione";
	
	public static void menuFruitore(ComprensorioController comprensorio, GerarchiaController gerarchia, ScambioController scambio, Utente utente) {
		String[] voci = {VISUALIZZA_RADICI, NAVIGA_GERARCHIA, PROPONI_SCAMBIO, SALVA_SU_XML};
		int scelta = 0;
		MyMenu menu = new MyMenu(SCELTA, voci);

		do {
			scelta = menu.scegli();
			switch(scelta) {
			case 1:
				gerarchia.visualizzaRadici();
				break;
			case 2:
				gerarchia.navigaGerarchiaFinoAFoglia();
				break;
			case 3:
				ScambioController.creaScambio(utente);
				break;
			case 4:
				GestioneGeneraleXML.salvataggioCompleto();
				break;
			default:
				break;
			}
		} while(scelta != 0);
	}
}