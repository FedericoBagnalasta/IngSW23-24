package it.unibs.model;

import java.util.ArrayList;

public class Comprensorio {

	private String name;
	private ArrayList<String> comuniComprensorio=new ArrayList<>();
	
	public Comprensorio(String name, ArrayList<String> comuniComprensorio) {
		this.name=name;
		this.comuniComprensorio=comuniComprensorio;
	}

	public String getName() {
		return name;
	}

	public ArrayList<String> getComuniComprensorio() {
		return comuniComprensorio;
	}
	
}
