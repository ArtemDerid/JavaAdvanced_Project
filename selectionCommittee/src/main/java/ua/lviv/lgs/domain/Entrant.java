package ua.lviv.lgs.domain;

import java.util.Map;

public class Entrant {
	
	private Integer id;
	private Integer userId;
	private Integer FacultyId;
	private Map<Subject, Integer> markForSubject;
	
	public Entrant() {
	}

	public Entrant(Integer userId, Integer facultyId, Map<Subject, Integer> markForSubject) {
		super();
		this.userId = userId;
		FacultyId = facultyId;
		this.markForSubject = markForSubject;
	}

	public Entrant(Integer id, Integer userId, Integer facultyId, Map<Subject, Integer> markForSubject) {
		super();
		this.id = id;
		this.userId = userId;
		FacultyId = facultyId;
		this.markForSubject = markForSubject;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getFacultyId() {
		return FacultyId;
	}

	public void setFacultyId(Integer facultyId) {
		FacultyId = facultyId;
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
		result = prime * result + ((FacultyId == null) ? 0 : FacultyId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((markForSubject == null) ? 0 : markForSubject.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		if (FacultyId == null) {
			if (other.FacultyId != null)
				return false;
		} else if (!FacultyId.equals(other.FacultyId))
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
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entrant [id=" + id + ", userId=" + userId + ", FacultyId=" + FacultyId + ", markForSubject="
				+ markForSubject + "]";
	}

}
