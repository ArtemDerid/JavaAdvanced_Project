package ua.lviv.lgs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.dao.SubjectRepository;
import ua.lviv.lgs.domain.Subject;

@Service
public class SubjectService {
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	public Subject save(Subject subject) {
		logger.debug("Save subject {} to database " + subject);
		return subjectRepository.save(subject);
	}
	
	public List<Subject> findAll(){
		logger.debug("Find all subjects {} ");
		return subjectRepository.findAll();
	}

}
