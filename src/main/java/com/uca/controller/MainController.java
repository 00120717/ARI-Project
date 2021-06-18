package com.uca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.uca.convert.TxtToJson;


@Controller
public class MainController {
	
	TxtToJson convert = new TxtToJson();
	@RequestMapping("/")
	public ModelAndView initMain() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("jsontext",convert.TextToJson(""));
		mav.setViewName("index");
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
