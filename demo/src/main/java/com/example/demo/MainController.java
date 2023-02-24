package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	
	@GetMapping("/main")
	public String  index() {
		return "jsp/main";
	}
	
	@GetMapping("/")
	public String  root() {
		return "redirect:/question/list";
	}
	
}
