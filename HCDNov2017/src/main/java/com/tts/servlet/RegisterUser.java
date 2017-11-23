package com.tts.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tts.dataaccess.data.DatabaseAccessUser;
import com.tts.dataaccess.manager.Emanager;
import com.tts.dbtables.TUser;

@WebServlet(description = "Register user", urlPatterns = { "/RegisterUser" })
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_ID = "USER_ID";
	private static final String USER_NAME = "userName";
	private static final String PASSWORD = "pass";
	private static final String EMAIL = "email";
	private Emanager emanger;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterUser() {
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
	}

	public void destroy() {
		try {
			emanger.unload();
		} catch (Exception e) {
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
		// doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		long userId = 1; 
				//Long.parseLong(request.getParameter(USER_ID));
		String userName = request.getParameter(USER_NAME);
		String password = request.getParameter(PASSWORD);
		String email = request.getParameter(EMAIL);

		try {
			//emanger = new Emanager();
			DatabaseAccessUser user = new DatabaseAccessUser(emanger.getEntityManager());
			TUser tuser = user.InsertStatement( userName, password, email);
			out.println( "Success User Id = " + tuser.getUserId() );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
		}
	}
}
