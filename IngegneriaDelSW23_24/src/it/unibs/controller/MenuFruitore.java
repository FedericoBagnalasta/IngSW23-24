package it.unibs.controller;

import it.unibs.fp.mylib.MyMenu;
import it.unibs.model.GestioneGeneraleXML;
import it.unibs.model.Utente;

public class MenuFruitore {
	
	private static final String NAVIGA_GERARCHIA = "Scegli una Gerarchia ed esplorala";
	private static final String PROPONI_SCAMBIO = "Formula una proposta di scambio";
	private static final String RITIRA_SCAMBIO = "Ritira uno scambio tra quelli che hai creato";
	private static final String VISUALIZZA_SCAMBI = "Visualizza tutti gli scambi di cui sei l'autore";
	private static final String SALVA_SU_XML = "Salva i dati sul file xml";
	private static final String SCELTA = "Scegli l'opzione";
	
	public static void menuFruitore(GerarchiaController gerarchia, Utente utente) {
		String[] voci = {NAVIGA_GERARCHIA, PROPONI_SCAMBIO, RITIRA_SCAMBIO, VISUALIZZA_SCAMBI, SALVA_SU_XML};
		int scelta = 0;
		MyMenu menu = new MyMenu(SCELTA, voci);

		do {
			scelta = menu.scegli();
			switch(scelta) {
			case 1:
				gerarchia.navigaGerarchiaFinoAFoglia();
				break;
			case 2:
				ScambioController.creaScambio(utente);
				break;
			case 3:
				ScambioController.cambiaStatoScambio(utente);
				break;
			case 4:
				ScambioController.visualizzaScambiFruitore(utente);
				break;
			case 5:
				GestioneGeneraleXML.salvataggioCompleto();
				break;
			default:
				break;
			}
		} while(scelta != 0);
	}
}