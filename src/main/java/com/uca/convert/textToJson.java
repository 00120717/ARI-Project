package com.uca.convert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.uca.domain.Cliente;

public class textToJson {
	List<Cliente> list_clientes = new ArrayList<>();
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
			 
			 list_clientes.add(cliente);
			 j = 0;
		}
		
		String jsonR = "[" + json + "]";
		
		//JsonArray jsonObject = new JsonParser().parse(jsonR).getAsJsonArray();
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			// Writing to a file   
	        mapper.writeValue(new File("/app/src/main/resources/descargaArchivos/clientes.json"), list_clientes );
        } catch (IOException e) {
            e.printStackTrace();
        } 
		
		
	}
}
