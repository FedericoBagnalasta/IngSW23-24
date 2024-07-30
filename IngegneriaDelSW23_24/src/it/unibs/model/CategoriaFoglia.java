package it.unibs.model;

import java.util.ArrayList;

public class CategoriaFoglia implements Categoria {

	private static final String FOGLIA = "Foglia";
	
	private String nome;
	private ValoreDominio valoreDominio;
	private CategoriaRadice radice;
	private final String tipo = FOGLIA;
	private final String campo = null;
	private final ArrayList<ValoreDominio> dominio = new ArrayList<ValoreDominio>();
	private final ArrayList<Categoria> figli = new ArrayList<>();

	public CategoriaFoglia(String nome, ValoreDominio valore, CategoriaRadice radice) {
		this.nome = nome;
		this.valoreDominio = valore;
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

	public ValoreDominio getValoreDominio() {
		return valoreDominio;
	}

	public CategoriaRadice getCategoriaRadice() {
		return radice;
	}
	
	public String getTipo() {
		return tipo;
	}

	public ArrayList<ValoreDominio> getDominio() {
		return dominio;
	}

	public ArrayList<Categoria> getFigli() {
		return figli;
	}

	public String getCampo() {
		return campo;
	}

	public boolean haStessaRadice(CategoriaFoglia fogliaNuova) {
		if(this.getCategoriaRadice().getNome().equals(fogliaNuova.getCategoriaRadice().getNome())) {
			return true;
		}
		return false;
	}
}