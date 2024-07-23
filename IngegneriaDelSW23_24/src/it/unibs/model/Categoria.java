package it.unibs.model;

import java.util.ArrayList;

public interface Categoria {
	
	//Si potrebbe inserire l'attributo nome

	public ValoreDominio getValore();
	
	public ArrayList<ValoreDominio> getDominio();
	
	public ArrayList<Categoria> getFigli();
	
	public String getNome();
	
	public CategoriaRadice getCategoriaRadice();
	
	
	
	default public ArrayList<String> getNomiGerarchia() {
		ArrayList<String> nomiGerarchia = new ArrayList<>();
		for(Categoria c : this.getFigli()) {
			nomiGerarchia.add(c.getNome());
			c.getNomiGerarchia();
		}
		return nomiGerarchia;
	}
}
