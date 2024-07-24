package it.unibs.controller;

import java.util.ArrayList;
import it.unibs.model.Categoria;
import it.unibs.model.CategoriaFoglia;
import it.unibs.model.CategoriaNonFoglia;
import it.unibs.model.ElencoComprensori;
import it.unibs.model.ElencoFattoriDiConversione;
import it.unibs.model.ElencoGerarchie;
import it.unibs.model.FattoreDiConversione;
import it.unibs.model.Comprensorio;
import it.unibs.model.Gerarchia;
import it.unibs.model.Utente;
import it.unibs.model.ValoreDominio;
import it.unibs.view.ConfiguratoreView;

public class ConfiguratoreController {
	
	Utente utente;
	
	public ConfiguratoreController() {
		super();
	}
	
	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public void creaGerarchia() {
		String nomeRadice = ConfiguratoreView.inserisciNomeRadiceGerarchia();
		
		while(ElencoGerarchie.verificaEsistenzaRadice(nomeRadice)) {
			ConfiguratoreView.radiceGiaPresente();
			nomeRadice = ConfiguratoreView.inserisciNomeRadiceGerarchia();
		}
		
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
			nomeFoglia = ConfiguratoreView.inserisciNomeFogliaGerarchia();
		} while(elencoNomiGerarchia.contains(nomeFoglia));
		CategoriaFoglia foglia = new CategoriaFoglia(nomeFoglia, valore, padre.getCategoriaRadice());
		
		//CHIEDE SE (FORSE OBBLIGATORIO) VUOLE AGGIUNGERE DEI FATTORI DI CONVERSIONE
		ElencoFattoriDiConversione.creaFDC_Deducibili(creaFattoreDiConversione());
		//DERIVARE TUTTI I FDC POSSIBILI
		
		return foglia;
	}
	
	public CategoriaNonFoglia creaNonFoglia(Categoria padre, ValoreDominio valore) {
		String nomeNonFoglia;
		ArrayList<String> elencoNomiGerarchia = padre.getCategoriaRadice().getNomiGerarchia();
		do {
			nomeNonFoglia = ConfiguratoreView.inserisciNomeNonFogliaGerarchia();
		} while(elencoNomiGerarchia.contains(nomeNonFoglia));
		
		String campo = ConfiguratoreView.inserisciCampo();
		ArrayList<ValoreDominio> dominio = creaDominio();
		
		return new CategoriaNonFoglia(nomeNonFoglia, campo, valore, dominio, padre.getCategoriaRadice());
	}
	
	public FattoreDiConversione creaFattoreDiConversione() {
		FattoreDiConversione fdcNuovo;
		CategoriaFoglia f1, f2;
		double valore;
		do {
		//METODO PER MOSTRARE LA STRUTTURA DELLA GERARCHIA
			do {
				f1 = selezionaCategoriaFoglia();
			}while(f1 == null);
			
			do {
				f2 = selezionaCategoriaFoglia();
			}while(f2 == null);
		
			valore = ConfiguratoreView.inserisciValoreFDC();
			fdcNuovo = new FattoreDiConversione(f1, f2, valore);
		} while(ElencoFattoriDiConversione.verificaEsistenzaFDC(fdcNuovo));
		ElencoFattoriDiConversione.aggiungiFDC(fdcNuovo);
		return fdcNuovo;
			
	}
	
	//DA FINIRE
	public CategoriaFoglia selezionaCategoriaFoglia() {
		String nomeFoglia = ConfiguratoreView.inserisciNomeFogliaGerarchia();
		String nomeRadice = ConfiguratoreView.inserisciNomeRadiceGerarchia();
		
		//FORSE Da fin modo da stampare messaggio per fallimento operazione di ricerca
		CategoriaFoglia foglia = ElencoGerarchie.selezionaFoglia(nomeFoglia, nomeRadice);
		if(foglia == null){
			ConfiguratoreView.fogliaNonTrovata();
			return null;
		}
		else return foglia;
		
	}
	
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
		String nomeComprensorio = ConfiguratoreView.inserisciNomeComprensorio();
		
		while(ElencoComprensori.verificaEsistenzaComprensorio(nomeComprensorio)) {
			ConfiguratoreView.comuneGiaPresente();
			nomeComprensorio = ConfiguratoreView.inserisciNomeComprensorio();
		};
		
		return nomeComprensorio;
	}
	
	public ArrayList<String> creaComuniComprensorio() {
		ArrayList<String> elencoComuni = new ArrayList<>();
		do {
			String nuovoComune = ConfiguratoreView.inserisciComune();
			if(elencoComuni.contains(nuovoComune)) {
				ConfiguratoreView.comuneGiaPresente();
			}
			else elencoComuni.add(nuovoComune);
		} while(ConfiguratoreView.inserisciAltroComune());
		
		return elencoComuni;
	}
	
	public void creaComprensorio() {
		Comprensorio comprensorio = new Comprensorio(creaNomeComprensorio(), creaComuniComprensorio());
		ElencoComprensori.aggiungiComprensorio(comprensorio);
	}
	
	public static void visualizzaComprensori() {
		for(Comprensorio comprensorio : ElencoComprensori.getElencoComprensori()) {
			ConfiguratoreView.visualizzaNomeComprensorio(comprensorio.getNome());
			for(String comune : comprensorio.getComuniComprensorio()) {
				ConfiguratoreView.visualizzaNomeComune(comune);
			}
		}
	}
	
	public static void visualizzaGerarchie() {
		for(Gerarchia gerarchia : ElencoGerarchie.getElencoGerarchie()) {
			ConfiguratoreView.visualizzaNomeRadiceGerarchia(gerarchia);
			visualizzaFigliCategoria(gerarchia.getRadice(), gerarchia);
		}
	}
	
	public static void visualizzaFigliCategoria(Categoria categoria, Gerarchia gerarchia) {
		for(Categoria c : categoria.getFigli()) {
			ConfiguratoreView.visualizzaNomeFiglioCategoria(categoria, gerarchia);
			visualizzaFigliCategoria(c, gerarchia);
		}
	}
	
	public static void visualizzaFattoriDiConversione(CategoriaFoglia foglia) {
		for(FattoreDiConversione fattore : ElencoFattoriDiConversione.getElencoFattoriDiConversione()) {
			if(fattore.getC1().verificaUguaglianzaFoglie(foglia)) {
				ConfiguratoreView.visualizzaFattoreDiConversione(fattore);
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