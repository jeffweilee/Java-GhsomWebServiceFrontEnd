package edu.nccu.soslab.GhsomWebSite;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import edu.nccu.soslab.Json.LogCheck;
import edu.nccu.soslab.Json.Response;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogIn()
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

		if (LogCheck.logCheck(request))
		{
			HttpSession session = request.getSession();
			String pass = "1";
			String msg = pass + "," + session.getAttribute("login");
			response.setContentType("application/text"); // json
			PrintWriter resWriter = response.getWriter(); // PrintWriter
			resWriter.write(msg);
			resWriter.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		// Account Password
		try
		{
			Response reqLog = new Response();
			Gson gson = new Gson();

			// ReadInput
			reqLog = StringReader(request, reqLog, gson);
			// Verify

			if (reqLog.acc != null && reqLog.pw != null)
			{
				if (GetUserSql(reqLog.acc, reqLog.pw, Response.key))
				{
					WriteBack(request, response, reqLog, gson);
				}
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Response StringReader(HttpServletRequest request, Response reqLog,
			Gson gson) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		String s;
		while ((s = request.getReader().readLine()) != null)
		{
			sb.append(s);
		}
		reqLog = gson.fromJson(sb.toString(), Response.class);
		return reqLog;
	}

	protected Connection con = null;
	protected Statement stmt = null;
	protected ResultSet rs = null;

	protected Boolean GetUserSql(String acc, String pw, String key)
			throws Exception
	{
		String tbl = "ghsomservice.tbluser";
		// String col = "user_Name, AES_DECRYPT(user_Password," + Response.key
		// + ") AS pw";
		String col = "user_Name,user_Password";

		GetSQL gs = new GetSQL();
		Boolean flag = false;

		ResultSet rs = gs.getSql("SELECT " + col + " FROM " + tbl);

		while (rs.next())
		{
			if (acc.equals(rs.getString(1))
					&& Encrypt(pw).equals(rs.getString(2)))
			{
				flag = true;
				break;
			}
		}
		rs.close();
		rs = null;
		gs.closeSql();

		if (flag)
		{
			return true;
		}
		return false;

	}

	private void WriteBack(HttpServletRequest request,
			HttpServletResponse response, Response reqLog, Gson gson)
			throws IOException
	{
		request.getSession().setAttribute("login", reqLog.acc);
		System.out.println("login as " + reqLog.acc);

		reqLog.login = 1;
		response.setContentType("application/json"); // json
		PrintWriter resWriter = response.getWriter(); // PrintWriter
		resWriter.write(gson.toJson(reqLog));
		resWriter.close();
	}

	public static String Encrypt(String msg) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException
	{
		KeyGenerator keyG = KeyGenerator.getInstance("AES");
		// set key length
		keyG.init(128);
		byte[] key = Response.key.getBytes();// byte[] key = secuK.getEncoded();
		SecretKeySpec spec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		// encrypt
		cipher.init(Cipher.ENCRYPT_MODE, spec);
		// get encryptData
		byte[] encryptData = cipher.doFinal(msg.getBytes());
		//System.out.println("encryptData：" + new String(encryptData));
		return new String(encryptData);
	}

}
