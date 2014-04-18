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
import edu.nccu.soslab.Json.ResGetInput;

/**
 * Servlet implementation class GetProject
 */
@WebServlet("/GetInput")
public class GetInput extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetInput()
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
			/* doGet() in GetInput.java serves function GetInputByProject(id) in GetInput.js */
			
			/* response Implementation */
			ResGetInput resInputJson = new ResGetInput();

			/* login check */
			LoginCheck(resInputJson, request, "GET");

			/* write back */
			WriteBack(response, resInputJson);
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
			/* doPost() in GetInput.java serves function GetInput() in GetInput.js */
			/* doPost() in GetInput.java serves function GetInputSelect(id, p_id) in GetInputSelect.js */
			
			/* response Implementation */
			ResGetInput resInputJson = new ResGetInput();

			/* login check */
			LoginCheck(resInputJson, request, "POST");

			/* write back */
			WriteBack(response, resInputJson);

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void LoginCheck(ResGetInput resInputJson,
			HttpServletRequest request, String m) throws ServletException,
			SQLException
	{
		if (LogCheck.logCheck(request))// valid login
		{
			resInputJson.exitValue = 1;
			if (m.equals("GET"))
			{
				String p_id = request.getParameter("p_id");
				doGetInputSql(resInputJson, p_id);
			} else if (m.equals("POST"))
			{
				postGetInputSql(resInputJson);
			}

		} else
		{
			resInputJson.exitValue = 0;
		}

	}

	private void doGetInputSql(ResGetInput resInputJson, String p_id)
			throws ServletException, SQLException
	{
		String tbl = "ghsomservice.tblinput i";
		String col = "*";
		String restrict = " WHERE i.project_Id=" + p_id;
		String order = " ORDER BY i.input_Id ";

		GetSQL gs = new GetSQL();

		ResultSet rs = gs.getSql("SELECT " + " " + col + " " + " FROM " + " "
				+ tbl + " " + restrict + " " + order);

		while (rs.next())
		{
			resInputJson.Add(rs.getInt(1), rs.getInt(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getString(6));
		}
		rs.close();
		rs = null;
		gs.closeSql();
	}

	private void postGetInputSql(ResGetInput resInputJson)
			throws ServletException, SQLException
	{
		String tbl = "ghsomservice.tblinput";
		String col = "*";
		GetSQL gs = new GetSQL();

		ResultSet rs = gs.getSql("SELECT " + col + " FROM " + tbl);

		while (rs.next())
		{

			resInputJson.Add(rs.getInt(1), rs.getInt(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getString(6));
		}
		rs.close();
		rs = null;
		gs.closeSql();

	}

	private void WriteBack(HttpServletResponse response,
			ResGetInput resInputJson) throws IOException
	{
		response.setContentType("application/json"); // json
		PrintWriter resWriter = response.getWriter(); // PrintWriter
		Gson gson = new Gson();
		resWriter.write(gson.toJson(resInputJson));
		resWriter.close();

	}
}
