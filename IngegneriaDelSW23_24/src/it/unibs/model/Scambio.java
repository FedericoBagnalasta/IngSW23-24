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
	
	public boolean verificaUguaglianzaScambio(Scambio scambio) {
		if(this.getStato().equals("Aperto") &&
				this.getUtente().getComprensorio().getNome().equals(scambio.getUtente().getComprensorio().getNome()) &&
				this.getFogliaRichiesta().verificaUguaglianzaFoglie(scambio.getFogliaOfferta()) &&
				this.getFogliaOfferta().verificaUguaglianzaFoglie(scambio.getFogliaRichiesta()) &&
				this.getOreRichiesta() == scambio.getOreOfferta() &&
				this.getOreOfferta() == scambio.getOreRichiesta()) {
			return true;
		}
		return false;
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