package it.unibs.controller;

import java.util.ArrayList;


import it.unibs.model.Categoria;
import it.unibs.model.CategoriaFoglia;
import it.unibs.model.CategoriaNonFoglia;
import it.unibs.model.CategoriaRadice;
import it.unibs.model.ElencoComprensori;
import it.unibs.model.ElencoGerarchie;
import it.unibs.model.Comprensorio;
import it.unibs.model.ElencoComprensori;
import it.unibs.model.ElencoUtenti;
import it.unibs.model.Gerarchia;
import it.unibs.model.Utente;
import it.unibs.model.ValoreDominio;
import it.unibs.view.ConfiguratoreView;
import it.unibs.view.LoginView;

public class ConfiguratoreController extends Utente {//Da tenere extends Utente? 
	
	
	public ConfiguratoreController(String nome, String password, String ruolo) {
		super(nome, password, ruolo);
	}
	
	public void creaGerarchia() {
		String nomeRadice=ConfiguratoreView.inserisciNomeRadiceGerarchia();
		while(!ElencoGerarchie.verificaOriginalitaRadiceNome(nomeRadice)) {
			ConfiguratoreView.nomegiaPresente();
			nomeRadice = ConfiguratoreView.inserisciNomeRadiceGerarchia();
		}
		String campo = ConfiguratoreView.inserisciCampo();
		ArrayList<ValoreDominio> dominio = ConfiguratoreView.inserisciDominio();
		Gerarchia nuovaGerarchia = ElencoGerarchie.aggiungiGerarchia(nomeRadice, campo, dominio);
		creaFigliCategoria(nuovaGerarchia.getRadice());//Si occupa di creare la struttura (Foglie e NonFolgie) della gerarchia
	}
	
	private void creaFigliCategoria(Categoria categoriaPadre) {
		//DEVO POTER SCEGLIERE A QUALE CATEGORIA COLLEGARE IL NUOVO ELEMENTO	
		
		do {
			for(ValoreDominio valore: categoriaPadre.getDominio()) {
				if(ConfiguratoreView.richiestaAggiuntaCategoriaFoglia(valore)) {
					CategoriaFoglia foglia = creaFoglia(categoriaPadre, valore);
					categoriaPadre.getFigli().add(foglia); //AGGIUNGE IL FIGLIO NELL'ELENCO DEL PADRE					
				}
				else if(ConfiguratoreView.richiestaAggiuntaCategoriaNonFoglia(valore)) {
					CategoriaNonFoglia nonFoglia = creaNonFoglia(categoriaPadre, valore);
					
					//CHIAMATA RICORSIVA
					creaFigliCategoria(nonFoglia);
					
					categoriaPadre.getFigli().add(nonFoglia);
				}
			}
		}while(ConfiguratoreView.richiestaContinuazioneStruttura());//Finchè l'utente lo desidera, continua a creare figli
	}
	
	public CategoriaFoglia creaFoglia(Categoria padre, ValoreDominio valore) {
		String nomeFoglia = ConfiguratoreView.inserisciNomeFoglia();//manca controllo sul nome della foglia
		CategoriaFoglia foglia = new CategoriaFoglia(nomeFoglia, valore);
		
		//CHIEDE SE (FORSE OBBLIGATORIO) VUOLE AGGIUNGERE DEI FATTORI DI CONVERSIONE
		
		return foglia;
	}
	
	public CategoriaNonFoglia creaNonFoglia(Categoria padre, ValoreDominio valore) {
		String nomeNonFoglia = ConfiguratoreView.inserisciNomeNonFoglia(); 
		String campo = ConfiguratoreView.inserisciCampo();
		ArrayList<ValoreDominio> dominio = ConfiguratoreView.inserisciDominio();//METODO PER IL DOMINIO DA SPOSTARE IN CONTROLLER
		return new CategoriaNonFoglia(nomeNonFoglia, campo, valore, dominio);
		
		
	}
	
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
	
	
	public void creaComprensorio() {
		String nomeComprensorio=ConfiguratoreView.inserisciNomeComprensorio();
		while(!ElencoComprensori.verificaOriginalita(nomeComprensorio)) {//Verifica se il nome non è già presente nella lista dei comprensori
			ConfiguratoreView.nomegiaPresente();
			nomeComprensorio=ConfiguratoreView.inserisciNomeComprensorio();
		}
		ArrayList<String> elencoComuni=ConfiguratoreView.inserisciComuniComprensorio();
		ElencoComprensori.aggiungiComprensorio(nomeComprensorio, elencoComuni);
		*/

	public String creaNomeComprensorio() {
		String nomeComprensorio;
		do {
			nomeComprensorio = ConfiguratoreView.inserisciNomeComprensorio();
		} while(ElencoComprensori.verificaComprensorio(nomeComprensorio));
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
	
	//Se questa classe eredita da Utente, si riesce a rendere questo metodo non-static
	
	public static void cambiaCredenziali(Utente utente) {
		String nome;
		String password;
		
		do {
			nome = LoginView.inserisciNome();
		} while(ElencoUtenti.isDuplicato(nome));
		
		utente.setNome(nome);
		password = LoginView.inserisciPassword();
		utente.setPassword(password);
	}
	

	/*
	public static Utente cambiaCredenz() {
		String nome;
		String password;
		String ruolo = "Configuratore";
		
		do {
			nome = LoginView.inserisciNome();
		} while(LoginModel.verificaDuplicati(nome));	//oppure si usa direttamente ElencoUtenti.controllaDuplicati()
		password = LoginView.inserisciPassword();
		
		return LoginModel.creaUtente(nome, password, ruolo);
	}
	*/
	
	
}