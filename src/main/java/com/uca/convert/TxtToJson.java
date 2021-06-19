package com.uca.convert;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class TxtToJson {
	public JsonArray TextToJson(String text, char delim) throws JsonSyntaxException{
	
		String[] parts = text.split("\n");
		String FullJson = "";
			
		String json = "";
		int j = 0;
		for(int i=0; i<parts.length;i++) {
			String[] c = parts[i].split("\\"+delim);
			 json = json + "{\"documento\": \""+ c[j] + "\", \"primer_nombre\": \""+c[j+1]+"\", \"apellido\":\""+c[j+2]+"\",\"credit_card\": \""+c[j+3]+"\","
						+ " \"tipo\": \""+c[j+4]+"\",\"telefono\": \""+c[j+5]+"\"} ";
			 if(i<parts.length-1) {
				 json = json + ",";
			 }
			 
			 
			 j = 0;
		}
		
		String jsonR = "[" + json + "]";
		
		JsonArray jsonObject = new JsonParser().parse(jsonR).getAsJsonArray();
		return jsonObject;
	}
}
