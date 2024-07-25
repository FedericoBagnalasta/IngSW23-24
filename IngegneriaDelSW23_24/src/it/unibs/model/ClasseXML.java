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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.unibs.view.ConfiguratoreView;

import java.io.File;
import java.util.ArrayList;

public class ClasseXML {
	
	//PARTE SALVATAGGIO ======================================================================================
	
	public static void salvataggioCompleto() {
		salvaElencoComprensoriSuXML("/Users/matteokovacic/git/IngSW23-24/IngegneriaDelSW23_24/FileComprensori.xml");
		salvaElencoGerarchieSuXML("/Users/matteokovacic/git/IngSW23-24/IngegneriaDelSW23_24/FileGerarchie.xml");
	}
	//Bisogna assicurarsi che il metodo per le gerarchie e quello per i comprensori non sovrascrivano
	//il file (perché non crearne 2?) a vicenda
	
	public static void salvaElencoComprensoriSuXML(String filePath) {
		Document doc = creaFileXML();

		//Salvataggio elencoComprensori (radice)
		Element rootElement = doc.createElement("elencoComprensori");
		doc.appendChild(rootElement);

		for(Comprensorio comprensorio : ElencoComprensori.getElencoComprensori()) {
			//Salvataggio comprensorio (figlio della radice)
			Element nuovoComprensorio = doc.createElement("comprensorio");
			//Salvataggio nome comprensorio
			Element nome = doc.createElement("nome");
			nome.appendChild(doc.createTextNode(comprensorio.getNome()));
			nuovoComprensorio.appendChild(nome);

			rootElement.appendChild(nuovoComprensorio);

			for(String comune : comprensorio.getComuniComprensorio()) {
				//Salvataggio comune (figlio del figlio)
				Element nomeComune = doc.createElement("comune");
				nomeComune.appendChild(doc.createTextNode(comune));
				nuovoComprensorio.appendChild(nomeComune);
			}
		}
		salvaFileXML(doc, filePath);
	}
	
	public static void salvaElencoGerarchieSuXML(String filePath) {
		Document doc = creaFileXML();

		//Salvataggio elencoGerarchie (radice)
		Element rootElement = doc.createElement("elencoGerarchie");
		doc.appendChild(rootElement);

		for(Gerarchia gerarchia : ElencoGerarchie.getElencoGerarchie()) {
			//Salvataggio gerarchia (figlio della radice)
			Element nuovaGerarchia = doc.createElement("gerarchia");
			//Salvataggio nome radice gerarchia
			Element nomeRadice = doc.createElement("nomeRadice");
			nomeRadice.appendChild(doc.createTextNode(gerarchia.getRadice().getNome()));
			nuovaGerarchia.appendChild(nomeRadice);
			
			rootElement.appendChild(nuovaGerarchia);
			
			ricorsione(gerarchia.getRadice(), doc, nuovaGerarchia);
			
			
			
				
		}
		salvaFileXML(doc, filePath);
	}
	
	public static void ricorsione(Categoria categoria, Document doc, Element nuovaGerarchia) {
		for(Categoria categoriaFiglio : categoria.getFigli()) {
			Element nuovaCategoria = doc.createElement("categoria");
			
			Element nomeCategoria = doc.createElement("nomeCategoria");
			nomeCategoria.appendChild(doc.createTextNode(categoriaFiglio.getNome()));
			nuovaCategoria.appendChild(nomeCategoria);
			
			nuovaGerarchia.appendChild(nuovaCategoria);
		}
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

			ConfiguratoreView.salvataggioFileXML();

		} catch(TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
	
	//PARTE CARICAMENTO ======================================================================================
	
	public static void caricamentoCompleto() {
		caricaElencoComprensoriDaXML("/Users/matteokovacic/git/IngSW23-24/IngegneriaDelSW23_24/FileComprensori.xml");
		//caricaElencoGerarchieDaXML(filePath);
	}
	
	public static void caricaElencoComprensoriDaXML(String filePath) {
		Document doc = caricaFileXML(filePath);
		doc.getDocumentElement().normalize();

		NodeList listaComprensori = doc.getElementsByTagName("comprensorio");

		for(int i = 0; i < listaComprensori.getLength(); i++) {
			Node nodoComprensorio = listaComprensori.item(i);

			if(nodoComprensorio.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nodoComprensorio;

				//Caricamento del nome del comprensorio
				String nome = element.getElementsByTagName("nome").item(0).getTextContent();

				NodeList listaComuni = element.getElementsByTagName("comune");
				ArrayList<String> comuniComprensorio = new ArrayList<>();

				for(int j = 0; j < listaComuni.getLength(); j++) {
					Node nodoComune = listaComuni.item(j);
					if (nodoComune.getNodeType() == Node.ELEMENT_NODE) {
						//Salvataggio in un arraylist dei comuni del comprensorio
						comuniComprensorio.add(nodoComune.getTextContent());
					}
				}
				Comprensorio comprensorio = new Comprensorio(nome, comuniComprensorio);
				ElencoComprensori.getElencoComprensori().add(comprensorio);
			}
		}
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