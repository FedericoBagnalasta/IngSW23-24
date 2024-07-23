package it.unibs.model;

import java.util.ArrayList;

public class Gerarchia {

	private CategoriaRadice radice;

	public Gerarchia(String nome, String campo, ArrayList<ValoreDominio> dominio) {
		this.radice = new CategoriaRadice(nome, campo, dominio);
	}

	public CategoriaRadice getRadice() {
		return radice;
	}
}