package com.epam.exercise1.entity;

public class User extends BaseEntity<String> {
	private String name;
	private boolean instructor;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isInstructor() {
		return instructor;
	}

	public void setInstructor(boolean instructor) {
		this.instructor = instructor;
	}

}
