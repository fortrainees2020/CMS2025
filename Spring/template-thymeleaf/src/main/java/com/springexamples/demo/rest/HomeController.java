package com.springexamples.demo.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	String home(ModelMap modal) {
		modal.addAttribute("title", "Hi buddy");
		modal.addAttribute("message", "Welcome to SprinExamples.com");
		return "home";
	}
	
	@GetMapping("/hello")
	public String sayHello(Model theModel)
	{
		theModel.addAttribute("theDate", new java.util.Date());
	//	theModel.addAttribute("myempObj",empobj);
		return "helloWorld";
		
	}

}
