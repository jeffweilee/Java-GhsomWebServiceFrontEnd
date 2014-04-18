package edu.nccu.soslab.Json;

import java.util.ArrayList;

public class Response
{
	public int exitValue;
	public int login;
	public String acc;
	public String pw;
	public static String key = "GhsomService@102";
	public ArrayList<String> account;
	public ArrayList<String> password;
	public ArrayList<String> responseMsgs;

	public Response()
	{
		responseMsgs = new ArrayList<>();		
	}

}
