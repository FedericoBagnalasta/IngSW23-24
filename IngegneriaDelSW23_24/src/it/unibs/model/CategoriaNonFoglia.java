package it.unibs.model;

import java.util.ArrayList;

public class CategoriaNonFoglia implements Categoria {

	private String nome;
	
	private String campo;
	private ArrayList<String> dominio = new ArrayList<>();
	
	private String valore;	//è un elemento del dominio. Eventuale descrizione => creazione classe
	
	private ArrayList<Categoria> figli = new ArrayList<>();

	public CategoriaNonFoglia(String nome, String campo, String valore) {
		super();
		this.nome = nome;
		this.campo = campo;
		this.valore = valore;
	}
	
	public CategoriaNonFoglia creaNonFoglia(ArrayList<String> dominioPadre, String nome) {
		
	}

	public ArrayList<String> getDominio() {
		return dominio;
	}

	public void setDominio(ArrayList<String> dominio) {
		this.dominio = dominio;
	}
}
