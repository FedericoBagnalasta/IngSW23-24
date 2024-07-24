package it.unibs.model;

import java.util.ArrayList;

public class ElencoComprensori {

	private static ArrayList<Comprensorio> elencoComprensori = new ArrayList<>();
	
	public static void aggiungiComprensorio(Comprensorio comprensorio) {
		elencoComprensori.add(comprensorio);
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
	
	//VIENE USATO SOLO DA FRUITORE
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