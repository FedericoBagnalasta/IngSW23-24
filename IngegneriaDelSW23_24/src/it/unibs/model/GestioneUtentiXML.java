package it.unibs.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GestioneUtentiXML {

	//PARTE SALVATAGGIO ======================================================================================================

	public static void salvaElencoUtentiSuXML(String filePath) {
		Document doc = GestioneGeneraleXML.creaFileXML();
		Element elementoElencoUtenti = doc.createElement("elencoUtenti");

		doc.appendChild(elementoElencoUtenti);

		for(Utente utente : ElencoUtenti.getElencoUtenti()) {
			Element elementoUtente = doc.createElement("utente");
			
			if(utente.getRuolo().equals("Configuratore")) {
				salvaInfoBaseSuXML(elementoUtente, doc, utente, elementoElencoUtenti);
			}
			else {
				salvaInfoExtraSuXML(elementoUtente, doc, utente, elementoElencoUtenti);
			}
			//salvaUtenteSuXML(elementoUtente, doc, utente, elementoElencoUtenti);
		}
		GestioneGeneraleXML.salvaFileXML(doc, filePath);
	}
	
	public static void salvaInfoBaseSuXML(Element elementoUtente, Document doc, Utente utente, Element elementoElencoUtenti) {
		elementoUtente.appendChild(GestioneGeneraleXML.creaElemento(doc, "nome", utente.getNome()));
		
		elementoUtente.appendChild(GestioneGeneraleXML.creaElemento(doc, "password", utente.getPassword()));
		
		elementoUtente.appendChild(GestioneGeneraleXML.creaElemento(doc, "ruolo", utente.getRuolo()));
		
		elementoElencoUtenti.appendChild(elementoUtente);
	}
	
	public static void salvaInfoExtraSuXML(Element elementoUtente, Document doc, Utente utente, Element elementoElencoUtenti) {	
		salvaInfoBaseSuXML(elementoUtente, doc, utente, elementoElencoUtenti);
		
		GestioneComprensoriXML.salvaComprensorioSuXML(doc, utente.getComprensorio(), elementoUtente);
		
		elementoUtente.appendChild(GestioneGeneraleXML.creaElemento(doc, "indirizzo", utente.getIndirizzo()));
		
		elementoElencoUtenti.appendChild(elementoUtente);
	}
	
	/*
	public static void salvaUtenteSuXML(Element elementoUtente, Document doc, Utente utente, Element elementoElencoUtenti) {
		elementoUtente.appendChild(GestioneGeneraleXML.creaElemento(doc, "nome", utente.getNome()));
		
		elementoUtente.appendChild(GestioneGeneraleXML.creaElemento(doc, "password", utente.getPassword()));
		
		elementoUtente.appendChild(GestioneGeneraleXML.creaElemento(doc, "ruolo", utente.getRuolo()));
		
		elementoUtente.appendChild(GestioneGeneraleXML.creaElemento(doc, "comprensorio", utente.getComprensorio().getNome()));
		
		GestioneComprensoriXML.salvaComprensorioSuXML(doc, utente.getComprensorio(), elementoUtente);
		
		elementoUtente.appendChild(GestioneGeneraleXML.creaElemento(doc, "indirizzo", utente.getIndirizzo()));
		
		elementoElencoUtenti.appendChild(elementoUtente);
	}
	*/
	
	//PARTE CARICAMENTO ======================================================================================================

	public static void caricaElencoUtentiDaXML(String filePath) {
		Document doc = GestioneGeneraleXML.caricaFileXML(filePath);
		doc.getDocumentElement().normalize();

		NodeList listaUtenti = doc.getElementsByTagName("utente");

		for(int i = 0; i < listaUtenti.getLength(); i++) {
			Node nodoUtente = listaUtenti.item(i);

			if(nodoUtente.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoUtente = (Element) nodoUtente;
				
				Utente utente;

				String nome = elementoUtente.getElementsByTagName("nome").item(0).getTextContent();
				
				String password = elementoUtente.getElementsByTagName("password").item(0).getTextContent();
				
				String ruolo = elementoUtente.getElementsByTagName("ruolo").item(0).getTextContent();
				
				if(elementoUtente.getElementsByTagName("comprensorio").item(0) != null) {
					Comprensorio comprensorio = GestioneComprensoriXML.caricaComprensorioSuXML(nodoUtente);
					
					String indirizzo = elementoUtente.getElementsByTagName("indirizzo").item(0).getTextContent();
					
					utente = new Utente(nome, password, ruolo, comprensorio, indirizzo);
				}
				else {
					utente = new Utente(nome, password, ruolo);
				}
				
				ElencoUtenti.getElencoUtenti().add(utente);
			}
		}
	}
}