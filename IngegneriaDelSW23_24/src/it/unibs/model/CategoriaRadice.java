package it.unibs.model;

import java.util.ArrayList;

public class CategoriaRadice implements Categoria {

	private String nome;
	
	private String campo;
	private ArrayList<String> dominio = new ArrayList<>();
	
	private ArrayList<Categoria> figli = new ArrayList<>();

	//Ho tolto String valore dai parametri
	public CategoriaRadice(String nome, String campo) {
		super();
		this.nome = nome;
		this.campo = campo;
	}

	public String getNome() {
		return nome;
	}

	public String getCampo() {
		return campo;
	}

	public ArrayList<String> getDominio() {
		return dominio;
	}

	public ArrayList<Categoria> getFigli() {
		return figli;
	}
	
	
}
