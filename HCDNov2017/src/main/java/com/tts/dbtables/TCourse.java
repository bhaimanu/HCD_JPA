package com.tts.dbtables;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the T_COURSE database table.
 * 
 */
@Entity
@Table(name="T_COURSE")
@NamedQuery(name="TCourse.findAll", query="SELECT t FROM TCourse t")
public class TCourse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_id_generator")
	@SequenceGenerator(name="course_id_generator", sequenceName = "course_id_seq", allocationSize=1)
	@Column(name="COURSEID", updatable = false, nullable = false)
	private long courseid;

	@Temporal(TemporalType.DATE)
	private Date coursebegindate;

	@Temporal(TemporalType.DATE)
	private Date courseenddate;

	private String coursename;

	//bi-directional many-to-one association to TBooking
	@OneToMany(mappedBy="TCourse")
	private List<TBooking> TBookings;

	public TCourse() {
	}

	public long getCourseid() {
		return this.courseid;
	}

	public void setCourseid(long courseid) {
		this.courseid = courseid;
	}

	public Date getCoursebegindate() {
		return this.coursebegindate;
	}

	public void setCoursebegindate(Date coursebegindate) {
		this.coursebegindate = coursebegindate;
	}

	public Date getCourseenddate() {
		return this.courseenddate;
	}

	public void setCourseenddate(Date courseenddate) {
		this.courseenddate = courseenddate;
	}

	public String getCoursename() {
		return this.coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public List<TBooking> getTBookings() {
		return this.TBookings;
	}

	public void setTBookings(List<TBooking> TBookings) {
		this.TBookings = TBookings;
	}

	public TBooking addTBooking(TBooking TBooking) {
		getTBookings().add(TBooking);
		TBooking.setTCourse(this);

		return TBooking;
	}

	public TBooking removeTBooking(TBooking TBooking) {
		getTBookings().remove(TBooking);
		TBooking.setTCourse(null);

		return TBooking;
	}

}