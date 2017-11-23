package com.tts.dataaccess.data;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;

import com.tts.dbtables.TBooking;
import com.tts.dbtables.TCourse;
import com.tts.dbtables.TUser;


public class DatabaseAccessBookings {

	private EntityManager em;

	public DatabaseAccessBookings(EntityManager emI ) throws ClassNotFoundException, SQLException {

		em = emI;
	}
	
	public TBooking[] SelectAllBookings() {
		TBooking[] booking = new TBooking[4];
		int i = 0;
		// rs = null;
		List<TBooking> resultList = em.createNamedQuery("TBooking.findAll").getResultList();

		// ResultSet rs = psmtSelectAll.executeQuery();

		if (resultList.isEmpty()) {
			return booking;
		} else {
			for (TBooking u : resultList) {
				if (booking.length < i + 1) {
					int number = booking.length * 2;
					booking = increaseSizeOfArray(booking, number);
				}
				booking[i] = u;
				i++;

			}
		}

		booking = decreaseSizeOfArray(booking, i);
		return booking;

	}

	private TBooking[] increaseSizeOfArray(TBooking[] arr, int number) {
		TBooking[] brr = new TBooking[(number)];
		for (int i = 0; i < arr.length; i++) {
			brr[i] = arr[i];
		}
		return brr;
	}
	
private	TBooking[] decreaseSizeOfArray(TBooking[] arr, int number)
	{
	TBooking[] brr = new TBooking[(number)];
	for (int i = 0; i < number; i++) {
		brr[i] = arr[i];
	}
	return brr;
	}

	public TBooking SelectBooking(long BookingId) {

		return (em.find(TBooking.class, BookingId));

	}

	public TUser Selectuser(long userId) {
		return (em.find(TUser.class, userId));
	}

	public void BookCourse(long userId, long CourseId)
			throws java.lang.Exception {
		DatabaseAccessUser dbsUser = new DatabaseAccessUser(em);
		TUser user = dbsUser.Selectuser(userId);
		DatabaseAccessCourse dbsCourse = new DatabaseAccessCourse(em);
		TCourse course = dbsCourse.SelectCourse(CourseId);

		//
		em.getTransaction().begin();
		TBooking booking = new TBooking();
		booking.setKey(Long.parseLong(String.format("%08d", userId) + String.format("%08d", CourseId) ));
		booking.setTCourse(course);
		booking.setTUser(user);
		user.getTBookings().add(booking);//booking
		//em.persist(booking);
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}
	
	}