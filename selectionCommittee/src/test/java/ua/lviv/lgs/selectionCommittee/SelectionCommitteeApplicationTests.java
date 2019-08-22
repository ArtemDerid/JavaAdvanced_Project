package ua.lviv.lgs.selectionCommittee;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import ua.lviv.lgs.dao.EntrantRepository;
import ua.lviv.lgs.dao.FacultyRepository;
import ua.lviv.lgs.dao.SubjectRepository;
import ua.lviv.lgs.dao.UserRepository;
import ua.lviv.lgs.domain.Entrant;
import ua.lviv.lgs.domain.Faculty;
import ua.lviv.lgs.domain.NameOfSubject;
import ua.lviv.lgs.domain.Subject;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.domain.UserRole;
import ua.lviv.lgs.service.EntrantService;
import ua.lviv.lgs.service.FacultyService;
import ua.lviv.lgs.service.SubjectService;
import ua.lviv.lgs.service.UserService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SelectionCommitteeApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	SubjectService subjectService;

	@Autowired
	SubjectRepository subjectRepository;

	@Autowired
	FacultyService facultyService;

	@Autowired
	FacultyRepository facultyRepository;

	@Autowired
	EntrantService entrantService;

	@Autowired
	EntrantRepository entrantRepository;

	@Test
	public void testSaveUser() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		User user = new User();
		user.setEmail("user@gmail.com");
		user.setFirstName("User");
		user.setLastName("User");
		user.setPassword("test");
		user.setConfirmPassword("test");
		user.setRole(UserRole.ROLE_ADMIN);
		userService.save(user);

		users = userRepository.findAll();
		assertThat(users, hasSize(1));

		User userFromDB = users.get(0);
		assertTrue(userFromDB.getEmail().equals(user.getEmail()));
		assertTrue(userFromDB.getFirstName().equals(user.getFirstName()));
		assertTrue(userFromDB.getLastName().equals(user.getLastName()));
		assertTrue(userFromDB.getRole().equals(user.getRole()));
	}

	@Test
	public void testFindByEmail() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		User user = new User();
		user.setEmail("user@gmail.com");
		user.setFirstName("User");
		user.setLastName("User");
		user.setPassword("test");
		user.setConfirmPassword("test");
		user.setRole(UserRole.ROLE_ADMIN);
		userService.save(user);

		users = userRepository.findAll();
		assertThat(users, hasSize(1));

		User findByEmail = userService.findByEmail("user@gmail.com");

		assertTrue(findByEmail.getEmail().equals(user.getEmail()));
		assertTrue(findByEmail.getFirstName().equals(user.getFirstName()));
		assertTrue(findByEmail.getLastName().equals(user.getLastName()));
		assertTrue(findByEmail.getRole().equals(user.getRole()));
	}

	@Test
	public void testSaveSubject() {
		List<Subject> subjects = subjectRepository.findAll();
		assertThat(subjects, hasSize(0));

		Subject subject = new Subject();
		subject.setMark(90);
		subject.setNameOfSubject(NameOfSubject.CHEMISTRY);
		subjectService.save(subject);

		subjects = subjectRepository.findAll();
		assertThat(subjects, hasSize(1));

		Subject subjectFromDB = subjects.get(0);
		assertTrue(subjectFromDB.getMark() == subject.getMark());
		assertTrue(subjectFromDB.getNameOfSubject().equals(subject.getNameOfSubject()));
	}

	@Test
	public void testFindAllSubjects() {
		List<Subject> subjects = subjectRepository.findAll();
		assertThat(subjects, hasSize(0));

		Subject firstSubject = new Subject();
		firstSubject.setMark(90);
		firstSubject.setNameOfSubject(NameOfSubject.CHEMISTRY);

		Subject secondSubject = new Subject();
		secondSubject.setMark(50);
		secondSubject.setNameOfSubject(NameOfSubject.MATH);
		subjectRepository.saveAll(Arrays.asList(firstSubject, secondSubject));

		subjects = subjectRepository.findAll();
		assertThat(subjects, hasSize(2));

		List<Subject> findAll = subjectService.findAll();

		assertTrue(findAll.get(0).getMark() == firstSubject.getMark());
		assertTrue(findAll.get(0).getNameOfSubject().equals(firstSubject.getNameOfSubject()));

		assertTrue(findAll.get(1).getMark() == secondSubject.getMark());
		assertTrue(findAll.get(1).getNameOfSubject().equals(secondSubject.getNameOfSubject()));
	}

	@Test
	public void testFindAllFaculties() {
		List<Faculty> faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));

		Faculty firstFaculty = new Faculty();
		firstFaculty.setName("Test");
		firstFaculty.setNumberOfStudents(50);
		List<NameOfSubject> subjects = new ArrayList<>();
		subjects.add(NameOfSubject.FOREIGN_LANGUAGE);
		subjects.add(NameOfSubject.GEOGRAPHY);
		firstFaculty.setRequiredSubjects(subjects);

		Faculty secondFaculty = new Faculty();
		secondFaculty.setName("PER");
		secondFaculty.setNumberOfStudents(10);
		List<NameOfSubject> requiredSubjects = new ArrayList<>();
		requiredSubjects.add(NameOfSubject.HISTORY);
		requiredSubjects.add(NameOfSubject.UKRAINIAN_LANGUAGE);
		secondFaculty.setRequiredSubjects(requiredSubjects);

		facultyRepository.saveAll(Arrays.asList(firstFaculty, secondFaculty));

		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(2));

		List<Faculty> findAllFaculties = facultyService.findAllFaculties();

		assertTrue(findAllFaculties.get(0).getName().equals(firstFaculty.getName()));
		assertTrue(findAllFaculties.get(0).getNumberOfStudents().equals(firstFaculty.getNumberOfStudents()));
		assertTrue(findAllFaculties.get(0).getRequiredSubjects().equals(firstFaculty.getRequiredSubjects()));

		assertTrue(findAllFaculties.get(1).getName().equals(secondFaculty.getName()));
		assertTrue(findAllFaculties.get(1).getNumberOfStudents().equals(secondFaculty.getNumberOfStudents()));
		assertTrue(findAllFaculties.get(1).getRequiredSubjects().equals(secondFaculty.getRequiredSubjects()));
	}

	@Test
	public void testFindByIdFaculties() {
		List<Faculty> faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));

		Faculty faculty = new Faculty();
		faculty.setName("Test");
		faculty.setNumberOfStudents(50);
		List<NameOfSubject> subjects = new ArrayList<>();
		subjects.add(NameOfSubject.FOREIGN_LANGUAGE);
		subjects.add(NameOfSubject.GEOGRAPHY);
		faculty.setRequiredSubjects(subjects);

		facultyRepository.save(faculty);

		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(1));

		Faculty findById = facultyService.findById(faculties.get(0).getId());

		assertTrue(findById.getName().equals(faculty.getName()));
		assertTrue(findById.getNumberOfStudents().equals(faculty.getNumberOfStudents()));
		assertTrue(findById.getRequiredSubjects().equals(faculty.getRequiredSubjects()));
	}

	@Test
	public void testSaveEntrant() {
		List<Entrant> entrants = entrantRepository.findAll();
		assertThat(entrants, hasSize(0));

		User user = new User();
		user.setEmail("user@gmail.com");
		user.setFirstName("User");
		user.setLastName("User");
		user.setPassword("test");
		user.setConfirmPassword("test");
		user.setRole(UserRole.ROLE_ADMIN);
		userService.save(user);
		User userFromDB = userRepository.findAll().stream().findFirst().get();

		Faculty faculty = new Faculty();
		faculty.setName("Test");
		faculty.setNumberOfStudents(50);
		List<NameOfSubject> subjects = new ArrayList<>();
		subjects.add(NameOfSubject.FOREIGN_LANGUAGE);
		subjects.add(NameOfSubject.GEOGRAPHY);
		faculty.setRequiredSubjects(subjects);
		facultyRepository.save(faculty);

		Faculty facultyFromDB = facultyRepository.findAll().stream().findFirst().get();

		Subject firstSubject = new Subject();
		firstSubject.setMark(90);
		firstSubject.setNameOfSubject(NameOfSubject.PHYSICS);

		Subject secondSubject = new Subject();
		secondSubject.setMark(80);
		secondSubject.setNameOfSubject(NameOfSubject.FOREIGN_LANGUAGE);

		subjectRepository.saveAll(Arrays.asList(firstSubject, secondSubject));

		List<Subject> listOfSubjects = new ArrayList<>();
		listOfSubjects.addAll(Arrays.asList(firstSubject, secondSubject));

		Entrant entrant = new Entrant();
		entrant.setUser(userFromDB);
		entrant.setFaculty(facultyFromDB);
		entrant.setMarks(listOfSubjects);
		entrantService.save(entrant);

		entrants = entrantRepository.findAll();
		assertThat(entrants, hasSize(1));

		Entrant entrantFromDB = entrants.get(0);

		assertTrue(entrantFromDB.getUser().equals(userFromDB));
		assertTrue(entrantFromDB.getFaculty().equals(facultyFromDB));
		assertTrue(entrantFromDB.getMarks().equals(entrant.getMarks()));
	}

	@Test
	public void tesUpdateEntrant() {
		List<Entrant> entrants = entrantRepository.findAll();
		assertThat(entrants, hasSize(0));

		User user = new User();
		user.setEmail("user@gmail.com");
		user.setFirstName("User");
		user.setLastName("User");
		user.setPassword("test");
		user.setConfirmPassword("test");
		user.setRole(UserRole.ROLE_ADMIN);
		userService.save(user);
		User userFromDB = userRepository.findAll().stream().findFirst().get();

		Faculty faculty = new Faculty();
		faculty.setName("Test");
		faculty.setNumberOfStudents(50);
		List<NameOfSubject> subjects = new ArrayList<>();
		subjects.add(NameOfSubject.FOREIGN_LANGUAGE);
		subjects.add(NameOfSubject.GEOGRAPHY);
		faculty.setRequiredSubjects(subjects);
		facultyRepository.save(faculty);

		Faculty facultyFromDB = facultyRepository.findAll().stream().findFirst().get();

		Subject firstSubject = new Subject();
		firstSubject.setMark(90);
		firstSubject.setNameOfSubject(NameOfSubject.PHYSICS);

		Subject secondSubject = new Subject();
		secondSubject.setMark(80);
		secondSubject.setNameOfSubject(NameOfSubject.FOREIGN_LANGUAGE);

		subjectRepository.saveAll(Arrays.asList(firstSubject, secondSubject));

		List<Subject> listOfSubjects = new ArrayList<>();
		listOfSubjects.addAll(Arrays.asList(firstSubject, secondSubject));

		Entrant entrant = new Entrant();
		entrant.setUser(userFromDB);
		entrant.setFaculty(facultyFromDB);
		entrant.setMarks(listOfSubjects);
		entrant.setAccepted(false);
		entrantService.save(entrant);

		entrants = entrantRepository.findAll();
		assertThat(entrants, hasSize(1));

		Entrant entrantFromDB = entrants.get(0);

		entrantService.update(entrantFromDB.getId());

		assertTrue(entrantFromDB.getUser().equals(userFromDB));
		assertTrue(entrantFromDB.getFaculty().equals(facultyFromDB));
		assertTrue(entrantFromDB.getMarks().equals(entrant.getMarks()));
		assertTrue(entrantFromDB.isAccepted() == true);
	}

	@Test
	public void testGetAcceptedEntrants() {
		List<Entrant> entrants = entrantRepository.findAll();
		assertThat(entrants, hasSize(0));

		User user = new User();
		user.setEmail("user@gmail.com");
		user.setFirstName("User");
		user.setLastName("User");
		user.setPassword("test");
		user.setConfirmPassword("test");
		user.setRole(UserRole.ROLE_ADMIN);
		userService.save(user);
		User userFromDB = userRepository.findAll().stream().findFirst().get();

		Faculty faculty = new Faculty();
		faculty.setName("Test");
		faculty.setNumberOfStudents(50);
		List<NameOfSubject> subjects = new ArrayList<>();
		subjects.add(NameOfSubject.FOREIGN_LANGUAGE);
		subjects.add(NameOfSubject.GEOGRAPHY);
		faculty.setRequiredSubjects(subjects);
		facultyRepository.save(faculty);

		Faculty facultyFromDB = facultyRepository.findAll().stream().findFirst().get();

		Subject firstSubject = new Subject();
		firstSubject.setMark(90);
		firstSubject.setNameOfSubject(NameOfSubject.PHYSICS);

		Subject secondSubject = new Subject();
		secondSubject.setMark(80);
		secondSubject.setNameOfSubject(NameOfSubject.FOREIGN_LANGUAGE);

		subjectRepository.saveAll(Arrays.asList(firstSubject, secondSubject));

		List<Subject> listOfSubjects = new ArrayList<>();
		listOfSubjects.addAll(Arrays.asList(firstSubject, secondSubject));

		Entrant entrant = new Entrant();
		entrant.setUser(userFromDB);
		entrant.setFaculty(facultyFromDB);
		entrant.setMarks(listOfSubjects);
		entrant.setAccepted(false);
		entrantService.save(entrant);

		User user1 = new User();
		user1.setEmail("user1@gmail.com");
		user1.setFirstName("User");
		user1.setLastName("User");
		user1.setPassword("test");
		user1.setConfirmPassword("test");
		user1.setRole(UserRole.ROLE_ADMIN);
		userService.save(user1);
		User userFromDB1 = userRepository.findAll().stream().filter(u->u.getEmail().equals(user1.getEmail())).findFirst().get();

		Faculty faculty1 = new Faculty();
		faculty1.setName("Test1");
		faculty1.setNumberOfStudents(50);
		List<NameOfSubject> subjects1 = new ArrayList<>();
		subjects1.add(NameOfSubject.FOREIGN_LANGUAGE);
		subjects1.add(NameOfSubject.GEOGRAPHY);
		faculty1.setRequiredSubjects(subjects);
		facultyRepository.save(faculty1);

		Faculty facultyFromDB1 = facultyRepository.findAll().stream()
				.filter(f -> f.getName().equals(faculty1.getName())).findFirst().get();

		Subject firstSubject1 = new Subject();
		firstSubject.setMark(90);
		firstSubject.setNameOfSubject(NameOfSubject.PHYSICS);

		Subject secondSubject1 = new Subject();
		secondSubject.setMark(80);
		secondSubject.setNameOfSubject(NameOfSubject.FOREIGN_LANGUAGE);

		subjectRepository.saveAll(Arrays.asList(firstSubject1, secondSubject1));

		List<Subject> listOfSubjects1 = new ArrayList<>();
		listOfSubjects.addAll(Arrays.asList(firstSubject1, secondSubject1));

		Entrant newEntrant = new Entrant();
		newEntrant.setUser(userFromDB1);
		newEntrant.setFaculty(facultyFromDB1);
		newEntrant.setMarks(listOfSubjects1);
		newEntrant.setAccepted(false);
		entrantService.save(newEntrant);

		entrants = entrantRepository.findAll();
		assertThat(entrants, hasSize(2));

		Entrant entrantFromDB = entrants.get(0);

		entrantService.update(entrantFromDB.getId());

		List<Entrant> acceptedEntrants = entrantService.getAcceptedEntrants();
		Entrant acceptedEntrant = acceptedEntrants.stream().findAny().get();

		assertTrue(acceptedEntrant.isAccepted() == true);
	}

	@Test
	public void testGetRegisteredEntrants() {
		List<Entrant> entrants = entrantRepository.findAll();
		assertThat(entrants, hasSize(0));

		User user = new User();
		user.setEmail("user@gmail.com");
		user.setFirstName("User");
		user.setLastName("User");
		user.setPassword("test");
		user.setConfirmPassword("test");
		user.setRole(UserRole.ROLE_ADMIN);
		userService.save(user);
		User userFromDB = userRepository.findAll().stream().findFirst().get();

		Faculty faculty = new Faculty();
		faculty.setName("Test");
		faculty.setNumberOfStudents(50);
		List<NameOfSubject> subjects = new ArrayList<>();
		subjects.add(NameOfSubject.FOREIGN_LANGUAGE);
		subjects.add(NameOfSubject.GEOGRAPHY);
		faculty.setRequiredSubjects(subjects);
		facultyRepository.save(faculty);

		Faculty facultyFromDB = facultyRepository.findAll().stream().findFirst().get();

		Subject firstSubject = new Subject();
		firstSubject.setMark(90);
		firstSubject.setNameOfSubject(NameOfSubject.PHYSICS);

		Subject secondSubject = new Subject();
		secondSubject.setMark(80);
		secondSubject.setNameOfSubject(NameOfSubject.FOREIGN_LANGUAGE);

		subjectRepository.saveAll(Arrays.asList(firstSubject, secondSubject));

		List<Subject> listOfSubjects = new ArrayList<>();
		listOfSubjects.addAll(Arrays.asList(firstSubject, secondSubject));

		Entrant entrant = new Entrant();
		entrant.setUser(userFromDB);
		entrant.setFaculty(facultyFromDB);
		entrant.setMarks(listOfSubjects);
		entrant.setAccepted(false);
		entrantService.save(entrant);

		User user1 = new User();
		user1.setEmail("user1@gmail.com");
		user1.setFirstName("User");
		user1.setLastName("User");
		user1.setPassword("test");
		user1.setConfirmPassword("test");
		user1.setRole(UserRole.ROLE_ADMIN);
		userService.save(user1);
		User userFromDB1 = userRepository.findAll().stream().filter(u -> u.getEmail().equals(user1.getEmail())).findFirst().get();

		Faculty faculty1 = new Faculty();
		faculty1.setName("Test1");
		faculty1.setNumberOfStudents(50);
		List<NameOfSubject> subjects1 = new ArrayList<>();
		subjects1.add(NameOfSubject.FOREIGN_LANGUAGE);
		subjects1.add(NameOfSubject.GEOGRAPHY);
		faculty1.setRequiredSubjects(subjects);
		facultyRepository.save(faculty1);

		Faculty facultyFromDB1 = facultyRepository.findAll().stream()
				.filter(f -> f.getName().equals(faculty1.getName())).findFirst().get();

		Subject firstSubject1 = new Subject();
		firstSubject.setMark(90);
		firstSubject.setNameOfSubject(NameOfSubject.PHYSICS);

		Subject secondSubject1 = new Subject();
		secondSubject.setMark(80);
		secondSubject.setNameOfSubject(NameOfSubject.FOREIGN_LANGUAGE);

		subjectRepository.saveAll(Arrays.asList(firstSubject1, secondSubject1));

		List<Subject> listOfSubjects1 = new ArrayList<>();
		listOfSubjects.addAll(Arrays.asList(firstSubject1, secondSubject1));

		Entrant newEntrant = new Entrant();
		newEntrant.setUser(userFromDB1);
		newEntrant.setFaculty(facultyFromDB1);
		newEntrant.setMarks(listOfSubjects1);
		newEntrant.setAccepted(false);
		entrantService.save(newEntrant);

		entrants = entrantRepository.findAll();
		assertThat(entrants, hasSize(2));

		Entrant entrantFromDB = entrants.get(0);

		entrantService.update(entrantFromDB.getId());

		List<Entrant> registeredEntrants = entrantService.getRegisteredEntrants();
		Entrant registeredEntrant = registeredEntrants.stream().findAny().get();

		assertTrue(registeredEntrant.isAccepted() == false);
	}

	@Test
	public void testGetEntrantsFromFaculty() {
		List<Entrant> entrants = entrantRepository.findAll();
		assertThat(entrants, hasSize(0));

		User user = new User();
		user.setEmail("user@gmail.com");
		user.setFirstName("User");
		user.setLastName("User");
		user.setPassword("test");
		user.setConfirmPassword("test");
		user.setRole(UserRole.ROLE_ADMIN);
		userService.save(user);
		User userFromDB = userRepository.findAll().stream().findFirst().get();

		Faculty faculty = new Faculty();
		faculty.setName("Test");
		faculty.setNumberOfStudents(50);
		List<NameOfSubject> subjects = new ArrayList<>();
		subjects.add(NameOfSubject.FOREIGN_LANGUAGE);
		subjects.add(NameOfSubject.GEOGRAPHY);
		faculty.setRequiredSubjects(subjects);
		facultyRepository.save(faculty);

		Faculty facultyFromDB = facultyRepository.findAll().stream().findFirst().get();

		Subject firstSubject = new Subject();
		firstSubject.setMark(90);
		firstSubject.setNameOfSubject(NameOfSubject.PHYSICS);

		Subject secondSubject = new Subject();
		secondSubject.setMark(80);
		secondSubject.setNameOfSubject(NameOfSubject.FOREIGN_LANGUAGE);

		subjectRepository.saveAll(Arrays.asList(firstSubject, secondSubject));

		List<Subject> listOfSubjects = new ArrayList<>();
		listOfSubjects.addAll(Arrays.asList(firstSubject, secondSubject));

		Entrant entrant = new Entrant();
		entrant.setUser(userFromDB);
		entrant.setFaculty(facultyFromDB);
		entrant.setMarks(listOfSubjects);
		entrant.setAccepted(false);
		entrantService.save(entrant);

		User user1 = new User();
		user1.setEmail("user1@gmail.com");
		user1.setFirstName("User");
		user1.setLastName("User");
		user1.setPassword("test");
		user1.setConfirmPassword("test");
		user1.setRole(UserRole.ROLE_ADMIN);
		userService.save(user1);
		User userFromDB1 = userRepository.findAll().stream().filter(u -> u.getEmail().equals(user1.getEmail()))
				.findFirst().get();

		Faculty faculty1 = new Faculty();
		faculty1.setName("Test1");
		faculty1.setNumberOfStudents(50);
		List<NameOfSubject> subjects1 = new ArrayList<>();
		subjects1.add(NameOfSubject.FOREIGN_LANGUAGE);
		subjects1.add(NameOfSubject.GEOGRAPHY);
		faculty1.setRequiredSubjects(subjects);
		facultyRepository.save(faculty1);

		Faculty facultyFromDB1 = facultyRepository.findAll().stream()
				.filter(f -> f.getName().equals(faculty1.getName())).findFirst().get();

		Subject firstSubject1 = new Subject();
		firstSubject.setMark(90);
		firstSubject.setNameOfSubject(NameOfSubject.PHYSICS);

		Subject secondSubject1 = new Subject();
		secondSubject.setMark(80);
		secondSubject.setNameOfSubject(NameOfSubject.FOREIGN_LANGUAGE);

		subjectRepository.saveAll(Arrays.asList(firstSubject1, secondSubject1));

		List<Subject> listOfSubjects1 = new ArrayList<>();
		listOfSubjects.addAll(Arrays.asList(firstSubject1, secondSubject1));

		Entrant newEntrant = new Entrant();
		newEntrant.setUser(userFromDB1);
		newEntrant.setFaculty(facultyFromDB1);
		newEntrant.setMarks(listOfSubjects1);
		newEntrant.setAccepted(false);
		entrantService.save(newEntrant);

		entrants = entrantRepository.findAll();
		assertThat(entrants, hasSize(2));

		Entrant entrantFromDB = entrants.get(0);

		entrantService.update(entrantFromDB.getId());

		List<Entrant> enrolledEntrants = entrantService.getEntrantsFromFaculty(facultyFromDB);
		List<Entrant> notEnrolledEntrants = entrantService.getEntrantsFromFaculty(facultyFromDB1);

		assertThat(enrolledEntrants, hasSize(1));
		assertThat(notEnrolledEntrants, hasSize(0));
	}

}
