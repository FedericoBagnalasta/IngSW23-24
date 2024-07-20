package it.unibs.model;

public class Gerarchia {

	private CategoriaRadice radice;

	public Gerarchia(String nome, String campo) {
		radice=new CategoriaRadice(nome, campo);
	}

	public CategoriaRadice getRadice() {
		return radice;
	}
	
	
}
