package it.unibs.controller;

import java.util.ArrayList;
import it.unibs.model.Categoria;
import it.unibs.model.CategoriaFoglia;
import it.unibs.model.CategoriaNonFoglia;
import it.unibs.model.CategoriaRadice;
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

			for(ValoreDominio valore : categoriaPadre.getDominio()) {
				//E' POSSIBILE NON ASSOCIARE UNA FOGLIA AD UN VALORE DEL DOMINIO
				if(ConfiguratoreView.richiestaAggiuntaCategoriaFoglia(valore.getValore())) {
					creaFoglia(categoriaPadre, valore);			
				}
				else if(ConfiguratoreView.richiestaAggiuntaCategoriaNonFoglia(valore.getValore())) {
					CategoriaNonFoglia nonFoglia = creaNonFoglia(categoriaPadre, valore);
					
					//CHIAMATA RICORSIVA
					creaFigliCategoria(nonFoglia);
					
					
				
				}
			}
			
	}
	
	public CategoriaFoglia creaFoglia(Categoria padre, ValoreDominio valore) {
		String nomeFoglia;
		ArrayList<String> elencoNomiGerarchia = padre.getCategoriaRadice().getNomiGerarchia();
		do {
			nomeFoglia = ConfiguratoreView.inserisciNomeFogliaGerarchia();
		} while(elencoNomiGerarchia.contains(nomeFoglia));
		CategoriaFoglia foglia = new CategoriaFoglia(nomeFoglia, valore, padre.getCategoriaRadice());
		padre.getFigli().add(foglia);
		//CHIEDE SE (FORSE OBBLIGATORIO) VUOLE AGGIUNGERE DEI FATTORI DI CONVERSIONE
		//Verifica che non venga creato un fdc prima che ci siano almeno 2 foglie
		if(ElencoGerarchie.dueOpiuFoglie()) {
			//ERRORE checkForComodification
			ElencoFattoriDiConversione.creaFDC_Deducibili(creaFattoreDiConversione(foglia));
		}
		
		//DERIVARE TUTTI I FDC POSSIBILI
		
		return foglia;
	}
	
	public CategoriaNonFoglia creaNonFoglia(Categoria padre, ValoreDominio valore) {
		String nomeNonFoglia;
		CategoriaNonFoglia nonFoglia;
		ArrayList<String> elencoNomiGerarchia = padre.getCategoriaRadice().getNomiGerarchia();
		do {
			nomeNonFoglia = ConfiguratoreView.inserisciNomeNonFogliaGerarchia();
		} while(elencoNomiGerarchia.contains(nomeNonFoglia));
		
		String campo = ConfiguratoreView.inserisciCampo();
		ArrayList<ValoreDominio> dominio = creaDominio();
		
		nonFoglia = new CategoriaNonFoglia(nomeNonFoglia, campo, valore, dominio, padre.getCategoriaRadice());
		padre.getFigli().add(nonFoglia);
		return nonFoglia;
	}
	
	public FattoreDiConversione creaFattoreDiConversione(CategoriaFoglia fogliaNuova) {		
		FattoreDiConversione fdcNuovo;
		CategoriaFoglia f1, f2;
		double valore;
		do {
			visualizzaGerarchie();
		//METODO PER MOSTRARE LA STRUTTURA DELLA GERARCHIA
			do {
				ConfiguratoreView.inserimentoPrimaFoglia();
				//Impongo che almeno una foglia della fdc sia della nuova gerarchia (ovvero abbia la stessa radice della gerarchia appena creata)
				f1 = selezionaCategoriaFogliaConRadiceFissata(fogliaNuova.getCategoriaRadice());
			}while(f1 == null);
			
			do {
				ConfiguratoreView.inserimentoSecondaFoglia();
				f2 = selezionaCategoriaFoglia();
			}while(f2 == null);
		
			valore = ConfiguratoreView.inserisciValoreFDC();
			fdcNuovo = new FattoreDiConversione(f1, f2, valore);
		} while(ElencoFattoriDiConversione.verificaEsistenzaFDC(fdcNuovo) || fdcNuovo.verificaFDCImpossibile());

		ElencoFattoriDiConversione.aggiungiFDC(fdcNuovo);
		return fdcNuovo;
	}
	
	public CategoriaFoglia selezionaCategoriaFogliaConRadiceFissata(CategoriaRadice radice) {
		String nomeFoglia = ConfiguratoreView.inserisciNomeFogliaRicerca();
		String nomeRadice = ConfiguratoreView.inserisciNomeRadiceRicerca();
		
		//FORSE Da fin modo da stampare messaggio per fallimento operazione di ricerca
		CategoriaFoglia foglia = ElencoGerarchie.selezionaFoglia(nomeFoglia, nomeRadice);
		if(foglia == null){
			ConfiguratoreView.fogliaNonTrovata();
			return null;
		}
		
		if(!radice.getNome().equals(nomeRadice)) {
			ConfiguratoreView.fogliaDiGerarchiaVecchia(radice.getNome());
			return null;
		}
		
		else return foglia;
		
	}
	
	//DA FINIRE
	public CategoriaFoglia selezionaCategoriaFoglia() {
		String nomeFoglia = ConfiguratoreView.inserisciNomeFogliaRicerca();
		String nomeRadice = ConfiguratoreView.inserisciNomeRadiceRicerca();

		
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
			ConfiguratoreView.visualizzaNomeRadiceGerarchia(gerarchia.getRadice().getNome());
			visualizzaFigliCategoria(gerarchia.getRadice());
		}
	}
	
	public static void visualizzaFigliCategoria(Categoria categoria) {
		for(Categoria c : categoria.getFigli()) {
			//Da cambiare parametri con String
			//Da cambiare definizione di padre: stampa solo radice
			ConfiguratoreView.visualizzaNomeFiglioCategoria(categoria.getNome(), c.getNome(), c.getTipo());
			visualizzaFigliCategoria(c);
		}
	}
	
	public static void visualizzaFattoriDiConversione(CategoriaFoglia foglia) {
		if(foglia == null) {
			ConfiguratoreView.fogliaNonTrovata();
			return;
		}
		for(FattoreDiConversione fattore : ElencoFattoriDiConversione.getElencoFattoriDiConversione()) {
			if(fattore.getC1().verificaUguaglianzaFoglie(foglia)) {
				ConfiguratoreView.visualizzaFattoreDiConversione(fattore.getC1().getNome(), fattore.getC2().getNome(), fattore.getValore());
			}
		}
	}
}