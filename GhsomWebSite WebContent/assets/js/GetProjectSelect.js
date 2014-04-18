function GetProjectSelect(id)
{
	$.ajax({
		url : location.protocol + "//" + location.host + "/XGhsomServiceWeb/GetProject",
		type : "POST",
		contentType : "application/json",
		datatype : "json",
		async : false,
		data : "",
		error : function(xhr)
		{
			alert(xhr.status + ":" + xhr.statusText); 
		},
		success : function(response)
		{

			/*
			 * var colAry = { col : [ { name : "project_Id" }, { name :
			 * "project_Name" }, { name : "desc" } ] };
			 */
			if (response.exitValue == 0)
			{
				location.replace('login.html');
			}
			else
			{
				var select = document.getElementById(id);
				var resAry = response.projects;
				var arylength = resAry.length;// j

				/* create option head */
				var option = document.createElement("option");
				option.text = "Select Project";
				option.value = "#";
				option.hidden = "hidden";
				select.appendChild(option);

				/* create table content */
				for ( var i = 0; i < arylength; i++)
				{
					var option = document.createElement("option");
					option.text = resAry[i].project_Name;
					option.value = resAry[i].project_Id;
					select.appendChild(option);

				}
			}
		}

	});
}