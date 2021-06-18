package com.uca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {
	
	@RequestMapping("/inicio")
	public ModelAndView initMain() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
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
