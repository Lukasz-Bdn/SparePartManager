package pl.lukasz.sparepartmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		return "home/home";
	}
	
	@GetMapping("login")
	public String login() {
		return "home/login";
	}
}