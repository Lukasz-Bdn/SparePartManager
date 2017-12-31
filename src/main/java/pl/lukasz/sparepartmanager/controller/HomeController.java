package pl.lukasz.sparepartmanager.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.lukasz.sparepartmanager.bean.LoginData;
import pl.lukasz.sparepartmanager.bean.SessionManager;
import pl.lukasz.sparepartmanager.entity.Location;
import pl.lukasz.sparepartmanager.entity.User;
import pl.lukasz.sparepartmanager.entity.UserRole;
import pl.lukasz.sparepartmanager.repository.UserRepository;
import pl.lukasz.sparepartmanager.repository.UserRoleRepository;

@Controller("/")
public class HomeController {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserRoleRepository userRoleRepo;
	
	@GetMapping("/")
	public String home() {
		return "home/home";
	}
	
	@GetMapping("login")
	public String loginGet(Model m) {
		m.addAttribute("loginData", new LoginData());
		return "home/login";
	}
	
	@PostMapping("login")
	public String loginPost(@ModelAttribute LoginData loginData, BindingResult bindingResult
							, Model m) {
		User user = this.userRepo.findOneByUsername(loginData.getUsername());
		if(user!=null && user.isPasswordCorrent(loginData.getPassword())) {
			HttpSession session = SessionManager.session();
			session.setAttribute("user", user);
			session.setAttribute("userRole", this.userRoleRepo.findOneByUserId(user.getId()));
			return "redirect:/";
		}
		m.addAttribute("msg", "Please use valid login data");
		return "home/login";
	}
	
	@GetMapping("register")
	public String registerGet(Model m) {
		m.addAttribute("user", new User());
		return "home/register";
	}
	
	@PostMapping("register")
	public String registerPost(@ModelAttribute User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "redirect:register";
		} else {
			this.userRepo.save(user);
			UserRole userRole = new UserRole();
			userRole.setRole("ROLE_USER");
			userRole.setUser(user);
			return "redirect:/login";
		}
	}
	
	@GetMapping("logout")
	public String logoutGet() {
		HttpSession session = SessionManager.session();
		session.invalidate();
		return "redirect:/";
	}
}