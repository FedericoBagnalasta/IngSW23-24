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

import it.unibs.view.XMLView;

import java.io.File;

public class GestioneGeneraleXML {
	
	//PARTE FORMATTAZIONE ====================================================================================================
	
	public static void formattazioneCompleta() {
		formattaFileXML("resources/FileUtenti.xml", "elencoUtenti");
		formattaFileXML("resources/FileComprensori.xml", "elencoComprensori");
		formattaFileXML("resources/FileGerarchie.xml", "elencoGerarchie");
		formattaFileXML("resources/FileFattoriDiConversione.xml", "elencoFattoriDiConversione");
	}
	
	public static void formattaFileXML(String filePath, String elenco) {
		Document doc = creaFileXML();
		
		Element elementoElencoUtenti = doc.createElement(elenco);
		doc.appendChild(elementoElencoUtenti);
		
		salvaFileXML(doc, filePath);
	}
	
	//PARTE SALVATAGGIO ======================================================================================================
	
	public static void salvataggioCompleto() {
		GestioneUtentiXML.salvaElencoUtentiSuXML("resources/FileUtenti.xml");
		GestioneComprensoriXML.salvaElencoComprensoriSuXML("resources/FileComprensori.xml");
		GestioneGerarchieXML.salvaElencoGerarchieSuXML("resources/FileGerarchie.xml");
		GestioneFattoriDiConversioneXML.salvaElencoFDCSuXML("resources/FileFattoriDiConversione.xml");
	}
	
	public static Element creaElemento(Document doc, String tagElemento, String datoElemento) {
		Element elemento = doc.createElement(tagElemento);
		elemento.appendChild(doc.createTextNode(datoElemento));
		return elemento;
	}
	
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
	
	public static void salvaFileXML(Document doc, String filePath) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.transform(source, result);

			XMLView.salvataggioFileXML(filePath.substring(10));

		} catch(TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
	
	//PARTE CARICAMENTO ======================================================================================================
	
	public static void caricamentoCompleto() {
		GestioneUtentiXML.caricaElencoUtentiDaXML("resources/FileUtenti.xml");
		GestioneComprensoriXML.caricaElencoComprensoriDaXML("resources/FileComprensori.xml");
		GestioneGerarchieXML.caricaElencoGerarchieDaXML("resources/FileGerarchie.xml");
		GestioneFattoriDiConversioneXML.caricaElencoFDCDaXML("resources/FileFattoriDiConversione.xml");
	}
	
	public static Document caricaFileXML(String filePath) {
		File inputFile;
		DocumentBuilderFactory dbFactory;
		DocumentBuilder dBuilder;
		Document doc = null;
		
		try {
			inputFile = new File(filePath);
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(inputFile);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
}