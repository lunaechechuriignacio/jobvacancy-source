package com.jobvacancy.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * A JobOffer.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "JOB_OFFER")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class JobOffer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "location")
	private String location;

	@Column(name = "description")
	private String description;

	@Column(name = "date_expires")
	private Date dateExpires;

	@Column(name = "applied")
	private long applied;

	@Column(name = "satisfied")
	private boolean satisfied;

	@Column(name = "date_validation")
	private boolean dateValidation;

	@ManyToOne
	private User owner;

	public JobOffer() {
		this.applied = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {

		if (id == null) {
			this.setSatisfied(false);
		}

		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateExpires() {
		java.sql.Date date = new java.sql.Date(dateExpires.getTime());
		return date;
	}

	public void setDateExpires(Date dateExpires) {
		this.dateExpires = dateExpires;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User user) {
		this.owner = user;
	}

	public long getApplied() {
		return applied;
	}

	public void setApplied() {
		this.applied += 1;
	}

	public boolean getDateValidation() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		@SuppressWarnings("unused")
		boolean test = this.dateValidation;

		return Integer.parseInt(sdf.format(new Date())) > Integer.parseInt(sdf
				.format(this.dateExpires));
	}

	public void setDateValidation() {
		Date date = new Date();
		this.dateValidation = date.compareTo(this.getDateExpires()) > 0;

	}

	public boolean isSatisfied() {
		return satisfied;
	}

	public void setSatisfied(boolean satisfied) {
		if (this.id == null)
			this.satisfied = false;
		else if (satisfied == false) {
			this.dateCorrection();
			this.satisfied = true;
		}

		else {
			this.dateCorrection();
			this.satisfied = false;
		}
	}

	private void dateCorrection() {
		Calendar c = Calendar.getInstance();
		c.setTime(this.dateExpires);
		c.add(Calendar.DATE, 1);

		this.dateExpires = c.getTime();

	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		JobOffer jobOffer = (JobOffer) o;

		if (!Objects.equals(id, jobOffer.id))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return "JobOffer{" + "id=" + id + ", title='" + title + "'"
				+ ", location='" + location + "'" + ", description='"
				+ description + "'" + ", dateExpires='"
				+ dateExpires.toString() + "'" + ", applied='" + applied + "'"
				+ ", satisfied='" + satisfied + "'" + '}';
	}

}
