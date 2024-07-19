package it.unibs.model;

public class LoginModel {
	
	ElencoUtenti listaUtenti = new ElencoUtenti();

	//Metodo per la verifica dell'unicità delle nuove credenziali
	public static boolean verificaDuplicati(String nome) {
		if(ElencoUtenti.isDuplicato(nome)) {
			return true;
		}
		return false;
	}
	
	public static Utente creaUtente(String nome, String password, String ruolo) {
		Utente newUtente = new Utente(nome, password, ruolo);
		ElencoUtenti.aggiungiUtente(newUtente);
		return newUtente;
	}

	public static boolean verificaAccount(String nome, String password) {
		for(Utente utente : ElencoUtenti.getListaCredenziali()) {
			if(utente.getNome().equals(nome) && utente.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
}