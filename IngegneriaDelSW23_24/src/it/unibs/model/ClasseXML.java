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

import it.unibs.view.XMLView;

import java.io.File;
import java.util.ArrayList;

public class ClasseXML {
	
	//PARTE FORMATTAZIONE ====================================================================================================
	
	public static void formattazioneCompleta() {
		formattaFileXML("/Users/matteokovacic/git/IngSW23-24/IngegneriaDelSW23_24/FileUtenti.xml", "elencoUtenti");
		formattaFileXML("/Users/matteokovacic/git/IngSW23-24/IngegneriaDelSW23_24/FileComprensori.xml", "elencoComprensori");
		formattaFileXML("/Users/matteokovacic/git/IngSW23-24/IngegneriaDelSW23_24/FileGerarchie.xml", "elencoGerarchie");
		formattaFileXML("/Users/matteokovacic/git/IngSW23-24/IngegneriaDelSW23_24/FileFattoriDiConversione.xml", "elencoFattoriDiConversione");
	}
	
	public static void formattaFileXML(String filePath, String elenco) {
		Document doc = creaFileXML();
		
		Element elementoElencoUtenti = doc.createElement(elenco);
		doc.appendChild(elementoElencoUtenti);
		
		salvaFileXML(doc, filePath);
	}
	
	//PARTE SALVATAGGIO ======================================================================================================
	
	public static void salvataggioCompleto() {
		salvaElencoUtentiSuXML("/Users/matteokovacic/git/IngSW23-24/IngegneriaDelSW23_24/FileUtenti.xml");
		salvaElencoComprensoriSuXML("/Users/matteokovacic/git/IngSW23-24/IngegneriaDelSW23_24/FileComprensori.xml");
		salvaElencoGerarchieSuXML("/Users/matteokovacic/git/IngSW23-24/IngegneriaDelSW23_24/FileGerarchie.xml");
		salvaElencoFDCSuXML("/Users/matteokovacic/git/IngSW23-24/IngegneriaDelSW23_24/FileFattoriDiConversione.xml");
	}
	
	public static void salvaElencoUtentiSuXML(String filePath) {
		Document doc = creaFileXML();
		
		Element elementoElencoUtenti = doc.createElement("elencoUtenti");
		doc.appendChild(elementoElencoUtenti);

		for(Utente utente : ElencoUtenti.getElencoUtenti()) {
			Element elementoUtente = doc.createElement("utente");
			
			elementoUtente.appendChild(creaElemento(doc, "nome", utente.getNome()));
			
			elementoUtente.appendChild(creaElemento(doc, "password", utente.getPassword()));
			
			elementoUtente.appendChild(creaElemento(doc, "ruolo", utente.getRuolo()));
			
			elementoElencoUtenti.appendChild(elementoUtente);
		}
		salvaFileXML(doc, filePath);
	}
		
	public static void salvaElencoComprensoriSuXML(String filePath) {
		Document doc = creaFileXML();
		
		Element elementoElencoComprensori = doc.createElement("elencoComprensori");
		doc.appendChild(elementoElencoComprensori);

		for(Comprensorio comprensorio : ElencoComprensori.getElencoComprensori()) {
			Element elementoComprensorio = doc.createElement("comprensorio");
			
			elementoComprensorio.appendChild(creaElemento(doc, "nome", comprensorio.getNome()));
			
			for(String comune : comprensorio.getComuniComprensorio()) {
				elementoComprensorio.appendChild(creaElemento(doc, "comune", comune));
			}
			elementoElencoComprensori.appendChild(elementoComprensorio);
		}
		salvaFileXML(doc, filePath);
	}
	
	public static void salvaElencoFDCSuXML(String filePath) {
		Document doc = creaFileXML();
		
		Element elementoElencoFDC = doc.createElement("elencoFattoriDiConversione");
		doc.appendChild(elementoElencoFDC);
		
		for(FattoreDiConversione fattore : ElencoFattoriDiConversione.getElencoFattoriDiConversione()) {
			Element elementoFDC = doc.createElement("fattore");
			
			Element elementoFoglia1 = doc.createElement("foglia1");
			salvaCategoriaFoglia(fattore.getC1(), doc, elementoFoglia1);
			elementoFDC.appendChild(elementoFoglia1);
			
			elementoFDC.appendChild(creaElemento(doc, "valoreFDC", String.valueOf(fattore.getValore())));
			
			Element elementoFoglia2 = doc.createElement("foglia2");
			salvaCategoriaFoglia(fattore.getC2(), doc, elementoFoglia2);
			elementoFDC.appendChild(elementoFoglia2);
			
			elementoElencoFDC.appendChild(elementoFDC);
		}
		salvaFileXML(doc, filePath);
	}
	
