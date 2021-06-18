package com.uca.controller;

import com.uca.convert.Convert_to_delim;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.uca.convert.TxtToJson;
import com.uca.convert.TxtToXml;


@Controller
public class MainController {

	TxtToJson convert = new TxtToJson();
	@RequestMapping("/")

	public ModelAndView initMain() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		TxtToXml conv = new TxtToXml();
		TxtToJson cvt = new TxtToJson();
		/*conv.generate("03423423;rober;fuentes;04534534532;GOLD;2343243523\n" + 
				"353452323;alberto;alfaro;0534534523;PLATINUM;3423523432", ';');*/
		System.out.println(cvt.TextToJson("03423423;rober;fuentes;04534534532;GOLD;2343243523\n" + 
				"353452323;alberto;alfaro;0534534523;PLATINUM;3423523432", ';'));
		
		mav.setViewName("home2");
		return mav;
	}

	@RequestMapping("/send")
	public ModelAndView formTextJson(@ModelAttribute String text ) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		mav.addObject("jsontext","Json text");
		return mav;
	}

}
