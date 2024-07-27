package it.unibs.model;

import java.util.ArrayList;

public class ElencoUtenti {

	private static ArrayList<Utente> elencoUtenti = new ArrayList<>();
	private static final String utentePredefinito = "utente";
	private static final String passwordPredefinita = "password";

	public static void aggiungiUtente(Utente newCredenziali) {
		elencoUtenti.add(newCredenziali);	
	}

	public static boolean isPrimoAccesso(String nome, String password) {
		if(nome.equals(utentePredefinito) && password.equals(passwordPredefinita)) {
			return true;
		}
		return false;
	}

	public static boolean isDuplicato(String nome) {
		for(Utente utente : elencoUtenti) {
			if(utente.getNome().equals(nome) || utentePredefinito.equals(nome)) {
				return true;
			}
		}
		return false;
	}

	public static Utente trovaUtente(String nome, String password) {
		for(Utente utente : getElencoUtenti()) {
			if(utente.getNome().equals(nome) && utente.getPassword().equals(password)) {
				return utente;
			}
		}
		return null;
	}

	public static ArrayList<Utente> getElencoUtenti() {
		return elencoUtenti;
	}

	//Metodo per verificare che un FRUITORE non stia facendo login con le credenziali base
	public static boolean erratoUsoCredenzialiBase(String nome, String ruolo) {
		if(nome.equals(utentePredefinito) && ruolo.equals("Fruitore")) {
			return true;
		}
		return false;
	}
}