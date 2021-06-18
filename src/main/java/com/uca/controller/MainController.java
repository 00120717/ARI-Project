package com.uca.controller;

<<<<<<< Updated upstream
import com.uca.convert.Convert_to_delim;
import org.json.simple.parser.ParseException;
=======
import java.io.Console;

>>>>>>> Stashed changes
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

<<<<<<< Updated upstream
import java.io.IOException;
=======
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.uca.convert.TxtToJson;
import com.uca.convert.TxtToXml;
>>>>>>> Stashed changes


@Controller
public class MainController {

	@RequestMapping("/")
<<<<<<< Updated upstream
	public ModelAndView initMain() throws IOException, ParseException {
		ModelAndView mav = new ModelAndView();
		//mav.addObject("jsontext",convert.TextToJson(""));
		mav.setViewName("home");

		Convert_to_delim c = new Convert_to_delim();
		//c.jsonToTxt("s",';');

=======
	public ModelAndView initMain() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		TxtToXml conv = new TxtToXml();
		conv.generate("03423423;rober;fuentes;04534534532;GOLD;2343243523\r\n" + 
				"353452323;alberto;alfaro;0534534523;PLATINUM;3423523432",';');
>>>>>>> Stashed changes
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
