function GetTask()
{
	$.ajax({
		url : location.protocol + "//" + location.host + "/XGhsomServiceWeb/GetTask",
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
						name : "task_Id"
					}, {
						name : "session_Id"
					}, {
						name : "status_Code"
					}, {
						name : "worker_Id"
					} ]
				};

				var cols = colAry.col.length;// i
				var resAry = response.tasks;
				var arylength = resAry.length;// j
				/*
				 * if (arylength == 0) { location.replace('login.html'); }
				 */
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
						if (text == 0)
						{
							text = "0";
						}
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
function GetTaskByProject(id)
{
	if ($.isNumeric(id) && id >= 0)
	{
		$.ajax({
			url : location.protocol + "//" + location.host + "/XGhsomServiceWeb/GetTask",
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
						name : "task_Id"
					}, {
						name : "session_Id"
					}, {
						name : "status_Code"
					}, {
						name : "worker_Id"
					} ]
				};

				var cols = colAry.col.length;// i
				var resAry = response.tasks;
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
						if (text == 0)
						{
							text = "0";
						}
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