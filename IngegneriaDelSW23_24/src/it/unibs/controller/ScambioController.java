package it.unibs.controller;

import java.util.ArrayList;

import it.unibs.model.CategoriaFoglia;
import it.unibs.model.ElencoFattoriDiConversione;
import it.unibs.model.ElencoScambi;
import it.unibs.model.FattoreDiConversione;
import it.unibs.model.Scambio;
import it.unibs.model.Utente;
import it.unibs.view.ScambioView;

public class ScambioController {

	private static final String APERTO = "Aperto";
	private static final String CHIUSO = "Chiuso";
	private static final String RITIRATO = "Ritirato";

	public static void creaScambio(Utente utente) {
		GerarchiaController gerarchiaController = new GerarchiaController();
		boolean proposta;
		
		do {
			ScambioView.scegliFogliaRichiesta();
			CategoriaFoglia fogliaRichiesta = (CategoriaFoglia)gerarchiaController.navigaGerarchiaFinoAFoglia();
			int oreRichiesta = ScambioView.inserisciOreRichiesta();
			
			ScambioView.scegliFogliaOfferta();
			CategoriaFoglia fogliaOfferta = (CategoriaFoglia)gerarchiaController.navigaGerarchiaFinoAFoglia();
			while(fogliaOfferta.verificaUguaglianzaFoglie(fogliaRichiesta)) {
				ScambioView.msgStessaFoglia();
				fogliaOfferta = (CategoriaFoglia)gerarchiaController.navigaGerarchiaFinoAFoglia();
			}
			
			FattoreDiConversione fdc = ElencoFattoriDiConversione.trovaFDC(fogliaRichiesta, fogliaOfferta);
			int oreOfferta = (int)(Math.round(oreRichiesta * fdc.getValore()));
			
			ScambioView.visualizzaScambio(fogliaRichiesta.getNome(), fogliaOfferta.getNome(), oreRichiesta, oreOfferta);
			if(ScambioView.confermaScambio()) {
				Scambio scambio = new Scambio(fogliaRichiesta, fogliaOfferta, oreRichiesta, oreOfferta, APERTO, utente);
				
				scambio.setStato(APERTO);				
				
				Scambio scambioComplementare = ElencoScambi.trovaScambioComplementare(scambio);
				if(scambioComplementare != null) {
					scambio.setStato(CHIUSO);
					scambioComplementare.setStato(CHIUSO);
				}
				
				ElencoScambi.aggiungiScambio(scambio);
				break;
			} else {
				proposta = ScambioView.propostaNuovoScambio();
			}
		} while(proposta);
	}
	
	public static void visualizzaScambiConfiguratore(GerarchiaController gerarchia) {
		if(ElencoScambi.getElencoScambi().size() == 0) {
			ScambioView.msgScambioAssente();
		}
		else {
			ScambioView.scegliFogliaRichiesta();
			CategoriaFoglia fogliaRichiesta = (CategoriaFoglia)gerarchia.navigaGerarchiaFinoAFoglia();		
			
			ArrayList<Scambio> listaScambi = trovaScambioConFoglia(fogliaRichiesta);
			if(listaScambi.size() == 0) {
				ScambioView.msgScambioNonTrovato();
			}
			else {
				ScambioView.visualizzaScambiConfiguratore(fogliaRichiesta.getNome());
				
				for(Scambio scambio : listaScambi) {
					if(scambio.getFogliaRichiesta().verificaUguaglianzaFoglie(fogliaRichiesta) ||
							scambio.getFogliaOfferta().verificaUguaglianzaFoglie(fogliaRichiesta)) {
						ScambioView.visualizzaScambio(scambio.getFogliaRichiesta().getNome(),
								scambio.getFogliaOfferta().getNome(), scambio.getOreRichiesta(), scambio.getOreOfferta());
					}
				}
			}
		}
	}
	
	public static ArrayList<Scambio> trovaScambioConFoglia(CategoriaFoglia foglia) {
		ArrayList<Scambio> listaScambi = new ArrayList<>();
		
		for(Scambio scambio : ElencoScambi.getElencoScambi()) {
			if(scambio.getFogliaRichiesta().verificaUguaglianzaFoglie(foglia) ||
					scambio.getFogliaOfferta().verificaUguaglianzaFoglie(foglia)) {
				listaScambi.add(scambio);
			}
		}
		
		return listaScambi;
	}
	
	public static void visualizzaScambiFruitore(Utente utente) {
		if(ElencoScambi.getElencoScambi().size() == 0) {
			ScambioView.msgScambioAssente();
		}
		else {
			ArrayList<Scambio> listaScambi = trovaScambioConFruitore(utente);
			if(listaScambi.size() == 0) {
				ScambioView.msgScambioNonTrovato();
			}
			else {
				ScambioView.visualizzaScambiFruitore();
				
				for(Scambio scambio : listaScambi) {
					if(scambio.getUtente().getNome().equals(utente.getNome())) {
						ScambioView.visualizzaScambio(scambio.getFogliaRichiesta().getNome(),
								scambio.getFogliaOfferta().getNome(), scambio.getOreRichiesta(), scambio.getOreOfferta());
					}
				}
			}
		}
	}
	
	public static ArrayList<Scambio> trovaScambioConFruitore(Utente utente) {
		ArrayList<Scambio> listaScambi = new ArrayList<>();
		
		for(Scambio scambio : ElencoScambi.getElencoScambi()) {
			if(scambio.getUtente().getNome().equals(utente.getNome())) {
				listaScambi.add(scambio);
			}
		}
		
		return listaScambi;
	}
	
	public static void cambiaStatoScambio(Utente utente) {
		ScambioView.visualizzaScambiFruitore();
		
		for(Scambio scambio : ElencoScambi.getElencoScambi()) {
			if(scambio.getUtente().getNome().equals(utente.getNome())) {
				ScambioView.visualizzaScambio(scambio.getFogliaRichiesta().getNome(),
						scambio.getFogliaOfferta().getNome(), scambio.getOreRichiesta(), scambio.getOreOfferta());
				
				if(ScambioView.propostaRitiroScambio()) {
					scambio.setStato(RITIRATO);
				}
			}
		}
	}
}