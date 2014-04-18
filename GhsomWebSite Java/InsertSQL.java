package edu.nccu.soslab.GhsomWebSite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import com.mysql.jdbc.PreparedStatement;

public class InsertSQL
{
	protected Connection con = null;
	protected PreparedStatement stmt = null;
	protected ResultSet rs = null;

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
				System.out.println("sql "+ T.get(p));
				
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

