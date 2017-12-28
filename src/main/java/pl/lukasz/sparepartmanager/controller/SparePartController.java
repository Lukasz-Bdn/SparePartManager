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
import pl.lukasz.sparepartmanager.entity.SparePart;
import pl.lukasz.sparepartmanager.repository.LocationRepository;
import pl.lukasz.sparepartmanager.repository.SparePartRepository;

@Controller
@RequestMapping("/sparepart")
public class SparePartController {
	@Autowired
	private SparePartRepository sparePartRepo;
	
	@GetMapping("/all")
	public String all(Model m) {
		return "location/list";
	}
	
	@GetMapping("/addform")
	public String addformGet(Model m) {
		m.addAttribute("sparePart", new SparePart());
		return "sparepart/addSparePart";
	}
	
	@PostMapping("/addform")
	public String addformPost(@Valid @ModelAttribute SparePart sparePart, 
								BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "sparepart/addSparePart";
		}
		this.sparePartRepo.save(sparePart);
		return "redirect:/sparepart/all";
	}
	
	@GetMapping("/{id}/edit")
	public String editGet(@PathVariable int id, Model m) {
		SparePart sparePart = this.sparePartRepo.findOne(id);
		m.addAttribute("sparePart", sparePart);
		return "sparepart/addSparePart";
	}
	
	@PostMapping("/{id}/edit")
	public String editPost(@ModelAttribute SparePart sparePart) {
		this.sparePartRepo.save(sparePart);
		return "redirect:/sparepart/all";
	}
	
	@GetMapping("/{id}/delete")
	public String deleteGet(@PathVariable int id, Model m) {
		SparePart sparePart = this.sparePartRepo.findOne(id);
		m.addAttribute("sparePart", sparePart);
		return "sparepart/confirm";
	}
	
	@PostMapping("/{id}/delete")
	public String deletePost(@PathVariable int id) {
		this.sparePartRepo.delete(id);
		return "redirect:/sparepart/all";
	}
	
	//Model attributes
	@ModelAttribute("availableSpareParts")
	public List<SparePart> availableSpareParts() {
		return sparePartRepo.findAll();
	}
}
