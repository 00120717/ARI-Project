package com.uca.convert; 

import org.springframework.boot.json.GsonJsonParser;

public class Txt_to_Json {
	public GsonJsonParser TextToJson(String text) {
		GsonJsonParser parser = new GsonJsonParser();
		
		text = "01234567-7;Marvin;Ramirez;1234567890;GOLD;22225555";
		
		String string = ""; 
		
		for(int i = 0; i < text.length(); ++i ) {
			
		}
		
		return parser;
	}
	
}
