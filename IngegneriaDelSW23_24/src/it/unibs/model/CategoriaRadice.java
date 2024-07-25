package it.unibs.model;

import java.util.ArrayList;

public class CategoriaRadice implements Categoria {

	private String nome;
	private String campo;
	private ArrayList<ValoreDominio> dominio = new ArrayList<>();
	private ArrayList<Categoria> figli = new ArrayList<>();
	private CategoriaRadice radice;
	
	private final ValoreDominio valore = new ValoreDominio("Radice", "Valore dell'elemento radice");

	public CategoriaRadice(String nome, String campo, ArrayList<ValoreDominio> dominio) {
		super();
		this.nome = nome;
		this.campo = campo;
		this.dominio = dominio;
		radice = this;
	}
	
	public void aggiungiFiglio(Categoria nuovoFiglio) {
		figli.add(nuovoFiglio);
	}

	public String getNome() {
		return nome;
	}
	
	public String getTipo() {
		return "Radice";
	}

	public String getCampo() {
		return campo;
	}

	public ValoreDominio getValore() {
		return valore;
	}
	
	public ArrayList<ValoreDominio> getDominio() {
		return dominio;
	}
	
	public ArrayList<Categoria> getFigli() {
		return figli;
	}

	public CategoriaRadice getCategoriaRadice() {
		return radice;
	}
}
