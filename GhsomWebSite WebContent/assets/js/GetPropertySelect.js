function GetPropertySelect(id)
{
	$.ajax({
		url : location.protocol + "//" + location.host + "/XGhsomServiceWeb/GetProperty",
		type : "POST",
		contentType : "application/json",
		datatype : "json",
		data : "",
		error : function(xhr)
		{
			 alert(xhr.status + ":" + xhr.statusText); 
		},
		success : function(response)
		{

			/*
			 * var colAry = { col : [ { name : "property_Id" }, { name :
			 * "property_Name" }, { name : "property_Desc" }, { name : "tau_1" }, {
			 * name : "tau_2" } ] };
			 */
			if (response.exitValue == 0)
			{
				location.replace('login.html');
			}
			else
			{
				var select = document.getElementById(id);
				var resAry = response.properties;
				var arylength = resAry.length;// j

				/* create option head */
				var option = document.createElement("option");
				option.text = "Select Property";
				option.value = "#";
				option.hidden = "hidden";
				select.appendChild(option);

				/* create table content */
				for ( var i = 0; i < arylength; i++)
				{
					var option = document.createElement("option");
					option.text = resAry[i].property_Name + " #TAU_1:" + resAry[i].tau_1 + " #TAU_2:" + resAry[i].tau_2 + " #DESC:" + resAry[i].property_Desc;
					option.value = resAry[i].property_Id;
					select.appendChild(option);

				}
			}
		}

	});
}