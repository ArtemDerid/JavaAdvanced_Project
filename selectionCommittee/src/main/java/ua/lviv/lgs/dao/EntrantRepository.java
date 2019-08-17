package ua.lviv.lgs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.lgs.domain.Entrant;
import ua.lviv.lgs.domain.Faculty;

@Repository
public interface EntrantRepository extends JpaRepository<Entrant, Integer>{
	
	@Query("Select e from Entrant e where e.accepted=?1")
	List<Entrant> find(@RequestParam("accepted") boolean accepted);
	
	@Query("Select e from Entrant e where e.faculty=?1 and e.accepted=true")
	List<Entrant> findByFaculty(@RequestParam("faculty") Faculty faculty);
}
