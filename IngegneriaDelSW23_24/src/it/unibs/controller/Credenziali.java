package it.unibs.controller;

public class Credenziali {

	private String nome;
	private String password;
	
	public Credenziali(String nome, String password) {
		super();
		this.nome = nome;
		this.password = password;
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
}
