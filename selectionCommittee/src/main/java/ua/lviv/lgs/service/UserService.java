package ua.lviv.lgs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.dao.UserRepository;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.domain.UserRole;

@Service
public class UserService {

	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	public void save(User user) {
		logger.debug("Create and save new user {} to database" + user);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setConfirmPassword(bCryptPasswordEncoder.encode(user.getConfirmPassword()));
		user.setRole(UserRole.ROLE_ENTRANT);
		userRepository.save(user);
	}

	public User findByEmail(String email) {
		logger.debug("Find user {} by email " + email);
		return userRepository.findByEmail(email).get();
	}

}
