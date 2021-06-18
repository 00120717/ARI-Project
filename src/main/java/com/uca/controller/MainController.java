package com.uca.controller;

import com.uca.convert.Convert_to_delim;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


@Controller
public class MainController {

	@RequestMapping("/")
	public ModelAndView initMain() throws IOException, ParseException {
		ModelAndView mav = new ModelAndView();
		//mav.addObject("jsontext",convert.TextToJson(""));
		mav.setViewName("home");

		Convert_to_delim c = new Convert_to_delim();
		//c.jsonToTxt("s",';');

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
