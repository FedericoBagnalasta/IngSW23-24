package it.unibs.model;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

public class XMLWriter {
	
	public static void salvataggioCompleto(String filePath) {
		salvaElencoComprensoriSuXML(filePath);
	}
	//Bisogna assicurarsi che il metodo per le gerarchie e quello per i comprensori non sovrascrivano
	//il file (perché non crearne 2?) a vicenda

	public static Document creaFileXML() {
		DocumentBuilderFactory docFactory;
		DocumentBuilder docBuilder;
		Document doc = null;
		try {
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();
			
		} catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}
		return doc;
	}
	
	public static void salvaElencoComprensoriSuXML(String filePath) {
		Document doc = creaFileXML();

		//Creazione elencoComprensori
		Element rootElement = doc.createElement("elencoComprensori");
		doc.appendChild(rootElement);

		for(Comprensorio comprensorio : ElencoComprensori.getElencoComprensori()) {
			//Creazione comprensorio
			Element nuovoComprensorio = doc.createElement("comprensorio");
			//Creazione nome comprensorio
			Element nome = doc.createElement("nome");
			nome.appendChild(doc.createTextNode(comprensorio.getNome()));
			nuovoComprensorio.appendChild(nome);

			rootElement.appendChild(nuovoComprensorio);

			for(String comune : comprensorio.getComuniComprensorio()) {
				//Creazione comune
				Element nomeComune = doc.createElement("comune");
				nomeComune.appendChild(doc.createTextNode(comune));
				nuovoComprensorio.appendChild(nomeComune);
			}
		}
		salvaFileXML(doc, filePath);
	}
	
	public static void salvaFileXML(Document doc, String filePath) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));

			transformer.transform(source, result);

			System.out.println("File salvato");

		} catch(TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
}