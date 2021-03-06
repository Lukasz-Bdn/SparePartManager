package pl.lukasz.sparepartmanager.controller;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.lukasz.sparepartmanager.entity.User;
import pl.lukasz.sparepartmanager.repository.UserRepository;

@Controller("/")
public class HomeController {
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/")
	public String home() {
		return "home/home";
	}
	
	@GetMapping("login")
	public String loginGet() {
		return "home/login";
	}
	
	@PostMapping("login") 
	public String loginPost() {
		return "redirect:/";
	}
	
	@GetMapping("register")
	public String registerGet(Model m) {
		m.addAttribute("user", new User());
		return "home/register";
	}
	
	@PostMapping("register")
	public String registerPost(@Valid @ModelAttribute User user, BindingResult bindingResult, Model m) {
			if(bindingResult.hasErrors()) {
				return "redirect:register";
			} else {
				user.setUserRole("ROLE_USER");
				try {
					this.userRepo.save(user);
				} catch (Exception e) {
					m.addAttribute("msg", "User with this email and/or username is already present."
							+ " Please select different email/username or login using existing "
							+ "credentials");
					return "home/register";
				}
				return "redirect:/login";
			}
	}

	@GetMapping("403")
	public String accessDenied() {
		return "errors/accessDenied";
	}
}