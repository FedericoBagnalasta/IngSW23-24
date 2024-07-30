package it.unibs.controller;

import it.unibs.fp.mylib.MyMenu;
import it.unibs.model.GestioneGeneraleXML;
import it.unibs.view.LoginView;
import it.unibs.view.XMLView;

public class MenuConfiguratore {

	private static final String CREA_COMPRENSORIO = "Crea un nuovo comprensorio geografico";
	private static final String CREA_GERARCHIA = "Crea una nuova gerarchia di categorie";
	private static final String VISUALIZZA_COMPRENSORI = "Visualizza tutti i comprensori";
	private static final String VISUALIZZA_GERARCHIE = "Visualizza tutte le gerarchie";
	private static final String VISUALIZZA_FDC = "Visualizza tutti i fattori di conversione di una certa foglia";
	private static final String SALVA_SU_XML = "Salva i dati sul file xml";
	private static final String FORMATTA_XML = "Formatta i file xml";
	private static final String SCELTA = "Scegli l'opzione";
	
	public static void menuConfiguratore(ComprensorioController comprensorio, GerarchiaController gerarchia) {
		String[] voci = {CREA_COMPRENSORIO, CREA_GERARCHIA, VISUALIZZA_COMPRENSORI, VISUALIZZA_GERARCHIE, VISUALIZZA_FDC, SALVA_SU_XML, FORMATTA_XML};
		int scelta = 0;
		MyMenu menu = new MyMenu(SCELTA, voci);

		do {
			scelta = menu.scegli();
			switch(scelta) {
			case 1:
				comprensorio.creaComprensorio();
				break;
			case 2:
				gerarchia.creaGerarchia();
				break;
			case 3:
				ComprensorioController.visualizzaComprensori();
				break;
			case 4:
				GerarchiaController.visualizzaGerarchie();
				break;
			case 5:
				GerarchiaController.visualizzaGerarchie();
				GerarchiaController.visualizzaFattoriDiConversione(
						gerarchia.selezionaCategoriaFogliaPerFDC());
				break;
			case 6:
				GestioneGeneraleXML.salvataggioCompleto();
				break;
			case 7:
				if(LoginView.confermaScelta()) {
					GestioneGeneraleXML.formattazioneCompleta();
					//Dopo averla fatta, avviare un'altra sessione
				}
				else{
					XMLView.msgFormattazioneNonAvvenuta();
				}
				break;
			default:
				break;
			}
		} while(scelta != 0);
	}
}