package it.unibs.model;

import it.unibs.controller.ComprensorioController;
import it.unibs.controller.GerarchiaController;
import it.unibs.controller.LoginController;
import it.unibs.controller.MenuConfiguratore;
import it.unibs.controller.MenuFruitore;
import it.unibs.controller.ScambioController;

public class Main {

	private static final String CONFIGURATORE = "Configuratore";

	public static void main(String[] args) {
		GestioneGeneraleXML.caricamentoCompleto();
		
		LoginController loginController = new LoginController();
		Utente utente = loginController.loginGenerale();

		ComprensorioController comprensorio = new ComprensorioController();
		GerarchiaController gerarchia = new GerarchiaController();
		ScambioController scambio = new ScambioController();

		if(utente != null) {
			if(utente.getRuolo().equals(CONFIGURATORE)) {
				MenuConfiguratore.menuConfiguratore(comprensorio, gerarchia);
			}
			else {
				MenuFruitore.menuFruitore(comprensorio, gerarchia, scambio, utente);
			}
		}
	}
}