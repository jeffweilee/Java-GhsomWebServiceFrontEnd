package edu.nccu.soslab.Json;

import java.util.ArrayList;

public class ResGetInput extends Response
{
	/* subclass */
	public class Input
	{
		public int input_Id;
		public int project_Id;
		public String input_Name;
		public String input_File_Path;
		public String desc_File_Path;
		public String input_Desc;

		public Input(int input_Id, int project_Id, String input_Name,
				String input_File_Path, String desc_File_Path, String input_Desc)
		{
			this.input_Id = input_Id;
			this.project_Id = project_Id;
			this.input_Name = input_Name;
			this.input_File_Path = input_File_Path;
			this.desc_File_Path = desc_File_Path;
			this.input_Desc = input_Desc;
		}
	}

	/* members */
	public ArrayList<Input> inputs;

	public ResGetInput()
	{
		inputs = new ArrayList<>();
	}

	public void Add(int input_Id, int project_Id, String input_Name,
			String input_File_Path, String desc_File_Path, String input_Desc)
	{
		Input p = new Input(input_Id, project_Id, input_Name, input_File_Path,
				desc_File_Path, input_Desc);
		this.inputs.add(p);
	}
}
