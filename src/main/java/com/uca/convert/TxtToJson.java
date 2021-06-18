package com.uca.convert;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class TxtToJson {
	public JsonElement TextToJson(String text, char delim) throws JsonSyntaxException{
	
		String[] parts = text.split("\n");
			
		String json = "";
		int j = 0;
		for(int i=0; i<parts.length;i++) {
			String[] c = parts[i].split("\\"+delim);
			 json = "{\"documento\": \""+ c[j] + "\", \"primer_nombre\": \""+c[j+1]+"\", \"apellido\":\""+c[j+2]+"\",\"credit_card\": \""+c[j+3]+"\","
						+ " \"tipo\": \""+c[j+4]+"\",\"telefono\": \""+c[j+5]+"\"}, ";
			 j = 0;
		}
		
		String jsonR = "[" + json + "]";
		
		JsonObject jsonObject = new JsonParser().parse(jsonR).getAsJsonObject();
		return jsonObject;
	}
}
