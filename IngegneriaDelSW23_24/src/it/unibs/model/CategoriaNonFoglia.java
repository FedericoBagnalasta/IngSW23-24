package it.unibs.model;

import java.util.ArrayList;

public class CategoriaNonFoglia implements Categoria {

	private String nome;
	private String campo;
	private ArrayList<ValoreDominio> dominio = new ArrayList<>();
	private ValoreDominio valoreDominio;	//è un elemento del dominio. Eventuale descrizione => creazione classe
	private ArrayList<Categoria> figli = new ArrayList<>();
	private CategoriaRadice radice;

	public CategoriaNonFoglia(String nome, String campo, ValoreDominio valore, ArrayList<ValoreDominio> dominio, CategoriaRadice radice) {
		super();
		this.nome = nome;
		this.campo = campo;
		this.valoreDominio = valore;
		this.dominio = dominio;
		this.radice = radice;
	}
	
	/*public void aggiungiFiglio(Categoria nuovoFiglio) {
		figli.add(nuovoFiglio);
	}
	*/
	
	
	/*
	public CategoriaNonFoglia creaNonFoglia(ArrayList<String> dominioPadre, String nome) {
		
	}
	*/
	

	public String getNome() {
		return nome;
	}
	
	public String getTipo() {
		return "NonFoglia";
	}

	public String getCampo() {
		return campo;
	}
	
	public ArrayList<ValoreDominio> getDominio() {
		return dominio;
	}

	public ValoreDominio getValoreDominio() {
		return valoreDominio;
	}

	public ArrayList<Categoria> getFigli() {
		return figli;
	}

	public CategoriaRadice getCategoriaRadice() {
		return radice;
	}
}
