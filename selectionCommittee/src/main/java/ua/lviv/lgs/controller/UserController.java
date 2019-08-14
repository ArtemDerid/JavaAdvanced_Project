/**
 * 
 */
package ua.lviv.lgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.service.FacultyService;
import ua.lviv.lgs.service.UserService;

/**
 * @author Artem
 *
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private FacultyService facultyService;
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		return "registration";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "registration";
		}
		userService.save(userForm);
		return "redirect:/home";
	}
	
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if(error != null)
			model.addAttribute("error", "invalid username or password");
		if(logout != null) 
			model.addAttribute("message", "You've been logged out");
		return "login";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView welcome() {
  		ModelAndView map = new ModelAndView("home");
  		map.addObject("faculties", facultyService.findAllFaculties());
  		return map;
  	}

}
