package it.unibs.model;

import java.util.ArrayList;

public class CategoriaFoglia implements Categoria {

	private String nome;
	private ValoreDominio valore;
	private final ArrayList<ValoreDominio> dominio = new ArrayList<ValoreDominio>();
	private ArrayList<FattoreDiConversione> elencoFattoriDiConversione = 
													new ArrayList<FattoreDiConversione>();
	private final ArrayList<Categoria> figli = new ArrayList<>();
	

	public CategoriaFoglia(String nome, ValoreDominio valore) {
		super();
		this.nome = nome;
		this.valore = valore;
	}
	
	public void aggiungiFattoreDiConversione(CategoriaFoglia c, double valore) {
		elencoFattoriDiConversione.add(new FattoreDiConversione(c, valore));
		c.aggiungiFattoreDiConversione(this, 1/valore);//Aggiunto il simmetrico del fattoreDiConversione
		//Ricerca automatica dei FDC ricavabili da quelli esistenti
	}

	public String getNome() {
		return nome;
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

	public ArrayList<FattoreDiConversione> getElencoFattoriDiConversione() {
		return elencoFattoriDiConversione;
	}	
}
