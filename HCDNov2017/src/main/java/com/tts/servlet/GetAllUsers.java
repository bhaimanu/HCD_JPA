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

import com.tts.dbtables.TUser;
import com.tts.dataaccess.data.DatabaseAccessUser;
import com.tts.dataaccess.manager.Emanager;

/**
 * Servlet implementation class GetAllUsers
 */
@WebServlet(description = "Get all Registered users", urlPatterns = { "/GetAllUsers" })
public class GetAllUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Emanager emanger;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAllUsers() {
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
		//out.println("<h3>Error</h3>");
		try {
			DatabaseAccessUser dbAccess = new DatabaseAccessUser(emanger.getEntityManager());
			TUser[] user = dbAccess.SelectAllUsers();// dbAccess.SelectStatement(user,
													// password)
			JSONArray jsArray = new JSONArray();

			if (user == null) {
				out.println("<h3>Error</h3>");

			} else {
				for (int i = 0; i < user.length; i++) {
					JSONObject jsobject = new JSONObject();
					jsobject.put("USERID", user[i].getUserId());
					jsobject.put("USERNAME", user[i].getUserName());
					jsobject.put("EMAIL", user[i].getEmail());
					jsArray.add(jsobject);
				}
			}

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
	}
}