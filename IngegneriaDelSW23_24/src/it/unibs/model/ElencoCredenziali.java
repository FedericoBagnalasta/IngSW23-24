package it.unibs.model;

import java.util.ArrayList;
import it.unibs.controller.Credenziali;

public class ElencoCredenziali {
	
	private static ArrayList<Credenziali> listaCredenziali=new ArrayList<>();

	public ElencoCredenziali() {
		super();
	}
	
	public static void aggiungiCredenziali(Credenziali newCredenziali) {
		listaCredenziali.add(newCredenziali);
	}
	
	public static boolean controllaDuplicati(String nome) {
		for(Credenziali credenziali: listaCredenziali) {
			if(credenziali.getNome()==nome) {
				return true;
			}
		}
		return false; 
	}

}
