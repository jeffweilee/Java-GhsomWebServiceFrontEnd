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
import edu.nccu.soslab.Json.ResGetProperty;

/**
 * Servlet implementation class GetProject
 */
@WebServlet("/GetProperty")
public class GetProperty extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetProperty()
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
		try
		{
			/* doPost()  in GetProperty.java serves function GetProperty() in GetProperty.js */
			/* doPost()  in GetProperty.java serves function GetPropertySelect(id) in GetPropertySelect.js */
			
			/* response Implementation */
			ResGetProperty resPropertyJson = new ResGetProperty();

			/* login check */
			LoginCheck(resPropertyJson,request);

			/* write back */
			WriteBack(resPropertyJson, response);

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void LoginCheck(ResGetProperty resPropertyJson,HttpServletRequest request)
			throws ServletException, SQLException
	{
		if (LogCheck.logCheck(request))// valid login
		{
			GetPropertySql(resPropertyJson);
			resPropertyJson.exitValue = 1;

		} else
		{
			resPropertyJson.exitValue = 0;
		}
	}

	private void GetPropertySql(ResGetProperty resPropertyJson)
			throws ServletException, SQLException
	{
		String tbl = "ghsomservice.tblproperty";
		String col = "*";
		GetSQL gs = new GetSQL();

		ResultSet rs = gs.getSql("SELECT " + col + " FROM " + tbl);

		while (rs.next())
		{
			resPropertyJson.Add(rs.getInt(1), rs.getString(16),
					rs.getDouble(2), rs.getDouble(3), rs.getInt(4),
					rs.getDouble(5), rs.getDouble(6), rs.getString(7),
					rs.getString(8), rs.getInt(9), rs.getString(10),
					rs.getInt(11), rs.getInt(12), rs.getInt(13),
					rs.getBoolean(14), rs.getDouble(15), rs.getInt(17),
					rs.getString(18));

		}

		rs.close();
		rs = null;
		gs.closeSql();
	}

	private void WriteBack(ResGetProperty resPropertyJson,
			HttpServletResponse response) throws IOException
	{
		response.setContentType("application/json"); // json
		PrintWriter resWriter = response.getWriter(); // PrintWriter
		Gson gson = new Gson();
		resWriter.write(gson.toJson(resPropertyJson));
		resWriter.close();
	}
}
