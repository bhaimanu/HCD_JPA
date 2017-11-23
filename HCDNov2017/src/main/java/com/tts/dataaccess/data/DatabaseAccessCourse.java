package com.tts.dataaccess.data;

import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;

import com.tts.dbtables.TBooking;
import com.tts.dbtables.TCourse;
import com.tts.dbtables.TUser;



public class DatabaseAccessCourse {
	private EntityManager em;

	public DatabaseAccessCourse(EntityManager emI) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
			em = emI;

	}

	
	public TUser[]  getAllBookedUsers(long courseId) throws java.lang.Exception {
		TCourse course = this.SelectCourse(courseId);
		int i = 0;
		DatabaseAccessUser BbsUser = new DatabaseAccessUser(em);
		TUser[] User = new TUser[400];

		List<TBooking> resultList = course.getTBookings();
		
		if (resultList.isEmpty()) {
			return null;
		} else {
			for (TBooking booking : resultList) {
				if (User.length < i + 1) {
					int number = User.length * 2;
					User = BbsUser.increaseSizeOfArray(User, number);
				}
				User[i] = booking.getTUser();
				i++;

			}
		}

		User = BbsUser.decreaseSizeOfArray(User, i);
		return User;

	}


	
	public TCourse[] SelectAllCourses() {
		TCourse[] TCourse = new TCourse[400];
		int i = 0;
		// rs = null;
		List<TCourse> resultList = em.createNamedQuery("TCourse.findAll").getResultList();

		// ResultSet rs = psmtSelectAll.executeQuery();

		if (resultList.isEmpty()) {
			return TCourse;
		} else {
			for (TCourse u : resultList) {
				if (TCourse.length < i + 1) {
					int number = TCourse.length * 2;
					TCourse = increaseSizeOfArray(TCourse, number);
				}
				TCourse[i] = u;
				i++;

			}
		}

		TCourse = decreaseSizeOfArray(TCourse, i);
		return TCourse;

	}

	public TCourse[] increaseSizeOfArray(TCourse[] arr, int number) {
		TCourse[] brr = new TCourse[(number)];
		for (int i = 0; i < arr.length; i++) {
			brr[i] = arr[i];
		}
		return brr;
	}

	public TCourse[] decreaseSizeOfArray(TCourse[] arr, int number) {
		TCourse[] brr = new TCourse[(number)];
		for (int i = 0; i < number; i++) {
			brr[i] = arr[i];
		}
		return brr;
	}

	public TCourse SelectCourse(long CourseId) {
		try {
			return (em.find(TCourse.class, CourseId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null; // out.println(e.getMessage());
		}

	}

	public TCourse SelectStatement(long CourseID) throws java.lang.Exception {
		TCourse Course = null;

		Course = this.SelectCourse(CourseID);

		if (Course == null) {
			Exception exception = new Exception("Course not found");
			throw (exception);
		}

		return Course;

	}

	public TCourse InsertStatement(String coursename, java.util.Date beginDate,
			java.util.Date endDate) throws java.lang.Exception {

		TCourse Course = new TCourse();
		//Course.setCourseid(CourseId);
		Course.setCoursename(coursename);
		Course.setCoursebegindate(beginDate);
		Course.setCourseenddate(endDate);
		em.getTransaction().begin();
		em.persist(Course);
		em.getTransaction().commit();
		em.close();
	return Course;
	}

	private TCourse SelectCourse(PrintWriter out, long courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void UpdateStatement(long CourseId, String coursename, Date coursebegindate, Date courseenddate)
			throws java.lang.Exception {
		// this.SelectStatement(Course, password)

		em.getTransaction().begin();
		TCourse Course = em.find(TCourse.class, CourseId);
		;
		if (Course == null) {
			Exception exception = new Exception("Record Not Found");
			throw (exception);
		} else {

			Course.setCoursename(coursename);
			Course.setCoursebegindate(coursebegindate);
			Course.setCourseenddate(courseenddate);

			em.getTransaction().commit();
			em.close();
		}

	}
}