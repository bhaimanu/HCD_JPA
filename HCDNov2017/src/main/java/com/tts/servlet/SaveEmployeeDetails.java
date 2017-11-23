package com.tts.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.tts.dataaccess.data.DatabaseAccessUser;
import com.tts.dataaccess.manager.Emanager;
import com.tts.dbtables.TUser;

/**
 * Servlet implementation class SaveEmployeeDetails
 */
@WebServlet(description = "Save Employee details", urlPatterns = { "/SaveEmployeeDetails" })
public class SaveEmployeeDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_ID = "USER_ID";
	private static final String USER_NAME = "USER_NAME";
	private static final String PASSWORD = "PASS";
	private static final String EMAIL = "EMAIL";
	private Emanager emanger;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveEmployeeDetails() {
		super();
		// TODO Auto-generated constructor stub
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		long userId = Long.parseLong(request.getParameter(USER_ID));
		String userName = request.getParameter(USER_NAME);
		String password = request.getParameter(PASSWORD);
		String email = request.getParameter(EMAIL);
		out.println(userId + " " + userName + " " + password + " " + email);
		try {
			Emanager emanger = new Emanager();
			DatabaseAccessUser dbAccess = new com.tts.dataaccess.data.DatabaseAccessUser(emanger.getEntityManager());
			dbAccess.UpdateStatement(userId, userName, password, email);
			dbAccess = new com.tts.dataaccess.data.DatabaseAccessUser(emanger.getEntityManager());
			TUser dbRecord = dbAccess.SelectStatement(userId, password);
			JSONArray jsArray = new JSONArray();
			JSONObject jsobject = new JSONObject();
			jsobject.put("USERID", dbRecord.getUserId());
			jsobject.put("USERNAME", dbRecord.getUserName());
			jsobject.put("EMAIL", dbRecord.getEmail());
			jsArray.add(jsobject);
			out.println(jsArray.toJSONString());

		} catch (ClassNotFoundException e) {
			// send error message
			out.println(e.getMessage());

		} catch (SQLException e) {
			// Auto-generated catch block
			out.println(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
		}
		// out.println("</body></html>");

	}

}
