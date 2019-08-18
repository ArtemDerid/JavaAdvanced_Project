package ua.lviv.lgs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.dao.FacultyRepository;
import ua.lviv.lgs.domain.Faculty;

@Service
public class FacultyService {
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private FacultyRepository facultyRepository;
	
	public List<Faculty> findAllFaculties(){
		logger.debug("Find all faculties {} ");
		return facultyRepository.findAll();
	} 
	
	public Faculty findById(Integer id) {
		logger.debug("Find faculty {} by id " + id);
		return facultyRepository.findById(id).get();
	}
	
}
