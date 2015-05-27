package com.epam.suhuj5.trender.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class WorkingHours {

	@Id
	private String id;
	private String date;
	private String inoffice;
	private String outofoffice;
	private String daystart;
	private String dayend;
	private String kind;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the inoffice
	 */
	public String getInoffice() {
		return inoffice;
	}

	/**
	 * @param inoffice
	 *            the inoffice to set
	 */
	public void setInoffice(String inoffice) {
		this.inoffice = inoffice;
	}

	/**
	 * @return the outofoffice
	 */
	public String getOutofoffice() {
		return outofoffice;
	}

	/**
	 * @param outofoffice
	 *            the outofoffice to set
	 */
	public void setOutofoffice(String outofoffice) {
		this.outofoffice = outofoffice;
	}

	/**
	 * @return the daystart
	 */
	public String getDaystart() {
		return daystart;
	}

	/**
	 * @param daystart
	 *            the daystart to set
	 */
	public void setDaystart(String daystart) {
		this.daystart = daystart;
	}

	/**
	 * @return the dayend
	 */
	public String getDayend() {
		return dayend;
	}

	/**
	 * @param dayend
	 *            the dayend to set
	 */
	public void setDayend(String dayend) {
		this.dayend = dayend;
	}

	/**
	 * @return the kind
	 */
	public String getKind() {
		return kind;
	}

	/**
	 * @param kind
	 *            the kind to set
	 */
	public void setKind(String kind) {
		this.kind = kind;
	}
}
