package it.unibs.model;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GestioneGerarchieXML {
	
	//PARTE SALVATAGGIO ======================================================================================================

	public static void salvaElencoGerarchieSuXML(String filePath) {
		Document doc = GestioneGeneraleXML.creaFileXML();

		Element elementoElencoGerarchie = doc.createElement("elencoGerarchie");
		doc.appendChild(elementoElencoGerarchie);

		for(Gerarchia gerarchia : ElencoGerarchie.getElencoGerarchie()) {
			Element elementoGerarchia = doc.createElement("gerarchia");
			
			Element elementoRadice = salvaCategoriaRadice(gerarchia.getRadice(), doc);
			salvaFigliCategoria(gerarchia.getRadice(), doc, elementoRadice);	
			elementoGerarchia.appendChild(elementoRadice);
			
			elementoElencoGerarchie.appendChild(elementoGerarchia);
		}
		GestioneGeneraleXML.salvaFileXML(doc, filePath);
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
		
		elementoRadice.appendChild(GestioneGeneraleXML.creaElemento(doc, "nome", categoria.getNome()));
		
		elementoRadice.appendChild(GestioneGeneraleXML.creaElemento(doc, "campo", categoria.getCampo()));
		
		Element elementoDominio = doc.createElement("dominio");
		
		for(ValoreDominio valore : categoria.getDominio()) {
			salvaValoreDominio(valore, doc, elementoDominio);
		}
		elementoRadice.appendChild(elementoDominio);
		
		return elementoRadice;
	}
	
	public static void salvaCategoriaFoglia(Categoria categoria, Document doc, Element elementoGerarchia) {
		elementoGerarchia.appendChild(GestioneGeneraleXML.creaElemento(doc, "nome", categoria.getNome()));
		
		elementoGerarchia.appendChild(GestioneGeneraleXML.creaElemento(doc, "radice", categoria.getRadice().getNome()));
		
		salvaValoreDominio(categoria.getValoreDominio(), doc, elementoGerarchia);
	}
	
	public static void salvaCategoriaNonFoglia(Categoria categoria, Document doc, Element elementoGerarchia) {
		elementoGerarchia.appendChild(GestioneGeneraleXML.creaElemento(doc, "nome", categoria.getNome()));
		
		elementoGerarchia.appendChild(GestioneGeneraleXML.creaElemento(doc, "campo", categoria.getCampo()));
		
		Element elementoDominio = doc.createElement("dominio");
		
		for(ValoreDominio valore : categoria.getDominio()) {
			salvaValoreDominio(valore, doc, elementoDominio);
		}
		elementoGerarchia.appendChild(elementoDominio);
		
		elementoGerarchia.appendChild(GestioneGeneraleXML.creaElemento(doc, "radice", categoria.getRadice().getNome()));
		
		salvaValoreDominio(categoria.getValoreDominio(), doc, elementoGerarchia);
	}
	
	public static void salvaValoreDominio(ValoreDominio valoreDominio, Document doc, Element elementoGerarchia) {
		Element elementoValoreDominio = doc.createElement("valoreDominio");
		
		elementoValoreDominio.appendChild(GestioneGeneraleXML.creaElemento(doc, "valore", valoreDominio.getValore()));
		
		elementoValoreDominio.appendChild(GestioneGeneraleXML.creaElemento(doc, "descrizione", valoreDominio.getDescrizione()));

		elementoGerarchia.appendChild(elementoValoreDominio);
	}
	
	//PARTE CARICAMENTO ======================================================================================================
	
	public static void caricaElencoGerarchieDaXML(String filePath) {
		Document doc = GestioneGeneraleXML.caricaFileXML(filePath);
		doc.getDocumentElement().normalize();

		NodeList listaGerarchie = doc.getElementsByTagName("gerarchia");

		for(int i = 0; i < listaGerarchie.getLength(); i++) {
			Node nodoGerarchia = listaGerarchie.item(i);
			if(nodoGerarchia.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoGerarchia = (Element) nodoGerarchia;
				
				Node nodoRadice = elementoGerarchia.getElementsByTagName("radice").item(0);
				if(nodoRadice.getNodeType() == Node.ELEMENT_NODE) {
					Element elementoRadice = (Element)nodoRadice;

					String nomeRadice = elementoRadice.getElementsByTagName("nome").item(0).getTextContent();

					String campoRadice = elementoRadice.getElementsByTagName("campo").item(0).getTextContent();

					ArrayList<ValoreDominio> dominioRadice = caricaValoriDominio(elementoRadice.getElementsByTagName("dominio").item(0));

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
		
		ValoreDominio valoreDominio = caricaValoreDominio(elemento.getElementsByTagName("valoreDominio").item(0));
		
		return new CategoriaFoglia(nome, valoreDominio, radice);
	}
	
	public static CategoriaNonFoglia caricaCategoriaNonFoglia(Element elemento, CategoriaRadice radice, ArrayList<Categoria> figli) {
		String nome = elemento.getElementsByTagName("nome").item(0).getTextContent();
		
		String campo = elemento.getElementsByTagName("campo").item(0).getTextContent();
		
		ValoreDominio valoreDominio = caricaValoreDominio(elemento.getElementsByTagName("valoreDominio").item(0));
		
		ArrayList<ValoreDominio> dominio = caricaValoriDominio(elemento.getElementsByTagName("dominio").item(0));
		
		return new CategoriaNonFoglia(nome, valoreDominio, radice, campo, dominio, figli);
	}
	
	public static ValoreDominio caricaValoreDominio(Node node) {
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			Element elementoValoreDominio = (Element)node;
			
			String valore = elementoValoreDominio.getElementsByTagName("valore").item(0).getTextContent();
			
			String descrizione = elementoValoreDominio.getElementsByTagName("descrizione").item(0).getTextContent();
			
			return new ValoreDominio(valore, descrizione);
		}
		return null;
	}

	public static ArrayList<ValoreDominio> caricaValoriDominio(Node node) {
		NodeList listaValori = node.getChildNodes();
		ArrayList<ValoreDominio> dominioRadice = new ArrayList<>();

		for(int i = 0; i < listaValori.getLength(); i++) {
			if(listaValori.item(i).getNodeName().equals("valoreDominio")) {
				dominioRadice.add(caricaValoreDominio(listaValori.item(i)));
			}
		}
		return dominioRadice;
	}
}
