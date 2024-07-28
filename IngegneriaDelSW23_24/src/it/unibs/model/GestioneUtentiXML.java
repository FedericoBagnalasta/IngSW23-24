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
			
			elementoUtente.appendChild(GestioneGeneraleXML.creaElemento(doc, "nome", utente.getNome()));
			
			elementoUtente.appendChild(GestioneGeneraleXML.creaElemento(doc, "password", utente.getPassword()));
			
			elementoUtente.appendChild(GestioneGeneraleXML.creaElemento(doc, "ruolo", utente.getRuolo()));
			
			elementoElencoUtenti.appendChild(elementoUtente);
		}
		GestioneGeneraleXML.salvaFileXML(doc, filePath);
	}
	
	//PARTE CARICAMENTO ======================================================================================================
	
	public static void caricaElencoUtentiDaXML(String filePath) {
		Document doc = GestioneGeneraleXML.caricaFileXML(filePath);
		doc.getDocumentElement().normalize();

		NodeList listaUtenti = doc.getElementsByTagName("utente");

		for(int i = 0; i < listaUtenti.getLength(); i++) {
			Node nodoUtente = listaUtenti.item(i);
			if(nodoUtente.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoUtente = (Element) nodoUtente;

				String nome = elementoUtente.getElementsByTagName("nome").item(0).getTextContent();
				
				String password = elementoUtente.getElementsByTagName("password").item(0).getTextContent();
				
				String ruolo = elementoUtente.getElementsByTagName("ruolo").item(0).getTextContent();
				
				Utente utente = new Utente(nome, password, ruolo);
				ElencoUtenti.getElencoUtenti().add(utente);
			}
		}
	}
}
