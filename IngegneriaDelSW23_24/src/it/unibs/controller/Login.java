package it.unibs.controller;

public class Login {

	private static final String utentePredefinito = "utenteBase";
	private static final String passwordPredefinita = "passwordBase";
	
	public boolean verificaPrimoAccesso(String nome, String password) {
		if(nome.equals(utentePredefinito) && password.equals(passwordPredefinita))
			return true;
		else return false;
	}
	
	public void cambiaCredenziali() {
		do {
			//Si richiama un metodo della view che richiede all'utente di inserire le nuove credenziali
		} while(!verificaUnicita());
		//Crea un oggetto credenziali e lo aggiunge al loro elenco
	}
	
	public void login(String nome, String password) {
		//La view restituisce il nome e la password (inseriti dall'utente) che si utilizzano di seguito
		if(verificaPrimoAccesso(nome, password)) {
			cambiaCredenziali();
		}
		
	}
	
	public boolean verificaUnicita(String nome) {
		//Serve un ciclo che controlla il nome di tutte le persone (conf e fruit) che saranno salvati su un arraylist
		//Oppure facciamo un hashmap di tutte le credenziali e in questo caso guardiamo solo il nome (chiave)
		
		return false;
	}
}
