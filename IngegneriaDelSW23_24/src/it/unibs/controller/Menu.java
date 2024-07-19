package it.unibs.controller;

import it.unibs.fp.mylib.MyMenu;
import it.unibs.model.Utente;

public class Menu {

	//Voci configuratore
	private static final String CREA_COMPRENSORIO = "Crea un nuovo comprensorio geografico";
	private static final String CREA_GERARCHIA = "Crea una nuova gerarchia di categorie";
	
	private static final String SCELTA = "Scegli l'opzione";
	

	public static void menu(Utente utente) {
		
		String[] vociConfiguratore = {CREA_COMPRENSORIO, CREA_GERARCHIA};
		//String[] vociFruitore = {DAI_BISCOTTI, DAI_CAREZZE};
		int scelta = 0;
		MyMenu menu;
		
		if(utente.getRuolo().equals("Configuratore")) {
			menu=new MyMenu(SCELTA, vociConfiguratore);
		}
		//else menu=new MyMenu(SCELTA, vociFruitore);
		System.out.println("Bagna è un coglione");     // DA TOGLIERE. Era una prova
		do {
			//scelta=menu.scegli();
			switch(scelta) {
				case 1:
					
					//utente.riceviBiscotti();
					break;
				case 2:
					//utente.riceviCarezze();
					break;
				default:
					break;
			}
		}while(scelta != 0 /*&& !tamagotchi.sonoMorto()*/);
	}
}