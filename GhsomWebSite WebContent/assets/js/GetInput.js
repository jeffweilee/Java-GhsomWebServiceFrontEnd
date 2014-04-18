function GetInput()
{
	$.ajax({
		url : location.protocol + "//" + location.host + "/XGhsomServiceWeb/GetInput",
		type : "POST",
		contentType : "application/json",
		datatype : "json",
		async : false,
		data : "",
		error : function(xhr)
		{
			alert(xhr.status + ":" + xhr.statusText);
			location.reload();
		},
		success : function(response)
		{
			if (response.exitValue == 0)
			{
				location.replace('login.html');
			}
			else
			{
				var colAry = {
					col : [ {
						name : "input_Id"
					}, {
						name : "project_Id"
					}, {
						name : "input_Name"
					}, {
						name : "input_File_Path"
					}, {
						name : "desc_File_Path"
					}, {
						name : "input_Desc"
					} ]
				};

				var body = document.getElementById("content");
				var tbl = document.createElement("table");
				var tblBody = document.createElement("tbody");

				var cols = colAry.col.length;// i
				var resAry = response.inputs;
				var arylength = resAry.length;// j

				/* create table head */
				var row = document.createElement("tr");
				row.setAttribute("class", "headfix");
				for ( var i = 0; i < cols; i++)
				{
					var cell = document.createElement("th");
					var cellText = document.createTextNode(colAry.col[i].name);
					cell.appendChild(cellText);
					row.appendChild(cell);
				}
				// row added to end of table body
				tblBody.appendChild(row);

				/* create table content */

				// cells creation
				for ( var j = 0; j < arylength; j++)
				{
					// table row creation
					var row = document.createElement("tr");

					for ( var i = 0; i < cols; i++)
					{
						// create element <td> and text node
						// Make text node the contents of <td> element
						// put <td> at end of the table row
						var cell = document.createElement("td");
						var col = colAry.col[i].name;
						var text = resAry[j][col];
						if (text == null || text == "undefined" || text == "")
						{
							text = "N/A";
						}
						var cellText = document.createTextNode(text);

						cell.appendChild(cellText);
						row.appendChild(cell);
					}

					// row added to end of table body
					tblBody.appendChild(row);
				}

				// append the <tbody> inside the <table>
				tbl.appendChild(tblBody);
				// put <table> in the <body>
				body.appendChild(tbl);
				// tbl set attribute to
				// tbl.setAttribute("class", "table");

			}
		}
	});
}

function GetInputByProject(id)
{
	if ($.isNumeric(id) && id >= 0)
	{
		$.ajax({
			url : location.protocol + "//" + location.host + "/XGhsomServiceWeb/GetInput",
			type : "GET",
			contentType : "application/json",
			datatype : "json",
			data : 'p_id=' + id,
			error : function(xhr)
			{
				alert(xhr.status + ":" + xhr.statusText);
			},
			success : function(response)
			{

				var colAry = {
					col : [ {
						name : "input_Id"
					}, {
						name : "project_Id"
					}, {
						name : "input_Name"
					}, {
						name : "input_File_Path"
					}, {
						name : "desc_File_Path"
					}, {
						name : "input_Desc"
					} ]
				};

				var cols = colAry.col.length;// i
				var resAry = response.inputs;
				var arylength = resAry.length;// j

				$('#content').empty();

				var body = document.getElementById("content");
				var tbl = document.createElement("table");
				var tblBody = document.createElement("tbody");

				/* create table head */
				var row = document.createElement("tr");
				for ( var i = 0; i < cols; i++)
				{
					var cell = document.createElement("th");
					var cellText = document.createTextNode(colAry.col[i].name);
					cell.appendChild(cellText);
					row.appendChild(cell);
				}
				// row added to end of table body
				tblBody.appendChild(row);

				/* create table content */

				// cells creation
				for ( var j = 0; j < arylength; j++)
				{
					// table row creation
					var row = document.createElement("tr");

					for ( var i = 0; i < cols; i++)
					{
						// create element <td> and text node
						// Make text node the contents of <td> element
						// put <td> at end of the table row
						var cell = document.createElement("td");
						var col = colAry.col[i].name;
						var text = resAry[j][col];
						if (text == null || text == "undefined" || text == "")
						{
							text = "N/A";
						}
						var cellText = document.createTextNode(text);

						cell.appendChild(cellText);
						row.appendChild(cell);
					}

					// row added to end of table body
					tblBody.appendChild(row);
				}

				// append the <tbody> inside the <table>
				tbl.appendChild(tblBody);
				// put <table> in the <body>
				body.appendChild(tbl);
				// tbl set attribute to
				// tbl.setAttribute("class", "table");

			}

		});
	}
}

function processUrlokBack()
{
	var parameters = location.search.substring(1).split("&");
	var temp0 = parameters[0].split("=");
	var temp1 = parameters[1].split("=");
	if (unescape(temp1[0]) == "ok" && unescape(temp1[1]) == "1")
	{
		if (unescape(temp0[0]) == "pn")
		{
			var pn = unescape(temp0[1]);
			viewLastAdd(pn);

		}
	}
}
function viewLastAdd(pn)
{
	$("select option").filter(function()
	{
		// may want to use $.trim in here
		return $(this).val() == pn;
	}).attr('selected', true);

	GetInputByProject($('#projectselect').val());
}