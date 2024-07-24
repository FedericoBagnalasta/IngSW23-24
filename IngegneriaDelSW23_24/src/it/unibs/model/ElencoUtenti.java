package it.unibs.model;

import java.util.ArrayList;

public class ElencoUtenti {
	
	private static ArrayList<Utente> listaUtenti = new ArrayList<>();
	private static final String utentePredefinito = "utenteBase";
	private static final String passwordPredefinita = "passwordBase";
	
	public static void aggiungiUtente(Utente newCredenziali) {
		listaUtenti.add(newCredenziali);	//Ricordarsi che ciascun utente (Configuratore/Fruitore) è individuato univocamente dal nome
	}
	
	public static boolean isPrimoAccesso(String nome, String password) {
		if(nome.equals(utentePredefinito) && password.equals(passwordPredefinita)) {
			return true;
		}
		return false;
	}
	
	public static boolean isDuplicato(String nome) {
		for(Utente utente : listaUtenti) {
			if(utente.getNome() == nome) {
				return true;
			}
		}
		return false;
	}
	
	public static Utente restituisciUtente(String nome, String password) {
		for(Utente utente : getListaUtenti()) {
			if(utente.getNome().equals(nome) && utente.getPassword().equals(password)) {
				return utente;
			}
		}
		return null;
	}
	
	public static ArrayList<Utente> getListaUtenti() {
		return listaUtenti;
	}
	
	//Metodo per verificare che un FRUITORE non stia facendo login con le credenziali base
	public static boolean erratoUsoCredenzialiBase(String nome, String ruolo) {
		if(nome.equals(utentePredefinito) && ruolo.equals("Fruitore")) {
			return true;
		}
		return false;
	}
}