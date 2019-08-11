package ua.lviv.lgs.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.lgs.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
}
