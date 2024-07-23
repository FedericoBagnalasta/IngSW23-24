package it.unibs.model;

import java.util.ArrayList;

public class ElencoComprensori {

	private static ArrayList<Comprensorio> elencoComprensori = new ArrayList<>();
	
	public static void aggiungiComprensorio(String nome, ArrayList<String> comuniComprensorio) {
		elencoComprensori.add(new Comprensorio(nome, comuniComprensorio));
	}
	
	public static boolean verificaEsistenzaComprensorio(String nomeComprensorio) {
		for(Comprensorio comprensorio : elencoComprensori) {
			if(comprensorio.getNome().equals(nomeComprensorio)) {
				return true;
			}
		}
		return false;
	}
	
	public static ArrayList<Comprensorio> getElencoComprensori() {
		return elencoComprensori;
	}
	
	public static int numeroComprensori() {
		return elencoComprensori.size();
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
}