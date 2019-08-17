package ua.lviv.lgs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Enumerated(EnumType.STRING)
	private NameOfSubject nameOfSubject;

	@Column
	private int mark;

	public Subject() {
	}

	public Subject(NameOfSubject nameOfSubject, int mark) {
		super();
		this.nameOfSubject = nameOfSubject;
		this.mark = mark;
	}

	public Subject(int id, NameOfSubject nameOfSubject, int mark) {
		super();
		this.id = id;
		this.nameOfSubject = nameOfSubject;
		this.mark = mark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NameOfSubject getNameOfSubject() {
		return nameOfSubject;
	}

	public void setNameOfSubject(NameOfSubject nameOfSubject) {
		this.nameOfSubject = nameOfSubject;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + mark;
		result = prime * result + ((nameOfSubject == null) ? 0 : nameOfSubject.hashCode());
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
		Subject other = (Subject) obj;
		if (id != other.id)
			return false;
		if (mark != other.mark)
			return false;
		if (nameOfSubject != other.nameOfSubject)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nameOfSubject.toString().toLowerCase().replace('_', ' ') + " - " + mark;
	}

}
