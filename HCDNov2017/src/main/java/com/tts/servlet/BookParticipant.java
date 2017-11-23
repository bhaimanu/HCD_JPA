package com.tts.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.tts.dbtables.TCourse;
import com.tts.dataaccess.data.DatabaseAccessBookings;
import com.tts.dataaccess.data.DatabaseAccessUser;
import com.tts.dataaccess.manager.Emanager;

@WebServlet(description = "Book Participant", urlPatterns = { "/BookParticipant" })
public class BookParticipant extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_ID = "UserIdBook";
	private static final String COURSE_ID = "CourseIdBook";
	private Emanager emanger;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookParticipant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		try {
			emanger = new Emanager();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			emanger.unload();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			long userId = Long.parseLong(request.getParameter(USER_ID));
			long courseId = Long.parseLong(request.getParameter(COURSE_ID));
			DatabaseAccessBookings booking = new DatabaseAccessBookings(emanger.getEntityManager());
			booking.BookCourse(userId, courseId);
			emanger.unload();
			emanger.load();
/*
			DatabaseAccessUser dbUser = new com.tts.dataaccess.data.DatabaseAccessUser(emanger.getEntityManager());
			
			TCourse[] courses = dbUser.getAllBookings(userId);// dbAccess.SelectStatement(user,
			SimpleDateFormat sm = new SimpleDateFormat("dd.MM.yyyy"); // password)
			JSONArray jsArray = new JSONArray();

			if (courses == null) {

			} else {
				for (int i = 0; i < courses.length; i++) {
					JSONObject jsobject = new JSONObject();
					jsobject.put("CourseId", courses[i].getCourseid());
					jsobject.put("CourseName", courses[i].getCoursename());
					jsobject.put("BeginDate", sm.format(courses[i].getCoursebegindate()));
					jsobject.put("EndDate", sm.format(courses[i].getCourseenddate()));
					jsArray.add(jsobject);
				}
			}


			out.println(jsArray.toJSONString());
	*/
		} catch (ClassNotFoundException e) {
			// send error message
			// out.println(e.getMessage());

		} catch (SQLException e) {
			// Auto-generated catch block
			out.println(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
		}
	}

}
