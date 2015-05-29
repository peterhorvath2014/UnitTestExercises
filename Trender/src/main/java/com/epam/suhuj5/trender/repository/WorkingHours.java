package com.epam.suhuj5.trender.repository;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class WorkingHours implements Serializable {

	private static final long serialVersionUID = -9065813650746697619L;

	@Id
	private String id;
	private DateTime date;
	private Period inoffice;
	private Period outofoffice;
	private DateTime daystart;
	private DateTime dayend;
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
	public DateTime getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(DateTime date) {
		this.date = date;
	}

	/**
	 * @return the inoffice
	 */
	public Period getInoffice() {
		return inoffice;
	}

	/**
	 * @param inoffice
	 *            the inoffice to set
	 */
	public void setInoffice(Period inoffice) {
		this.inoffice = inoffice;
	}

	/**
	 * @return the outofoffice
	 */
	public Period getOutofoffice() {
		return outofoffice;
	}

	/**
	 * @param outofoffice
	 *            the outofoffice to set
	 */
	public void setOutofoffice(Period outofoffice) {
		this.outofoffice = outofoffice;
	}

	/**
	 * @return the daystart
	 */
	public DateTime getDaystart() {
		return daystart;
	}

	/**
	 * @param daystart
	 *            the daystart to set
	 */
	public void setDaystart(DateTime daystart) {
		this.daystart = daystart;
	}

	/**
	 * @return the dayend
	 */
	public DateTime getDayend() {
		return dayend;
	}

	/**
	 * @param dayend
	 *            the dayend to set
	 */
	public void setDayend(DateTime dayend) {
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

	@Override
	public String toString() {
		return "WorkingHours [id=" + id + ", date=" + date + ", inoffice=" + inoffice + ", outofoffice=" + outofoffice
				+ ", daystart=" + daystart + ", dayend=" + dayend + ", kind=" + kind + "]";
	}

}