	public static void salvaElencoGerarchieSuXML(String filePath) {
		Document doc = creaFileXML();

		Element elementoElencoGerarchie = doc.createElement("elencoGerarchie");
		doc.appendChild(elementoElencoGerarchie);

		for(Gerarchia gerarchia : ElencoGerarchie.getElencoGerarchie()) {
			Element elementoGerarchia = doc.createElement("gerarchia");
			
			Element elementoRadice = salvaCategoriaRadice(gerarchia.getRadice(), doc);
			salvaFigliCategoria(gerarchia.getRadice(), doc, elementoRadice);	
			elementoGerarchia.appendChild(elementoRadice);
			
			elementoElencoGerarchie.appendChild(elementoGerarchia);
		}
		salvaFileXML(doc, filePath);
	}
	
	public static void salvaFigliCategoria(Categoria categoria, Document doc, Element elementoGerarchia) {
		for(Categoria cat : categoria.getFigli()) {
			Element nuovaCategoria = doc.createElement("categoria");
			
			if(cat.getTipo().equals("Foglia")) {
				salvaCategoriaFoglia(cat, doc, nuovaCategoria);
			}
			else {
				salvaCategoriaNonFoglia(cat, doc, nuovaCategoria);
			}
			elementoGerarchia.appendChild(nuovaCategoria);
			
			salvaFigliCategoria(cat, doc, nuovaCategoria);
		}
	}
	
	public static Element salvaCategoriaRadice(Categoria categoria, Document doc) {
		Element elementoRadice = doc.createElement("radice");
		
		elementoRadice.appendChild(creaElemento(doc, "nome", categoria.getNome()));
		
		elementoRadice.appendChild(creaElemento(doc, "campo", categoria.getCampo()));
		
		Element elementoDominio = doc.createElement("dominio");
		
		for(ValoreDominio valore : categoria.getDominio()) {
			salvaValoreDominio(valore, doc, elementoDominio);
		}
		elementoRadice.appendChild(elementoDominio);
		
		return elementoRadice;
	}
	
	public static void salvaCategoriaFoglia(Categoria categoria, Document doc, Element elementoGerarchia) {
		elementoGerarchia.appendChild(creaElemento(doc, "nome", categoria.getNome()));
		
		elementoGerarchia.appendChild(creaElemento(doc, "radice", categoria.getCategoriaRadice().getNome()));
		
		salvaValoreDominio(categoria.getValoreDominio(), doc, elementoGerarchia);
	}
	
	public static void salvaCategoriaNonFoglia(Categoria categoria, Document doc, Element elementoGerarchia) {
		elementoGerarchia.appendChild(creaElemento(doc, "nome", categoria.getNome()));
		
		elementoGerarchia.appendChild(creaElemento(doc, "campo", categoria.getCampo()));
		
		Element elementoDominio = doc.createElement("dominio");
		
		for(ValoreDominio valore : categoria.getDominio()) {
			salvaValoreDominio(valore, doc, elementoDominio);
		}
		elementoGerarchia.appendChild(elementoDominio);
		
		elementoGerarchia.appendChild(creaElemento(doc, "radice", categoria.getCategoriaRadice().getNome()));
		
		salvaValoreDominio(categoria.getValoreDominio(), doc, elementoGerarchia);
	}
	
