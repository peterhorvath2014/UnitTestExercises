package com.epam.exercise1.entity;

import java.util.Date;

public class Examination extends BaseEntity<Integer> {

	private Date examDate;
	private Integer examinerId;

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public Integer getExaminerId() {
		return examinerId;
	}

	public void setExaminerId(Integer examinerId) {
		this.examinerId = examinerId;
	}

}
