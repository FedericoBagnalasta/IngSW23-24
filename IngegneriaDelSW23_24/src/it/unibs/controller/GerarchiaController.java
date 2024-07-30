package it.unibs.controller;

import java.util.ArrayList;

import it.unibs.model.Categoria;
import it.unibs.model.CategoriaFoglia;
import it.unibs.model.CategoriaNonFoglia;
import it.unibs.model.CategoriaRadice;
import it.unibs.model.ElencoFattoriDiConversione;
import it.unibs.model.ElencoGerarchie;
import it.unibs.model.FattoreDiConversione;
import it.unibs.model.Gerarchia;
import it.unibs.model.ValoreDominio;
import it.unibs.view.FDCView;
import it.unibs.view.GerarchiaView;
import it.unibs.view.LoginView;

public class GerarchiaController {

	public void creaGerarchia() {
		String nomeRadice = GerarchiaView.inserisciNomeRadiceGerarchia();

		while(ElencoGerarchie.verificaEsistenzaRadice(nomeRadice)) {
			GerarchiaView.msgRadiceGiaPresente();
			nomeRadice = GerarchiaView.inserisciNomeRadiceGerarchia();
		}
		String campo = GerarchiaView.inserisciCampo();
		ArrayList<ValoreDominio> dominio = creaDominio();
		Gerarchia nuovaGerarchia = ElencoGerarchie.aggiungiGerarchia(nomeRadice, campo, dominio);

		creaFigliCategoria(nuovaGerarchia.getRadice());
	}

	private void creaFigliCategoria(Categoria categoriaPadre) {
		for(ValoreDominio valore : categoriaPadre.getDominio()) {
			
			if(GerarchiaView.richiestaAggiuntaCategoriaFoglia(valore.getValore())) {
				creaFoglia(categoriaPadre, valore);			
			}
			else {
				CategoriaNonFoglia nonFoglia = creaNonFoglia(categoriaPadre, valore);

				creaFigliCategoria(nonFoglia);
			}
		}		
	}

	public CategoriaFoglia creaFoglia(Categoria padre, ValoreDominio valore) {
		String nomeFoglia;
		ArrayList<String> elencoNomiGerarchia = padre.getRadice().getNomiGerarchia();
		do {
			nomeFoglia = GerarchiaView.inserisciNomeFogliaGerarchia();
		} while(elencoNomiGerarchia.contains(nomeFoglia));
		CategoriaFoglia foglia = new CategoriaFoglia(nomeFoglia, valore, padre.getRadice());
		padre.getFigli().add(foglia);
		
		if(ElencoGerarchie.dueOpiuFoglie()) {
			FattoreDiConversione fdcNuovo = creaFattoreDiConversione(foglia);
			ElencoFattoriDiConversione.creaFDC_Deducibili(fdcNuovo);
			ElencoFattoriDiConversione.creaFDC_Deducibili(fdcNuovo.creaSimmetrico());
		}
		return foglia;
	}

	public CategoriaNonFoglia creaNonFoglia(Categoria padre, ValoreDominio valore) {
		String nomeNonFoglia;
		CategoriaNonFoglia nonFoglia;
		ArrayList<String> elencoNomiGerarchia = padre.getRadice().getNomiGerarchia();
		do {
			nomeNonFoglia = GerarchiaView.inserisciNomeNonFogliaGerarchia();
		} while(elencoNomiGerarchia.contains(nomeNonFoglia));

		String campo = GerarchiaView.inserisciCampo();
		ArrayList<ValoreDominio> dominio = creaDominio();

		nonFoglia = new CategoriaNonFoglia(nomeNonFoglia, valore, padre.getRadice(), campo, dominio);
		padre.getFigli().add(nonFoglia);
		return nonFoglia;
	}

	public FattoreDiConversione creaFattoreDiConversione(CategoriaFoglia fogliaNuova) {		
		FattoreDiConversione fdcNuovo;
		CategoriaFoglia f1, f2;
		double valore;
		do {
			visualizzaGerarchie();
			
			do {
				FDCView.inserimentoPrimaFoglia();
				
				f1 = selezionaCategoriaFogliaConRadiceFissata(fogliaNuova.getRadice());
			} while(f1 == null);

			do {
				FDCView.inserimentoSecondaFoglia();
				f2 = selezionaCategoriaFoglia();
			} while(f2 == null);

			valore = FDCView.inserisciValoreFDC();
			fdcNuovo = new FattoreDiConversione(f1, f2, valore);
		} while(ElencoFattoriDiConversione.verificaEsistenzaFDC(fdcNuovo) || fdcNuovo.fDCSullaStessaFoglia());

		ElencoFattoriDiConversione.aggiungiFDC(fdcNuovo);
		return fdcNuovo;
	}

