package it.unibs.model;

public class FattoreDiConversione {

	private CategoriaFoglia c;
	private double valore;
	
	public FattoreDiConversione(CategoriaFoglia c, double valore) {
		this.c = c;
		this.valore = valore;
	}
	
	public CategoriaFoglia getC() {
		return c;
	}
	
	public double getValore() {
		return valore;
	}
}
