package it.unibs.controller;

import java.util.ArrayList;

import it.unibs.model.CategoriaFoglia;
import it.unibs.model.ElencoFattoriDiConversione;
import it.unibs.model.ElencoInsiemiChiusi;
import it.unibs.model.ElencoScambi;
import it.unibs.model.FattoreDiConversione;
import it.unibs.model.Scambio;
import it.unibs.model.Utente;
import it.unibs.view.ScambioView;

public class ScambioController {

	private static final String APERTO = "Aperto";
	private static final String RITIRATO = "Ritirato";

	public static void creaScambio(Utente utente) {
		GerarchiaController gerarchiaController = new GerarchiaController();
		
		ScambioView.scegliFogliaRichiesta();
		CategoriaFoglia fogliaRichiesta = gerarchiaController.navigaGerarchiaFinoAFoglia();

		int oreRichiesta = ScambioView.inserisciOreRichiesta();

		ScambioView.scegliFogliaOfferta();
		CategoriaFoglia fogliaOfferta = gerarchiaController.navigaGerarchiaFinoAFoglia();
		while(fogliaOfferta.verificaUguaglianzaFoglie(fogliaRichiesta)) {
			ScambioView.msgStessaFoglia();
			fogliaOfferta = gerarchiaController.navigaGerarchiaFinoAFoglia();
		}

		FattoreDiConversione fdc = ElencoFattoriDiConversione.trovaFDC(fogliaRichiesta, fogliaOfferta);
		int oreOfferta = (int)(Math.round(oreRichiesta * fdc.getValore()));

		ScambioView.visualizzaScambio(fogliaRichiesta, oreRichiesta, fogliaOfferta, oreOfferta, APERTO);
		if(ScambioView.confermaScambio()) {
			Scambio nuovoScambio = new Scambio(fogliaRichiesta, fogliaOfferta, oreRichiesta, oreOfferta, APERTO, utente);

			//ref parte 2 (extract e move methods)
			ElencoInsiemiChiusi.controllaInsiemeChiuso(nuovoScambio);

			ElencoScambi.aggiungiScambio(nuovoScambio);
		}
		else {
			ScambioView.msgAnnullamentoScambio();
		}
	}
	
	public static void visualizzaScambiConfiguratore(GerarchiaController gerarchia) {
		if(ElencoScambi.getElencoScambi().size() == 0) {
			ScambioView.msgScambioAssente();
		}
		else {
			ScambioView.scegliFogliaRichiesta();
			CategoriaFoglia fogliaRichiesta = gerarchia.navigaGerarchiaFinoAFoglia();		
			
			ArrayList<Scambio> listaScambi = ElencoScambi.trovaScambioConFoglia(fogliaRichiesta);
			if(listaScambi.size() == 0) {
				ScambioView.msgScambioNonTrovato();
			}
			else {
				ScambioView.visualizzaScambiConfiguratore(fogliaRichiesta);
				
				for(Scambio scambio : listaScambi) {
					if(scambio.getFogliaRichiesta().verificaUguaglianzaFoglie(fogliaRichiesta) ||
							scambio.getFogliaOfferta().verificaUguaglianzaFoglie(fogliaRichiesta)) {
						ScambioView.visualizzaScambio(scambio.getFogliaRichiesta(), scambio.getOreRichiesta(),
								scambio.getFogliaOfferta(), scambio.getOreOfferta(), scambio.getStato());
					}
				}
			}
		}
	}
	
	public static void visualizzaScambiFruitore(Utente utente) {
		if(ElencoScambi.getElencoScambi().size() == 0) {
			ScambioView.msgScambioAssente();
		}
		else {
			ArrayList<Scambio> listaScambi = ElencoScambi.trovaScambioConFruitore(utente);
			if(listaScambi.size() == 0) {
				ScambioView.msgScambioNonTrovato();
			}
			else {
				ScambioView.visualizzaScambiFruitore();
				
				for(Scambio scambio : listaScambi) {
					ScambioView.visualizzaScambio(scambio.getFogliaRichiesta(), scambio.getOreRichiesta(),
							scambio.getFogliaOfferta(), scambio.getOreOfferta(), scambio.getStato());
				}
			}
		}
	}
	
	public static void cambiaStatoScambio(Utente utente) {
		ArrayList<Scambio> scambiRitirabili = new ArrayList<>();
		
		if(ElencoScambi.getElencoScambi().size() == 0) {
			ScambioView.msgScambioAssente();
		}
		else {
			ArrayList<Scambio> listaScambi = ElencoScambi.trovaScambioConFruitore(utente);
			for(Scambio s : listaScambi) {
				if(s.getStato().equals(APERTO)) {
					scambiRitirabili.add(s);
				}
			}
			if(scambiRitirabili.size() == 0) {
				ScambioView.msgScambioRitirabileAssente();
			}
			else {
				ScambioView.visualizzaScambiFruitore();

				for(Scambio scambio : scambiRitirabili) {
					ScambioView.visualizzaScambio(scambio.getFogliaRichiesta(), scambio.getOreRichiesta(),
							scambio.getFogliaOfferta(), scambio.getOreOfferta(), scambio.getStato());

					if(ScambioView.propostaRitiroScambio()) {
						scambio.setStato(RITIRATO);
					}
				}
			}
		}
	}
	
	public static void visualizzaInsiemiChiusi() {
		if(ElencoInsiemiChiusi.getElencoInsiemiChiusi().size() == 0) {
			ScambioView.msgInsiemeChiusoAssente();
		}
		else {
			ScambioView.visualizzaInsiemiChiusi();
			
			for(ArrayList<Scambio> anelloDiScambi : ElencoInsiemiChiusi.getElencoInsiemiChiusi()) {
				ScambioView.delimitazioneInsiemiChiusi();
				for(Scambio scambio : anelloDiScambi) {
					ScambioView.visualizzaScambio(scambio.getFogliaRichiesta(), scambio.getOreRichiesta(),
							scambio.getFogliaOfferta(), scambio.getOreOfferta(), scambio.getStato());
				}
			}
		}
	}
}