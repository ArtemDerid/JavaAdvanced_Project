package ua.lviv.lgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import ua.lviv.lgs.service.EntrantService;

@Controller
public class EntrantController {

	@Autowired
	private EntrantService entrantService;
	
	@GetMapping("/registeredEntrants")
	public ModelAndView showAllRegisteredEntrants() {
		return new ModelAndView("registeredEntrants", "entrants", entrantService.getAllEntrants());
	}
	
}
