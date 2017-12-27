package pl.lukasz.sparepartmanager.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.lukasz.sparepartmanager.entity.Location;
import pl.lukasz.sparepartmanager.repository.LocationRepository;

@Controller
@RequestMapping("/location")
public class LocationController {
	@Autowired
	private LocationRepository locationRepo;
	
	@GetMapping("/all")
	public String all(Model m) {
		return "location/list";
	}
	
	@GetMapping("/addform")
	public String addformGet(Model m) {
		m.addAttribute("location", new Location());
		return "location/addLocation";
	}
	
	@PostMapping("/addform")
	public String addformPost(@Valid @ModelAttribute Location location, 
								BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "location/addLocation";
		}
		this.locationRepo.save(location);
		return "redirect:/location/all";
	}
	
	@GetMapping("/{id}/edit")
	public String editGet(@PathVariable int id, Model m) {
		Location location = this.locationRepo.findOne(id);
		m.addAttribute("location", location);
		return "location/addLocation";
	}
	
	@PostMapping("/{id}/edit")
	public String editPost(@ModelAttribute Location location) {
		this.locationRepo.save(location);
		return "redirect:/location/all";
	}
	
	
	//Model attributes
	@ModelAttribute("availableLocations")
	public List<Location> availableLocations() {
		return locationRepo.findAll();
	}
}
