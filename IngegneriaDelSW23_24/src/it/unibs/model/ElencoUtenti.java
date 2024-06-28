package it.unibs.model;

import java.util.ArrayList;
import it.unibs.controller.Utente;

public class ElencoUtenti {
	
	private static ArrayList<Utente> listaCredenziali=new ArrayList<>();
	private static final String utentePredefinito = "utenteBase";
	private static final String passwordPredefinita = "passwordBase";
	private static final Utente credenzialiBase=new Utente(utentePredefinito, passwordPredefinita, "Configuratore");
	
	public ElencoUtenti() {
		super();
		listaCredenziali.add(credenzialiBase);
	}
	
	public static void aggiungiUtenti(Utente newCredenziali) {
		listaCredenziali.add(newCredenziali);
	}
	
	public static boolean verificaPrimoAccesso(String nome, String password) {
		if(nome.equals(utentePredefinito) && password.equals(passwordPredefinita))
			return true;
		else return false;
	}
	
	/*
	 * Metodo per verificare che un FRUITORE non stia facendo login con le credenziali base
	 */
	public static boolean erratoUsoCredenzialiBase(String nome, String ruolo) {
		if (utentePredefinito.equals(nome)&&"Fruitore".equals(ruolo)) {
			return true;
		}
		return false;
	}
	
	public static boolean controllaDuplicati(String nome) {
		for(Utente credenziali: listaCredenziali) {
			if(credenziali.getNome()==nome) {
				return true;
			}
		}
		return false; 
	}

}
