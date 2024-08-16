package it.unibs.model;

import java.util.ArrayList;

public interface Categoria {	

	public Categoria selezionaFiglioDalValore(ValoreDominio valoreScelto);

	public ArrayList<String> getNomiGerarchia();
	
	
	//ref parte 2 (polimorfismo, open-closed, composite)
	public int contaFoglieCategoria();

	public String getTipo();

	public ValoreDominio getValoreDominio();

	public ArrayList<ValoreDominio> getDominio();

	public ArrayList<Categoria> getFigli();

	public String getNome();
	
	public CategoriaRadice getRadice();
	
	public String getCampo();
}