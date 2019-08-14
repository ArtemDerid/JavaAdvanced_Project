package ua.lviv.lgs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.lgs.domain.Entrant;

@Repository
public interface EntrantRepository extends JpaRepository<Entrant, Integer>{
}
