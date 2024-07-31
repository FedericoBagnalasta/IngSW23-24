package it.unibs.model;

public class Scambio {

	private CategoriaFoglia fogliaRichiesta;
	private CategoriaFoglia fogliaOfferta;
	private int oreRichiesta;
	private int oreOfferta;
	private String stato;
	private Utente utente;
	
	public Scambio(CategoriaFoglia richiesta, CategoriaFoglia offerta, int oreRichiesta, int oreOfferta, Utente utente) {
		this.fogliaRichiesta = richiesta;
		this.fogliaOfferta = offerta;
		this.oreRichiesta = oreRichiesta;
		this.oreOfferta = oreOfferta;
		this.utente = utente;
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

	public Utente getUtente() {
		return utente;
	}
}