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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_ID = "USER_ID";
	private static final String PASSWORD = "PASS";
	private Emanager emanger ;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//response.sendError(120);
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		PrintWriter out = response.getWriter();
		long userId =  Long.parseLong(request.getParameter(USER_ID));
		String password =  request.getParameter(PASSWORD);
		//out.println(userId); 
		//out.println(password); 
		try {
			Emanager emanger = new Emanager();
			DatabaseAccessUser  dbAccess = new com.tts.dataaccess.data.DatabaseAccessUser(emanger.getEntityManager());	

			TUser dbRecord = dbAccess.SelectStatement(userId, password);
			//out.println("Manu");
			
			JSONArray jsArray = new JSONArray();
			
			JSONObject jsobject = new JSONObject();
			jsobject.put("USERID", dbRecord.getUserId());
			jsobject.put("USERNAME", dbRecord.getUserName());
			jsobject.put("EMAIL", dbRecord.getEmail());
			jsArray.add(jsobject);
			out.println(jsArray.toJSONString());
			emanger.unload();
		} catch (ClassNotFoundException e) {
			// send error message

			//out.println(e.getMessage());
			out.println("Manu1");			
		} catch (SQLException e) {
			//Auto-generated catch block
			out.println(e.getMessage());
			} catch (Exception e) {
			// TODO Auto-generated catch block
				//out.println("Manu29");

				out.println(e.getMessage());
		}
		//out.println("</body></html>");

		
	}

}
