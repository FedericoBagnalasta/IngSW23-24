package it.unibs.model;

import java.util.ArrayList;

public class Gerarchia {

	private static final String FOGLIA = "Foglia";
	
	private CategoriaRadice radice;

	public Gerarchia(String nome, String campo, ArrayList<ValoreDominio> dominio) {
		this.radice = new CategoriaRadice(nome, campo, dominio);
	}

	public Gerarchia(String nome, String campo, ArrayList<ValoreDominio> dominio, ArrayList<Categoria> figli) {
		this.radice = new CategoriaRadice(nome, campo, dominio, figli);
	}

	public static CategoriaFoglia trovaFoglia(Categoria categoria, String nomeFoglia) {
		CategoriaFoglia foglia;

		for(Categoria c : categoria.getFigli()) {
			if(c.getNome().equals(nomeFoglia) && c.getTipo().equals(FOGLIA)) {
				return (CategoriaFoglia)c;
			}
			foglia = trovaFoglia(c, nomeFoglia);

			if(foglia instanceof CategoriaFoglia) {
				return foglia;
			}
		}
		return null;
	}

	public CategoriaRadice getRadice() {
		return radice;
	}
}