function AddProject()
{
	var pn = $.trim($('#project_name').val());
	var pd = $.trim($('#project_desc').val());
	if (!$("div").hasClass("error") && pn != '')
	{

		var reqJson = {
			project_Name : pn,
			project_Desc : pd

		};
		VerifyName();
		if ($("#pn").val() == "0")
		{
			$("#pn").val('');
			alert("The name has been used!");
		}
		else
		{

			$.ajax({
				url : location.protocol + "//" + location.host + "/XGhsomServiceWeb/AddProject",
				type : "POST",
				contentType : "application/json",
				datatype : "json",
				data : JSON.stringify(reqJson),
				error : function(xhr)
				{
					alert(xhr.status + ":" + xhr.statusText);
					location.reload();
				},
				success : function(response)
				{
					// alert("Project Add Success!");
					$("#modal-body").html("<font color='green'>Project Add Success!!</font>");
					setTimeout(function()
					{
						location.reload();
					}, 2000);
				}

			});
		}
	}
	else
	{
		alert("Please fix all fields with error!");
	}

	return false;
}
