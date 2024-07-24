package it.unibs.model;

import java.util.ArrayList;

public class CategoriaFoglia implements Categoria {

	private String nome;
	private ValoreDominio valore;
	private ArrayList<FattoreDiConversione> elencoFattoriDiConversione = new ArrayList<FattoreDiConversione>();
	private CategoriaRadice radice;
	
	//Si potrebbe non dichiararli
	private final ArrayList<ValoreDominio> dominio = new ArrayList<ValoreDominio>();
	private final ArrayList<Categoria> figli = new ArrayList<>();
	

	public CategoriaFoglia(String nome, ValoreDominio valore, CategoriaRadice radice) {
		super();
		this.nome = nome;
		this.valore = valore;
		this.radice = radice;
	}
	
	/*NON VA PIU' QUI, FDC SPOSTATI IN CLASSE SEPARATA
	public void aggiungiFattoreDiConversione(CategoriaFoglia c, double valore) {
		elencoFattoriDiConversione.add(new FattoreDiConversione(c, valore));
		c.aggiungiFattoreDiConversione(this, 1/valore);//Aggiunto il simmetrico del fattoreDiConversione
		//Ricerca automatica dei FDC ricavabili da quelli esistenti
	}
	*/
	public boolean sonoUguali(CategoriaFoglia f2) {
		if(!(getCategoriaRadice().getNome().equals(f2.getCategoriaRadice().getNome()))) {
			return false;
		}
		else if(!(getNome().equals(f2.getNome()))) {
			return false;
		}
		return true;
	}

	public String getNome() {
		return nome;
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

}
