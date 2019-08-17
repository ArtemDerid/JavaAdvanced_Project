package ua.lviv.lgs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.dao.EntrantRepository;
import ua.lviv.lgs.domain.Entrant;
import ua.lviv.lgs.domain.Faculty;

@Service
public class EntrantService {
	
	@Autowired
	private EntrantRepository entrantRepository;
	
	public Entrant save(Entrant entrant) {
		return entrantRepository.save(entrant);
	}
	
	public List<Entrant> getAllEntrants(){
		return entrantRepository.findAll();
	}
	
	public Entrant update(Integer id) {		
		Entrant entrant = new Entrant();
		Entrant entrantFromDb = entrantRepository.getOne(id);
		entrant.setUser(entrantFromDb.getUser());
		entrant.setFaculty(entrantFromDb.getFaculty());
		entrant.setMarks(entrantFromDb.getMarks());
		entrant.setTotalMark(entrantFromDb.getTotalMark());
		entrant.setEncodedImage(entrantFromDb.getEncodedImage());
		entrant.setId(id);
		entrant.setAccepted(true);
		return entrantRepository.save(entrant);
	}
	
	public List<Entrant> getAcceptedEntrants(){
		return entrantRepository.find(true);
	}
	
	public List<Entrant> getRegisteredEntrants(){
		return entrantRepository.find(false);
	}
	
	public List<Entrant> getEntrantsFromFaculty(Faculty faculty){
		return entrantRepository.findByFaculty(faculty);
	}
}
