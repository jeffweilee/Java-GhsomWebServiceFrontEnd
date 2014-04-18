package edu.nccu.soslab.GhsomWebSite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;

public class GetSQL
{
	protected Connection con = null;
	protected Statement stmt = null;
	protected ResultSet rs = null;

	public ResultSet getSql(String sql) throws ServletException
	{

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager
					.getConnection("jdbc:mysql://localhost/ghsomservice?"
							+ "user=root&password=root");
			stmt = con.createStatement();
		    rs = stmt.executeQuery(sql);
			return rs;
			/* displaying records */

		} catch (SQLException e)
		{
			throw new ServletException("Servlet Could not display records.", e);
		} catch (ClassNotFoundException e)
		{
			throw new ServletException("JDBC Driver not found.", e);
		}
	}

	public void closeSql()
	{
		try
		{
			if (rs != null)
			{
				rs.close();
				rs = null;
			}
			if (stmt != null)
			{
				stmt.close();
				stmt = null;
			}
			if (con != null)
			{
				con.close();
				con = null;
			}
		} catch (SQLException e)
		{
		}
	}

}
