package it.unibs.controller;

import java.util.ArrayList;

import it.unibs.model.Categoria;
import it.unibs.model.CategoriaFoglia;
import it.unibs.model.CategoriaNonFoglia;
import it.unibs.model.ElencoComprensori;
import it.unibs.model.ElencoFattoriDiConversione;
import it.unibs.model.ElencoGerarchie;
import it.unibs.model.Comprensorio;
import it.unibs.model.ElencoComprensori;
import it.unibs.model.ElencoUtenti;
import it.unibs.model.Gerarchia;
import it.unibs.model.ValoreDominio;
import it.unibs.view.ConfiguratoreView;

public class ConfiguratoreController {
	
	public void creaGerarchia() {
		String nomeRadice;
		
		do {
			nomeRadice = ConfiguratoreView.inserisciNomeRadiceGerarchia();
			ConfiguratoreView.radiceGiaPresente();
		} while(ElencoGerarchie.verificaEsistenzaRadice(nomeRadice));
		
		String campo = ConfiguratoreView.inserisciCampo();
		ArrayList<ValoreDominio> dominio = creaDominio();
		Gerarchia nuovaGerarchia = ElencoGerarchie.aggiungiGerarchia(nomeRadice, campo, dominio);
		
		creaFigliCategoria(nuovaGerarchia.getRadice());//Si occupa di creare la struttura (Foglie e NonFoglie) della gerarchia
	}
	
	private void creaFigliCategoria(Categoria categoriaPadre) {
		//DEVO POTER SCEGLIERE A QUALE CATEGORIA COLLEGARE IL NUOVO ELEMENTO	
		
		do {
			for(ValoreDominio valore : categoriaPadre.getDominio()) {
				//E' POSSIBILE NON ASSOCIARE UNA FOGLIA AD UN VALORE DEL DOMINIO
				if(ConfiguratoreView.richiestaAggiuntaCategoriaFoglia(valore)) {
					CategoriaFoglia foglia = creaFoglia(categoriaPadre, valore);
					categoriaPadre.getFigli().add(foglia);				
				}
				else if(ConfiguratoreView.richiestaAggiuntaCategoriaNonFoglia(valore)) {
					CategoriaNonFoglia nonFoglia = creaNonFoglia(categoriaPadre, valore);
					
					//CHIAMATA RICORSIVA
					creaFigliCategoria(nonFoglia);
					
					
					categoriaPadre.getFigli().add(nonFoglia);
				}
			}
		} while(ConfiguratoreView.richiestaContinuazioneStruttura());
	}
	
	public CategoriaFoglia creaFoglia(Categoria padre, ValoreDominio valore) {
		String nomeFoglia;
		ArrayList<String> elencoNomiGerarchia = padre.getCategoriaRadice().getNomiGerarchia();
		do {
			nomeFoglia = ConfiguratoreView.inserisciNomeFoglia();
		} while(elencoNomiGerarchia.contains(nomeFoglia));
		CategoriaFoglia foglia = new CategoriaFoglia(nomeFoglia, valore, padre.getCategoriaRadice());
		
		//CHIEDE SE (FORSE OBBLIGATORIO) VUOLE AGGIUNGERE DEI FATTORI DI CONVERSIONE
		
		
		return foglia;
	}
	
	public CategoriaNonFoglia creaNonFoglia(Categoria padre, ValoreDominio valore) {
		String nomeNonFoglia;
		ArrayList<String> elencoNomiGerarchia = padre.getCategoriaRadice().getNomiGerarchia();
		do {
			nomeNonFoglia = ConfiguratoreView.inserisciNomeNonFoglia();
		} while(elencoNomiGerarchia.contains(nomeNonFoglia));
		
		String campo = ConfiguratoreView.inserisciCampo();
		ArrayList<ValoreDominio> dominio = creaDominio();
		
		return new CategoriaNonFoglia(nomeNonFoglia, campo, valore, dominio, padre.getCategoriaRadice());
	}
	
	// 		NON RIMUOVERE
	
	/*public FattoreDiConversione creaFattoreDiConversione() {
		CategoriaFoglia f1, f2;
		double valore;
		do {
		//METODO PER MOSTRARE LA STRUTTURA DELLA GERARCHIA
		f1 = selezionaCategoriaFoglia();
		f2 = selezioneCategoriaFoglia();
		valore = ConfiguratoreView.inserisciValoreFDC();
		}while(ElencoFattoriDiConversione.verificaEsistenzaFDC(new FattoreDiConversione(f1, f2, valore)));
			
	}*/
	
	public ArrayList<ValoreDominio> creaDominio() {
		ArrayList<ValoreDominio> dominio = new ArrayList<>();
		String nomeValore;
		String descrizione = "Assente";
		
		do {
			nomeValore = ConfiguratoreView.inserisciNomeValoreDominio();
			if(ConfiguratoreView.richiestaDescrizioneValoreDominio()) {
				descrizione = ConfiguratoreView.inserisciDescrizioneValoreDominio();	
			}
			dominio.add(new ValoreDominio(nomeValore, descrizione));
		} while(ConfiguratoreView.richiestaNomeValoreDominio());
		
		return dominio;
	}
	
	public String creaNomeComprensorio() {
		String nomeComprensorio;
		
		do {
			nomeComprensorio = ConfiguratoreView.inserisciNomeComprensorio();
		} while(ElencoComprensori.verificaEsistenzaComprensorio(nomeComprensorio));
		
		return nomeComprensorio;
	}
	
	public ArrayList<String> creaComuniComprensorio() {
		ArrayList<String> elencoComuni = new ArrayList<>();
		do {
			String comune = ConfiguratoreView.inserisciComuniComprensorio();
			for(String s : elencoComuni) {
				if(s.equals(comune)) {
					ConfiguratoreView.comuneGiaPresente();
					break;
				}
				else {
					elencoComuni.add(comune);
				}
			}
		} while(ConfiguratoreView.inserisciAltroComune());
		
		return elencoComuni;
	}
	
	public Comprensorio creaComprensorio() {
		return new Comprensorio(creaNomeComprensorio(), creaComuniComprensorio());
	}
	
	public static void visualizzaComprensorio() {
		for(Comprensorio comprensorio : ElencoComprensori.getElencoComprensori()) {
			ConfiguratoreView.visualizzaNomeComprensorio(comprensorio.getNome());
			for(String comune : comprensorio.getComuniComprensorio()) {
				ConfiguratoreView.visualizzaNomeComune(comune);
			}
		}
	}
	/*
	
	//NON SERVE PIU' (I VALORI VENGONO ASSEGNATI PRIMA DELLA CREAZIONE)
	/*private ArrayList<ValoreDominio> ottieniValoriDominioDisponibili(Categoria padre){
		ArrayList<ValoreDominio> elencoValoriDisponibili = padre.getDominio();
		ArrayList<ValoreDominio> elencoValoriUsati = new ArrayList<ValoreDominio>();
		for(Categoria c: padre.getFigli()) {
			elencoValoriUsati.add(c.getValore());
		}
		elencoValoriDisponibili.removeAll(elencoValoriUsati);
		return elencoValoriDisponibili;
	}
	*/
}