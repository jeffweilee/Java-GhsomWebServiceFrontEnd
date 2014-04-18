function AddSession()
{
	var sn = $('#sessionname').val();
	if (!$("div").hasClass("error") && sn != '')
	{

		var reqJson = {
			session_Name : $.trim($('#sessionname').val()),
			session_Desc : $.trim($('#sessiondesc').val()),
			property_Id : $.trim($('#selectproperty').val()),
			project_Id : $.trim($('#selectproject').val()),
			input_Id : $.trim($('#selectinput').val())

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
				url : location.protocol + "//" + location.host + "/XGhsomServiceWeb/AddSession",
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
					// alert("Session Add Success!");
					$("#modal-body").html("<font color='green'>Session Add Success!!</font>");
					var pn = response.project_Id;
					location.replace("session.html?pn=" + pn + "&ok=1");

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
