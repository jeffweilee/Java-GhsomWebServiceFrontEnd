package edu.nccu.soslab.GhsomWebSite;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.nccu.soslab.Json.LogCheck;
import edu.nccu.soslab.Json.ResGetSession;

/**
 * Servlet implementation class GetProject
 */
@WebServlet("/GetSession")
public class GetSession extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetSession()
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
		try
		{
			/* doGet()  in GetSession.java serves function GetSessionByProject(id) in GetSession.js */
			
			/* response */
			ResGetSession resSessionJson = new ResGetSession();

			/* login check */
			LoginCheck(resSessionJson, request, "GET");

			/* write back */
			WriteBack(response, resSessionJson);

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			/* doPost() in GeSession.java serves function GetSession() in GetSession.js */
			
			/* response */
			ResGetSession resSessionJson = new ResGetSession();

			/* login check */
			LoginCheck(resSessionJson, request, "POST");

			/* write back */
			WriteBack(response, resSessionJson);

		} catch (Exception e)
		{

		}
	}

	private void LoginCheck(ResGetSession resSessionJson,
			HttpServletRequest request, String m) throws ServletException,
			SQLException
	{
		if (LogCheck.logCheck(request))// valid login
		{
			resSessionJson.exitValue = 1;
			if (m.equals("GET"))
			{
				String p_id = request.getParameter("p_id");
				doGetSessionSql(resSessionJson, p_id);
			} else if (m.equals("POST"))
			{
				postGetSessionSql(resSessionJson);
			}

		} else
		{
			resSessionJson.exitValue = 0;
		}
	}

	private void doGetSessionSql(ResGetSession resSessionJson, String p_id)
			throws ServletException, SQLException
	{
		String tbl = " ghsomservice.tblsession s, ghsomservice.tblinput i ";
		String col = " s.session_Id, s.session_Name, s.session_Desc, s.property_Id, s.input_Id ";
		String restrict = " i.project_Id=" + p_id
				+ " AND s.input_Id=i.input_Id ";
		String order = " Order By s.session_Id ";

		GetSQL gs = new GetSQL();

		ResultSet rs = gs.getSql("SELECT " + " " + col + " " + " FROM " + " "
				+ tbl + " " + " WHERE " + restrict + " " + order);

		while (rs.next())
		{
			resSessionJson.Add(rs.getInt(1), rs.getInt(4), rs.getInt(5),
					rs.getString(2), rs.getString(3));
		}

		rs.close();
		rs = null;
		gs.closeSql();
	}

	private void postGetSessionSql(ResGetSession resSessionJson)
			throws ServletException
	{
		String tbl = "ghsomservice.tblsession";
		String col = "*";
		GetSQL gs = new GetSQL();

		ResultSet rs = gs.getSql("SELECT " + col + " FROM " + tbl);

		try
		{
			while (rs.next())
			{
				resSessionJson.Add(rs.getInt(1), rs.getInt(4), rs.getInt(5),
						rs.getString(2), rs.getString(3));
			}
			rs.close();
			rs = null;
			gs.closeSql();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void WriteBack(HttpServletResponse response,
			ResGetSession resSessionJson) throws IOException
	{
		response.setContentType("application/json"); // json
		PrintWriter resWriter = response.getWriter(); // PrintWriter
		Gson gson = new Gson();
		resWriter.write(gson.toJson(resSessionJson));
		resWriter.close();

	}
}
