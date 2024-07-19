package it.unibs.model;

public class Fruitore extends Utente {
	
	private String comprensorio;

	public Fruitore(String nome, String password, String ruolo, String comprensorio) {
		super(nome, password, ruolo);
		this.comprensorio = comprensorio;
	}
	
	

	public String getComprensorio() {
		return comprensorio;
	}

	public void setComprensorio(String comprensorio) {
		this.comprensorio = comprensorio;
	}
}
