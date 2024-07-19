package it.unibs.model;

import it.unibs.controller.LoginController;
import it.unibs.controller.Menu;

public class Main {

	public static void main(String[] args) {
		Utente utente = LoginController.login();
		
		Menu.menu(utente);
		
	}
}