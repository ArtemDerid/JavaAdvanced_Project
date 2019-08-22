package ua.lviv.lgs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.dao.EntrantRepository;
import ua.lviv.lgs.domain.Entrant;
import ua.lviv.lgs.domain.Faculty;

@Service
public class EntrantService {

	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private EntrantRepository entrantRepository;

	public Entrant save(Entrant entrant) {
		logger.debug("Create and save entrant {} " + entrant);
		return entrantRepository.save(entrant);
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
		logger.debug("Update entrant from DB with id {} " + id);
		return entrantRepository.save(entrant);
	}

	public List<Entrant> getAcceptedEntrants() {
		logger.debug("Show entrants accepted by admin");
		return entrantRepository.find(true);
	}

	public List<Entrant> getRegisteredEntrants() {
		logger.debug("Show entrant registrations which are not accepted yet");
		return entrantRepository.find(false);
	}

	public List<Entrant> getEntrantsFromFaculty(Faculty faculty) {
		logger.debug("Show enrolled entrants on faculty " + faculty);
		return entrantRepository.findByFaculty(faculty);
	}
}
