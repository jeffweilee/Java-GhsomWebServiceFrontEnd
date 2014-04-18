package edu.nccu.soslab.Json;

import java.util.ArrayList;


public class ResGetTask extends Response
{
	/* subclass */
	public class Task
	{
		public int task_Id;
		public int session_Id; 
		public int status_Code;
		public int worker_Id;

		public Task(int task_Id,  int session_Id,
				int status_Code, int worker_Id)
		{
			this.task_Id = task_Id;
			//this.project_Id = project_Id; // not from tblTask
			//this.input_Id = input_Id; // not from tblTask
			this.session_Id = session_Id; // reference *_Id
			this.status_Code = status_Code;
			this.worker_Id = worker_Id;
		}
	}

	/* members */
	public ArrayList<Task> tasks;

	public ResGetTask()
	{
		tasks = new ArrayList<>();
	}

	public void Add(int task_Id,  int session_Id,
			int status_Code, int worker_Id)
	{
		Task t = new Task(task_Id, session_Id,
				status_Code, worker_Id);
		this.tasks.add(t);
	}
}
