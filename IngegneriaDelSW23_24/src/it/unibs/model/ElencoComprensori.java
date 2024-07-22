package it.unibs.model;

import java.util.ArrayList;

public class ElencoComprensori {

	private static ArrayList<Comprensorio> elencoComprensori = new ArrayList<>();
	
	//Non sono sicuro serva
	public static void aggiungiComprensorio(String nome, ArrayList<String> comuniComprensorio) {
		elencoComprensori.add(new Comprensorio(nome, comuniComprensorio));
	}
	
	/*
	public boolean verificaComune(Comprensorio comprensorio, String comune) {
		for(String nomeComune : comprensorio.getComuniComprensorio()) {
			if(nomeComune.equals(comune)) {
				return true;
			}
		}
		return false;
	}
	*/
	
	public static boolean verificaComprensorio(String nomeComprensorio) {
		for(Comprensorio comprensorio : elencoComprensori) {
			if(comprensorio.getNome().equals(nomeComprensorio)) {
				return true;
			}
		}
		return false;
	}

	public static boolean verificaOriginalita(String nomeNuovoComprensorio) {
		for(Comprensorio c: elencoComprensori) {
			if(nomeNuovoComprensorio.equals(c.getNome())) {
				return false;
			}
		}
		return true;
	}
	
	public static ArrayList<Comprensorio> getElencoComprensori() {
		return elencoComprensori;
	}
	
	public static int numeroComprensori() {
		return elencoComprensori.size();
	}
}