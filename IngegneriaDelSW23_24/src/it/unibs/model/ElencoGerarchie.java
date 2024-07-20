package it.unibs.model;

import java.util.ArrayList;

public class ElencoGerarchie {

	private static ArrayList<Gerarchia> elencoGerarchie=new ArrayList<>();

	public static void aggiungiGerarchia(String nome, String campo) {
		Gerarchia nuovaGerarchia=new Gerarchia(nome, campo);
		elencoGerarchie.add(nuovaGerarchia);
	}
	
	public static boolean verificaOriginalita(String nomeNuovaRadice) {
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