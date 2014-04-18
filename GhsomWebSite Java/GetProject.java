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
import edu.nccu.soslab.Json.ResGetProject;

/**
 * Servlet implementation class GetProject
 */
@WebServlet("/GetProject")
public class GetProject extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetProject()
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
			/* doPost()  in GetProject.java serves function GetProperty() in GetProperty.js */
			/* doPost()  in GetProject.java serves function GetProjectSelect(id) in GetProjectSelect.js */
			
			/* response Implementation */
			ResGetProject resProjectJson = new ResGetProject();

			/* login check */
			LoginCheck(resProjectJson,request);

			/* write back */
			WriteBack(resProjectJson, response);

		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	private void LoginCheck(ResGetProject resProjectJson,HttpServletRequest request)
			throws ServletException, SQLException
	{
		if (LogCheck.logCheck(request))
		{
			GetProjectSql(resProjectJson);
			resProjectJson.exitValue = 1;
		} else
		{
			resProjectJson.exitValue = 0;
		}
	}

	private void GetProjectSql(ResGetProject resProjectJson)
			throws ServletException, SQLException
	{
		String tbl = "ghsomservice.tblproject";
		String col = "*";
		GetSQL gs = new GetSQL();

		ResultSet rs = gs.getSql("SELECT " + col + " FROM " + tbl);

		while (rs.next())
		{
			resProjectJson.Add(rs.getInt(1), rs.getString(2), rs.getString(3));
		}

		rs.close();
		rs = null;
		gs.closeSql();
	}

	private void WriteBack(ResGetProject resProjectJson,
			HttpServletResponse response) throws IOException
	{
		/* response */
		response.setContentType("application/json"); // json
		PrintWriter resWriter = response.getWriter(); // PrintWriter
		Gson gson = new Gson();
		resWriter.write(gson.toJson(resProjectJson));
		resWriter.close();
	}
}
