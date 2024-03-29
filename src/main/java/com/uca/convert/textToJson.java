package com.uca.convert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonSyntaxException;
import com.uca.controller.clienteController;
import com.uca.domain.Cliente;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class textToJson {
	List<String> list_clientes = new ArrayList<>();
	List<Cliente> list_clientesC = new ArrayList<>();
	clienteController clienteController = new clienteController();

	public void TextToJson(String text, char delim) throws JsonSyntaxException{
		
		String[] parts = text.split("\n");
		String FullJson = "";
			
		String json = "";
		int j = 0;
		
		for(int i=0; i<parts.length;i++) {
			String[] c = parts[i].split("\\"+delim);
			Cliente cliente = new Cliente();
			
			 json = json + "{\"documento\": \""+ c[j] + "\", \"primer_nombre\": \""+c[j+1]+"\", \"apellido\":\""+c[j+2]+"\",\"credit_card\": \""+c[j+3]+"\","
						+ " \"tipo\": \""+c[j+4]+"\",\"telefono\": \""+c[j+5]+"\"} ";
	
			 cliente.setDocumento(c[j]);
			 cliente.setPrimer_nombre(c[j+1]);
			 cliente.setApellido(c[j+2]);
			 cliente.setCredit_card(c[j+3]);
			 cliente.setTipo(c[j+4]);
			 cliente.setTelefono(c[j+5]);

			 list_clientes.add(clienteController.generateToken(cliente));
			 j = 0;
		}
		
		String jsonR = "[" + json + "]";

		ObjectMapper mapper = new ObjectMapper();
		
		try {
			//listString to listCliente
			for (String list_cliente : list_clientes) {
				list_clientesC.add(clienteController.parseToken(list_cliente));
			}

			// Writing to a file   
	        mapper.writeValue(new File("src/main/resources/descargaArchivos/clientes.json"), list_clientesC );
        } catch (IOException e) {
            e.printStackTrace();
        } 
		
		
	}
}
