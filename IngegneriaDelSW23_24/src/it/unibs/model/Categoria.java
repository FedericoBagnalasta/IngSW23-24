package it.unibs.model;

import java.util.ArrayList;

public interface Categoria {

	public ValoreDominio getValore();
	
	public ArrayList<ValoreDominio> getDominio();
	
	public ArrayList<Categoria> getFigli();
	
	public CategoriaRadice getCategoriaRadice();
	
}
