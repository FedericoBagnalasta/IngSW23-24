package it.unibs.model;

public class Scambio {
	
	private static final String APERTO = "Aperto";
	
	private CategoriaFoglia fogliaRichiesta;
	private CategoriaFoglia fogliaOfferta;
	private int oreRichiesta;
	private int oreOfferta;
	private String stato;
	private Utente utente;
	
	public Scambio(CategoriaFoglia fogliaRichiesta, CategoriaFoglia fogliaOfferta,
			int oreRichiesta, int oreOfferta, Utente utente) {
		this.fogliaRichiesta = fogliaRichiesta;
		this.fogliaOfferta =fogliaOfferta;
		this.oreRichiesta = oreRichiesta;
		this.oreOfferta = oreOfferta;
		this.utente = utente;
	}

	public Scambio(CategoriaFoglia fogliaRichiesta, CategoriaFoglia fogliaOfferta,
			int oreRichiesta, int oreOfferta, String stato, Utente utente) {
		this.fogliaRichiesta = fogliaRichiesta;
		this.fogliaOfferta = fogliaOfferta;
		this.oreRichiesta = oreRichiesta;
		this.oreOfferta = oreOfferta;
		this.stato = stato;
		this.utente = utente;
	}
	
	public boolean verificaUguaglianzaScambio(Scambio scambio) {
		if(this.getStato().equals(APERTO) &&
				!(this.getUtente().getNome().equals(scambio.getUtente().getNome())) &&
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