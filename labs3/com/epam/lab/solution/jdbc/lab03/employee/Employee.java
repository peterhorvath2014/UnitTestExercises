package com.epam.lab.solution.jdbc.lab03.employee;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@Entity
@NamedQueries({ @NamedQuery(name = "selectEmployeesWithSalaryLimit", query = "from Employee e where e.position.monthlySalary > :monthlySalaryLimit order by e.position.monthlySalary desc, e.lastName") })
public class Employee {

	private Integer id;
	private String firstName;
	private String lastName;
	private Gender gender;
	private int yearOfApplication;
	private Position position;

	public Employee() {
	}

	public Employee(String firstName, String lastName, Gender gender, Position position, int yearOfApplication) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.position = position;
		this.yearOfApplication = yearOfApplication;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getYearOfApplication() {
		return yearOfApplication;
	}

	public void setYearOfApplication(int yearOfApplication) {
		this.yearOfApplication = yearOfApplication;
	}

	@ManyToOne
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Transient
	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append(getFullName());
		builder.append(" (");
		builder.append(getGender().toString().toLowerCase());
		builder.append("), ");
		builder.append(getPosition().getPositionName());
		builder.append(", applied since ");
		builder.append(getYearOfApplication());
		builder.append(", ");
		builder.append(getPosition().getMonthlySalary());
		builder.append(" Euros / month ");

		return builder.toString();
	}

}
