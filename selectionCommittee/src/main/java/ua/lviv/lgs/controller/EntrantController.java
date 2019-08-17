package ua.lviv.lgs.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.lviv.lgs.domain.Entrant;
import ua.lviv.lgs.domain.Faculty;
import ua.lviv.lgs.service.EntrantService;
import ua.lviv.lgs.service.FacultyService;

@Controller
public class EntrantController {

	@Autowired
	private EntrantService entrantService;

	@Autowired
	private FacultyService facultyService;

	@GetMapping("/registeredEntrants")
	public ModelAndView showAllRegisteredEntrants() {
		return getEntrants();
	}

	@GetMapping("/statement")
	public ModelAndView showStatement() {
		return new ModelAndView("statement", "entrants", entrantService.getAcceptedEntrants());
	}

	@RequestMapping(value = "/registeredEntrants", method = RequestMethod.POST)
	public ModelAndView updateEntrant(@RequestParam("entrantId") String entrantId) {
		entrantService.update(Integer.parseInt(entrantId.replaceAll("\\s", "")));
		return getEntrants();
	}

	@GetMapping("/enrolledEntrants")
	public ModelAndView showEnrolledStudents(@RequestParam("facId") String facultyId) {
		Faculty faculty = facultyService.findById(Integer.parseInt(facultyId.replaceAll("\\s", "")));
		List<Entrant> enrolledEntrants = entrantService.getEntrantsFromFaculty(faculty);
		enrolledEntrants = enrolledEntrants.stream().sorted().collect(Collectors.toList());
				
		if (faculty.getNumberOfStudents() < enrolledEntrants.size()) {
			enrolledEntrants = enrolledEntrants.subList(0, faculty.getNumberOfStudents());
		}
		
		ModelAndView model = new ModelAndView("enrolledEntrants");
		model.addObject("faculty", faculty);
		model.addObject("entrants", enrolledEntrants);
		return model;
	}

	private ModelAndView getEntrants() {
		ModelAndView map = new ModelAndView("registeredEntrants");
		map.addObject("entrants", entrantService.getRegisteredEntrants());
		return map;
	}

}
