package com.tts.dataaccess.data;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;

import com.tts.dbtables.TCourse;
import com.tts.dbtables.TUser;
import com.tts.dbtables.*;
public class DatabaseAccessUser {

	private EntityManager em;

	public DatabaseAccessUser(EntityManager emI) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		em = emI;
	}

	public TUser[] SelectAllUsers() {
		TUser[] user = new TUser[4];
		int i = 0;
		// rs = null;
		List<TUser> resultList = em.createNamedQuery("TUser.findAll").getResultList();

		// ResultSet rs = psmtSelectAll.executeQuery();

		if (resultList.isEmpty()) {
			return user;
		} else {
			for (TUser u : resultList) {
				if (user.length < i + 1) {
					int number = user.length * 2;
					user = increaseSizeOfArray(user, number);
				}
				user[i] = u;
				i++;

			}
		}

		user = decreaseSizeOfArray(user, i);
		return user;

	}

	public TUser[] increaseSizeOfArray(TUser[] arr, int number) {
		TUser[] brr = new TUser[(number)];
		for (int i = 0; i < arr.length; i++) {
			brr[i] = arr[i];
		}
		return brr;
	}

	public TUser[] decreaseSizeOfArray(TUser[] arr, int number) {
		TUser[] brr = new TUser[(number)];
		for (int i = 0; i < number; i++) {
			brr[i] = arr[i];
		}
		return brr;
	}

	public TUser Selectuser(long userId) {

		return (em.find(TUser.class, userId));

	}

	public TUser SelectStatement(long userID, String password) throws java.lang.Exception {
		TUser user = null;

		user = this.Selectuser(userID);

		if (user == null) {
			Exception exception = new Exception("User name or Password wrong");
			throw (exception);
		}

		if (user.getPassword().equals(password)) {
			return user;
		} else {
			Exception exception = new Exception("User name or Password wrong");
			throw (exception);
		}

	}
	public TCourse[]  getAllBookings(long userId) throws java.lang.Exception {
		TUser user = this.Selectuser(userId);
		int i = 0;
		DatabaseAccessCourse BbsCourse = new DatabaseAccessCourse(em);
		TCourse[] Course = new TCourse[400];

		List<TBooking> resultList = user.getTBookings();
		
		if (resultList.isEmpty()) {
			return null;
		} else {
			for (TBooking booking : resultList) {
				if (Course.length < i + 1) {
					int number = Course.length * 2;
					Course = BbsCourse.increaseSizeOfArray(Course, number);
				}
				Course[i] = booking.getTCourse();
				i++;

			}
		}

		Course = BbsCourse.decreaseSizeOfArray(Course, i);
		return Course;

	}

	public void BookCourse(String userId, long CourseId)
			throws java.lang.Exception {
		
	}

	public void CancelCourse(long userId, long CourseId)
			throws java.lang.Exception {
		
		
		em.getTransaction().begin();
		
		TBooking booking = SelectBooking(Long.parseLong(String.format("%08d", userId) + String.format("%08d", CourseId) ));
	
		if (!em.contains(booking)) {
		    TBooking current = em.merge(booking);
		}
		em.remove(booking);
		em.getTransaction().commit();
		em.close();
	}

	public TBooking SelectBooking(Long BookingId) {

		return (em.find(TBooking.class, BookingId));

	}
	
	public TUser  InsertStatement( String userName, String password, String email)
			throws java.lang.Exception {
		// this.SelectStatement(user, password)
		TUser user = null;

		//user = this.Selectuser(userId);
		//if (user != null) {
			//Exception exception = new Exception("Record already exist");
			//throw (exception);
		//}

		user = new TUser();
		//user.setUserId(userId);
		user.setUserName(userName);
		user.setPassword(password);
		user.setEmail(email);

		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		return user;
	}

	public void UpdateStatement(long userId, String userName, String password, String email)
			throws java.lang.Exception {
		// this.SelectStatement(user, password)

		em.getTransaction().begin();
		TUser user = em.find(TUser.class, userId);
		;
		if (user == null) {
			Exception exception = new Exception("Record Not Found");
			throw (exception);
		} else {

			user.setUserName(userName);
			user.setPassword(password);
			user.setEmail(email);

			em.getTransaction().commit();
			em.close();
		}

	}
}