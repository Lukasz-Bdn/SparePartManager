package pl.lukasz.sparepartmanager.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.lukasz.sparepartmanager.bean.SessionManager;
import pl.lukasz.sparepartmanager.entity.Location;
import pl.lukasz.sparepartmanager.entity.User;
import pl.lukasz.sparepartmanager.repository.LocationRepository;
import pl.lukasz.sparepartmanager.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private LocationRepository locationRepo;
	
	@GetMapping("/all")
	public String all(Model m) {
		List<User> userList = this.userRepo.findAll();
		m.addAttribute("userList", userList);
		return "user/list";
	}
	
	@GetMapping("/{id}/edit")
	public String editGet(@PathVariable int id, Model m) {
		User user = this.userRepo.findOne(id);
		m.addAttribute("user", user);
		List<Location> locations = this.locationRepo.findAll();
		HttpSession session = SessionManager.session();
		session.setAttribute("password", user.getPassword());
		m.addAttribute("locations", locations);
		return "user/editUser";
	}
	
	@PostMapping("/{id}/edit")
	@Transactional
	public String editPost(@ModelAttribute User user) {
		HttpSession session = SessionManager.session();
		user.setPasswordEncrypted(session.getAttribute("password").toString());
		session.setAttribute("password", null);
		this.userRepo.save(user);
		return "redirect:/user/all";
	}
}
