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
	//Bisogna assicurarsi che il metodo per le gerarchie non sovrascriva quello che scrive quelle per i comprensori

	public static void salvaElencoComprensoriSuXML(String filePath) {
		try {
			//Creazione file xml
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

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
			
			//Inserimento dei dati sul file xml
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));

			transformer.transform(source, result);

			System.out.println("File salvato");

		} catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch(TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
}