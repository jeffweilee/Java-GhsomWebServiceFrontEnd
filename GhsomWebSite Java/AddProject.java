package edu.nccu.soslab.GhsomWebSite;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.nccu.soslab.Json.LogCheck;
import edu.nccu.soslab.Json.ReqAddProject;

/**
 * Servlet implementation class AddProject
 */
@WebServlet("/AddProject")
public class AddProject extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProject()
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
	 * @throws IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException
	{
		/*
		 * doPost() in AddProject.java serves function AddProject() in
		 * AddProject.js
		 */

		/* request */
		try
		{
			if (LogCheck.logCheck(request))
			{

				InsertPropertySql(StringReader(request));

			}
		} catch (Exception e)
		{
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,
					"Please check format or required feilds!");
		}

	}

	/**
	 * private method
	 * 
	 * */
	private ReqAddProject StringReader(HttpServletRequest request)
			throws IOException
	{
		Gson gson = new Gson();
		StringBuilder sb = new StringBuilder();
		String s;
		while ((s = request.getReader().readLine()) != null)
		{
			sb.append(s);
		}

		ReqAddProject reqProjectJson = gson.fromJson(sb.toString(),
				ReqAddProject.class);
		return reqProjectJson;
	}

	private void InsertPropertySql(ReqAddProject reqProjectJson)
			throws ServletException
	{

		ArrayList<String> reqProjects = new ArrayList<>();
		reqProjects.add(reqProjectJson.project_Name.trim());
		reqProjects.add(reqProjectJson.project_Desc.trim());

		String tbl = " ghsomservice.tblproject ";
		String col = " (`project_Name`, `desc`) ";
		String val = " (?,?) ";
		InsertSQL gs = new InsertSQL();

		gs.insertSql("INSERT INTO " + tbl + col + " VALUES " + val, reqProjects);
		gs.closeSql();
	}
}
