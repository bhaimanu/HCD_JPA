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
import com.tts.dataaccess.data.DatabaseAccessCourse;
import com.tts.dataaccess.manager.Emanager;

/**
 * Servlet implementation class GetAllUsers
 */
@WebServlet(description = "Get all Registered users", urlPatterns = { "/GetAllCourses" })
public class GetAllCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Emanager emanger;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAllCourses() {
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
		JSONObject jsonObj = new JSONObject();

		PrintWriter out = response.getWriter();

		try {
			DatabaseAccessCourse dbAccess = new com.tts.dataaccess.data.DatabaseAccessCourse(
					emanger.getEntityManager());
			TCourse[] courses = dbAccess.SelectAllCourses();// dbAccess.SelectStatement(user,
			SimpleDateFormat sm = new SimpleDateFormat("dd.MM.yyyy"); // password)
			JSONArray jsArray = new JSONArray();

			if (courses == null) {
				out.println("<h3>Error</h3>");

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