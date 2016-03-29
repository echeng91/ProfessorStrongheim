package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the STUDENTS database table.
 * 
 */
@Entity
@Table(name="STUDENTS")
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="S_ID")
	private long sId;

	private String firstname;

	private String lastname;

	//bi-directional many-to-one association to Assignment
	@OneToMany(mappedBy="student")
	private List<Assignment> assignments;

	public Student() {
	}

	public long getSId() {
		return this.sId;
	}

	public void setSId(long sId) {
		this.sId = sId;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<Assignment> getAssignments() {
		return this.assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public Assignment addAssignment(Assignment assignment) {
		getAssignments().add(assignment);
		assignment.setStudent(this);

		return assignment;
	}

	public Assignment removeAssignment(Assignment assignment) {
		getAssignments().remove(assignment);
		assignment.setStudent(null);

		return assignment;
	}

}