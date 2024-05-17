package it.unibs.model;

import java.util.ArrayList;

public class CategoriaRadice implements Categoria {

	private String nome;
	
	private String campo;
	private ArrayList<String> dominio = new ArrayList<>();
	
	private ArrayList<Categoria> figli = new ArrayList<>();

	public CategoriaRadice(String nome, String campo, String valore) {
		super();
		this.nome = nome;
		this.campo = campo;
	}
}
