package edu.nccu.soslab.GhsomWebSite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import edu.nccu.soslab.Json.LogCheck;
import edu.nccu.soslab.Json.ReqAddInput;

/**
 * Servlet implementation class AddInput
 */
@WebServlet("/AddInput")
@MultipartConfig
public class AddInput extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddInput()
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
		/* doPost() in AddInput.java serves form submit in innputadd.html */
		try
		{
			/* request */
			if (LogCheck.logCheck(request))// valid login
			{
				// step1-text
				ReqAddInput reqInput = new ReqAddInput();
				GetText(reqInput, request);

				// step2-file-WriteFile ,InsertSql;
				String pathSeperator = "\\";
				ProcessFile(request, reqInput, pathSeperator);

				/* reponse */
				response.setContentType("text/html");
				response.sendRedirect("web/inputadd.html?ok=Finish&ok=1&pn="
						+ reqInput.project_Id);
				System.out.println("File copied.");

			} else
			{
				response.setContentType("text/html");
				response.sendRedirect("web/login.html");
				System.out.println("Invalid request.");
			}
		} catch (Exception e)
		{

			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,
					"Please check format or required feilds!");
		}
	}

	protected void GetText(ReqAddInput reqInput, HttpServletRequest request)
	{

		reqInput.project_Id = Integer.parseInt(request
				.getParameter("project_Id"));
		reqInput.input_Name = request.getParameter("input_name").trim();
		reqInput.input_Desc = request.getParameter("input_desc").trim();
		reqInput.input_File_Path = "";
		reqInput.desc_File_Path = "";
	}

	protected void ProcessFile(HttpServletRequest request,
			ReqAddInput reqInput, String pathSeperator)
			throws IllegalStateException, IOException, ServletException,
			SQLException
	{
		Part nameFile = request.getPart("input_file_name");
		Part descFile = request.getPart("input_file_desc");

		if (nameFile.getSize() != 0 && descFile.getSize() != 0)
		{
			InputStream vectorFilein = nameFile.getInputStream();
			InputStream descFilein = descFile.getInputStream();

			// new file path
			String filePath = "C:" + pathSeperator + "Users" + pathSeperator
					+ "Administrator" + pathSeperator + "workspace"
					+ pathSeperator + "XGhsomServiceWeb" + pathSeperator
					+ "WebContent" + pathSeperator + "GhsomService"
					+ pathSeperator + "input";

			WriteFile(filePath, pathSeperator, vectorFilein, descFilein,
					reqInput);
		} else
		{

		}
	}

	protected void WriteFile(String filePath, String pathSeperator,
			InputStream vectorFilein, InputStream descFilein,
			ReqAddInput reqInput) throws ServletException,
			FileNotFoundException, SQLException, IOException
	{
		GetSQL gs = new GetSQL();

		ResultSet rs = gs
				.getSql("select auto_increment from information_schema.TABLES where TABLE_NAME ='tblinput' and TABLE_SCHEMA='ghsomservice'");

		if (rs.next())
		{
			int input_Id = rs.getInt(1);
			// new file dir
			File file = new File(filePath + pathSeperator + input_Id);

			if (!file.exists())
			{
				file.mkdirs();
			}

			// new file name
			String vector = pathSeperator + input_Id + pathSeperator + "Vector";
			String desc = pathSeperator + +input_Id + pathSeperator + "Desc";

			reqInput.input_File_Path = "GhsomService" + pathSeperator + "input"
					+ vector;
			reqInput.desc_File_Path = "GhsomService" + pathSeperator + "input"
					+ desc;

			OutputStream vectorFileout = new FileOutputStream(filePath + vector);
			OutputStream descFileout = new FileOutputStream(filePath + desc);

			byte[] Nbuf = new byte[1024];
			byte[] Dbuf = new byte[1024];

			// Nfile
			int len;
			while ((len = vectorFilein.read(Nbuf)) > 0)
			{
				vectorFileout.write(Nbuf, 0, len);
			}
			vectorFilein.close();
			vectorFileout.close();

			// Dfile
			while ((len = descFilein.read(Dbuf)) > 0)
			{
				descFileout.write(Dbuf, 0, len);
			}
			descFilein.close();
			descFileout.close();

			InsertInputSql(reqInput);
		}
	}

	protected void InsertInputSql(ReqAddInput reqInput) throws ServletException
	{
		// Insert Input SQL
		ArrayList<String> reqInputs = new ArrayList<>();
		reqInputs.add(String.valueOf(reqInput.project_Id));
		reqInputs.add(reqInput.input_Name);
		reqInputs.add(reqInput.input_File_Path);
		reqInputs.add(reqInput.desc_File_Path);
		reqInputs.add(reqInput.input_Desc);

		String tbl = "ghsomservice.tblinput";
		String col = " (project_Id, input_Name, input_File_Path, desc_File_Path,input_Desc) ";
		String val = " (?,?,?,?,?) ";
		InsertSQL iSql = new InsertSQL();
		iSql.insertSql("INSERT INTO " + tbl + col + " VALUES " + val, reqInputs);

		iSql.closeSql();

	}

}
