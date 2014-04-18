package edu.nccu.soslab.Json;

import java.util.ArrayList;

public class ResGetProject extends Response
{
	/* subclass */
	public class Project
	{
		public int project_Id;
		public String project_Name;
		public String desc;

		public Project(int id, String name, String dc)
		{
			project_Id = id;
			project_Name = name;
			desc = dc;
		}
	}

	/* members */
	public ArrayList<Project> projects;

	public ResGetProject()
	{
		projects = new ArrayList<>();
	}

	public void Add(int id, String name, String desc)
	{
		Project p = new Project(id, name, desc);
		this.projects.add(p);
	}
	
}
