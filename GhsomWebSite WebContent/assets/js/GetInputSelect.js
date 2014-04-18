function GetInputSelect(id, p_id)
{
	if (p_id == 0)
	{
		var select = document.getElementById(id);

		/* create option head */
		var option = document.createElement("option");
		option.text = "Select Input";
		option.value = "#";
		option.hidden = "hidden";
		select.appendChild(option);
	}
	else
	{
		$.ajax({
			url : location.protocol + "//" + location.host + "/XGhsomServiceWeb/GetInput",
			type : "GET",
			contentType : "application/json",
			datatype : "json",
			data : "p_id=" + p_id,
			error : function(xhr)
			{
				alert(xhr.status + ":" + xhr.statusText);
			},
			success : function(response)
			{

				/*
				 * var colAry = { col : [ { name : "input_Id" }, { name :
				 * "input_Name" }, { name : "input_Desc" } ] };
				 */
				if (response.exitValue == 0)
				{
					location.replace('login.html');
				}
				else
				{

					var select = document.getElementById(id);
					var resAry = response.inputs;
					var arylength = resAry.length;// j

					/* create option head */
					var option = document.createElement("option");
					option.text = "Select Input";
					option.value = "#";
					option.hidden = "hidden";
					select.appendChild(option);

					/* create table content */
					for ( var i = 0; i < arylength; i++)
					{
						var option = document.createElement("option");
						option.text = resAry[i].input_Name + "  #DESC:" + resAry[i].input_Desc;
						option.value = resAry[i].input_Id;
						select.appendChild(option);

					}
				}
			}
		});
	}
}