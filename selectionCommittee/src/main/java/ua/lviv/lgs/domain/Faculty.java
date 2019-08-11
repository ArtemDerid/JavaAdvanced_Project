package ua.lviv.lgs.domain;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "faculty")
public class Faculty {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String name;
	
	@Column(name = "number_of_students")
	private Integer numberOfStudents;
	
	@ElementCollection(targetClass = Subject.class)
	@CollectionTable(name="subject", joinColumns = @JoinColumn(name="faculty_id") ) 
	@Column(name="required_subjects", nullable=false) 
	private List<Subject> requiredSubjects;

	public Faculty() {
	}

	public Faculty(String name, Integer numberOfStudents, List<Subject> requiredSubjects) {
		super();
		this.name = name;
		this.numberOfStudents = numberOfStudents;
		this.requiredSubjects = requiredSubjects;
	}

	public Faculty(Integer id, String name, Integer numberOfStudents, List<Subject> requiredSubjects) {
		super();
		this.id = id;
		this.name = name;
		this.numberOfStudents = numberOfStudents;
		this.requiredSubjects = requiredSubjects;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setNumberOfStudents(Integer numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}

	public List<Subject> getRequiredSubjects() {
		return requiredSubjects;
	}

	public void setRequiredSubjects(List<Subject> requiredSubjects) {
		this.requiredSubjects = requiredSubjects;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((numberOfStudents == null) ? 0 : numberOfStudents.hashCode());
		result = prime * result + ((requiredSubjects == null) ? 0 : requiredSubjects.hashCode());
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
		Faculty other = (Faculty) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numberOfStudents == null) {
			if (other.numberOfStudents != null)
				return false;
		} else if (!numberOfStudents.equals(other.numberOfStudents))
			return false;
		if (requiredSubjects == null) {
			if (other.requiredSubjects != null)
				return false;
		} else if (!requiredSubjects.equals(other.requiredSubjects))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Faculty [id=" + id + ", name=" + name + ", numberOfStudents=" + numberOfStudents + ", requiredSubjects="
				+ requiredSubjects + "]";
	}

}
