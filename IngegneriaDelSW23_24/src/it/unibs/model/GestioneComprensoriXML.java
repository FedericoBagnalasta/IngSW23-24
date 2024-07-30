package it.unibs.model;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GestioneComprensoriXML {

	//PARTE SALVATAGGIO ======================================================================================================

	public static void salvaElencoComprensoriSuXML(String filePath) {
		Document doc = GestioneGeneraleXML.creaFileXML();
		Element elementoElencoComprensori = doc.createElement("elencoComprensori");

		doc.appendChild(elementoElencoComprensori);

		for(Comprensorio comprensorio : ElencoComprensori.getElencoComprensori()) {

			salvaComprensorioSuXML(doc, comprensorio, elementoElencoComprensori);
		}
		GestioneGeneraleXML.salvaFileXML(doc, filePath);
	}

	public static void salvaComprensorioSuXML(Document doc, Comprensorio comprensorio, Element elementoPadre) {
		Element elementoComprensorio = doc.createElement("comprensorio");

		elementoComprensorio.appendChild(GestioneGeneraleXML.creaElemento(doc, "nome", comprensorio.getNome()));

		for(String comune : comprensorio.getComuniComprensorio()) {
			elementoComprensorio.appendChild(GestioneGeneraleXML.creaElemento(doc, "comune", comune));
		}
		elementoPadre.appendChild(elementoComprensorio);
	}

	//PARTE CARICAMENTO ======================================================================================================

	public static void caricaElencoComprensoriDaXML(String filePath) {
		Document doc = GestioneGeneraleXML.caricaFileXML(filePath);
		doc.getDocumentElement().normalize();

		NodeList listaComprensori = doc.getElementsByTagName("comprensorio");

		for(int i = 0; i < listaComprensori.getLength(); i++) {
			Node nodoComprensorio = listaComprensori.item(i);

			if(nodoComprensorio.getNodeType() == Node.ELEMENT_NODE) {

				Comprensorio comprensorio = caricaComprensorioSuXML(nodoComprensorio);

				ElencoComprensori.getElencoComprensori().add(comprensorio);
			}
		}
	}

	public static Comprensorio caricaComprensorioSuXML(Node nodoComprensorio) {
		Element elementoComprensorio = (Element)nodoComprensorio;

		String nome = elementoComprensorio.getElementsByTagName("nome").item(0).getTextContent();

		NodeList listaComuni = elementoComprensorio.getElementsByTagName("comune");
		ArrayList<String> comuniComprensorio = new ArrayList<>();

		for(int j = 0; j < listaComuni.getLength(); j++) {
			Node nodoComune = listaComuni.item(j);
			if(nodoComune.getNodeType() == Node.ELEMENT_NODE) {
				comuniComprensorio.add(nodoComune.getTextContent());
			}
		}
		return new Comprensorio(nome, comuniComprensorio);
	}
}