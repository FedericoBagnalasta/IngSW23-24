package it.unibs.model;

import it.unibs.controller.ConfiguratoreController;
import it.unibs.controller.LoginController;
import it.unibs.controller.Menu;

public class Main {

	public static void main(String[] args) {
		GestioneGeneraleXML.caricamentoCompleto();
		LoginController loginController = new LoginController();
		Utente utente = loginController.loginGenerale();

		ConfiguratoreController configuratore;

		if(utente.getRuolo().equals("Configuratore")) {
			configuratore = new ConfiguratoreController();
			configuratore.setUtente(utente);
			Menu.menu(configuratore);
		}
	}
}