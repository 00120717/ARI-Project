package com.uca.convert;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


public class TxtToXml {
	public void generate(String text, char delim) throws Exception{
		
		String [] cliente = text.split("\n");
		
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
            for(int i=0; i<cliente.length;i+=1){
            	String [] c = cliente[i].split("\\"+delim);
            	
                Element clienteNode = document.createElement("cliente"); 
           
                Element documentoNode = document.createElement("documento"); 
                Text nodeDocumentoValue = document.createTextNode(c[j]);
                documentoNode.appendChild(nodeDocumentoValue);      
           
                Element nombreNode = document.createElement("primer_nombre");
                Text nodeNombreValue = document.createTextNode(c[j+1]);                
                nombreNode.appendChild(nodeNombreValue);
                
                Element apellidoNode = document.createElement("apellido"); 
                Text nodeApellidoValue = document.createTextNode(c[j+2]);                
                apellidoNode.appendChild(nodeApellidoValue);
                
                Element cardNode = document.createElement("credit_card");
                Text nodeCardValue = document.createTextNode(c[j+3]);                
                cardNode.appendChild(nodeCardValue);
                
                Element tipoNode = document.createElement("tipo"); 
                Text nodeTipoValue = document.createTextNode(c[j+4]);                
                tipoNode.appendChild(nodeTipoValue);
                
                Element telefonoNode = document.createElement("telefono"); 
                Text nodeTelefonoValue = document.createTextNode(c[j+5]);                
                telefonoNode.appendChild(nodeTelefonoValue);
                
                clienteNode.appendChild(documentoNode);
                clienteNode.appendChild(nombreNode);
                clienteNode.appendChild(apellidoNode);
                clienteNode.appendChild(cardNode);
                clienteNode.appendChild(tipoNode);
                clienteNode.appendChild(telefonoNode);                
                //append itemNode to raiz
                raiz.appendChild(clienteNode); //pegamos el elemento a la raiz "Documento"
                j+=0;
            }                
            //Generate XML
            Source source = new DOMSource(document);
            //Indicamos donde lo queremos almacenar
            Result result = new StreamResult(new java.io.File("/app/src/main/resources/descargaArchivos/clientes.xml")); //nombre del archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            
        }
		
    }
}
