package it.unibs.controller;

public class Credenziali {//Probabilmente in model

	private String nome;
	private String password;
	private String ruolo;//Si potrebbe mettere un boolean al posto
	
	public Credenziali(String nome, String password, String ruolo) {
		super();
		this.nome = nome;
		this.password = password;
		this.ruolo = ruolo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	
}
