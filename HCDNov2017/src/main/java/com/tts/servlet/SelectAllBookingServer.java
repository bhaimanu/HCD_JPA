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

import com.tts.dataaccess.data.DatabaseAccessBookings;
import com.tts.dataaccess.data.DatabaseAccessUser;
import com.tts.dataaccess.manager.Emanager;
import com.tts.dbtables.TBooking;
import com.tts.dbtables.TCourse;

/**
 * Servlet implementation class SelectAllBookingServer
 */
@WebServlet("/SelectAllBookingServer")
public class SelectAllBookingServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_ID = "USER_ID";
	private Emanager emanger;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectAllBookingServer() {
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject jsonObj = new JSONObject();

		PrintWriter out = response.getWriter();


		try {
			DatabaseAccessBookings dbbooking = new DatabaseAccessBookings(emanger.getEntityManager());
			TBooking[] bookings = dbbooking.SelectAllBookings( );// dbAccess.SelectStatement(user,
			SimpleDateFormat sm = new SimpleDateFormat("dd.MM.yyyy"); // password)
			JSONArray jsArray = new JSONArray();

			if (bookings == null) {

			} else {
				for (int i = 0; i < bookings.length; i++) {
					JSONObject jsobject = new JSONObject();
					jsobject.put("UserId", bookings[i].getTUser().getUserId());
					jsobject.put("CourseId", bookings[i].getTCourse().getCourseid());
					jsobject.put("CourseName", bookings[i].getTCourse().getCoursename());
					jsobject.put("BeginDate", sm.format(bookings[i].getTCourse().getCoursebegindate()));
					jsobject.put("EndDate", sm.format(bookings[i].getTCourse().getCourseenddate()));
					jsArray.add(jsobject);
				}
			}

			out.println(jsArray.toJSONString());

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