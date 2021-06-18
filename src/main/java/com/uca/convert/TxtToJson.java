package com.uca.convert;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class TxtToJson {
	public JsonElement TextToJson(String text) throws JsonSyntaxException{
		
		text = "01234567-7;Marvin;Ramirez;1234567890;GOLD;22225555";
		String documento,primer_nombre,apellido,credit_card,tipo,telefono = "";
		
		
		
		String[] parts = text.split("\\;");
		
		documento = parts[0];
		primer_nombre = parts[1];
		apellido = parts[2];
		credit_card = parts[3];
		tipo = parts[4];
		telefono = parts[5];
		
		//String string = "{ \"documento\": \"01234567-7\", \"primer_nombre\": \"Marvin\", \"apellido\":\"Ramirez\",\"credit_card\": \"04534534532\","
		//		+ " \"tipo\": \"GOLD\",\"telefono\": \"123456789\"}"; 
		
		String json = "{ \"documento\": \""+ documento + "\", \"primer_nombre\": \""+primer_nombre+"\", \"apellido\":\""+apellido+"\",\"credit_card\": \""+credit_card+"\","
				+ " \"tipo\": \""+tipo+"\",\"telefono\": \""+telefono+"\"}";
		
		
		JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
		return jsonObject;
	}
}
