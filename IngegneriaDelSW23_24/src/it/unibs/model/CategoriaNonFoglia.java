package it.unibs.model;

import java.util.ArrayList;

public class CategoriaNonFoglia implements Categoria {

	private String nome;
	
	private String campo;
	private ArrayList<String> dominio = new ArrayList<>();
	
	private String valore;	//è un elemento del dominio. Eventuale descrizione => creazione classe

	public CategoriaNonFoglia(String nome, String campo, String valore) {
		super();
		this.nome = nome;
		this.campo = campo;
		this.valore = valore;
	}
	
	
}
