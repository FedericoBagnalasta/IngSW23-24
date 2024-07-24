package it.unibs.model;

import it.unibs.controller.ConfiguratoreController;
import it.unibs.controller.LoginController;
import it.unibs.controller.Menu;

public class Main {

	public static void main(String[] args) {

		/*
		ConfiguratoreController utente = LoginController.login();
		Menu menu = new Menu();
		menu.menuConfiguratore(utente);
		*/

		LoginController loginController = new LoginController();
		Utente utente = loginController.loginGenerale();
		
		ConfiguratoreController configuratore;
		
		if(utente == null) {
			return;
		}
		
		else if(utente.getRuolo() == "Configuratore") {
			configuratore = new ConfiguratoreController();
			configuratore.setUtente(utente);
			Menu.menu(configuratore);
		}
	}
}