	public CategoriaFoglia selezionaCategoriaFogliaConRadiceFissata(CategoriaRadice radice) {
		String nomeFoglia = GerarchiaView.inserisciNomeFogliaRicerca();
		String nomeRadice = GerarchiaView.inserisciNomeRadiceRicerca();

		CategoriaFoglia foglia = ElencoGerarchie.selezionaFoglia(nomeFoglia, nomeRadice);
		if(foglia == null){
			GerarchiaView.fogliaNonTrovata();
			return null;
		}
		if(!radice.getNome().equals(nomeRadice)) {
			FDCView.msgFogliaDiGerarchiaSbagliata(radice.getNome());
			return null;
		}
		else return foglia;
	}

	public CategoriaFoglia selezionaCategoriaFoglia() {
		String nomeFoglia = GerarchiaView.inserisciNomeFogliaRicerca();
		String nomeRadice = GerarchiaView.inserisciNomeRadiceRicerca();
		CategoriaFoglia foglia = ElencoGerarchie.selezionaFoglia(nomeFoglia, nomeRadice);

		if(foglia == null) {
			GerarchiaView.fogliaNonTrovata();
			return null;
		}
		else return foglia;
	}

	public CategoriaFoglia selezionaCategoriaFogliaPerFDC() {
		String nomeFoglia = FDCView.inserisciFogliaPerFDC();
		String nomeRadice = FDCView.inserisciRadicePerFDC();
		return ElencoGerarchie.selezionaFoglia(nomeFoglia, nomeRadice);
	}

	public ArrayList<ValoreDominio> creaDominio() {
		ArrayList<ValoreDominio> dominio = new ArrayList<>();
		String nomeValore;
		String descrizione;
		ArrayList<String> listaNomiValori = new ArrayList<>();

		do {
			descrizione = "Assente";
			
			nomeValore = GerarchiaView.inserisciNomeValoreDominio();
			while(listaNomiValori.contains(nomeValore)) {
				GerarchiaView.msgEsisteGiaNomeValoreDominio();
				nomeValore = GerarchiaView.inserisciNomeValoreDominio();
			}
			listaNomiValori.add(nomeValore);
			
			if(GerarchiaView.richiestaDescrizioneValoreDominio()) {
				descrizione = GerarchiaView.inserisciDescrizioneValoreDominio();	
			}
			dominio.add(new ValoreDominio(nomeValore, descrizione));
		} while(GerarchiaView.richiestaNomeValoreDominio());

		return dominio;
	}
	
	public static void visualizzaGerarchie() {
		for(Gerarchia gerarchia : ElencoGerarchie.getElencoGerarchie()) {
			GerarchiaView.visualizzaNomeRadiceGerarchia(gerarchia.getRadice().getNome());
			visualizzaFigliCategoria(gerarchia.getRadice());
		}
	}

	public static void visualizzaFigliCategoria(Categoria categoria) {
		for(Categoria c : categoria.getFigli()) {
			GerarchiaView.visualizzaNomeFiglioCategoria(categoria.getNome(), c.getNome(), c.getTipo());
			visualizzaFigliCategoria(c);
		}
	}

	public static void visualizzaFattoriDiConversione(CategoriaFoglia foglia) {
		if(foglia == null) {
			GerarchiaView.fogliaNonTrovata();
			return;
		}
		for(FattoreDiConversione fattore : ElencoFattoriDiConversione.getElencoFattoriDiConversione()) {
			if(fattore.getC1().verificaUguaglianzaFoglie(foglia)) {
				FDCView.visualizzaFattoreDiConversione(fattore.getC1().getNome(), fattore.getC1().getRadice().getNome(),
						fattore.getC2().getNome(), fattore.getC2().getRadice().getNome(), fattore.getValore());
			}
		}
	}
}
