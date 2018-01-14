package pl.lukasz.sparepartmanager.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
import pl.lukasz.sparepartmanager.entity.PartCatalog;
import pl.lukasz.sparepartmanager.entity.Shipment;
import pl.lukasz.sparepartmanager.entity.SparePart;
import pl.lukasz.sparepartmanager.entity.User;
import pl.lukasz.sparepartmanager.repository.LocationRepository;
import pl.lukasz.sparepartmanager.repository.ManufacturerRepository;
import pl.lukasz.sparepartmanager.repository.PartCatalogRepository;
import pl.lukasz.sparepartmanager.repository.ShipmentRepository;
import pl.lukasz.sparepartmanager.repository.SparePartRepository;
import pl.lukasz.sparepartmanager.repository.UserRepository;
import pl.lukasz.sparepartmanager.utility.LocationFilter;

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
	@Autowired
	private PartCatalogRepository partCatalogRepo;
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/all")
	public String all(Model m) {
		List<SparePart> availableSpareParts = new ArrayList<>();
		if(getLoggedUser().getUserRole().equals("ROLE_ADMIN")) {
			availableSpareParts = this.sparePartRepo.findAll();
		} else {
			availableSpareParts = this.sparePartRepo.findByCurrentLocationId(getLoggedUser()
					.getLocation().getId());
		}
		m.addAttribute("availableSpareParts", availableSpareParts);
		return "sparepart/list";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/addform")
	public String addformGet(Model m) {
		m.addAttribute("sparePart", new SparePart());
		return "sparepart/addSparePart";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/{id}/edit")
	public String editPost(@ModelAttribute SparePart sparePart) {
		this.sparePartRepo.save(sparePart);
		return "redirect:/sparepart/all";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}/delete")
	public String deleteGet(@PathVariable int id, Model m) {
		SparePart sparePart = this.sparePartRepo.findOne(id);
		m.addAttribute("sparePart", sparePart);
		return "sparepart/confirm";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/{id}/delete")
	public String deletePost(@PathVariable int id, Model m) {
		try {
			this.sparePartRepo.delete(id);
			return "redirect:/sparepart/all";
		} catch (Exception e) {
			m.addAttribute("msg", "Sorry this spare part cannot be deleted. Only unused "
					+ " (never shipped) parts can be deleted from database");
			return "sparepart/confirm";
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("global")
	public String globalGet(Model m) {
		List<SparePart> loadedParts = this.sparePartRepo.findAllByCurrentLocationIsGlobal(true);
		List<SparePart> spareParts = SparePart.selectStatus(loadedParts, "Available");
		m.addAttribute("spareParts", spareParts);
		return "sparepart/global";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/{partId}/shiptolocation")
	public String shipToLocationGet(@PathVariable int partId, Model m) {
		SparePart sparePart = this.sparePartRepo.findOne(partId);
		m.addAttribute("sparePart", sparePart);
		m.addAttribute("shipment", new Shipment());
		return "sparepart/ship";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/{partId}/shiptolocation")
	@Transactional
	public String shipToLocationPost(@PathVariable int partId, @ModelAttribute Shipment shipment,
									BindingResult bindingResult, Model m) {
		SparePart sparePart = sparePartRepo.findOne(partId);
		if(bindingResult.hasErrors()) {
			m.addAttribute("sparePart", sparePart);
			return "sparepart/ship";
		}
		sparePart.setCurrentStatus("Shipped to location");
		shipment.setOrigin(sparePart.getCurrentLocation());
		shipment.setSparePart(sparePart);
		shipment.setDateShipped(new Date());
		shipmentRepo.save(shipment);
		return "redirect:/sparepart/shippedtolocation";
	}
	
	@GetMapping("shippedtolocation")
	public String shippedToLocation(Model m) {
		List<Shipment> shipmentsToLocation = shipmentRepo
				.findAllByIsArchivedAndOriginIsGlobal(false, true);
		shipmentsToLocation = LocationFilter.
				filterShipments(getLoggedUser(),
								shipmentsToLocation);
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
		Shipment shipment = this.shipmentRepo.findOne(id);
		SparePart sparePart = shipment.getSparePart();
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
		s.setAttribute("shipment", null);
		return "redirect:/sparepart/shippedtolocation";
	}
	
	@GetMapping("{id}/shipments/arrivedToLocation")
	public String arrivedToLocationGet(@PathVariable int id, Model m) {
		Shipment shipment = this.shipmentRepo.findOne(id);
		SparePart sparePart = shipment.getSparePart();
		m.addAttribute("sparePart", sparePart);
		return "sparepart/arrivedToLocation";
	}
	
	@PostMapping("{id}/shipments/arrivedToLocation")
	@Transactional
	public String arrivedToLocation(@PathVariable int id, @ModelAttribute SparePart sparePartForm) {
		Shipment shipment = this.shipmentRepo.findOne(id);
		shipment.setDateArrived(new Date());
		shipment.setArchived(true);
		SparePart sparePart = shipment.getSparePart();
		sparePart.setCurrentStorageLocation(sparePartForm.getCurrentStorageLocation());
		sparePart.setCurrentLocation(shipment.getDestination());
		sparePart.setCurrentStatus("Available in remote location");
		this.shipmentRepo.save(shipment);
		return "redirect:/sparepart/location";
		}
	
	@GetMapping("location")
	public String location(Model m) {
		List<SparePart> spareParts = new ArrayList<>();
		if (getLoggedUser().getUserRole().equals("ROLE_ADMIN")) {
			spareParts = sparePartRepo.findAllByCurrentLocationIsGlobal(false);
			spareParts = SparePart.selectStatus(spareParts, "Available in remote location");
		} else {
			spareParts = this.sparePartRepo.findByCurrentLocationId(getLoggedUser()
					.getLocation().getId());
			spareParts = SparePart.selectStatus(spareParts, "Available in remote location");
		}
		m.addAttribute("spareParts", spareParts);
		return "sparepart/location";
	}
	
	@GetMapping("system")
	public String systemGet(Model m) {
		List<SparePart> spareParts = new ArrayList<>();
		if (getLoggedUser().getUserRole().equals("ROLE_ADMIN")) {
			spareParts = sparePartRepo.findAllByCurrentLocationIsGlobal(false);
			spareParts = SparePart.selectStatus(spareParts, "In system");
		} else {
			spareParts = this.sparePartRepo.findByCurrentLocationId(getLoggedUser()
					.getLocation().getId());
			spareParts = SparePart.selectStatus(spareParts, "In system");
		}
		m.addAttribute("spareParts", spareParts);
		return "sparepart/system";
	}
	
	@GetMapping("{id}/insert")
	public String insertGet(@PathVariable int id, Model m) {
		SparePart sparePart = sparePartRepo.findOne(id);
		m.addAttribute("sparePart", sparePart);
		return "sparepart/intoSystem";
	}
	
	@PostMapping("{id}/insert")
	public String insertPost(@PathVariable int id,@ModelAttribute SparePart sparePartForm) {
		SparePart sparePart = sparePartRepo.findOne(id);
		sparePart.setCurrentStatus("In system");
		sparePart.setCurrentStorageLocation(sparePartForm.getCurrentStorageLocation());
		sparePartRepo.save(sparePart);
		return "redirect:/sparepart/system";
	}
	
	@GetMapping("{id}/remove")
	public String removeGet(@PathVariable int id, Model m) {
		SparePart sparePart = sparePartRepo.findOne(id);
		m.addAttribute("sparePart", sparePart);
		return "sparepart/confirmremove";
	}
	
	@PostMapping("{id}/remove")
	public String removePost(@PathVariable int id, @ModelAttribute SparePart sparePartForm) {
		SparePart sparePart = sparePartRepo.findOne(id);
		sparePart.setCurrentStatus("Removed from system");
		sparePart.setCurrentStorageLocation(sparePartForm.getCurrentStorageLocation());
		sparePartRepo.save(sparePart);
		return "redirect:/sparepart/removed";
	}
	
	@GetMapping("removed")
	public String readyToShip(Model m) {
		
		List<SparePart> spareParts = new ArrayList<>();
		if (getLoggedUser().getUserRole().equals("ROLE_ADMIN")) {
			spareParts = sparePartRepo.findAllByCurrentLocationIsGlobal(false);
			spareParts = SparePart.selectStatus(spareParts, "Removed from system");
		} else {
			spareParts = this.sparePartRepo.findByCurrentLocationId(getLoggedUser()
					.getLocation().getId());
			spareParts = SparePart.selectStatus(spareParts, "Removed from system");
		}
		m.addAttribute("spareParts", spareParts);
		return "sparepart/removed";

	}
	
	@GetMapping("{id}/tolocal")
	public String toLocal(@PathVariable int id) {
		SparePart sparePart = sparePartRepo.findOne(id);
		sparePart.setCurrentStatus("Available in remote location");
		sparePartRepo.save(sparePart);
		return "redirect:/sparepart/location";
	}
	
	@GetMapping("/{partId}/toglobal")
	public String shipToGlobalGet(@PathVariable int partId, Model m) {
		SparePart sparePart = this.sparePartRepo.findOne(partId);
		m.addAttribute("sparePart", sparePart);
		m.addAttribute("shipment", new Shipment());
		return "sparepart/toglobal";
	}
	
	@PostMapping("/{partId}/toglobal")
	@Transactional
	public String shipToGlobalPost(@PathVariable int partId, @ModelAttribute Shipment shipment, 
									BindingResult bindingResult) {
		SparePart sparePart = sparePartRepo.findOne(partId);
		sparePart.setCurrentStatus("Shipped to global");
		shipment.setOrigin(sparePart.getCurrentLocation());
		shipment.setSparePart(sparePart);
		shipment.setDateShipped(new Date());
		shipmentRepo.save(shipment);
		return "redirect:/sparepart/shippedtoglobal";
	}
	
	@GetMapping("shippedtoglobal")
	public String shippedToGlobal(Model m) {
		List<Shipment> shipmentsToGlobal = shipmentRepo
				.findAllByIsArchivedAndOriginIsGlobal(false, false);
		shipmentsToGlobal = LocationFilter.
				filterShipments(getLoggedUser(),
						shipmentsToGlobal);
		m.addAttribute("shipmentsToGlobal", shipmentsToGlobal);
		return "sparepart/shippedtoglobal";
	}
	
	
	@GetMapping("{id}/shipments/editglobal")
	public String shippedToGlobalEditGet(@PathVariable int id, Model m, RedirectAttributes ra) {
		Shipment shipment = this.shipmentRepo.findOne(id);
		HttpSession s = SessionManager.session();
		s.setAttribute("shipment", shipment);
		m.addAttribute("shipment", shipment);
		return "sparepart/editShipmentGlobal";
	}
	
	@PostMapping("{id}/shipments/editglobal")
	public String shippedToGlobalEditPost(@PathVariable int id, @ModelAttribute Shipment shipment) {
		HttpSession s = SessionManager.session();
		Shipment uneditedShipment = (Shipment) s.getAttribute("shipment");
		shipment = uneditedShipment.updateWith(shipment);
		this.shipmentRepo.save(shipment);
		s.setAttribute("shipment", null);
		return "redirect:/sparepart/shippedtoglobal";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("{id}/shipments/arrivedToGlobal")
	public String arrivedToGlobalGet(@PathVariable int id, Model m) {
		Shipment shipment = this.shipmentRepo.findOne(id);
		SparePart sparePart = shipment.getSparePart();
		m.addAttribute("sparePart", sparePart);
		return "sparepart/arrivedToGlobal";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("{id}/shipments/arrivedToGlobal")
	@Transactional
	public String arrivedToGlobalPost(@PathVariable int id, @ModelAttribute SparePart sparePartForm) {
		Shipment shipment = this.shipmentRepo.findOne(id);
		shipment.setDateArrived(new Date());
		shipment.setArchived(true);
		SparePart sparePart = shipment.getSparePart();
		sparePart.setCurrentLocation(shipment.getDestination());
		sparePart.setCurrentStatus("Available");
		sparePart.setCurrentStorageLocation(sparePartForm.getCurrentStorageLocation());
		this.shipmentRepo.save(shipment);
		return "redirect:/sparepart/global";
		}
	
	@GetMapping("shipmenthistory")
	public String shipmentHistory(Model m) {
		List<Shipment> availableShipments = this.shipmentRepo.findAllOrderByDateShipped();
		availableShipments = LocationFilter.
				filterShipments(getLoggedUser(),
						availableShipments);
		m.addAttribute("availableShipments", availableShipments);
		return "sparepart/shipmentHistory";
	}
	
	//Additional methods
	private User getLoggedUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((org.springframework.security.core.userdetails.User) principal)
				.getUsername();
		return this.userRepo.findOneByUsername(username);
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
	
	@ModelAttribute("availablePartCatalogs")
	public List<PartCatalog> availablePartCatalogs() {
		return this.partCatalogRepo.findAll();
	}

}