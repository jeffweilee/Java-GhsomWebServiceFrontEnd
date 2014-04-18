package edu.nccu.soslab.GhsomWebSite;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mysql.jdbc.PreparedStatement;

import edu.nccu.soslab.Json.LogCheck;
import edu.nccu.soslab.Json.ReqAddProperty;

/**
 * Servlet implementation class AddProperty
 */
@WebServlet("/AddProperty")
public class AddProperty extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProperty()
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
		 * doPost() in AddProperty.java serves function AddProperty() in
		 * AddProperty.js
		 */

		try
		{

			/* request */
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

	private ReqAddProperty StringReader(HttpServletRequest request)
			throws IOException
	{
		Gson gson = new Gson();
		StringBuilder sb = new StringBuilder();
		String s;
		while ((s = request.getReader().readLine()) != null)
		{
			sb.append(s); // System.out.println(s);
		}

		ReqAddProperty reqPropertyJson = gson.fromJson(sb.toString(),
				ReqAddProperty.class);
		checkValue(reqPropertyJson);

		return reqPropertyJson;
	}

	protected Connection con = null;
	protected PreparedStatement stmt = null;
	protected ResultSet rs = null;

	private void InsertPropertySql(ReqAddProperty reqPropertyJson)
			throws ServletException, SQLException, ClassNotFoundException
	{
		String tbl = "ghsomservice.tblproperty";
		String col = " (tau_1, tau_2,expand_Cycles, init_Learnrate, nr, html_Prefix,datafile_Extension, random_Seed, norm_Input_Vectors,init_X_Size, init_Y_Size, labels_Num, labels_Only,labels_Threshold, property_Name, attr_Filter, property_Desc) ";
		String val = " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		String sql = "INSERT INTO " + tbl + col + " VALUES " + val;

		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager
				.getConnection("jdbc:mysql://localhost/ghsomservice?"
						+ "user=root&password=root");
		stmt = (PreparedStatement) con.prepareStatement(sql);
		int p = 1;

		stmt.setString(p++, String.valueOf(reqPropertyJson.tau_1).trim());
		stmt.setString(p++, String.valueOf(reqPropertyJson.tau_2).trim());
		stmt.setString(p++, String.valueOf(reqPropertyJson.expand_Cycles)
				.trim());
		stmt.setString(p++, String.valueOf(reqPropertyJson.init_Learnrate)
				.trim());
		stmt.setString(p++, String.valueOf(reqPropertyJson.nr).trim());
		stmt.setString(p++, String.valueOf(reqPropertyJson.html_Prefix).trim());
		stmt.setString(p++, String.valueOf(reqPropertyJson.datafile_Extension)
				.trim());
		stmt.setString(p++, String.valueOf(reqPropertyJson.random_Seed).trim());
		stmt.setString(p++, String.valueOf(reqPropertyJson.norm_Input_Vectors)
				.trim());
		stmt.setString(p++, String.valueOf(reqPropertyJson.init_X_Size).trim());
		stmt.setString(p++, String.valueOf(reqPropertyJson.init_Y_Size).trim());
		stmt.setString(p++, String.valueOf(reqPropertyJson.labels_Num).trim());
		stmt.setInt(p++, reqPropertyJson.labels_Only);
		stmt.setString(p++, String.valueOf(reqPropertyJson.labels_Threshold)
				.trim());
		stmt.setString(p++, String.valueOf(reqPropertyJson.property_Name)
				.trim());
		stmt.setString(p++, String.valueOf(reqPropertyJson.attr_Filter).trim());
		stmt.setString(p++, String.valueOf(reqPropertyJson.property_Desc)
				.trim());

		stmt.executeUpdate();
		stmt.clearParameters();

	}

	public void insertSql(String sql, ArrayList<String> T)
			throws ServletException
	{

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager
					.getConnection("jdbc:mysql://localhost/ghsomservice?"
							+ "user=root&password=root");
			stmt = (PreparedStatement) con.prepareStatement(sql);
			int valnum = T.size();
			for (int p = 0; p < valnum; p++)
			{
				stmt.setString(p + 1, T.get(p));
			}
			stmt.executeUpdate();
			stmt.clearParameters();

			/* displaying records */

		} catch (SQLException e)
		{
			throw new ServletException("Servlet Could not display records.", e);
		} catch (ClassNotFoundException e)
		{
			throw new ServletException("JDBC Driver not found.", e);
		}
	}

	private void checkValue(ReqAddProperty reqPropertyJson)
	{
		if (reqPropertyJson.tau_1 == null || reqPropertyJson.tau_1.equals("")
				|| reqPropertyJson.tau_1.isNaN())
		{
			reqPropertyJson.tau_1 = 0.25;
		}
		if (reqPropertyJson.tau_2 == null || reqPropertyJson.tau_2.equals("")
				|| reqPropertyJson.tau_1.isNaN())
		{
			reqPropertyJson.tau_2 = 0.1;
		}
		if (Integer.valueOf(reqPropertyJson.expand_Cycles) == null
				|| Integer.valueOf(reqPropertyJson.expand_Cycles).equals(""))
		{
			reqPropertyJson.expand_Cycles = 25;
		}
		if (reqPropertyJson.init_Learnrate == null
				|| reqPropertyJson.init_Learnrate.equals(""))
		{
			reqPropertyJson.init_Learnrate = 0.3;
		}
		if (reqPropertyJson.nr == null || reqPropertyJson.nr.equals(""))
		{
			reqPropertyJson.nr = 0.0006;
		}
		if (reqPropertyJson.html_Prefix == null
				|| reqPropertyJson.html_Prefix.trim().equals(""))
		{
			reqPropertyJson.html_Prefix = "map";
		}
		if (reqPropertyJson.datafile_Extension == null
				|| reqPropertyJson.datafile_Extension.trim().equals(""))
		{
			reqPropertyJson.datafile_Extension = "";
		}
		if (Integer.valueOf(reqPropertyJson.random_Seed) == null
				|| Integer.valueOf(reqPropertyJson.random_Seed).equals(""))
		{
			reqPropertyJson.random_Seed = 123456789;
		}
		if (reqPropertyJson.norm_Input_Vectors == null
				|| reqPropertyJson.norm_Input_Vectors.trim().equals(""))
		{
			reqPropertyJson.norm_Input_Vectors = "NONE";
		}
		if (Integer.valueOf(reqPropertyJson.init_X_Size) == null
				|| Integer.valueOf(reqPropertyJson.init_X_Size).equals(""))
		{
			reqPropertyJson.init_X_Size = 2;
		}
		if (Integer.valueOf(reqPropertyJson.init_Y_Size) == null
				|| Integer.valueOf(reqPropertyJson.init_Y_Size).equals(""))
		{
			reqPropertyJson.init_Y_Size = 2;
		}
		if (Integer.valueOf(reqPropertyJson.labels_Num) == null
				|| Integer.valueOf(reqPropertyJson.labels_Num).equals(""))
		{
			reqPropertyJson.labels_Num = 5;
		}
		if (reqPropertyJson.labels_Threshold.equals("")
				|| reqPropertyJson.labels_Threshold == null)
		{
			reqPropertyJson.labels_Threshold = 0.00;
		}
		if (reqPropertyJson.property_Desc == null
				|| reqPropertyJson.property_Desc.trim().equals(""))
		{
			reqPropertyJson.property_Desc = null;
		}

	}

}
