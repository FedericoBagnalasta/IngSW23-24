package it.unibs.model;

import java.util.ArrayList;

public class CategoriaFoglia implements Categoria {

	private String nome;
	private ValoreDominio valore;
	private ArrayList<FattoreDiConversione> elencoFattoriDiConversione = new ArrayList<FattoreDiConversione>();
	private CategoriaRadice radice;
	private final ArrayList<ValoreDominio> dominio = new ArrayList<ValoreDominio>();
	private final ArrayList<Categoria> figli = new ArrayList<>();


	public CategoriaFoglia(String nome, ValoreDominio valore, CategoriaRadice radice) {
		super();
		this.nome = nome;
		this.valore = valore;
		this.radice = radice;
	}

	public boolean verificaUguaglianzaFoglie(CategoriaFoglia foglia) {
		if(this.getNome().equals(foglia.getNome()) && this.getCategoriaRadice().getNome().equals(foglia.getCategoriaRadice().getNome())) {
			return true;
		}
		return false;
	}

	public String getNome() {
		return nome;
	}

	public String getTipo() {
		return "Foglia";
	}

	public ValoreDominio getValore() {
		return valore;
	}

	public ArrayList<FattoreDiConversione> getElencoFattoriDiConversione() {
		return elencoFattoriDiConversione;
	}

	public CategoriaRadice getCategoriaRadice() {
		return radice;
	}

	//Potrebbero restituire null, senza dichiarare gli attributi
	public ArrayList<ValoreDominio> getDominio() {
		return dominio;
	}

	public ArrayList<Categoria> getFigli() {
		return figli;
	}

	public boolean haStessaRadice(CategoriaFoglia fogliaNuova) {
		if(this.getCategoriaRadice().getNome().equals(fogliaNuova.getCategoriaRadice().getNome())) {
			return true;
		}
		return false;
	}
}