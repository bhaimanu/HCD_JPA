package com.tts.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tts.dataaccess.data.DatabaseAccessCourse;
import com.tts.dataaccess.manager.Emanager;
import com.tts.dbtables.TCourse;

@WebServlet(description = "Register user", urlPatterns = { "/RegisterCourse" })
public class RegisterCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CourseName = "COURSE_NAME";
	private static final String BeginDate = "BEGIN_DATE";
	private static final String EndDate = "END_DATE";
	private Emanager emanger;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterCourse() {
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

		String courseName = request.getParameter(CourseName);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date beginDate = df.parse(request.getParameter(BeginDate));
			Date endDate = df.parse(request.getParameter(EndDate));
			DatabaseAccessCourse course = new DatabaseAccessCourse(emanger.getEntityManager());
			TCourse Tcourse = course.InsertStatement(courseName, beginDate, endDate);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
		}

	}

}
