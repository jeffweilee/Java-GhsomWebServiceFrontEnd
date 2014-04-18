package edu.nccu.soslab.GhsomWebSite;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mysql.jdbc.PreparedStatement;

import edu.nccu.soslab.Json.LogCheck;
import edu.nccu.soslab.Json.ReqAddSession;

/**
 * Servlet implementation class AddSession
 */
@WebServlet("/AddSession")
public class AddSession extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSession()
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
		 * doPost() in AddSession.java serves function AddSession() in
		 * AddSession.js
		 */
		try
		{

			if (LogCheck.logCheck(request))
			{
				WriteBack(response, InsertSessionSql(StringReader(request)));
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,
					"Please check format or required feilds!");
		}
	}

	/**
	 * private method
	 * 
	 * */

	private ReqAddSession StringReader(HttpServletRequest request)
			throws IOException
	{
		Gson gson = new Gson();
		StringBuilder sb = new StringBuilder();
		String s;
		while ((s = request.getReader().readLine()) != null)
		{
			sb.append(s);
		}

		ReqAddSession reqSessionJson = gson.fromJson(sb.toString(),
				ReqAddSession.class);

		return reqSessionJson;
	}

	protected String sessionId;
	protected Connection con = null;
	protected PreparedStatement stmt = null;
	protected ResultSet rs = null;

	private ReqAddSession InsertSessionSql(ReqAddSession reqSessionJson)
			throws ServletException, SQLException, ClassNotFoundException
	{
		ArrayList<String> reqSessions = new ArrayList<>();
		reqSessions.add(reqSessionJson.session_Name);
		reqSessions.add(reqSessionJson.session_Desc);
		reqSessions.add(String.valueOf(reqSessionJson.property_Id));
		reqSessions.add(String.valueOf(reqSessionJson.input_Id));

		String tbl = "ghsomservice.tblsession";
		String col = " (session_Name, session_Desc, property_Id, input_Id) ";
		String val = " (?,?,?,?) ";
		String sql = "INSERT INTO " + tbl + col + " VALUES " + val;

		/*
		 * InsertSQL gs = new InsertSQL(); gs.insertSql("INSERT INTO " + tbl +
		 * col + " VALUES " + val, reqSessions);
		 */
		/*
		 * if (rs.next()) { sessionId = String.valueOf(rs.getInt(1)); }
		 */

		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager
				.getConnection("jdbc:mysql://localhost/ghsomservice?"
						+ "user=root&password=root");
		stmt = (PreparedStatement) con.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
		int valnum = reqSessions.size();
		for (int p = 0; p < valnum; p++)
		{
			stmt.setString(p + 1, reqSessions.get(p));
		}
		stmt.executeUpdate();
		rs = stmt.getGeneratedKeys();
		if (rs.next())
		{
			sessionId = String.valueOf(rs.getInt(1));
		}

		stmt.clearParameters();
		con.close();
		rs.close();
		stmt.close();

		// Insert Task SQL
		InsertTaskSql(reqSessionJson);

		// return to writeBack()
		return reqSessionJson;
	}

	protected void InsertTaskSql(ReqAddSession reqSessionJson)
			throws ServletException
	{
		// Insert Task SQL
		ArrayList<String> reqTasks = new ArrayList<>();
		reqTasks.add(sessionId);
		String statusCode = "0";
		reqTasks.add(statusCode);

		String tbl = "ghsomservice.tbltask";
		String col = " (session_Id, status_Code) ";
		String val = " (?,?) ";
		InsertSQL iSql = new InsertSQL();
		iSql.insertSql("INSERT INTO " + tbl + col + " VALUES " + val, reqTasks);

		iSql.closeSql();
	}

	private void WriteBack(HttpServletResponse response,
			ReqAddSession reqSessionJson) throws IOException
	{
		response.setContentType("application/json"); // json
		PrintWriter resWriter = response.getWriter(); // PrintWriter
		Gson gson = new Gson();
		resWriter.write(gson.toJson(reqSessionJson));
		resWriter.close();

	}
}
