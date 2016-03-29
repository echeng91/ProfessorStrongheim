package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ASSIGNMENTS database table.
 * 
 */
@Entity
@Table(name="ASSIGNMENTS")
@NamedQuery(name="Assignment.findAll", query="SELECT a FROM Assignment a")
public class Assignment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="A_ID")
	private long aId;

	@Column(name="A_TYPE")
	private String aType;

	private BigDecimal grade;

	//bi-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name="S_ID")
	private Student student;

	public Assignment() {
	}

	public long getAId() {
		return this.aId;
	}

	public void setAId(long aId) {
		this.aId = aId;
	}

	public String getAType() {
		return this.aType;
	}

	public void setAType(String aType) {
		this.aType = aType;
	}

	public BigDecimal getGrade() {
		return this.grade;
	}

	public void setGrade(BigDecimal grade) {
		this.grade = grade;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}