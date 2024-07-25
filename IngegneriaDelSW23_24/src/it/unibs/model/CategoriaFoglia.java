package it.unibs.model;

import java.util.ArrayList;

public class CategoriaFoglia implements Categoria {

	private String nome;
	private ValoreDominio valoreDominio;
	private CategoriaRadice radice;
	
	//Si potrebbe non dichiararli
	private final ArrayList<ValoreDominio> dominio = new ArrayList<ValoreDominio>();
	private final ArrayList<Categoria> figli = new ArrayList<>();
	

	public CategoriaFoglia(String nome, ValoreDominio valore, CategoriaRadice radice) {
		super();
		this.nome = nome;
		this.valoreDominio = valore;
		this.radice = radice;
	}
	
	/*NON VA PIU' QUI, FDC SPOSTATI IN CLASSE SEPARATA
	public void aggiungiFattoreDiConversione(CategoriaFoglia c, double valore) {
		elencoFattoriDiConversione.add(new FattoreDiConversione(c, valore));
		c.aggiungiFattoreDiConversione(this, 1/valore);//Aggiunto il simmetrico del fattoreDiConversione
		//Ricerca automatica dei FDC ricavabili da quelli esistenti
	}
	*/
	
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

	public ValoreDominio getValoreDominio() {
		return valoreDominio;
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
	
	public String getCampo() {
		return null;
	}

	public boolean haStessaRadice(CategoriaFoglia fogliaNuova) {
		if(this.getCategoriaRadice().getNome().equals(fogliaNuova.getCategoriaRadice().getNome())) {
			return true;
		}
		return false;
	}

}
