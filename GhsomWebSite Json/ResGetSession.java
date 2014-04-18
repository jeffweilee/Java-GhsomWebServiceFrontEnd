package edu.nccu.soslab.Json;

import java.util.ArrayList;

public class ResGetSession extends Response
{
	/* subclass */
	public class Session
	{
		public int session_Id;
		// public int project_Id; // not from tblSession
		public int property_Id;
		public int input_Id; // reference *_Id
		public String session_Name;
		public String session_Desc;

		public Session(int session_Id, int property_Id, int input_Id,
				String session_Name, String session_Desc)
		{
			this.session_Id = session_Id;
			this.property_Id = property_Id;
			this.input_Id = input_Id;
			this.session_Name = session_Name;
			this.session_Desc = session_Desc;
		}
	}

	/* members */
	public ArrayList<Session> sessions;

	public ResGetSession()
	{
		sessions = new ArrayList<>();
	}

	public void Add(int session_Id, int property_Id, int input_Id,
			String session_Name, String session_Desc)
	{
		Session s = new Session(session_Id, property_Id, input_Id,
				session_Name, session_Desc);
		this.sessions.add(s);
	}
}
