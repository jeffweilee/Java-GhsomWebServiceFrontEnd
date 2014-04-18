package edu.nccu.soslab.Json;

import java.util.ArrayList;

public class ResGetProperty extends Response
{
	/* subclass */
	public class Property
	{
		public int property_Id;
		public Double tau_1;
		public Double tau_2;
		public int expand_Cycles;
		public Double init_Learnrate;
		public Double nr;
		public String html_Prefix;
		public String datafile_Extension;
		public int random_Seed;
		public String norm_Input_Vectors;
		public int init_X_Size;
		public int init_Y_Size;
		public int labels_Num;
		public Boolean labels_Only;
		public Double labels_Threshold;
		public String property_Name;
		public int attr_Filter;
		public String property_Desc;

		public Property(int property_Id, String property_Name, Double tau_1,
				Double tau_2, int expand_Cycles, Double init_Learnrate,
				Double nr, String html_Prefix, String datafile_Extension,
				int random_Seed, String norm_Input_Vectors, int init_X_Size,
				int init_Y_Size, int labels_Num, Boolean labels_Only,
				Double labels_Threshold, int attr_Filter, String property_Desc)
		{
			this.property_Id = property_Id;
			this.tau_1 = tau_1;
			this.tau_2 = tau_2;
			this.expand_Cycles = expand_Cycles;
			this.init_Learnrate = init_Learnrate;
			this.nr = nr;
			this.html_Prefix = html_Prefix;
			this.datafile_Extension = datafile_Extension;
			this.random_Seed = random_Seed;
			this.norm_Input_Vectors = norm_Input_Vectors;
			this.init_X_Size = init_X_Size;
			this.init_Y_Size = init_Y_Size;
			this.labels_Num = labels_Num;
			this.labels_Only = labels_Only;
			this.labels_Threshold = labels_Threshold;
			this.property_Name = property_Name;
			this.attr_Filter = attr_Filter;
			this.property_Desc = property_Desc;
		}
	}

	/* members */
	public ArrayList<Property> properties;

	public ResGetProperty()
	{
		properties = new ArrayList<>();
	}

	public void Add(int property_Id, String property_Name, Double tau_1,
			Double tau_2, int expand_Cycles, Double init_Learnrate, Double nr,
			String html_Prefix, String datafile_Extension, int random_Seed,
			String norm_Input_Vectors, int init_X_Size, int init_Y_Size,
			int labels_Num, Boolean labels_Only, Double labels_Threshold,
			int attr_Filter, String property_Desc)
	{
		Property p = new Property(property_Id, property_Name, tau_1, tau_2,
				expand_Cycles, init_Learnrate, nr, html_Prefix,
				datafile_Extension, random_Seed, norm_Input_Vectors,
				init_X_Size, init_Y_Size, labels_Num, labels_Only,
				labels_Threshold, attr_Filter, property_Desc);
		this.properties.add(p);
	}
}
