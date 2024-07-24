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

	/*
    public static void main(String[] args) {
        Persona persona = new Persona("Mario Rossi", 30);
        salvaPersonaSuXML(persona, "persona.xml");
    }
	*/
    public static void salvaComprensorioSuXML(Comprensorio comprensorio, String filePath) {
        try {
            // Creazione del documento XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Elemento radice
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("comprensorio");
            doc.appendChild(rootElement);
            
           for(String c : comprensorio.getComuniComprensorio()) {
        	   Element comune = doc.createElement("comune");
               comune.appendChild(doc.createTextNode(comprensorio.getNome()));
               rootElement.appendChild(comune);
           }
            
            
            
/*
            // Nome
            Element nome = doc.createElement("nome");
            nome.appendChild(doc.createTextNode(persona.getNome()));
            rootElement.appendChild(nome);
*/
           

            // Scrittura del contenuto su file XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));

            transformer.transform(source, result);

            System.out.println("File salvato!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}