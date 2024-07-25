package it.unibs.model;

import java.util.ArrayList;

import it.unibs.view.ConfiguratoreView;

public class CategoriaNonFoglia implements Categoria {

	private String nome;
	private String campo;
	private ArrayList<ValoreDominio> dominio = new ArrayList<>();
	private ValoreDominio valore;	//è un elemento del dominio. Eventuale descrizione => creazione classe
	private ArrayList<Categoria> figli = new ArrayList<>();
	private CategoriaRadice radice;

	public CategoriaNonFoglia(String nome, String campo, ValoreDominio valore, ArrayList<ValoreDominio> dominio, CategoriaRadice radice) {
		super();
		this.nome = nome;
		this.campo = campo;
		this.valore = valore;
		this.dominio = dominio;
		this.radice = radice;
	}

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

	public ValoreDominio getValore() {
		return valore;
	}

	public ArrayList<Categoria> getFigli() {
		return figli;
	}

	public CategoriaRadice getCategoriaRadice() {
		return radice;
	}
}
