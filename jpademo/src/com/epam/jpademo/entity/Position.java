package com.epam.jpademo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_position")
public class Position {
	@Id
	@Column(name="position_name")
	private String name;

	@Column(name = "monthly_salary")
	private int monthlySalary;

	public Position(String name, int monthlySalary) {
		super();
		this.name = name;
		this.monthlySalary = monthlySalary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

}
