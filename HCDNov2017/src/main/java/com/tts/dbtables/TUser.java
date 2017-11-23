package com.tts.dbtables;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the T_USERS database table.
 * 
 */
@Entity
@Table(name="T_USERS")
@NamedQuery(name="TUser.findAll", query="SELECT t FROM TUser t")
public class TUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
	@SequenceGenerator(name="user_id_generator", sequenceName = "user_id_seq", allocationSize=1)
	@Column(name="USER_ID", updatable = false, nullable = false)
	private long userId;

	private String email;

	private String password;

	@Column(name="USER_NAME")
	private String userName;

	//bi-directional many-to-one association to TBooking
	@OneToMany(mappedBy="TUser", cascade=CascadeType.PERSIST)
	private List<TBooking> TBookings;

	public TUser() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<TBooking> getTBookings() {
		return this.TBookings;
	}

	public void setTBookings(List<TBooking> TBookings) {
		this.TBookings = TBookings;
	}

	public TBooking addTBooking(TBooking TBooking) {
		getTBookings().add(TBooking);
		TBooking.setTUser(this);

		return TBooking;
	}

	public TBooking removeTBooking(TBooking TBooking) {
		getTBookings().remove(TBooking);
		TBooking.setTUser(null);

		return TBooking;
	}

}