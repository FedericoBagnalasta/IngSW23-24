package it.unibs.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FattoreDiConversione {

	private CategoriaFoglia c1;
	private CategoriaFoglia c2;
	private double valore;
	
	public FattoreDiConversione(CategoriaFoglia c1, CategoriaFoglia c2, double valore) {
		this.c1 = c1;
		this.c2 = c2;
		this.valore = new BigDecimal(valore).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}
	
	public CategoriaFoglia getC1() {
		return c1;
	}
	
	public CategoriaFoglia getC2() {
		return c2;
	}
	
	public double getValore() {
		return valore;
	}
	
	public FattoreDiConversione creaSimmetrico() {
		return new FattoreDiConversione(getC2(), getC1(), 1/getValore());
	}
	
	//DA FINIRE
	public boolean verificaUguaglianzaFattoriDiConversione(FattoreDiConversione fdC) {
		CategoriaFoglia c12 = fdC.getC1();
		CategoriaFoglia c22 = fdC.getC2();
		double valore = fdC.getValore();
		if(!(c12.getCategoriaRadice().getNome().equals(c1.getCategoriaRadice().getNome())
				&& c22.getCategoriaRadice().getNome().equals(c2.getCategoriaRadice().getNome()))) {
			return false;
		}
		if(!(c12.getNome().equals(c1.getNome()) && c22.getNome().equals(c2.getNome()))) {
			return false;
		}
		//Potrei fare controllo su valore per evitare inconsistenze 
		//return (valore==this.valore); //SERVE CONTROLLO INCONSISTENZA?
		return true;
	}

	//COntrolla che non venga messo un fdc su una sola foglia
	public boolean verificaFDCImpossibile() {
		if(c1.verificaUguaglianzaFoglie(c2)){
			return true;
		}
		return false;
	}
}
