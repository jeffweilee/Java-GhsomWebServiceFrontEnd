package edu.nccu.soslab.GhsomWebSite;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogOut")
public class LogOut extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogOut()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		// Account Password
		String msg = request.getReader().readLine().toString();
		String logoutJS[] = msg.split(":");

		if (logoutJS.length == 2)
		{
			if (logoutJS[1].substring(0, 1).equals("1"))
			{				
				request.getSession().invalidate();	
				System.out.println("Log out");
				
				response.setContentType("application/json");
				PrintWriter resWriter = response.getWriter(); // PrintWriter
				Gson gson = new Gson();
				resWriter.write(gson.toJson("0"));
				resWriter.close();
			}
		}

	}
}
