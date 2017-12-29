package pl.lukasz.sparepartmanager.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.lukasz.sparepartmanager.bean.SessionManager;
import pl.lukasz.sparepartmanager.entity.Location;
import pl.lukasz.sparepartmanager.entity.Manufacturer;
import pl.lukasz.sparepartmanager.entity.Shipment;
import pl.lukasz.sparepartmanager.entity.SparePart;
import pl.lukasz.sparepartmanager.repository.LocationRepository;
import pl.lukasz.sparepartmanager.repository.ManufacturerRepository;
import pl.lukasz.sparepartmanager.repository.ShipmentRepository;
import pl.lukasz.sparepartmanager.repository.SparePartRepository;

@Controller
@RequestMapping("/sparepart")
public class SparePartController {
	@Autowired
	private SparePartRepository sparePartRepo;
	@Autowired
	private ManufacturerRepository manufacturerRepo;
	@Autowired
	private LocationRepository locationRepo;
	@Autowired
	private ShipmentRepository shipmentRepo;

	
	@GetMapping("/all")
	public String all(Model m) {
		return "sparepart/list";
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
	
	@GetMapping("global")
	public String globalGet(Model m) {
		List<SparePart> spareParts = this.sparePartRepo.findAllByCurrentLocationIsGlobal(true);
		m.addAttribute("spareParts", spareParts);
		return "sparepart/global";
	}
	
	@GetMapping("/{id}/shiptolocation")
	public String shipToLocationGet(@PathVariable int id, Model m) {
		SparePart sparePart = this.sparePartRepo.findOne(id);
		m.addAttribute("sparePart", sparePart);
		m.addAttribute("shipment", new Shipment());
		return "sparepart/ship";
	}
	
	@PostMapping("/{id}/shiptolocation")
	@Transactional
	public String shipToLocationGet(@PathVariable int id, @ModelAttribute Shipment shipment, 
									BindingResult bindingResult) {
		SparePart sparePart = sparePartRepo.findOne(id);
		sparePart.setCurrentStatus("Shipped to location");
		shipment.setOrigin(sparePart.getCurrentLocation());
		shipment.setSparePart(sparePart);
		shipment.setDateShipped(new Date());
		shipmentRepo.save(shipment);
		return "redirect:/sparepart/shippedtolocation";
	}
	
	@GetMapping("shippedtolocation")
	public String shippedToLocation(Model m) {
		List<Shipment> shipmentsToLocation = shipmentRepo.findAll();
		m.addAttribute("shipmentsToLocation", shipmentsToLocation);
		return "sparepart/shipments";
	}
	
	@GetMapping("{id}/shipments/cancel")
	public String shippedToLocationCancelGet(@PathVariable int id, Model m) {
		Shipment shipment = this.shipmentRepo.findOne(id);
		m.addAttribute("shipment", shipment);
		return "sparepart/confirmshipcancel";
	}
	
	@PostMapping("{id}/shipments/cancel")
	@Transactional
	public String shippedToLocationCancelPost(@PathVariable int id, Model m) {
		SparePart sparePart = sparePartRepo.findOne(id);
		sparePart.setCurrentStatus("Available");
		this.shipmentRepo.delete(id);
		return "redirect:/sparepart/shippedtolocation";
	}
	
	@GetMapping("{id}/shipments/edit")
	public String shippedToLocationEditGet(@PathVariable int id, Model m, RedirectAttributes ra) {
		Shipment shipment = this.shipmentRepo.findOne(id);
		HttpSession s = SessionManager.session();
		s.setAttribute("shipment", shipment);
		m.addAttribute("shipment", shipment);
		return "sparepart/editShipment";
	}
	
	@PostMapping("{id}/shipments/edit")
	public String shippedToLocationEditPost(@PathVariable int id, @ModelAttribute Shipment shipment) {
		HttpSession s = SessionManager.session();
		Shipment uneditedShipment = (Shipment) s.getAttribute("shipment");
		shipment = uneditedShipment.updateWith(shipment);
		this.shipmentRepo.save(shipment);
		return "redirect:/sparepart/shippedtolocation";
	}
	
	//Model attributes
	@ModelAttribute("availableSpareParts")
	public List<SparePart> availableSpareParts() {
		return sparePartRepo.findAll();
	}
	
	@ModelAttribute("availableManufacturers")
	public List<Manufacturer> availableManufacturers() {
		return manufacturerRepo.findAll();
	}
	
	@ModelAttribute("availableLocations")
	public List<Location> availableLocations() {
		return locationRepo.findAll();
	}
	
	@ModelAttribute("globalLocations")
	public List<Location> globalLocations() {
		return locationRepo.findAllByIsGlobal(true);
	}
	
	@ModelAttribute("localLocations")
	public List<Location> localLocations() {
		return locationRepo.findAllByIsGlobal(false);
	}
}