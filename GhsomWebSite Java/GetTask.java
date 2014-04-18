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
import edu.nccu.soslab.Json.ResGetTask;

/**
 * Servlet implementation class GetProject
 */
@WebServlet("/GetTask")
public class GetTask extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTask()
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
			/* doGet() in GetTask.java serves function GetTaskByProject(id) in GetTask.js */
			
			/* response */
			ResGetTask resTaskJson = new ResGetTask();

			/* login check */
			LoginCheck(resTaskJson, request, "GET");

			/* write back */
			WriteBack(response, resTaskJson);

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
			/* doPost()  in GetTask.java serves function GetTask() in GetTask.js */
			
			/* response */
			ResGetTask resTaskJson = new ResGetTask();

			/* login check */
			LoginCheck(resTaskJson, request, "POST");

			/* write back */
			WriteBack(response, resTaskJson);

		} catch (Exception e)
		{

		}
	}

	private void LoginCheck(ResGetTask resTaskJson, HttpServletRequest request,
			String m) throws ServletException, SQLException
	{
		if (LogCheck.logCheck(request))// valid login
		{
			resTaskJson.exitValue = 1;
			if (m.equals("GET"))
			{
				String p_id = request.getParameter("p_id");
				doGetTaskSql(resTaskJson, p_id);
			} else if (m.equals("POST"))
			{
				postGetTaskSql(resTaskJson);
			}

		} else
		{
			resTaskJson.exitValue = 0;
		}
	}

	private void doGetTaskSql(ResGetTask resTaskJson, String p_id)
			throws ServletException, SQLException
	{
		String tbl = "ghsomservice.tblinput i,ghsomservice.tblsession s , ghsomservice.tbltask t";
		String col = "t.task_Id, t.session_Id, t.status_Code, t.worker_Id";
		String restrict = " WHERE i.project_Id=" + p_id
				+ " AND i.input_Id=s.input_Id AND  s.session_Id=t.session_Id ";
		String order = " ORDER BY t.task_Id ";

		GetSQL gs = new GetSQL();

		ResultSet rs = gs.getSql("SELECT " + " " + col + " " + " FROM " + " "
				+ tbl + " " + restrict + " " + order);

		while (rs.next())
		{
			resTaskJson.Add(rs.getInt(1), rs.getInt(2), rs.getInt(3),
					rs.getInt(4));
		}

		rs.close();
		rs = null;
		gs.closeSql();
	}

	private void postGetTaskSql(ResGetTask resTaskJson) throws ServletException
	{
		String tbl = "ghsomservice.tbltask";
		String col = "*";
		GetSQL gs = new GetSQL();

		ResultSet rs = gs.getSql("SELECT " + col + " FROM " + tbl);

		try
		{
			while (rs.next())
			{
				resTaskJson.Add(rs.getInt(1), rs.getInt(2), rs.getInt(3),
						rs.getInt(4));
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

	private void WriteBack(HttpServletResponse response, ResGetTask resTaskJson)
			throws IOException
	{
		response.setContentType("application/json"); // json
		PrintWriter resWriter = response.getWriter(); // PrintWriter
		Gson gson = new Gson();
		resWriter.write(gson.toJson(resTaskJson));
		resWriter.close();

	}
}
