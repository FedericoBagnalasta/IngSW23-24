package it.unibs.model;

import java.util.ArrayList;

public class ElencoGerarchie {

	private static ArrayList<Gerarchia> elencoGerarchie=new ArrayList<>();

	public static Gerarchia aggiungiGerarchia(String nome, String campo, ArrayList<ValoreDominio> dominio) {
		Gerarchia nuovaGerarchia=new Gerarchia(nome, campo, dominio);
		elencoGerarchie.add(nuovaGerarchia);
		return nuovaGerarchia;
	}
	
	public static boolean verificaOriginalitaRadiceNome(String nomeNuovaRadice) {
		for(Gerarchia c: elencoGerarchie) {
			if(nomeNuovaRadice.equals(c.getRadice().getNome())) {
				return false;
			}
		}
		return true;
	}
	
	public ArrayList<Gerarchia> getElencoGerarchie() {
		return elencoGerarchie;
	}
	
}
