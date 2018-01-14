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
import pl.lukasz.sparepartmanager.entity.PartCatalog;
import pl.lukasz.sparepartmanager.repository.ManufacturerRepository;
import pl.lukasz.sparepartmanager.repository.PartCatalogRepository;

@Controller
@RequestMapping("/partcatalog")
public class PartCatalogController {
	@Autowired
	private PartCatalogRepository partCatalogRepo;
	@Autowired
	private ManufacturerRepository manufacturerRepo;
	
	@GetMapping("/all")
	public String all(Model m) {
		return "partcatalog/list";
	}
	
	@GetMapping("/addform")
	public String addformGet(Model m) {
		m.addAttribute("partCatalog", new PartCatalog());
		return "partcatalog/addPartCatalog";
	}
	
	@PostMapping("/addform")
	public String addformPost(@Valid @ModelAttribute PartCatalog partCatalog, 
								BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "partcatalog/addPartCatalog";
		}
		this.partCatalogRepo.save(partCatalog);
		return "redirect:/partcatalog/all";
	}
	
	@GetMapping("/{id}/edit")
	public String editGet(@PathVariable int id, Model m) {
		PartCatalog partCatalog = this.partCatalogRepo.findOne(id);
		m.addAttribute("partCatalog", partCatalog);
		return "partcatalog/addPartCatalog";
	}
	
	@PostMapping("/{id}/edit")
	public String editPost(@ModelAttribute PartCatalog partCatalog) {
		this.partCatalogRepo.save(partCatalog);
		return "redirect:/partcatalog/all";
	}
	
	@GetMapping("/{id}/delete")
	public String deleteGet(@PathVariable int id, Model m) {
		PartCatalog partCatalog = this.partCatalogRepo.findOne(id);
		m.addAttribute("partCatalog", partCatalog);
		return "partcatalog/confirm";
	}
	
	@PostMapping("/{id}/delete")
	public String deletePost(@PathVariable int id, Model m) {
		try {
			this.partCatalogRepo.delete(id);
			return "redirect:/partcatalog/all";
		} catch (Exception e) {
			m.addAttribute("msg", "Sorry you cannot remove item from catalog "
					+ "if some parts are still present (including shipment history).");
			return "partcatalog/confirm";
		}
	}
	
	//Model attributes
	@ModelAttribute("availablePartCatalogs")
	public List<PartCatalog> availablePartCatalogs() {
		return this.partCatalogRepo.findAll();
	}
	
	@ModelAttribute("availableManufacturers")
	public List<Manufacturer> availableManufacturers() {
		return manufacturerRepo.findAll();
	}

}

