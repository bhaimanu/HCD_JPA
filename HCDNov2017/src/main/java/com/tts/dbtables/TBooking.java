package com.tts.dbtables;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the T_BOOKING database table.
 * 
 */
@Entity
@Table(name="T_BOOKING")
@NamedQuery(name="TBooking.findAll", query="SELECT t FROM TBooking t")
public class TBooking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"KEY\"")
	private long key;

	//bi-directional many-to-one association to TCourse
	@ManyToOne
	@JoinColumn(name="COURSEID")
	private TCourse TCourse;

	//bi-directional many-to-one association to TUser
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private TUser TUser;

	public TBooking() {
	}

	public long getKey() {
		return this.key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public TCourse getTCourse() {
		return this.TCourse;
	}

	public void setTCourse(TCourse TCourse) {
		this.TCourse = TCourse;
	}

	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

}