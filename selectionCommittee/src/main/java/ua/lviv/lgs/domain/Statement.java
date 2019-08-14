package ua.lviv.lgs.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "statement")
public class Statement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column
	private Date date;
	
	@ElementCollection(targetClass = Entrant.class)
	@OneToMany
	private List<Entrant> listOfEntrants;
	
	private static Statement statement;

	public Statement() {
	}

	public Statement getStatement() {
		if (statement == null) {
			statement = new Statement();
		}

		return statement;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Entrant> getListOfEntrants() {
		return listOfEntrants;
	}

	public void setListOfEntrants(List<Entrant> listOfEntrants) {
		this.listOfEntrants = listOfEntrants;
	}

	public static void setStatement(Statement statement) {
		Statement.statement = statement;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((listOfEntrants == null) ? 0 : listOfEntrants.hashCode());
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
		Statement other = (Statement) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (listOfEntrants == null) {
			if (other.listOfEntrants != null)
				return false;
		} else if (!listOfEntrants.equals(other.listOfEntrants))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Statement [date=" + date + ", listOfEntrants=" + listOfEntrants + "]";
	}

}