	public static void salvaValoreDominio(ValoreDominio valoreDominio, Document doc, Element elementoGerarchia) {
		Element elementoValoreDominio = doc.createElement("valoreDominio");
		
		elementoValoreDominio.appendChild(creaElemento(doc, "valore", valoreDominio.getValore()));
		
		elementoValoreDominio.appendChild(creaElemento(doc, "descrizione", valoreDominio.getDescrizione()));

		elementoGerarchia.appendChild(elementoValoreDominio);
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

			XMLView.salvataggioFileXML();

		} catch(TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
	
	//PARTE CARICAMENTO ======================================================================================================
	
	public static void caricamentoCompleto() {
		caricaElencoUtentiDaXML("/Users/matteokovacic/git/IngSW23-24/IngegneriaDelSW23_24/FileUtenti.xml");
		caricaElencoComprensoriDaXML("/Users/matteokovacic/git/IngSW23-24/IngegneriaDelSW23_24/FileComprensori.xml");
		caricaElencoGerarchieDaXML("/Users/matteokovacic/git/IngSW23-24/IngegneriaDelSW23_24/FileGerarchie.xml");
		caricaElencoFDCDaXML("/Users/matteokovacic/git/IngSW23-24/IngegneriaDelSW23_24/FileFattoriDiConversione.xml");
	}
	
	public static void caricaElencoUtentiDaXML(String filePath) {
		Document doc = caricaFileXML(filePath);
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
	
	public static void caricaElencoComprensoriDaXML(String filePath) {
		Document doc = caricaFileXML(filePath);
		doc.getDocumentElement().normalize();

		NodeList listaComprensori = doc.getElementsByTagName("comprensorio");

		for(int i = 0; i < listaComprensori.getLength(); i++) {
			Node nodoComprensorio = listaComprensori.item(i);
			if(nodoComprensorio.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoComprensorio = (Element) nodoComprensorio;

				String nome = elementoComprensorio.getElementsByTagName("nome").item(0).getTextContent();

				NodeList listaComuni = elementoComprensorio.getElementsByTagName("comune");
				ArrayList<String> comuniComprensorio = new ArrayList<>();

				for(int j = 0; j < listaComuni.getLength(); j++) {
					Node nodoComune = listaComuni.item(j);
					if(nodoComune.getNodeType() == Node.ELEMENT_NODE) {
						comuniComprensorio.add(nodoComune.getTextContent());
					}
				}
				Comprensorio comprensorio = new Comprensorio(nome, comuniComprensorio);
				ElencoComprensori.getElencoComprensori().add(comprensorio);
			}
		}
	}
	
	public static void caricaElencoFDCDaXML(String filePath) {
		Document doc = caricaFileXML(filePath);
		doc.getDocumentElement().normalize();
		
		NodeList listaFDC = doc.getElementsByTagName("fattore");
		
		for(int i = 0; i < listaFDC.getLength(); i++) {
			Node nodoFattore = listaFDC.item(i);
			if(nodoFattore.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoFattore = (Element) nodoFattore;
				
				double valore = Double.parseDouble(elementoFattore.getElementsByTagName("valoreFDC").item(0).getTextContent());
				
				CategoriaFoglia foglia1 = null;
				CategoriaFoglia foglia2 = null;
				
				Node nodoFoglia1 = elementoFattore.getFirstChild();
				if(nodoFoglia1.getNodeType() == Node.ELEMENT_NODE) {
					Element elementoFoglia1 = (Element) nodoFoglia1;
					
					String nomeRadice1 = elementoFoglia1.getElementsByTagName("radice").item(0).getTextContent();
					CategoriaRadice radice1 = ElencoGerarchie.trovaRadice(nomeRadice1);
					
					foglia1 = caricaCategoriaFoglia(elementoFoglia1, radice1);
				}
				
				Node nodoFoglia2 = elementoFattore.getLastChild();
				if(nodoFoglia2.getNodeType() == Node.ELEMENT_NODE) {
					Element elementoFoglia2 = (Element) nodoFoglia2;
					
					String nomeRadice2 = elementoFoglia2.getElementsByTagName("radice").item(0).getTextContent();
					CategoriaRadice radice2 = ElencoGerarchie.trovaRadice(nomeRadice2);
					
					foglia2 = caricaCategoriaFoglia(elementoFoglia2, radice2);
				}
				FattoreDiConversione FDC = new FattoreDiConversione(foglia1, foglia2, valore);
				ElencoFattoriDiConversione.getElencoFattoriDiConversione().add(FDC);
			}
		}
	}
	
	public static void caricaElencoGerarchieDaXML(String filePath) {
		Document doc = caricaFileXML(filePath);
		doc.getDocumentElement().normalize();

		NodeList listaGerarchie = doc.getElementsByTagName("gerarchia");

		for(int i = 0; i < listaGerarchie.getLength(); i++) {
			Node nodoGerarchia = listaGerarchie.item(i);
			if(nodoGerarchia.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoGerarchia = (Element) nodoGerarchia;
				
				Node nodoRadice = elementoGerarchia.getFirstChild();
				if(nodoRadice.getNodeType() == Node.ELEMENT_NODE) {
					Element elementoRadice = (Element)nodoRadice;

					String nomeRadice = elementoRadice.getElementsByTagName("nome").item(0).getTextContent();

					String campoRadice = elementoRadice.getElementsByTagName("campo").item(0).getTextContent();

					ArrayList<ValoreDominio> dominioRadice = caricaValoriDominio(elementoRadice);

					CategoriaRadice radice = new CategoriaRadice(nomeRadice, campoRadice, dominioRadice);
					
					ArrayList<Categoria> figliRadice = new ArrayList<>();
					
					caricaFigliCategoria(elementoRadice, radice, figliRadice);
					
					Gerarchia gerarchia = new Gerarchia(nomeRadice, campoRadice, dominioRadice, figliRadice);
					ElencoGerarchie.getElencoGerarchie().add(gerarchia);
				}
			}
		}
	}
	
	public static void caricaFigliCategoria(Element elementoPadre, CategoriaRadice radice, ArrayList<Categoria> figli) {
		NodeList listaFigliPadre = elementoPadre.getChildNodes();
		
		for(int j = 0; j < listaFigliPadre.getLength(); j++) {
			Node nodoFiglio = listaFigliPadre.item(j);
			if(nodoFiglio.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoFiglio = (Element)nodoFiglio;
				
				if(elementoFiglio.getNodeName().equals("categoria")) {
				
				boolean isFoglia = true;
				NodeList listaFigliCategoria = elementoFiglio.getChildNodes();
					for(int k = 0; k < listaFigliCategoria.getLength(); k++) {
						if(listaFigliCategoria.item(k).getNodeName().equals("campo")) {
							isFoglia = false;
						}
					}
					if(isFoglia) {
						figli.add(caricaCategoriaFoglia(elementoFiglio, radice));
					}
					else {
						ArrayList<Categoria> figliNonFoglia = new ArrayList<Categoria>();
						caricaFigliCategoria(elementoFiglio, radice, figliNonFoglia);
						figli.add(caricaCategoriaNonFoglia(elementoFiglio, radice, figliNonFoglia));
					}
				}
			}
		}
	}
	
	public static CategoriaFoglia caricaCategoriaFoglia(Element elemento, CategoriaRadice radice) {
		String nome = elemento.getElementsByTagName("nome").item(0).getTextContent();
		
		ValoreDominio valoreDominio = caricaValoreDominio(elemento.getLastChild());
		
		return new CategoriaFoglia(nome, valoreDominio, radice);
	}
	
	public static CategoriaNonFoglia caricaCategoriaNonFoglia(Element elemento, CategoriaRadice radice, ArrayList<Categoria> figli) {
		String nome = elemento.getElementsByTagName("nome").item(0).getTextContent();
		
		String campo = elemento.getElementsByTagName("campo").item(0).getTextContent();
		
		ValoreDominio valoreDominio = caricaValoreDominio(elemento.getLastChild());
		
		ArrayList<ValoreDominio> dominio = caricaValoriDominio(elemento);
		
		return new CategoriaNonFoglia(nome, campo, valoreDominio, dominio, radice, figli);
	}
	
	public static ValoreDominio caricaValoreDominio(Node node) {
		Node nodoValoreDominio = node;
		if(nodoValoreDominio.getNodeType() == Node.ELEMENT_NODE) {
			Element elementoValoreDominio = (Element)nodoValoreDominio;
			
			String valore = elementoValoreDominio.getElementsByTagName("valore").item(0).getTextContent();
			
			String descrizione = elementoValoreDominio.getElementsByTagName("descrizione").item(0).getTextContent();
			
			return new ValoreDominio(valore, descrizione);
		}
		return null;
	}

	public static ArrayList<ValoreDominio> caricaValoriDominio(Element elemento) {
		NodeList listaValori = elemento.getElementsByTagName("valoreDominio");
		ArrayList<ValoreDominio> dominioRadice = new ArrayList<>();

		for(int i = 0; i < listaValori.getLength(); i++) {
			dominioRadice.add(caricaValoreDominio(listaValori.item(i)));
		}
		return dominioRadice;
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