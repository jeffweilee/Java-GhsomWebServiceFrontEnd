package edu.nccu.soslab.Json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogCheck
{
	public static Boolean logCheck(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if (session.getAttribute("login") == null || ((String) session.getAttribute("login")).trim().equals(""))
		{
			return false;
		} else
		{
			
			return true;
		}
	}
}