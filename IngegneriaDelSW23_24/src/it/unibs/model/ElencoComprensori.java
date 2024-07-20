package it.unibs.model;

import java.util.ArrayList;

public class ElencoComprensori {

	private static ArrayList<Comprensorio> elencoComprensori = new ArrayList<>();
	
	public static void aggiungiComprensorio(String nome, ArrayList<String> comuniComprensorio) {
		elencoComprensori.add(new Comprensorio(nome, comuniComprensorio));
	}

	public static ArrayList<Comprensorio> getElencoComprensori() {
		return elencoComprensori;
	}
	
	public static int numeroComprensori() {
		return elencoComprensori.size();
	}
}