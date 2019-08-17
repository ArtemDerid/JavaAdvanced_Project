package ua.lviv.lgs.domain;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "entrant")
public class Entrant implements Comparable<Entrant>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "faculty_id", referencedColumnName = "id")
	private Faculty faculty;

	@ElementCollection(targetClass = Subject.class)
	private List<Subject> marks;
	
	@Column
	private Integer totalMark;
	
	@Transient
	private MultipartFile file;
	
	private boolean accepted;
	
	@Lob
	private String encodedImage;

	public Entrant() {
	}

	public Entrant(MultipartFile file, User user, Faculty faculty, List<Subject> marks) throws IOException {
		super();
		this.user = user;
		this.faculty = faculty;
		this.marks = marks;
		this.totalMark = 0;
		for (Subject subject : this.marks) {
			totalMark += subject.getMark();
		}
		this.accepted = false;
		this.encodedImage=Base64.getEncoder().encodeToString(file.getBytes());
	}

	public Entrant(MultipartFile file, Integer id, User user, Faculty faculty, List<Subject> marks) throws IOException {
		super();
		this.id = id;
		this.user = user;
		this.faculty = faculty;
		this.marks = marks;
		for (Subject subject : this.marks) {
			totalMark += subject.getMark();
		}
		this.accepted = false;
		this.encodedImage=Base64.getEncoder().encodeToString(file.getBytes());
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

	public List<Subject> getMarks() {
		return marks;
	}

	public void setMarks(List<Subject> marks) {
		this.marks = marks;
	}

	public String getEncodedImage() {
		return encodedImage;
	}

	public void setEncodedImage(String encodedImage) {
		this.encodedImage = encodedImage;
	}

	public Integer getTotalMark() {
		return totalMark;
	}

	public void setTotalMark(Integer totalMark) {
		this.totalMark = totalMark;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((faculty == null) ? 0 : faculty.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((marks == null) ? 0 : marks.hashCode());
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
		if (marks == null) {
			if (other.marks != null)
				return false;
		} else if (!marks.equals(other.marks))
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
		return "Entrant [id=" + id + ", user=" + user + ", faculty=" + faculty + ", marks=" + StringUtils.join(marks, "") + "]";
	}

	@Override
	public int compareTo(Entrant o) {
		if(this.totalMark<o.getTotalMark()) {
			return 1;
		}
		if(this.totalMark>o.getTotalMark()) {
			return -1;
		}
		return 0;
	}

}
