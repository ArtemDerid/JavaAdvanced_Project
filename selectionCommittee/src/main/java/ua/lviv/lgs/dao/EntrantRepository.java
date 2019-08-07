package ua.lviv.lgs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.lgs.domain.Entrant;

public interface EntrantRepository extends JpaRepository<Entrant, Integer>{
}
