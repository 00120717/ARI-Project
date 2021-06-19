package com.uca.convert;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.uca.domain.Cliente;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * xml/json to txt
 **/
public class Convert_to_delim {

    List<Cliente> list_clientes = new ArrayList<>();

    /**
     * xml to txt
     **/
    public void xmlToTxt(char delim) {
        readXML();
        saveDocByDelim(delim);
    }

    /**
     * json to txt
     **/
    public void jsonToTxt(char delim) throws IOException, ParseException {
        readJson();
        saveDocByDelim(delim);
    }


    //read data xml
    public void readXML() {
        try {
            SAXBuilder builder = new SAXBuilder();
            File xml = new File("src/main/resources/subidaArchivos/clientes.xml");

            Document document = builder.build(xml);

            Element root = document.getRootElement();
            List<Element> list = root.getChildren("cliente");

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
            Logger.getLogger(Convert_to_delim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //read data json
    public void readJson() throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(
                "src/main/resources/subidaArchivos/clientes.json"));

        for (Object o : jsonArray) {
            JSONObject person = (JSONObject) o;
            Cliente cliente = new Cliente();

            cliente.setDocumento((String) person.get("documento"));
            cliente.setPrimer_nombre((String) person.get("primer_nombre"));
            cliente.setApellido((String) person.get("apellido"));
            cliente.setCredit_card((String) person.get("credit_card"));
            cliente.setTipo((String) person.get("tipo"));
            cliente.setTelefono((String) person.get("telefono"));

            list_clientes.add(cliente);

        }
    }

    //save data txt
    public void saveDocByDelim(char delim) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/resources/descargaArchivos/clientes.txt"));
            for (Cliente list_cliente : list_clientes) {
                out.write(list_cliente.getDocumento() + delim + list_cliente.getPrimer_nombre() + delim + list_cliente.getApellido() + delim
                        + list_cliente.getCredit_card() + delim + list_cliente.getTipo() + delim + list_cliente.getTelefono());
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
