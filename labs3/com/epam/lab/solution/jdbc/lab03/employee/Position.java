package com.epam.lab.solution.jdbc.lab03.employee;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Position {
	private String positionName;
	private int monthlySalary;

	public Position() {
	}

	public Position(String positionName, int monthlySalary) {
		this.positionName = positionName;
		this.monthlySalary = monthlySalary;
	}

	@Id
	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public int getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
}
