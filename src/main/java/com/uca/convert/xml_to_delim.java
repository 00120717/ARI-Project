package com.uca.convert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.uca.domain.Cliente;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class xml_to_delim {

    List<Cliente> list_clientes = new ArrayList<>();

    //read data
    public void readXML() {
        try {
            SAXBuilder builder = new SAXBuilder();
            File xml = new File("src/main/resources/clientes.xml");

            Document document = builder.build(xml);

            Element root = document.getRootElement();
            List<Element> list = root.getChildren("cliente");

            //System.out.println("Documento\tPrimer_nombre\tApellido");
            for (int i = 0; i < list.size(); i++) {
                Cliente cliente = new Cliente();
                Element clienteaux = list.get(i);

                cliente.setDocumento(clienteaux.getChildTextTrim("documento"));
                cliente.setPrimer_nombre(clienteaux.getChildTextTrim("primer_nombre"));
                cliente.setApellido(clienteaux.getChildTextTrim("apellido"));
                cliente.setCredit_card(clienteaux.getChildTextTrim("credit_card"));
                cliente.setTipo(clienteaux.getChildTextTrim("tipo"));
                cliente.setTelefono(clienteaux.getChildTextTrim("telefono"));

                list_clientes.add(cliente);
            }

        } catch (JDOMException | IOException ex) {
            Logger.getLogger(xml_to_delim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveDocByDelim(){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("file.txt"));
            for (int i = 0; i < list_clientes.size(); i++) {
                out.write(list_clientes.get(i).getDocumento() + ";" + list_clientes.get(i).getPrimer_nombre() + ";" + list_clientes.get(i).getApellido() + ";"
                        + list_clientes.get(i).getCredit_card() + ";" + list_clientes.get(i).getTipo() + ";" + list_clientes.get(i).getTelefono() + ";");
                out.newLine();
            }
            out.close();
        } catch (IOException e) {}
    }

}
