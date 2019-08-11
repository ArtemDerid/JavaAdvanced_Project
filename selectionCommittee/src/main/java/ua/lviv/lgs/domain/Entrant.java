package ua.lviv.lgs.domain;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="entrant")
public class Entrant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "faculty_id", referencedColumnName = "id")
	private Faculty faculty;

	@Transient
	private Map<Subject, Integer> markForSubject;

	public Entrant() {
	}

	public Entrant(User user, Faculty faculty, Map<Subject, Integer> markForSubject) {
		super();
		this.user = user;
		this.faculty = faculty;
		this.markForSubject = markForSubject;
	}

	public Entrant(Integer id, User user, Faculty faculty, Map<Subject, Integer> markForSubject) {
		super();
		this.id = id;
		this.user = user;
		this.faculty = faculty;
		this.markForSubject = markForSubject;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Map<Subject, Integer> getMarkForSubject() {
		return markForSubject;
	}

	public void setMarkForSubject(Map<Subject, Integer> markForSubject) {
		this.markForSubject = markForSubject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((faculty == null) ? 0 : faculty.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((markForSubject == null) ? 0 : markForSubject.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrant other = (Entrant) obj;
		if (faculty == null) {
			if (other.faculty != null)
				return false;
		} else if (!faculty.equals(other.faculty))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (markForSubject == null) {
			if (other.markForSubject != null)
				return false;
		} else if (!markForSubject.equals(other.markForSubject))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entrant [id=" + id + ", user=" + user + ", faculty=" + faculty + ", markForSubject=" + markForSubject
				+ "]";
	}

}
