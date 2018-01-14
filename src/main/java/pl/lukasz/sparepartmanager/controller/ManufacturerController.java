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

import pl.lukasz.sparepartmanager.entity.Manufacturer;
import pl.lukasz.sparepartmanager.repository.ManufacturerRepository;

@Controller
@RequestMapping("/manufacturer")
public class ManufacturerController {
	@Autowired
	private ManufacturerRepository manufacturerRepo;
	
	@GetMapping("/all")
	public String all(Model m) {
		return "manufacturer/list";
	}
	
	@GetMapping("/addform")
	public String addformGet(Model m) {
		m.addAttribute("manufacturer", new Manufacturer());
		return "manufacturer/addManufacturer";
	}
	
	@PostMapping("/addform")
	public String addformPost(@Valid @ModelAttribute Manufacturer manufacturer, 
								BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "manufacturer/addManufacturer";
		}
		this.manufacturerRepo.save(manufacturer);
		return "redirect:/manufacturer/all";
	}
	
	@GetMapping("/{id}/edit")
	public String editGet(@PathVariable int id, Model m) {
		Manufacturer manufacturer = this.manufacturerRepo.findOne(id);
		m.addAttribute("manufacturer", manufacturer);
		return "manufacturer/addManufacturer";
	}
	
	@PostMapping("/{id}/edit")
	public String editPost(@ModelAttribute Manufacturer manufacturer) {
		this.manufacturerRepo.save(manufacturer);
		return "redirect:/manufacturer/all";
	}
	
	@GetMapping("/{id}/delete")
	public String deleteGet(@PathVariable int id, Model m) {
		Manufacturer manufacturer = this.manufacturerRepo.findOne(id);
		m.addAttribute("manufacturer", manufacturer);
		return "manufacturer/confirm";
	}
	
	@PostMapping("/{id}/delete")
	public String deletePost(@PathVariable int id, Model m) {
		try {
			this.manufacturerRepo.delete(id);
			return "redirect:/manufacturer/all";
		} catch (Exception e) {
			m.addAttribute("msg", "Sorry you cannot delete manufacturers if his parts " 
					+ "are still present (including shipment history).");
			return "manufacturer/confirm";
		}
	}
	
	//Model attributes
	@ModelAttribute("availableManufacturers")
	public List<Manufacturer> availableManufacturers() {
		return manufacturerRepo.findAll();
	}
}
