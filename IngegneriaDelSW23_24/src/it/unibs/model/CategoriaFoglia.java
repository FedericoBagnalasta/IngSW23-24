package it.unibs.model;

import java.util.HashMap;

public class CategoriaFoglia implements Categoria {

	private String nome;
	
	private String valore;
	
	private HashMap<String, Double> fattori = new HashMap<>();

	public CategoriaFoglia(String nome, String valore) {
		super();
		this.nome = nome;
		this.valore = valore;
	}
}
