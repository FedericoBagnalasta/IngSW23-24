package it.unibs.model;

import java.util.ArrayList;
import it.unibs.controller.Utente;

public class ElencoUtenti {
	
	private static ArrayList<Utente> listaUtente = new ArrayList<>();
	private static final String utentePredefinito = "utenteBase";
	private static final String passwordPredefinita = "passwordBase";
	private static final Utente credenzialiBase = new Utente(utentePredefinito, passwordPredefinita, "Configuratore");
	
	public ElencoUtenti() {
		listaUtente.add(credenzialiBase);
	}
	
	public static void aggiungiUtente(Utente newCredenziali) {
		listaUtente.add(newCredenziali);
	}
	
	public static boolean isPrimoAccesso(String nome, String password) {
		if(nome.equals(utentePredefinito) && password.equals(passwordPredefinita))
			return true;
		else return false;
	}
	
	//Metodo per verificare che un FRUITORE non stia facendo login con le credenziali base
	public static boolean erratoUsoCredenzialiBase(String nome, String ruolo) {
		if (utentePredefinito.equals(nome) && "Fruitore".equals(ruolo)) {
			return true;
		}
		return false;
	}
	
	public static boolean isDuplicato(String nome) {
		for(Utente utente : listaUtente) {
			if(utente.getNome() == nome) {
				return true;
			}
		}
		return false; 
	}

	public static ArrayList<Utente> getListaCredenziali() {
		return listaUtente;
	}
}