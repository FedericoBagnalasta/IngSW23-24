package it.unibs.model;

import java.util.ArrayList;

public class CategoriaRadice implements Categoria {

	private static final String RADICE = "Radice";
	
	private String nome;
	private final ValoreDominio valore = new ValoreDominio("Radice", "Valore dell'elemento radice");
	private final String tipo = RADICE;
	private String campo;
	private ArrayList<ValoreDominio> dominio = new ArrayList<>();
	private ArrayList<Categoria> figli = new ArrayList<>();

	public CategoriaRadice(String nome, String campo, ArrayList<ValoreDominio> dominio) {
		this.nome = nome;
		this.campo = campo;
		this.dominio = dominio;
	}
	
	//Costruttore per caricamento da xml
	public CategoriaRadice(String nome, String campo, ArrayList<ValoreDominio> dominio, ArrayList<Categoria> figli) {
		this.nome = nome;
		this.campo = campo;
		this.dominio = dominio;
		this.figli = figli;
	}

	public void aggiungiFiglio(Categoria nuovoFiglio) {
		figli.add(nuovoFiglio);
	}

	public String getNome() {
		return nome;
	}

	public String getTipo() {
		return tipo;
	}

	public String getCampo() {
		return campo;
	}

	public ValoreDominio getValoreDominio() {
		return valore;
	}

	public ArrayList<ValoreDominio> getDominio() {
		return dominio;
	}

	public ArrayList<Categoria> getFigli() {
		return figli;
	}

	public CategoriaRadice getRadice() {
		return this;
	}
}