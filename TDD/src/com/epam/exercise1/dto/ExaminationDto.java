package com.epam.exercise1.dto;

import java.sql.Date;

public class ExaminationDto {
	private Integer id;
	private String examinerId;
	private String examinerName;
	private Date examDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExaminerId() {
		return examinerId;
	}

	public void setExaminerId(String examinerId) {
		this.examinerId = examinerId;
	}

	public String getExaminerName() {
		return examinerName;
	}

	public void setExaminerName(String examinerName) {
		this.examinerName = examinerName;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
}
