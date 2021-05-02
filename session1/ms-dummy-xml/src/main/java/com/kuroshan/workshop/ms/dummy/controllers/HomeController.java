package com.kuroshan.workshop.ms.dummy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/")
public class HomeController {

	@GetMapping(value = "")
	public String index(ModelMap model) {
		log.info(".:: home ::.");
		model.addAttribute("message", "Spring MVC");
		return "index";
	}

}
