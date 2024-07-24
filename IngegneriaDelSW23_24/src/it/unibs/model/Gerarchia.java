package it.unibs.model;

import java.util.ArrayList;

public class Gerarchia {

	private CategoriaRadice radice;

	public Gerarchia(String nome, String campo, ArrayList<ValoreDominio> dominio) {
		this.radice = new CategoriaRadice(nome, campo, dominio);
	}
	
	public static CategoriaFoglia trovaFoglia(Categoria categoria, String nomeFoglia) {
		for(Categoria c : categoria.getFigli()) {
			if(c.getNome().equals(nomeFoglia)) {
				return (CategoriaFoglia)c;
			}
			trovaFoglia(c, nomeFoglia);
		}
		return null;	//messaggio di errore
	}

	public CategoriaRadice getRadice() {
		return radice;
	}
}