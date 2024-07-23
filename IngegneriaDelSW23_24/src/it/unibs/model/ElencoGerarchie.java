package it.unibs.model;

import java.util.ArrayList;

public class ElencoGerarchie {

	private static ArrayList<Gerarchia> elencoGerarchie = new ArrayList<>();

	public static Gerarchia aggiungiGerarchia(String nome, String campo, ArrayList<ValoreDominio> dominio) {
		Gerarchia nuovaGerarchia = new Gerarchia(nome, campo, dominio);
		elencoGerarchie.add(nuovaGerarchia);
		return nuovaGerarchia;
	}
	
	public static boolean verificaEsistenzaRadice(String nomeRadice) {
		for(Gerarchia g : elencoGerarchie) {
			if(nomeRadice.equals(g.getRadice().getNome())) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Gerarchia> getElencoGerarchie() {
		return elencoGerarchie;
	}
}