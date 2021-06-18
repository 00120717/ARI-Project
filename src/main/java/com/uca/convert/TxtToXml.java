package com.uca.convert;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


public class TxtToXml {
	public void generate(String text, char delim) throws Exception{
		
		String [] cliente = text.split("\\"+delim);
		System.out.println("TAMAÑO "+cliente.length);
        if(cliente.length == 0){
            System.out.println("ERROR empty ArrayList");
            return;
            
        }else{

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "clientes", null);
            document.setXmlVersion("1.0");

            //Main Node
            Element raiz = document.getDocumentElement();
            int j = 0; 
            for(int i=0; i<((cliente.length+1)/6);i+=1){
            	System.out.println("ITERACION "+cliente[10]);
                Element clienteNode = document.createElement("cliente"); 
           
                Element documentoNode = document.createElement("documento"); 
                Text nodeDocumentoValue = document.createTextNode(cliente[j]);
                documentoNode.appendChild(nodeDocumentoValue);      
           
                Element nombreNode = document.createElement("primer-nombre"); 
                Text nodeNombreValue = document.createTextNode(cliente[j+1]);                
                nombreNode.appendChild(nodeNombreValue);
                
                Element apellidoNode = document.createElement("apellido"); 
                Text nodeApellidoValue = document.createTextNode(cliente[j+2]);                
                apellidoNode.appendChild(nodeApellidoValue);
                
                Element cardNode = document.createElement("credit-card"); 
                Text nodeCardValue = document.createTextNode(cliente[j+3]);                
                cardNode.appendChild(nodeCardValue);
                
                Element tipoNode = document.createElement("tipo"); 
                Text nodeTipoValue = document.createTextNode(cliente[j+4]);                
                tipoNode.appendChild(nodeTipoValue);
                
                Element telefonoNode = document.createElement("telefono"); 
                Text nodeTelefonoValue = document.createTextNode(cliente[j+5]);                
                telefonoNode.appendChild(nodeTelefonoValue);
                
                clienteNode.appendChild(documentoNode);
                clienteNode.appendChild(nombreNode);
                clienteNode.appendChild(apellidoNode);
                clienteNode.appendChild(cardNode);
                clienteNode.appendChild(tipoNode);
                clienteNode.appendChild(telefonoNode);
                
                //append itemNode to raiz
                raiz.appendChild(clienteNode); //pegamos el elemento a la raiz "Documento"
                j+=6;
            }                
            //Generate XML
            Source source = new DOMSource(document);
            //Indicamos donde lo queremos almacenar
            Result result = new StreamResult(new java.io.File("clientes.xml")); //nombre del archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            
        }
		
    }
}
