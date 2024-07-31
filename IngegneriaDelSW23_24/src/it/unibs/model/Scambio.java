package it.unibs.model;

public class Scambio {

	private CategoriaFoglia fogliaRichiesta;
	private CategoriaFoglia fogliaOfferta;
	private int oreRichiesta;
	private int oreOfferta;
	private String stato;
	
	public Scambio(CategoriaFoglia richiesta, CategoriaFoglia offerta, int oreRichiesta, int oreOfferta) {
		this.fogliaRichiesta = richiesta;
		this.fogliaOfferta = offerta;
		this.oreRichiesta = oreRichiesta;
		this.oreOfferta = oreOfferta;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public CategoriaFoglia getFogliaRichiesta() {
		return fogliaRichiesta;
	}

	public CategoriaFoglia getFogliaOfferta() {
		return fogliaOfferta;
	}

	public int getOreRichiesta() {
		return oreRichiesta;
	}

	public int getOreOfferta() {
		return oreOfferta;
	}
}